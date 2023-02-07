package jpabook.jpashop2.domain;

import jpabook.jpashop2.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int price;
    private int count;

    //== 생성 method ==//

    // buying
    public OrderItem createOrderItem(Item item,int price, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        item.removeStock(count);
        orderItem.setCount(count);
        orderItem.setPrice(price);
        return orderItem;
    }

    //== business logic ==//

    // refund
    public void refundItem() {
        getItem().addStock(count);
    }

    // == 조회 로직 ==//
    public int getTotalPrice() {
        return getPrice() * getCount();
    }
}
