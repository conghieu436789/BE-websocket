package com.socket.demo.repository;

import com.socket.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
