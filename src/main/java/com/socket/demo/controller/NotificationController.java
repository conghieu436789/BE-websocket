package com.socket.demo.controller;

import com.socket.demo.model.Notification;
import com.socket.demo.model.User;
import com.socket.demo.service.INotificationService;
import com.socket.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    INotificationService notificationService;
    @Autowired
    IUserService userService;
    @GetMapping("/all/{id}")
    public List<Notification> getNotifications(@PathVariable("id") long id) {
        return notificationService.findAllByUser_receiver_id(id);
    }

    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable("id") long id) {
        Optional<Notification> notification = notificationService.findById(id);
        if (notification.isPresent()) {
            return notificationService.findById(id).get();
        }
        return null;
    }

    @PostMapping("/create")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') ")
    public ResponseEntity<Notification> createNotification(@Valid @RequestBody Notification notification) {
        try {
            User sender = userService.findById(notification.getUser_sender_id()).get();
            User receiver = userService.findById(notification.getUser_receiver_id()).get();
            notification.setStatus(false);
            notification.setUserSender(sender);
            notification.setUserReceiver(receiver);
            notificationService.save(notification);
            return new ResponseEntity<>(notification, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> deleteNotification(@PathVariable("id") long id) {
        notificationService.remove(id);
        return new ResponseEntity<>("Notification has been removed",HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Notification> updateProduct(@PathVariable("id") long id, @RequestBody Notification notification) {
        Optional<Notification> notification1 = notificationService.findById(id);
        if (notification1.isPresent()) {
            notification1.get().setTypeNoti(notification.getTypeNoti());
            notification1.get().setUser_receiver_id(notification.getUser_receiver_id());
            notification1.get().setUser_sender_id(notification.getUser_sender_id());
            User sender = userService.findById(notification.getUser_sender_id()).get();
            User receiver = userService.findById(notification.getUser_receiver_id()).get();
            notification1.get().setUserSender(sender);
            notification1.get().setUserReceiver(receiver);
            notification1.get().setStatus(notification.isStatus());
            notificationService.save(notification1.get());
            return new ResponseEntity<>(notification1.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
