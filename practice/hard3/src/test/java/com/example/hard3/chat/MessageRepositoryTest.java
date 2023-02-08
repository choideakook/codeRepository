package com.example.hard3.chat;

import com.example.hard3.chat.Message;
import com.example.hard3.chat.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

class MessageRepositoryTest {


    @Test
    void 등록() {
        Message message = new Message("me", "hello");

    }
}