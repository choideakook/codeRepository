package task.todos.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {

    private final EntityManager em;

    public void save(Todo todo) {
        em.persist(todo);
    }

    public Todo findById(Long id) {
        return em.find(Todo.class, id);
    }

    public List<Todo> findAll () {
        return em.createQuery("select t from Todo t", Todo.class)
                .getResultList();
    }

    public void delete(Todo todo) {
        em.remove(todo);
    }
}
