package smallmall.smallmall.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import smallmall.smallmall.excption.NotEnoughStockException;

import javax.persistence.*;

import static lombok.AccessLevel.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name = "dtype")
public abstract class Item {

    @Id @GeneratedValue
    @Column (name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    //== test logic ==//
    public void createItem(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }


    //== business logic ==//

    // 재고 추가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    // 재고 감소
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("Not enough stock");
        }
        this.stockQuantity = restStock;
    }

    // name 수정
    public void setName(String name, int stockQuantity) {
        this.name = name;
        this.stockQuantity = stockQuantity;
    }
}
