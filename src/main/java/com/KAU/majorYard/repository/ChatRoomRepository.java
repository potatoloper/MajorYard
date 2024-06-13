package com.KAU.majorYard.repository;

import com.KAU.majorYard.entity.ChatRoom;
import com.KAU.majorYard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findAllByUser(User user);
    Optional<ChatRoom> findByIdAndUser(Long id, User user);
}
