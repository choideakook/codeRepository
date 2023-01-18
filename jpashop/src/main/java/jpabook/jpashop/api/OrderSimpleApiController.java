package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ ~ ToOne 관계에서의 성능최적화 방법
 * Order 조회
 * Order - 연관관계 -> Member
 * Order - 연관관계 -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    // Entity 를 직접 조회하는 방법
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = this.orderRepository.findAllByCriteria(new OrderSearch());
        return all;
    }

    // Entity 조회후 DTO 로 변환하는 방법
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        return orderRepository.findAllByCriteria(new OrderSearch()).stream()
                // DB 에서 찾아온 orders 를 DTO 에 넣어서 stream 을 돌림
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
    }

    // fatch 문으로 필요한 Entity 를 조회한 후 DTO로 변환하는 방법
    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        // 영속성 context 가 쿼리를 보내기 전에 미리 쿼리를 보내 Data 를 전부 가져옴
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
    }

    // Entity 를 거치지않고 JPA 에서 DTO 를 통해 조회하는 방법
    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }

    // 조회를 원하는 필드 (API 스팩) 를 정확하게 입력해줘야 한다.
    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;

        private Address address;

        // 필드값을 주입하기 위한 생성자 생성
        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }

    }
}
