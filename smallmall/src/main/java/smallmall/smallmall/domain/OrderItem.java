package smallmall.smallmall.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import smallmall.smallmall.domain.item.Item;
import smallmall.smallmall.domain.item.Jelly;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne (fetch = LAZY)
    @JoinColumn (name = "item_id")
    private Item item;

    @ManyToOne (fetch = LAZY)
    @JoinColumn (name = "order_id")
    private Order order;

    private int orderPrice;

    private int count;

    //== 생성 method ==//

    public static OrderItem createOrderItem(Item item, int price, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(price);
        orderItem.setCount(count);
        item.removeStock(count);
        return orderItem;
    }

    //== business logic ==//

    // 주문 취소
    public void cancel() {
        getItem().addStock(count);
    }

    //== 조회 로직 ==//

    // 주문 총액
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
