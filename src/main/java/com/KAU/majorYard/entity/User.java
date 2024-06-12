package com.KAU.majorYard.entity;

import com.KAU.majorYard.entity.majorYard_enum.Gender;
import com.KAU.majorYard.entity.majorYard_enum.Grade;
import com.KAU.majorYard.entity.majorYard_enum.Role;
import com.KAU.majorYard.entity.majorYard_enum.UserAvailable;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Table(name = "User")
@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long id; // user_no


    @Column(name = "user_name")
    private String userName;
    private String nickName;

    @Column(name = "user_login_id")
    private String loginId;

    @Column(name = "user_pwd")
    private String password;


    @Column(name = "user_phone")
    private String userPhone;


    private String schoolEmail;

    @Column(name = "user_birth")
    private String userBirth;

    @Column(columnDefinition = "TEXT")
    private String userProfImg;

    @Enumerated(value = EnumType.STRING)
    private Gender gender; // male or female

    @Enumerated(value = EnumType.STRING)
    private Role role; // user or admin

    @Enumerated(value = EnumType.STRING)
    private Grade grade; //one two three four

    @Enumerated(value = EnumType.STRING)
    private UserAvailable userAvailable; // yes, no

//    private String follower;
//
//    private String follow;


    // User:Scarb = 1:N
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Scrab> userScrabs = new ArrayList<>();

    // User:Chat = 1:N
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessage> userChats = new ArrayList<>();

    // User:Like = 1:N

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> userLikes = new ArrayList<>();

    // User:Comment = 1:N

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> userComments = new ArrayList<>();


    //  User:Department = N:1
    @ManyToOne
    @JoinColumn(name = "department_no")
    private Department department;

    // User:Post = 1:N
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> userPosts = new ArrayList<>();

    public void addUserAuthority() {
        this.role = Role.ROLE_USER;
    }


//    public void addUserTofollowing(Follow follow) {
//    }
//
//    public void addUserrTofollower(Follow follow) {
//    }
//
//    public void removeUserTofollower(Follow follow) {
//    }
//
//    public void removeUserTofollowing(Follow follow) {
//    }
//
//    public Collection<Object> getFollowers() {
//    }




}
