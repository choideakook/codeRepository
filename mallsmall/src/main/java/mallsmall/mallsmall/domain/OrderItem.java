package mallsmall.mallsmall.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import mallsmall.mallsmall.domain.item.Item;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "orderItem")
    private Order order;

    @ManyToOne(fetch = LAZY)
    private Item item;

    private int orderPrice;

    private int count;

    //== 연관관계 편의 method ==//
    protected void connectOrder(Order order) {
        this.order = order;
    }

    //== business logic ==//

    // 주문 상품 생성
    public static OrderItem createOrderItem(Item item, int count) {
        item.removeStock(count);
        OrderItem orderItem = new OrderItem();
        orderItem.item =item;
        orderItem.count = count;
        orderItem.orderPrice = item.getPrice();
        return orderItem;
    }

    // 주문 상품 취소
    public void cancelOrderItem() {
        item.addStock(count);
    }

    // 주문 총액
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
