package smallmall.smallmall.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import smallmall.smallmall.domain.Address;
import smallmall.smallmall.domain.Member;
import smallmall.smallmall.domain.Order;
import smallmall.smallmall.domain.OrderStatus;
import smallmall.smallmall.domain.item.Jelly;
import smallmall.smallmall.excption.NotEnoughStockException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired OrderService orderService;
    @Autowired MemberService memberService;
    @Autowired ItemService itemService;

    @Test
    void create_Order() {
        // entity input
        Member member = new Member("MemberA");
        member.setAddress("010", "seoul");
        Jelly jelly = new Jelly();
        int itemPrice = 1000;
        jelly.createItem("apple jelly", itemPrice, 5);
        jelly.createSignature("worm", "apple");

        // save
        Long memberId = memberService.join(member);
        Long jellyId = itemService.create(jelly);

        //create order
        int orderCount = 3;
        Long orderId = orderService.createOrder(memberId, jellyId, orderCount);
        Order findOrder = orderService.findOne(orderId);

        // testing
        assertThat(findOrder.getMember()).isSameAs(memberService.findOne(memberId));
        assertThat(jelly.getStockQuantity()).isEqualTo(2);
        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(findOrder.getOrderItems().size()).isEqualTo(1);
        assertThat(findOrder.getTotalPrice()).isEqualTo(itemPrice * orderCount);
    }

    @Test
    void cancel_Order() {
        // entity input
        Member member = new Member("MemberA");
        member.setAddress("010", "seoul");
        Jelly jelly = new Jelly();
        int itemPrice = 1000;
        jelly.createItem("apple jelly", itemPrice, 5);
        jelly.createSignature("worm", "apple");

        // save
        Long memberId = memberService.join(member);
        Long jellyId = itemService.create(jelly);

        //create order
        int orderCount = 3;
        Long orderId = orderService.createOrder(memberId, jellyId, orderCount);
        Order findOrder = orderService.findOne(orderId);

        //cancel order
        orderService.cancelOrder(orderId);

        // testing
        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.CANCLE);
        assertThat(jelly.getStockQuantity()).isEqualTo(5);
    }

    @Test
    void stock_overCount() {
        // entity input
        Member member = new Member("MemberA");
        member.setAddress("010", "seoul");
        Jelly jelly = new Jelly();
        int itemPrice = 1000;
        jelly.createItem("apple jelly", itemPrice, 5);
        jelly.createSignature("worm", "apple");

        // save
        Long memberId = memberService.join(member);
        Long jellyId = itemService.create(jelly);

        //testing
        assertThrows(
                NotEnoughStockException.class,
                () -> orderService.createOrder(memberId, jellyId, 10)
        );

    }
}