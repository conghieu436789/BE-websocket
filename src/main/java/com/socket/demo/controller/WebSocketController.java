package com.socket.demo.controller;

import com.socket.demo.model.ChatMessage;
import com.socket.demo.model.User;
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


    @MessageMapping("/send/message/{senderId}/{receiverId}")
    public ChatMessage sendMessageTo(@Payload ChatMessage chatMessage, @DestinationVariable("senderId") String senderId, @DestinationVariable("receiverId") String receiverId){
        System.out.println(chatMessage);
//        User sender;
//        User receiver;
//        if(userService.findById(senderId).isPresent()) {
//            sender = userService.findById(senderId).get();
//        }
//        if(userService.findById(receiverId).isPresent()) {
//            receiver = userService.findById(receiverId).get();
//        }
        this.template.convertAndSend("/message/"+ senderId + "/" + receiverId,  chatMessage);
        return chatMessage;
    }

}
