package smallmall.smallmall.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smallmall.smallmall.domain.item.Item;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    // item 저장
    public void save(Item item) {
        em.persist(item);
    }

    // 모든 item 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    // id 로 item 조회
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    // name 으로 item 조회
    public List<Item> findByName(String name) {
        return em.createQuery("select i from Item i where i.name =:name", Item.class)
                .setParameter("name", name)
                .getResultList();
    }
}
