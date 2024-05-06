package com.KAU.majorYard.entity;


import com.KAU.majorYard.entity.majorYard_enum.BoardName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Board")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_no")
    private Long id; // board_no

    @Enumerated(value = EnumType.STRING)
    private BoardName boardName;

    //  Board:Post = 1:N
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Post> boardPosts = new ArrayList<>();


    //  Board:Department = N:1
    @ManyToOne
    @JoinColumn(name = "department_no")
    private Department department;

}
