package com.socket.demo.service;


import com.socket.demo.model.Notification;

import java.util.List;

public interface INotificationService extends IGeneralService<Notification> {
    List<Notification> findAllByUser_receiver_id(long id);
}
