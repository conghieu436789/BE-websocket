package com.socket.demo.service;

import com.socket.demo.model.ChatRoom;

import java.util.Optional;

public interface IChatRoomService extends IGeneralService<ChatRoom> {
    Optional<ChatRoom> getChatRoomByIds(Long firstUserId, Long secondUserId);
}
