package com.socket.demo.controller;

import com.socket.demo.model.ChatMessage;
import com.socket.demo.model.Product;
import com.socket.demo.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/message")
public class ChatMessageController {
    @Autowired
    private ChatMessageService chatMessageService;

    @GetMapping("/{chat_room_id}")
    public List<ChatMessage> getChatMessages(@PathVariable long chat_room_id) {
        try {
            return chatMessageService.getChatMessageByChatRoom(chat_room_id);
        } catch (Exception e) {
            return null;
        }
    }
}
