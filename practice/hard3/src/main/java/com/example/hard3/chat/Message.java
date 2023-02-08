package com.example.hard3.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    private UUID uuid;
    private LocalDateTime createDate;
    private String authorName;
    private String content;

    public Message(String authorName, String content) {
        this(UUID.randomUUID(), LocalDateTime.now(), authorName, content);
    }

}
