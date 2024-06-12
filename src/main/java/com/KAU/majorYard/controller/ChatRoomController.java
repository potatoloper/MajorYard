package com.KAU.majorYard.controller;


import com.KAU.majorYard.dto.request.ChatMessageRequestDto;
import com.KAU.majorYard.dto.request.ChatRoomRequestDto;
import com.KAU.majorYard.dto.response.ChatMessageResponseDto;
import com.KAU.majorYard.dto.response.ChatRoomResponseDto;
import com.KAU.majorYard.entity.ChatMessage;
import com.KAU.majorYard.entity.ChatRoom;
import com.KAU.majorYard.service.ChatMessageService;
import com.KAU.majorYard.service.ChatRoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatRoomController {


    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;


    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<List<ChatRoomResponseDto>> chatRoomList() {
        List<ChatRoom> chatRooms = chatRoomService.findAll();
        List<ChatRoomResponseDto> chatRoomResponseDtos = chatRooms.stream()
                .map(room -> new ChatRoomResponseDto(room.getId(), room.getRoomName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(chatRoomResponseDtos);
    }


    @PostMapping("/create")
    public ResponseEntity<ChatRoomResponseDto> createChatRoom(@RequestBody ChatRoomRequestDto requestDto) {
        System.out.println("Received roomName: " + requestDto.getRoomName()); // 로깅 추가
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomName(requestDto.getRoomName());
        chatRoom = chatRoomService.save(chatRoom);
        ChatRoomResponseDto responseDto = new ChatRoomResponseDto(chatRoom.getId(), chatRoom.getRoomName());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<ChatRoomResponseDto> roomPage(@PathVariable Long roomId) {
        ChatRoom chatRoom = chatRoomService.findById(roomId);
        if (chatRoom == null) {
            return ResponseEntity.notFound().build();
        }
        ChatRoomResponseDto responseDto = new ChatRoomResponseDto(roomId, chatRoom.getRoomName());
        return ResponseEntity.ok(responseDto);
    }

    @MessageMapping("/{roomId}")//client to server
    @SendTo("/sub/chat/{roomId}")//server to client
    public ChatMessage processChat(@RequestBody ChatMessage chat) {
        chatMessageService.save(chat);
        return chat;
    }



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


    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<ChatMessageResponseDto>> getMessages(@PathVariable Long roomId) {
        List<ChatMessageResponseDto> messages = chatMessageService.getMessages(roomId);
        if (messages.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(messages);
    }


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

}
