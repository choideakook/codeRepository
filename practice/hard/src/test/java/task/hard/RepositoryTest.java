package task.hard;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class RepositoryTest {

    Repository repository = new Repository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void 등록() {
        Message message = new Message("me", "hello");
        Message saveMessage = repository.save(message);

        Message findMessage = repository.findById(saveMessage.getUuid()).get();

        assertThat(saveMessage.getAuthorName()).isEqualTo("me");
    }
}