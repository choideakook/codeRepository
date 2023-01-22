package mallsmall.mallsmall.repository;

import lombok.RequiredArgsConstructor;
import mallsmall.mallsmall.domain.item.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    // save item
    public void save(Item item) {
        em.persist(item);
    }

    // find one
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    // find All item
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    // find by item
    public List<Item> findByName(String name) {
        return em.createQuery(
                "select i from Item i" +
                        " where i.name =: name", Item.class)
                .setParameter("name", name)
                .getResultList();
    }
}
