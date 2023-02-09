package task.todos.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// 클라이언트의 요청을 받고 api 를 통해 응답해주는 로직입니다.
@RestController
@RequiredArgsConstructor
public class TodoController {

    // 요청과 응답을 처리하기 위해 TodoService 를 DI 했습니다.
    private final TodoService service;

    // 요청받은 data 를 기반으로 DB 에 저장합니다.
    @PostMapping("/todos")
    public CreateTodoResponse saveTodo(
            @RequestBody @Valid CreateTodoRequest request
    ) {
        Todo todo = new Todo(request.getContent(), request.getPerformDate());
        Long todoId = service.save(todo);
        return new CreateTodoResponse("S-1", "성공", todoId);
    }
    // 요청받은 data 를 단순히 전달해주기 위한 전달용 객체입니다.
    @Data
    static class CreateTodoRequest {
        private String content;
        private String performDate;
    }
    // 응답할 data 를 단순히 전달해주기 위한 전달용 객체입니다.
    @Data
    @AllArgsConstructor
    static class CreateTodoResponse {
        private String resultCode;
        private String msg;
        private Long id;
    }

    // 요청받은 data 를 기반으로 DB 에 저장된 모든 data 를 응답합니다.
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

    // 요청받은 data 를 기반으로 특정 data 를 삭제합니다.
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
