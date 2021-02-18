package com.socket.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.List;

@CrossOrigin("*")
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOne
    @JoinColumn(name = "first_user_id",insertable = false,updatable = false)
    private User firstUser;

    private Long first_user_id;

    @ManyToOne
    @JoinColumn(name = "second_user_id",insertable = false,updatable = false)
    private User secondUser;

    private Long second_user_id;

//    @OneToMany(mappedBy = "chatRoom")
//    private List<ChatMessage> messages;
}
