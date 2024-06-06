package com.KAU.majorYard.service;

import com.KAU.majorYard.dto.request.PostPagingRequestDto;
import com.KAU.majorYard.dto.request.PostSaveRequestDto;
import com.KAU.majorYard.dto.request.PostUpdateRequestDto;
import com.KAU.majorYard.dto.response.PostPagingResponseDto;
import com.KAU.majorYard.dto.response.PostReadResponseDto;
import com.KAU.majorYard.entity.Board;
import com.KAU.majorYard.entity.Img;
import com.KAU.majorYard.entity.Post;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.entity.majorYard_enum.BoardName;
import com.KAU.majorYard.entity.majorYard_enum.PostType;
import com.KAU.majorYard.repository.BoardRepository;
import com.KAU.majorYard.repository.ImgRepository;
import com.KAU.majorYard.repository.PostRepository;
import com.KAU.majorYard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl{

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ImgRepository imgRepository;
    private final S3Service s3Service;


    @Transactional
    public void savePost(PostSaveRequestDto postDto, List<MultipartFile> multipartFiles) throws IOException {
        User user = userRepository.findById(postDto.getUserNo()).orElseThrow( () -> new RuntimeException("유저 PK가 확인되지 않습니다.") );
        Board board = boardRepository.findById(postDto.getBoardNo()).orElseThrow( () -> new RuntimeException("게시판이 확인되지 않습니다.") );

        Post post = postRepository.save(postDto.toEntity(user, board));

        if(multipartFiles != null){
            for (MultipartFile multipartFile : multipartFiles){
                s3Service.saveImage(multipartFile, post);
            }
        }
    }


    // 게시판 유형에 따라 적절한 Post 객체를 생성
    private Post createPostByBoardType(PostSaveRequestDto postDto, User user, Board board) {
        Post.PostBuilder postBuilder = Post.builder() // 공통 필드
                .postTitle(postDto.getPostTitle())
                .postContent(postDto.getPostContent())
                .user(user)
                .board(board);


        // 게시판 유형에 따라 추가 필드를 설정
        switch (board.getBoardName()) {
            case PromotionBoard:
                postBuilder.postType(postDto.getPostType());
                break;
            case QuestionBoard:
                postBuilder.answered(postDto.getAnswered());
                break;
            case StudyBoard:
                postBuilder.studyRegion(postDto.getStudyRegion())
                        .studyPartyOf(postDto.getStudyPartyOf())
                        .studyFee(postDto.getStudyFee())
                        .studyUntil(postDto.getStudyUntil());
                break;
            default:
                // FreeBoard 및 IssueBoard의 경우 추가 필드 없음
                break;
        }
        return postBuilder.build();
    }

    //Sort 이용한 페이징 전략
    @Transactional(readOnly = true)
    public Page<PostPagingResponseDto> findAllPosts(PostPagingRequestDto postPagingRequestDto) {

        Sort sort = Sort.by(Sort.Direction.fromString(postPagingRequestDto.getSort()), "id");
        Pageable pageable = PageRequest.of(postPagingRequestDto.getPage()-1, postPagingRequestDto.getSize(), sort);

        Page<Post> postPages = postRepository.findAll(pageable);

        Page<PostPagingResponseDto> postDTOPages = postPages.map(postPage -> new PostPagingResponseDto(postPage,s3Service.getFullPath(postPage.getPostImgs())));
        return postDTOPages;

    }

    // 게시글 상세페이지 조회
    @Transactional(readOnly = true)
    public PostReadResponseDto findPostById(Long id){
        Post post = postRepository.findById(id).orElseThrow( () -> new RuntimeException("게시글이 확인되지 않습니다.") );
        List<Img> imgs = post.getPostImgs();
        List<String> imgUrls = new ArrayList<>();
        if (imgs != null){
            for (Img img : imgs){
                imgUrls.add(s3Service.getFullPath(img.getStoredFileName()));
            }
        }
        return new PostReadResponseDto(post, imgUrls);
    }

    // 게시글 업데이트 (제목, 내용만)
    @Transactional
    public void updatePosts(Long id, PostUpdateRequestDto postDto){
        Post post = postRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("게시글이 확인되지 않습니다.") );
        post.update(postDto.getPostTitle(), postDto.getPostContent());
    }


    // 게시글 업데이트 TODO : 이전 이미지가 이미지 테이블에서 삭제되지 않는 에러 고치기
//    @Transactional
//    public void updatePosts(Long id, PostUpdateRequestDto postDto, List<MultipartFile> multipartFiles) throws IOException {
//        Post post = postRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("게시글이 확인되지 않습니다.") );
//        List<Img> imgs = post.getPostImgs();
//        System.out.println("imgs:  "+imgs);
//
//        if(multipartFiles != null){
//            if(imgs != null) {
//                for (Img img : imgs) {
//                    s3Service.deleteImage(img.getId());
//                }
//                post.setPostImagesNull();
//            }
//            for (MultipartFile multipartFile : multipartFiles){
//                s3Service.saveImage(multipartFile, post);
//            }
//        }
//
//        post.update(postDto.getPostTitle(), postDto.getPostContent());
//    }



    // 홍보게시글 답변여부 YES로 업데이트
    @Transactional
    public void pmtUpdateAnsweredPost(Long id){
        Post post = postRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("게시글이 확인되지 않습니다.") );
        post.updateAnswered();
    }

    // 게시글 삭제
    @Transactional
    public void deletePosts(Long id){
        Post post = postRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("게시글이 확인되지 않습니다.") );

        List<Img> imgs = post.getPostImgs();
        for (Img img : imgs){
            if (post.getPostImgs() != null) {
                s3Service.deleteImage(img.getId());
            }
        }

        postRepository.deleteById(id);
    }

}
