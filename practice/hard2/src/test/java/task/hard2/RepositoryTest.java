package task.hard2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RepositoryTest {

    Repository repository = new Repository();

    @AfterEach
    public void clearData() {
        repository.clearStore();
    }

    @Test
    void 등록() {
        Message message = new Message("me", "hello");
        Long messageId = repository.save(message);

        Message findMessage = repository.findById(messageId).get();

        assertThat(findMessage.getAuthorName()).isEqualTo("me");
        System.out.println(findMessage.getCreateDate());
    }
}