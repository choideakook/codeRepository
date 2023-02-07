package jpabook.jpashop2.service;

import jpabook.jpashop2.domain.*;
import jpabook.jpashop2.domain.item.Item;
import jpabook.jpashop2.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final ItemService itemService;

    public Long createOrder(Long memberId, Long itemId, int count) {
        Member findMember = memberService.findOneMember(memberId);
        Item findItem = itemService.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(findMember.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        OrderItem orderItem = OrderItem.createOrderItem(findItem, findItem.getPrice(), count);

        Order order = Order.createOrder(findMember, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    public void cancelOrder(Long orderId) {
        Order findOrder = orderRepository.findOne(orderId);
        findOrder.cancelOrder();
    }

    public List<Order> findAll(String name, OrderStatus status) {
        if (StringUtils.hasText(name) && status != null) {
            return orderRepository.findByItems(name, status);
        } else if (StringUtils.hasText(name)) {
            return  orderRepository.findByName(name);
        } else if (status != null) {
            return orderRepository.findByStatus(status);
        } else {
            return orderRepository.findAll();
        }
    }

    public Order findOne(Long id) {
        return orderRepository.findOne(id);
    }
}
