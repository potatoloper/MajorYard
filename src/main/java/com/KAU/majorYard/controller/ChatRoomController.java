package com.KAU.majorYard.controller;

import com.KAU.majorYard.dto.request.ChatMessageRequestDto;
import com.KAU.majorYard.dto.request.ChatRoomRequestDto;
import com.KAU.majorYard.dto.response.ChatMessageResponseDto;
import com.KAU.majorYard.dto.response.ChatRoomResponseDto;
import com.KAU.majorYard.entity.ChatRoom;
import com.KAU.majorYard.entity.User;
import com.KAU.majorYard.service.ChatMessageService;
import com.KAU.majorYard.service.ChatRoomService;
import com.KAU.majorYard.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/chat")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final UserService userService;
    private final SimpMessagingTemplate messagingTemplate;


    @GetMapping("/{userId}/list")
    public ResponseEntity<List<ChatRoomResponseDto>> chatRoomListByUser(@PathVariable Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        List<ChatRoom> chatRooms = chatRoomService.findAllByUserIdOrSenderId(user.getId(),user.getId());
        List<ChatRoomResponseDto> chatRoomResponseDtos = chatRooms.stream()
                .map(room -> ChatRoomResponseDto.builder()
                        .id(room.getId())
                        .roomName(room.getRoomName())
                        .userId(room.getUser().getId())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(chatRoomResponseDtos);
    }

    @PostMapping("/{userId}/create")
    public ResponseEntity<ChatRoomResponseDto> createChatRoom(@PathVariable Long userId, @RequestBody ChatRoomRequestDto requestDto) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        ChatRoom chatRoom = chatRoomService.createChatRoom(requestDto, user);
        ChatRoomResponseDto responseDto = ChatRoomResponseDto.builder()
                .id(chatRoom.getId())
                .roomName(chatRoom.getRoomName())
                .userId(chatRoom.getUser().getId())
                .build();
        return ResponseEntity.ok(responseDto);
    }




    @GetMapping("/{userId}/{roomId}")
    public ResponseEntity<ChatRoomResponseDto> roomPage(@PathVariable Long userId, @PathVariable Long roomId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        ChatRoom chatRoom = chatRoomService.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));
        ChatRoomResponseDto responseDto = ChatRoomResponseDto.builder()
                .id(roomId)
                .roomName(chatRoom.getRoomName())
                .userId(chatRoom.getUser().getId())
                .build();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("{userId}/{chatRoomId}/messages")
    public ResponseEntity<List<ChatMessageResponseDto>> getMessagesByChatRoom(@PathVariable Long chatRoomId, @PathVariable Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        List<ChatMessageResponseDto> messages = chatRoomService.getMessagesByChatRoom(chatRoomId);
        return ResponseEntity.ok(messages);
    }

    @MessageMapping("/messages")
    public ResponseEntity<Void> sendMessage(@RequestBody ChatMessageRequestDto requestDto) {
        messagingTemplate.convertAndSend("/sub/chatroom/"+requestDto.getRoomId(), requestDto);
        ChatMessageResponseDto message = chatMessageService.sendMessage(requestDto.getRoomId(), requestDto);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{roomId}")
    public ResponseEntity<String> deleteChatRoom(@PathVariable Long roomId, HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        chatRoomService.deleteChatRoom(roomId, userId);
        return ResponseEntity.ok("Chat room deleted successfully.");
    }

}

/*****************************************************************************************************************************************************/

//    @PostMapping("/{roomId}/messages")
//    public ResponseEntity<?> saveChatMessage(@PathVariable Long roomId, @RequestBody ChatMessageRequestDto requestDto) {
//        try {
//            ChatMessage savedMessage = chatMessageService.createAndSaveMessage(roomId, requestDto);
//            return ResponseEntity.ok(new ChatMessageResponseDto("Message saved successfully."));
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ChatMessageResponse("Error saving the message."));
//        }
//    }


    //    @GetMapping("/chat/create")
//    public String createChatRoomForm(Model model) {
//        model.addAttribute("chatRoom", new ChatRoom());
//        return "create-chat-room";
//    }



//    @PostMapping("/chat/create")
//    public String createChatRoom(@ModelAttribute ChatRoom chatRoom) {
//        Long roomId = chatRoomService.save(chatRoom);
//        return "redirect:/chat/" + roomId;
//    }

//    @GetMapping("/chat/{roomId}")
//    public String roomPage(@PathVariable Long roomId, Model model) {
//        ChatRoom chatRoom = chatRoomService.findById(roomId);
//        model.addAttribute("roomId", roomId);
//        model.addAttribute("roomName", chatRoom.getRoomName());
//        return "chat-room";
//    }
/*****************************************************************************************************************************************************/



