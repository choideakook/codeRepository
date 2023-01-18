package smallmall.smallmall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smallmall.smallmall.domain.Delivery;
import smallmall.smallmall.domain.Member;
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


        // 주문 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
    }
}
