package com.KAU.majorYard.controller;


import com.KAU.majorYard.entity.ChatMessage;
import com.KAU.majorYard.entity.ChatRoom;
import com.KAU.majorYard.service.ChatMessageService;
import com.KAU.majorYard.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    @GetMapping("")
    public String mainPage(Model model) {
        model.addAttribute("chatRooms", chatRoomService.findAll());
        return "main";
    }

    @GetMapping("/chat/create")
    public String createChatRoomForm(Model model) {
        model.addAttribute("chatRoom", new ChatRoom());
        return "create-chat-room";
    }

    @PostMapping("/chat/create")
    public String createChatRoom(@ModelAttribute ChatRoom chatRoom) {
        Long roomId = chatRoomService.save(chatRoom);
        return "redirect:/chat/" + roomId;
    }

    @GetMapping("/chat/{roomId}")
    public String roomPage(@PathVariable Long roomId, Model model) {
        ChatRoom chatRoom = chatRoomService.findById(roomId);
        model.addAttribute("roomId", roomId);
        model.addAttribute("roomName", chatRoom.getRoomName());
        return "chat-room";
    }

    @MessageMapping("/chat/{roomId}")//client to server
    @SendTo("/sub/chat/{roomId}")//server to client
    public ChatMessage processChat(@RequestBody ChatMessage chat) {
        chatMessageService.save(chat);
        return chat;
    }

}
