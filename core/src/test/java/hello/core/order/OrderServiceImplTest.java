package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepositry;
import org.junit.jupiter.api.Test;


class OrderServiceImplTest {

    @Test
    void createOrder () {
        MemoryMemberRepositry memberRepository = new MemoryMemberRepositry();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 20000);
        System.out.println("order = " + order);
    }

}