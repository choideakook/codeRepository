package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    // save member 로직
    private Member getMember() {
        Member member = new Member();
        member.setName("MemberA");
        member.setAddress(new Address("서울", "서울길", "123-456"));
        em.persist(member);
        return member;
    }

    // save item 로직
    private Book getItem(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    @Test
    void 상품주문 () {

        // given
        Member member = getMember();
        Item item = getItem("JPA Shop", 10000, 10);

        int orderCount = 2;

        // when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        Order getOrder = orderRepository.findOne(orderId);

        //then
        assertThat(OrderStatus.ORDER).isEqualTo(getOrder.getStatus());
        assertThat(1).isEqualTo(getOrder.getOrderItems().size());
        assertThat(item.getPrice() * orderCount).isEqualTo(getOrder.getTotalPrice());
        assertThat(10 - orderCount).isEqualTo(item.getStockQuantity());
    }

    @Test
    void 주문취소 () {

        // given
        Member member = getMember();

        int stockQuantity = 10;
        Item item = getItem("JPA Shop", 10000, stockQuantity);

        int orderCount = 4;
        Long order = orderService.order(member.getId(), item.getId(), orderCount);

        // when
        assertThat(stockQuantity - orderCount).isEqualTo(item.getStockQuantity());

        orderService.cancelOrder(order);
        Order findOrder = orderRepository.findOne(order);

        // then
        assertThat(stockQuantity).isEqualTo(item.getStockQuantity());
        assertThat(OrderStatus.CANCEL).isEqualTo(findOrder.getStatus());
    }

    @Test
    void 재고수량초과 () {

        // given
        Member member = getMember();
        Item item = getItem("JPA Shop", 10000, 10);

        //when
        int soldOutCount = 10;
        int overCount = 1;

        // then
        Long fullBuy = orderService.order(member.getId(), item.getId(), soldOutCount);
        Order getOrder = orderRepository.findOne(fullBuy);

        assertThat(10 - soldOutCount).isEqualTo(item.getStockQuantity());

        NotEnoughStockException e = assertThrows(NotEnoughStockException.class,
                () -> orderService.order(member.getId(), item.getId(), overCount));


    }

}