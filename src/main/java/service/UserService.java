package service;

import entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long join(User user){
        validateDuplicateUserID(user);
        validateDuplicateUserPhoneNum(user);
        userRepository.save(user);
        return user.getId();
    }

    // 아이디로 중복 확인
    private void validateDuplicateUserID(User user){
        List<User> findUsers = userRepository.findByLoginId(user.getLogin_id());
        if(!findUsers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    // 핸드폰 번호로 중복 확인
    private void validateDuplicateUserPhoneNum(User user){
        List<User> findUsers = userRepository.findByPhone(user.getUser_phone());
        if(!findUsers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    //회원 전체 조회
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    //PK로 회원 조회
    public User findOne(Long id){
        return userRepository.findOne(id);
    }
}
