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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<ChatRoomResponseDto>> chatRoomList(HttpServletRequest request) {
        User user = userService.getUserFromRequest(request);
        List<ChatRoom> chatRooms = chatRoomService.findAllByUser(user);
        List<ChatRoomResponseDto> chatRoomResponseDtos = chatRooms.stream()
                .map(room -> ChatRoomResponseDto.builder()
                        .id(room.getId())
                        .roomName(room.getRoomName())
                        .userId(room.getUser().getId())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(chatRoomResponseDtos);
    }

    @PostMapping("/create")
    public ResponseEntity<ChatRoomResponseDto> createChatRoom(@RequestBody ChatRoomRequestDto requestDto, HttpServletRequest request) {
        User user = userService.getUserFromRequest(request);
        ChatRoom chatRoom = chatRoomService.createChatRoom(requestDto, user);
        ChatRoomResponseDto responseDto = ChatRoomResponseDto.builder()
                .id(chatRoom.getId())
                .roomName(chatRoom.getRoomName())
                .userId(chatRoom.getUser().getId())
                .build();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<ChatRoomResponseDto> roomPage(@PathVariable Long roomId, HttpServletRequest request) {
        User user = userService.getUserFromRequest(request);
        ChatRoom chatRoom = chatRoomService.findByIdAndUser(roomId, user);
        if (chatRoom == null) {
            return ResponseEntity.notFound().build();
        }
        ChatRoomResponseDto responseDto = ChatRoomResponseDto.builder()
                .id(roomId)
                .roomName(chatRoom.getRoomName())
                .userId(chatRoom.getUser().getId())
                .build();
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{chatRoomId}/messages")
    public ResponseEntity<List<ChatMessageResponseDto>> getMessagesByChatRoom(@PathVariable Long chatRoomId) {
        List<ChatMessageResponseDto> messages = chatRoomService.getMessagesByChatRoom(chatRoomId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/{chatRoomId}/messages")
    public ResponseEntity<ChatMessageResponseDto> sendMessage(@PathVariable Long chatRoomId,
                                                              @RequestBody ChatMessageRequestDto requestDto) {
        ChatMessageResponseDto message = chatMessageService.sendMessage(chatRoomId, requestDto);
        return ResponseEntity.ok(message);
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



