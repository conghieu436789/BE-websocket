package com.socket.demo.service;

import com.socket.demo.model.ChatMessage;
import com.socket.demo.repository.IMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Autowired
    private IMessageRepository messageRepository;

    public ChatMessage saveMess(ChatMessage chatMessage) {
        return messageRepository.save(chatMessage);
    }
}
