package com.socket.demo.repository;

import com.socket.demo.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    @Query(value = "select * " +
            "from demo_jwt_websocket.notification " +
            "where user_receiver_id =?1", nativeQuery = true)
    List<Notification> findAllByUser_receiver_id(long id);
}
