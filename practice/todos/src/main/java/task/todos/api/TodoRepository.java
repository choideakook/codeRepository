package task.todos.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

// DB 와 관련된 작업을 처리하는 객체입니다.
@Repository
@RequiredArgsConstructor
public class TodoRepository {

    // jpa 기술을 사용하기 위해 EntityManager 를 DI 했습니다.
    private final EntityManager em;

    // DB 에 새로운 todo 를 저장하는 로직
    public void save(Todo todo) {
        em.persist(todo);
    }

    // DB 에 저장된 데이터중 id 값을 통해 특정 row 를 조회하는 로직
    public Todo findById(Long id) {
        return em.find(Todo.class, id);
    }

    // DB 에 저장된 모든 data 를 조회하는 로직
    public List<Todo> findAll () {
        return em.createQuery("select t from Todo t", Todo.class)
                .getResultList();
    }

    // DB 에 저장된 데이터중 특정 row 를 삭제하는 로직
    public void delete(Todo todo) {
        em.remove(todo);
    }
}
