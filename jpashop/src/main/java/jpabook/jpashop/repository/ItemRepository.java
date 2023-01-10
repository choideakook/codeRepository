package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    // item 은 JPA 에 저장하기 전까지 id 값이 없다.
        // (즉 완전히 새로 생성한 객체라는 의미)
    // 즉 신규 등록은 id 가 null 이기 때문에 persist 등록 해주면된고,
    // 신규가 아닌 이미 등록되어있는 item 을 update 하는 경우엔 id 값이 이미 있을테니
    // merge 로 병합해서 update 해주는 로직이다.
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
