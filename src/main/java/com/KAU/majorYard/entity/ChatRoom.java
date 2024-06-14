package com.KAU.majorYard.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Table(name = "ChatRoom")
@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_no")
    private Long id;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "roomName")
    private String roomName;


    // ChatRoom:ChatMessage = 1:N
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessage> chatMessages = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;


}
