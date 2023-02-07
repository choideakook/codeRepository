package jpabook.jpashop2.repository;

import jpabook.jpashop2.domain.Order;
import jpabook.jpashop2.domain.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }

    public List<Order> findByName(String name) {
        return em.createQuery("select o from Order o" +
                        " join o.member m" +
                        " where m.name like :itemName)", Order.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Order> findByStatus(OrderStatus status) {
        return em.createQuery("select o from Order o" +
                        " join m.member m" +
                        " where o.status = :status", Order.class)
                .setParameter("status", status)
                .getResultList();
    }

   public List<Order> findByItems(String name, OrderStatus status) {
        return em.createQuery("select o from Order o" +
                        " join o.member m" +
                        " where m.name like :itemName)" +
                        " and o.status = :status", Order.class)
                .setParameter("name", name)
                .setParameter("status", status)
                .getResultList();
    }


}
