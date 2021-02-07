package com.socket.demo.service;


import com.socket.demo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    List<User> getFriends(String username);
}
