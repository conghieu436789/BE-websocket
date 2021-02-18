package com.socket.demo.repository;

import com.socket.demo.model.ChatMessage;
import com.socket.demo.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface IMessageRepository extends JpaRepository<ChatMessage, Long> {
    @Query(value = "select * " +
            "from demo_jwt_websocket.chat_message " +
            "where chat_room_id =?1", nativeQuery = true)
    List<ChatMessage> getChatMessageByChatRoom(Long chatRoomId);

}
