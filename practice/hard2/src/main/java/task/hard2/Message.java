package task.hard2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
@AllArgsConstructor
public class Message {

    private Long id;
    private UUID uuid;
    private LocalDateTime createDate;
    private String authorName;
    private String content;

    protected Message() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Message(String authorName, String content) {
        this.uuid = UUID.randomUUID();
        this.createDate = LocalDateTime.now();
        this.authorName = authorName;
        this.content = content;
    }
}
