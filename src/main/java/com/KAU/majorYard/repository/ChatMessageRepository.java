package com.KAU.majorYard.repository;

import com.KAU.majorYard.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoomIdAndUserId(Long chatRoomId, Long userId);
}
