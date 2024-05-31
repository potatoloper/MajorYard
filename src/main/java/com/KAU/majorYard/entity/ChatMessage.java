package com.KAU.majorYard.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Table(name = "ChatMessage")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chat_message_no")
    private Long id;

    @Column(name = "sender")
    private String senderName;

    @Column(name = "chatMessage")
    private String text;

    // ChatMessage : User = N:1
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    private Timestamp createdTime;


    // ChatMessage : ChatRoom = N:1
    @ManyToOne
    @JoinColumn(name = "chat_room_no")
    private ChatRoom chatRoom;


//    @Builder
//    public ChatMessage(String sender, String message, ChatRoom chatRoom) {
//        this.sender = sender;
//        this.chatMessage = message;
//        this.chatRoom = chatRoom;
//    }
}
