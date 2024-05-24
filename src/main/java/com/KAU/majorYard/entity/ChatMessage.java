package com.KAU.majorYard.entity;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "ChatMessage")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chat_message_no")
    private Long id;

    private String sender;

    private String chatContents;

    // ChatMessage : User = N:1
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;


    // ChatMessage : ChatRoom = N:1
    @ManyToOne
    @JoinColumn(name = "chat_room_no")
    private ChatRoom chatRoom;
}
