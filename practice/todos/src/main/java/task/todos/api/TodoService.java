package task.todos.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;

    public Long save(Todo todo) {
        repository.save(todo);
        return todo.getId();
    }

    @Transactional(readOnly = true)
    public Todo findTodo(Long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Todo> findAll() {
        return repository.findAll();
    }

    public Long removeTodo(Long id) {
        Todo findTodo = repository.findById(id);
        repository.delete(findTodo);
        return id;
    }


}
