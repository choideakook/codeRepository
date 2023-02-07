package jpabook.jpashop2.service;

import jpabook.jpashop2.domain.*;
import jpabook.jpashop2.domain.item.Book;
import jpabook.jpashop2.domain.item.Item;
import jpabook.jpashop2.exception.NotEnoughStockException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired OrderService orderService;
    @Autowired MemberService memberService;
    @Autowired ItemService itemService;
    @Autowired EntityManager em;

    private Long createMember(String name, String city, String street, String zipcode) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address(city, street, zipcode));
        em.persist(member);
        return member.getId();
    }

    private Long createItem(String name, int price, int stock) {
        Book item = new Book();
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stock);
        em.persist(item);
        return item.getId();
    }

    @Test
    void 주문() {
        Long memberId = createMember("A", "c", "s", "z");
        int stock = 10;
        Long itemId = createItem("B", 1000, stock);

        int count = 3;
        Long createOrder = orderService.createOrder(memberId, itemId, count);

        Order findOrder = orderService.findOne(createOrder);
        Member findMember = memberService.findOneMember(memberId);
        Item findItem = itemService.findOne(itemId);
        int totalPrice = findOrder.getTotalPrice();

        assertThat(findOrder.getMember()).isEqualTo(findMember);
        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(findOrder.getDelivery().getStatus()).isEqualTo(DeliveryStatus.READY);
        assertThat(findItem.getStockQuantity()).isEqualTo(stock - count);
        assertThat(totalPrice).isEqualTo(count * findItem.getPrice());

        Long item2Id = createItem("c", 500, 10);
        Item findItem2 = itemService.findOne(item2Id);
        OrderItem orderItem = OrderItem.createOrderItem(findItem2, findItem2.getPrice(), 2);

        findOrder.getOrderItems().add(orderItem);
        totalPrice = findOrder.getTotalPrice();

        assertThat(totalPrice).isEqualTo(4000);

    }

    @Test
    void 재고부족() {
        Long memberId = createMember("A", "c", "s", "z");
        Long itemId = createItem("B", 1000, 10);

        assertThatThrownBy(() -> orderService.createOrder(memberId, itemId, 11))
                .isInstanceOf(NotEnoughStockException.class);
    }

    @Test
    void 주문취소() {
        Long memberId = createMember("A", "c", "s", "z");
        int stock = 10;
        Long itemId = createItem("B", 1000, stock);

        int count = 3;
        Long orderId = orderService.createOrder(memberId, itemId, count);
        Item item = itemService.findOne(itemId);

        assertThat(item.getStockQuantity()).isEqualTo(stock - count);

        orderService.cancelOrder(orderId);
        Order findOrder = orderService.findOne(orderId);

        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(item.getStockQuantity()).isEqualTo(stock);
    }

}