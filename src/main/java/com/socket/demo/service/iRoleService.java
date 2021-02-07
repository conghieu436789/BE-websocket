package com.socket.demo.service;

import com.socket.demo.model.Role;

import java.util.Optional;

public interface iRoleService extends IGeneralService<Role> {
    Optional<Role> findByName(String name);
}
