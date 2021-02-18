package com.socket.demo.service;

import com.socket.demo.model.ChatRoom;
import com.socket.demo.repository.IChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService implements IChatRoomService {
    @Autowired
    private IChatRoomRepository chatRoomRepository;
    @Override
    public Iterable<ChatRoom> findAll() {
        return chatRoomRepository.findAll();
    }

    @Override
    public Optional<ChatRoom> findById(Long id) {
        return chatRoomRepository.findById(id);
    }

    @Override
    public ChatRoom save(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    @Override
    public void remove(Long id) {
        chatRoomRepository.deleteById(id);
    }

    @Override
    public Optional<ChatRoom> getChatRoomByIds(Long firstUserId, Long secondUserId) {
        return chatRoomRepository.getChatRoomByIds(firstUserId, secondUserId);
    }
}
