package task.todos.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 비즈니스 로직을 수행하는 객체입니다.
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {

    // 비즈니스 로직을 수행하기 위해서 TodoRepository 를 DI 했습니다.
    private final TodoRepository repository;

    // Todo 를 저장하는 로직입니다.
    @Transactional
    public Long save(Todo todo) {
        repository.save(todo);
        return todo.getId();
    }

    // id 값을 통해 특정 Todo 를 조회하는 로직입니다.
    public Todo findTodo(Long id) {
        return repository.findById(id);
    }

    // 저장된 모든 Todo 를 조회하는 로직입니다.
    public List<Todo> findAll() {
        return repository.findAll();
    }

    // 저장된 Todo 중 특정 Todo 를 삭제하는 로직입니다.
    @Transactional
    public Long removeTodo(Long id) {
        Todo findTodo = repository.findById(id);
        repository.delete(findTodo);
        return id;
    }


}
