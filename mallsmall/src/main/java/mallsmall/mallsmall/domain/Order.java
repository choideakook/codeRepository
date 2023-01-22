package mallsmall.mallsmall.domain;

import lombok.Getter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@Getter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //== 연관관계 편의 method ==//

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void setOrderItems(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.connectOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.connectOrder(this);
    }

    //== business logic ==//

    // 주문 생성
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.setOrderItems(orderItem);
        }
        order.orderDate = LocalDateTime.now();
        order.status = OrderStatus.ORDER;
        return order;
    }

    // 주문 추가
    public void addOrder(OrderItem... orderItems) {
        for (OrderItem orderItem : orderItems) {
            this.setOrderItems(orderItem);
        }
        this.orderDate = LocalDateTime.now();
    }

    // 주문 취소
    public void cancelOrder() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("배송된 상품은 취소가 불가능 합니다.");
        }
        for (OrderItem orderItem : getOrderItems()) {
            orderItem.cancelOrderItem();
        }
        this.status = OrderStatus.CANCEL;
    }

    // 주문 가격 총합 조회
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
