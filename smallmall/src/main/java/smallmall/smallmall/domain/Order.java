package smallmall.smallmall.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter @Setter
@Table(name = "orders")
@NoArgsConstructor(access = PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column (name = "order_id")
    private Long id;

    @ManyToOne (fetch = LAZY)
    @JoinColumn (name = "member_id")
    private Member member;

    @OneToMany (mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne (fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated (EnumType.STRING)
    private OrderStatus status;

    //== 연관관계 편의 Method==//

    public void setMember (Member member){
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

    //== 생성 method ==//

    // 주문 생성
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

    //== business logic ==//

    // 주문 취소
    public void cancelOrder() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("item that have already been delivered cannot be canceled");
        }
        this.setStatus(OrderStatus.CANCLE);

        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //== 조회 로직 ==//

    // 주문 총 합계
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
