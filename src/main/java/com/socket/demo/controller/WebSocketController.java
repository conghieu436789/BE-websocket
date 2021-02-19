package com.socket.demo.controller;

import com.socket.demo.model.ChatMessage;
import com.socket.demo.model.ChatRoom;
import com.socket.demo.model.Notification;
import com.socket.demo.model.User;
import com.socket.demo.service.IChatRoomService;
import com.socket.demo.service.IMessageService;
import com.socket.demo.service.INotificationService;
import com.socket.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*")
@RestController
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    IUserService userService;

    @Autowired
    IMessageService messageService;
    @Autowired
    IChatRoomService chatRoomService;

    @Autowired
    INotificationService notificationService;

    @MessageMapping("/send/message/{chatRoomId}")
    public ChatMessage sendMessageTo(@Payload ChatMessage chatMessage, @DestinationVariable("chatRoomId") Long chatRoomId){
        System.out.println(chatMessage);
        ChatRoom chatRoom;
        if (chatRoomService.findById(chatRoomId).isPresent()) {
            chatRoom = chatRoomService.findById(chatRoomId).get();
            this.template.convertAndSend(chatRoom.getName(), chatMessage);
            messageService.save(chatMessage);
        }
        return chatMessage;
    }

    //notification
    @MessageMapping("/notification")
    public Notification sendNotification(@Payload Notification notification){
        System.out.println(notification);
        User sender = userService.findById(notification.getUser_sender_id()).get();
        User receiver = userService.findById(notification.getUser_receiver_id()).get();
        notification.setStatus(false);
        notification.setUserSender(sender);
        notification.setUserReceiver(receiver);
        notificationService.save(notification);
        this.template.convertAndSend("/notification", notification);
        return notification;
    }

}
