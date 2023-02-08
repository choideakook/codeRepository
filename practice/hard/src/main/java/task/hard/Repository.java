package task.hard;

import java.util.*;

public class Repository {

    private static Map<UUID, Message> store = new HashMap<>();

    public Message save(Message message) {
        store.put(message.getUuid(), message);
        return message;
    }

    public Optional<Message> findById(UUID uuid) {
        return Optional.ofNullable(store.get(uuid));
    }

    public List<Message> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
