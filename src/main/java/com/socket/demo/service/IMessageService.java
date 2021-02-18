package com.socket.demo.service;

import com.socket.demo.model.ChatMessage;

import java.util.List;

public interface IMessageService extends IGeneralService<ChatMessage>{
    List<ChatMessage> getChatMessageByChatRoom(Long chatRoomId);
}
