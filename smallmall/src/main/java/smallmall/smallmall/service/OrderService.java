package smallmall.smallmall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smallmall.smallmall.domain.Delivery;
import smallmall.smallmall.domain.Member;
import smallmall.smallmall.domain.Order;
import smallmall.smallmall.domain.OrderItem;
import smallmall.smallmall.domain.item.Item;
import smallmall.smallmall.repository.ItemRepository;
import smallmall.smallmall.repository.MemberRepository;
import smallmall.smallmall.repository.OrderRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    // 주문 기능
    public Long createOrder(Long memberId, Long itemId, int count) {

        // Entity 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    // 주문 취소
    public void cancelOrder(Long id) {
        Order order = orderRepository.findOne(id);
        order.cancelOrder();
    }

    // 주문 조회
    public Order findOne(Long id) {
        return orderRepository.findOne(id);
    }
}
