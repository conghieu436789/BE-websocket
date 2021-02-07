package com.socket.demo.repository;
import com.socket.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query(value = "SELECT * \n" +
            "FROM demo_jwt_websocket.users where demo_jwt_websocket.users.username <> ?1", nativeQuery = true)
    List<User> getFriends(String username);

//
}
