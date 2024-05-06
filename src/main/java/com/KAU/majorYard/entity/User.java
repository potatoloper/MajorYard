package entity;

import entity.majorYard_enum.Gender;
import entity.majorYard_enum.Grade;
import entity.majorYard_enum.Role;
import entity.majorYard_enum.UserAvailable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Column(name="user_no")
    private Long id; // user_no

    private String user_name;
    private String nick_name;
    private String login_id;
    private String password;

    private String user_phone;
    private String school_email;
    private String user_birth;


    @Column(columnDefinition = "TEXT")
    private String user_prof_img;
    private LocalDateTime created_dt;
    private LocalDateTime modified_dt;

    @Enumerated(value = EnumType.STRING)
    private Gender gender; // male or female
    @Enumerated(value = EnumType.STRING)
    private Role role; // user or admin

    @Enumerated(value = EnumType.STRING)
    private Grade grade; //one two three four

    @Enumerated(value = EnumType.STRING)
    private UserAvailable userAvailable; // yes, no



    // User:Scarb = 1:N
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Scrab> userScrabs = new ArrayList<>();

    // User:Chat = 1:N
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Chat> userChats = new ArrayList<>();

    // User:Like = 1:N
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Like> userLikes = new ArrayList<>();

    // User:Comment = 1:N
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> userComments = new ArrayList<>();

    // 주테이블(User) 외래키 단방향
    // User:Department = 1:1
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "department_no")
    private Department department;


    // User:Post = 1:N
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Post> userPosts = new ArrayList<>();

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }
    public void addUserAuthority() {
        this.role = Role.USER;
    }


}
