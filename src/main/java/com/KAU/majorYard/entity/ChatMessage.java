package com.KAU.majorYard.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Table(name = "ChatMessage")
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chat_message_no")
    private Long id;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "sender")
    private String senderName;

    @Column(name = "chatMessage")
    private String text;

    @Column(name = "createTime")
    @CreationTimestamp
    private Timestamp createdTime;

    // ChatMessage : User = N:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    // ChatMessage : ChatRoom = N:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_no")
    private ChatRoom chatRoom;



}
