package task.todos.api;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// JPA 기술과 H2 DB 를 사용해 과제를 해결했습니다.
@Entity
@Getter
@NoArgsConstructor
public class Todo {

    @Id @GeneratedValue
    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime performDate;
    private String content;

    // 새로운 Todo 를 생성하기 위한 생성자 입니다.
    public Todo(String content, String performDate) {
        this.createDate = LocalDateTime.now();
        this.performDate = LocalDateTime.parse(performDate,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.content = content;
    }
}
