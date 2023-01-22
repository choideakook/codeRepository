package mallsmall.mallsmall.service;

import lombok.RequiredArgsConstructor;
import mallsmall.mallsmall.domain.*;
import mallsmall.mallsmall.domain.item.Item;
import mallsmall.mallsmall.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final ItemService itemService;

    // create order
    @Transactional
    public Long createOrder(Long memberId, Long itemId, int count) {
        // entity 조회
        Member member = memberService.findOne(memberId);
        Item item = itemService.findOne(itemId);

        // delivery 생성
        Delivery delivery = Delivery.inputAddress(member.getAddress());

        // order item 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, count);

        // member 기존 주문내역 확인
        List<Order> findOrders = orderRepository.findByMember(member);
        if (!findOrders.isEmpty()) {
            // 기존 주문내역이 있을경우 주문 리스트에 추가
            Order order = findOrders.get(0);
            order.addOrder(orderItem);
            return order.getId();
        } else {
            // 신규 주문일경우 주문 생성
            Order order = Order.createOrder(member, delivery, orderItem);

            // order 저장
            orderRepository.save(order);
            return order.getId();
        }
    }

    // cancel order
    @Transactional
    public void cancelOrder(Long orderId) {
        Order findOrder = orderRepository.findOne(orderId);
        findOrder.cancelOrder();
    }

    // find order
    public Order findOne(Long id) {
        return orderRepository.findOne(id);
    }
}
