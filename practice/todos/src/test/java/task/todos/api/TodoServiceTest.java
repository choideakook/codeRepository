package task.todos.api;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TodoServiceTest {

    @Autowired TodoService service;

    @Test
    void name() {
        DateTimeFormatter per = DateTimeFormatter.ofPattern("2022-12-18 12:00:00");

        Todo todo = new Todo("todo", "2022-12-18 12:00:00");
        Long id = service.save(todo);

        Todo findTodo = service.findTodo(id);
        assertThat(findTodo.getContent()).isEqualTo("todo");
        System.out.println(findTodo.getPerformDate());

        service.removeTodo(id);

        Todo findTodo2 = service.findTodo(id);
        assertThat(findTodo2).isNull();
    }
}