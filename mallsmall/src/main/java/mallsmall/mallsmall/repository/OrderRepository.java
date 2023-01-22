package mallsmall.mallsmall.repository;

import lombok.RequiredArgsConstructor;
import mallsmall.mallsmall.domain.Member;
import mallsmall.mallsmall.domain.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    // create order
    public void save(Order order) {
        em.persist(order);
    }

    // find order
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    // find by member
    public List<Order> findByMember(Member member) {
        return em.createQuery(
                "select o from Order o" +
                        " where o.member =: member", Order.class)
                .setParameter("member", member)
                .getResultList();
    }

    // find All order with dto
    public List<Order> findAllWithMemberDelivery() {
        return em.createQuery(
                "select o from Order o" +
                        " join fetch o.Member m" +
                        " join fetch o.Delivery d", Order.class)
                .getResultList();
    }
}
