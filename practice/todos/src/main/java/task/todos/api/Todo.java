package task.todos.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor
public class Todo {

    @Id @GeneratedValue
    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime performDate;
    private String content;

    public Todo(String content, String performDate) {
        this.createDate = LocalDateTime.now();
        this.performDate = LocalDateTime.parse(performDate,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.content = content;
    }
}
