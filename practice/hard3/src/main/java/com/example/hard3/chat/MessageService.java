package com.example.hard3.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repository;

    public UUID save(Message message) {
        repository.save(message);
        return message.getUuid();
    }

    public Message findByUuid(UUID uuid) {
        return repository.findByUuid(uuid);
    }

    public List<Message> findAll() {
        return repository.findAll();
    }




}
