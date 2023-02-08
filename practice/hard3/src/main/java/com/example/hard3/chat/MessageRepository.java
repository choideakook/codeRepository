package com.example.hard3.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class MessageRepository {

    private final EntityManager em;

    public void save(Message message) {
        em.persist(message);
    }

    public Message findByUuid(UUID uuid) {
        return em.find(Message.class, uuid);
    }

    public List<Message> findAll () {
        return em.createQuery("select m from Message m", Message.class)
                .getResultList();
    }



}
