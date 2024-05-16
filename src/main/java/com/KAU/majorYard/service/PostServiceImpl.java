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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ImgRepository imgRepository;

    @Transactional
    @Override
    public void savePost(PostSaveRequestDto postDto) {

        try {
            User user = userRepository.findById(postDto.getUser_no()).orElseThrow();
            Board board = boardRepository.findById(postDto.getBoard_no()).orElseThrow();
            List<Img> imgs = postDto.getPostImgs();
            Post post = postRepository.save(postDto.toEntity(user,board, imgs));

            if(imgs != null){
                for (Img img : imgs){
                    img.changePost(post);
                }
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("해당되는 유저 혹은 게시판이 존재하지 않습니다.");
        }

    }

    //Sort 이용한 페이징 전략
    @Transactional(readOnly = true)
    public Page<PostPagingResponseDto> findAllPosts(PostPagingRequestDto postPagingRequestDto) {

        Sort sort = Sort.by(Sort.Direction.fromString(postPagingRequestDto.getSort()), "id");
        Pageable pageable = PageRequest.of(postPagingRequestDto.getPage()-1, postPagingRequestDto.getSize(), sort);

        Page<Post> postPages = postRepository.findAll(pageable);

        Page<PostPagingResponseDto> postDTOPages = postPages.map(postPage -> new PostPagingResponseDto(postPage));
        return postDTOPages;

    }

    @Transactional(readOnly = true)
    public PostReadResponseDto findPostById(Long id){
        Post post = postRepository.findById(id).orElseThrow();
        return new PostReadResponseDto(post);
    }

    @Transactional
    public void updatePosts(Long id, PostUpdateRequestDto postDto){
        Post post = postRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.") );
        List<Img> imgs = postDto.getPostImgs();
        post.update(postDto.getPostTitle(), postDto.getPostContent());
        if(imgs != null){
            imgRepository.saveAll(imgs);
            for (Img img : imgs){
                img.changePost(post);
            }
        }
    }

    @Transactional
    public void deletePosts(Long id){
        postRepository.deleteById(id);
    }

//    Sort 이용한 페이징 전략 2
//    public Page<PostResponseDto> findAllPosts(PostPagingDto postPagingDto){
//        Page<Post> postsPages = postRepository.findAll(PageRequest.of(postPagingDto.getPage(), postPagingDto.getSize(), Sort.by(Sort.Direction.DESC, "id")));
//
//        // 목록 : id, title, content, author
//        Page<PostResponseDto> postsResponseDtos = postsPages.map(
//                postPage -> new PostResponseDto(postPage));
//
//        return postsResponseDtos;
//    }

}