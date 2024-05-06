package repository;

import entity.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    //회원 저장 (회원가입)
    public void save(User user){
        em.persist(user);
    }

    //PK로 회원 조회
    public User findOne(Long id){
        return em.find(User.class, id);
    }

    //전체 회원 조회
    public List<User> findAll(){
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    //로그인 아이디로 회원 조회
    public List<User> findByLoginId(String login_id){
        return em.createQuery("select u from User u where u.login_id = :login_id", User.class)
                .setParameter("login_id",login_id)
                .getResultList();
    }

    //휴대폰 번호로 회원 조회
    public List<User> findByPhone(String phoneNum){
        return em.createQuery("select u from User u where u.user_phone = :user_phone", User.class)
                .setParameter("user_phone", phoneNum)
                .getResultList();
    }

    //이름으로 회원 조회
    public List<User> findByName(String name){
        return em.createQuery("select u from User u where u.user_name = :user_name", User.class)
                .setParameter("user_name",name)
                .getResultList();
    }

}
