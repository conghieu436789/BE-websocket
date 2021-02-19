package com.socket.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.sql.Timestamp;

@CrossOrigin("*")
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String typeNoti;

    @ManyToOne
    @JoinColumn(name = "user_sender_id",insertable = false,updatable = false)
    private User userSender;

    private long user_sender_id;


    @ManyToOne
    @JoinColumn(name = "user_receiver_id",insertable = false,updatable = false)
    private User userReceiver;

    private long user_receiver_id;

    private Timestamp createdDate;

    @JsonProperty
    private boolean status;


}
