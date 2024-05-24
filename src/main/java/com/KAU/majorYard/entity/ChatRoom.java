package com.KAU.majorYard.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Table(name = "ChatRoom")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {
    @Id
    private Long id;

    private String roomName;

    // ChatRoom:ChatMessage = 1:N
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ChatMessage> chatMessages = new ArrayList<>();
}
