package com.kaadi.KadiBusinessCard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private int id;

    @Column(name = "message_content")
    private String content;

    @Column(name = "sending_time")
    private String time;

    @Column(name="sender_id")
    private int senderId;

    @Column(name="receiver_id")
    private int receiverId;

    public Message(String content, int senderId, int receiverId){
        this.content=content;
        Date date= new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = simpleDateFormat.format(date);
        this.time=currentTime;
        this.senderId=senderId;
        this.receiverId=receiverId;
    }
}
