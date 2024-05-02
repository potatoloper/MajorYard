package domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;


@Table(name = "User")
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long dpartment_no;
    private String gender;
    private String user_login_id;
    private String password;
    private String user_phone;
    private String user_email;
    private String user_birth;


    @Column(columnDefinition = "TEXT")
    private String user_prof_img;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String created_dt;
    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String mdf_time;


    @Enumerated(value = EnumType.STRING)
    private String role; // 유저 or 관리자


}
