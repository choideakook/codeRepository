package mallsmall.mallsmall.domain.item;

import lombok.Getter;
import mallsmall.mallsmall.exception.NotEnoughStockException;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    //== business logic ==//

    // add stock
    public void addStock(int returnStock) {
        this.stockQuantity += returnStock;
    }

    // remove stock
    public void removeStock(int sellStock) {
        int calculator = this.stockQuantity - sellStock;
        if (calculator < 0) {
            throw new NotEnoughStockException("재고가 부족합니다.");
        }
        this.stockQuantity = calculator;
    }

    //== update item ==//
    public void updateItem(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
