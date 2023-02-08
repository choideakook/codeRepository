package task.hard2;

import java.util.*;

@org.springframework.stereotype.Repository
public class Repository {

    private static Map<Long, Message> store = new HashMap<>();
    private static long sequence = 0L;

    public Long save(Message message) {
        message.setId(++sequence);
        store.put(message.getId(), message);
        return message.getId();
    }

    public Optional<Message> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Message> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
