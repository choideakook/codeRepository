package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Order 의 Table 명은 관례상 orders 로 해야한다.
@Entity
@Table (name = "orders")
@Getter @Setter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column (name = "order_id")
    private Long id;

    // Member 의 PK 에서 참조한 FK
    @ManyToOne (fetch = FetchType.LAZY)   // N:1 의 연관관계
    @JoinColumn (name = "member_id")  // 연관관계의 주인이 표시 (조회, 저장, 수정, 삭제의 권한)
    private Member member;

    @OneToMany (mappedBy = "order" , cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne (fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn (name = "delivery_id")
    private Delivery delivery;

    // 주문시간
    private LocalDateTime orderDate;

    // 주문상태를 의미 [ order & cancel ] enum 으로 생성함
    @Enumerated (EnumType.STRING) // enum 값을 DB 에 저장하는 에노테이션
    private OrderStatus status;
    // EnumType.STRING : 이름으로 DB 에 저장 (항상 이것만 사용해야함)
    // EnumType.ORDINAL : 순서로 DB 에 저장 (중간에 순서가 바뀌면 망하기 때문에 사용금지) 기본값

    //== 연관관계 Method ==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

//    다른 Class 에서 instance 를 함부로 생성하지 말라는 의미
//    lombok 으로도 같은 효과를 사용할 수 있음
//    @NoArgsConstructor (access = AccessLevel.PROTECTED)
//    protected Order() {
//    }

    //== 생성 Method ==//
    // 외부 Class 에서 set 으로 필드를 각각 주입해주는 방식이 아닌,
    // create Method 실행으로 한번에 완성할 수 있게 설계회
    // 해당 method 가 실행되면 연관관계를 상태와 시간까지 모두 세팅해주고 생성이 됨
    // 앞으로 oder 를 생성할 땐 해당 method 만 실행하면됨
    // 고객이 한번에 여러 상품을 주문할 수 있으니 OrderItem 가변인자로 사용
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //== 비즈니스 로직==//
    /**
     * 주문 취소
     */
    public void cancel () {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("item that have already been delivered cannot be canceled");
        }
        this.setStatus(OrderStatus.CANCEL);
        // orderIems 에 담겨있는 주문 여러개를 each 문을 돌며 전부 삭제해줌
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //== 조회 로직 ==//
    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice(){
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
