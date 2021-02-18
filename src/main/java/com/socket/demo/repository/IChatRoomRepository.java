package com.socket.demo.repository;

import com.socket.demo.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query(value = "select * " +
            "from demo_jwt_websocket.chat_room " +
            "where (first_user_id = ?1 and second_user_id = ?2) " +
            "or (first_user_id = ?2 and second_user_id = ?1)" , nativeQuery = true)
    Optional<ChatRoom> getChatRoomByIds(Long firstUserId, Long secondUserId);
}
