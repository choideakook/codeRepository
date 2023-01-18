package smallmall.smallmall.repository;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;
import smallmall.smallmall.domain.Order;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    // order 저장
    public void save(Order order) {
        em.persist(order);
    }

    // id 로 order 조회
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    // 모든 order 조회
    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }

    // order N : 1 검색
    public List<Order> findAllWithMemberDelivery() {
        return em.createQuery(
                "select o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d", Order.class)
                .getResultList();
    }
}
