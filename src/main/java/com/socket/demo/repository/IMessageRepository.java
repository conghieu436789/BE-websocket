package com.socket.demo.repository;

import com.socket.demo.model.ChatMessage;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IMessageRepository extends PagingAndSortingRepository<ChatMessage, Integer> {

}
