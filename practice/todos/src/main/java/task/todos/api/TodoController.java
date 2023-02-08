package task.todos.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService service;

    @PostMapping("/todos")
    public CreateTodoResponse saveTodo(
            @RequestBody @Valid CreateTodoRequest request
    ) {
        Todo todo = new Todo(request.getContent(), request.getPerformDate());
        Long todoId = service.save(todo);
        return new CreateTodoResponse("S-1", "성공", todoId);
    }

    @Data
    static class CreateTodoRequest {
        private String content;
        private String performDate;
    }

    @Data
    @AllArgsConstructor
    static class CreateTodoResponse {
        private String resultCode;
        private String msg;
        private Long id;
    }

    @GetMapping("/todos")
    public Result findTodos() {
        List<Todo> findTodos = service.findAll();
        List<TodoDto> collect = findTodos.stream()
                .map(t -> new TodoDto(
                        t.getId(),
                        t.getCreateDate(),
                        t.getPerformDate(),
                        t.getContent()
                ))
                .collect(Collectors.toList());
        return new Result("S-1", "성공", collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private String resultCode;
        private String msg;
        private T todos;
    }

    @Data
    @AllArgsConstructor
    static class TodoDto {
        private Long id;
        private LocalDateTime createDate;
        private LocalDateTime performDate;
        private String content;
    }

    @DeleteMapping("/todos/{id}")
    public DeleteTodoResponse deleteTodo(
            @PathVariable("id") Long id
    ){
        service.removeTodo(id);
        Todo deletedTodo = service.findTodo(id);
        return new DeleteTodoResponse("S-1", "성공", deletedTodo);
    }

    @Data
    @AllArgsConstructor
    static class DeleteTodoResponse{
        private String resultCode;
        private String msg;
        private Todo data;
    }

}
