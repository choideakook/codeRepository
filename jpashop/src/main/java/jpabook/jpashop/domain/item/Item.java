package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// 구현체를 만들예정이라 추상 Class 로 세팅
// 상속관계 매핑이기 때문에 상속관계 전략을 지정해야 함 (부모 Class 에 생성)
@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE) // 상속관계 매핑 전략
@DiscriminatorColumn (name = "dtype") //싱글테이블이라 저장할 때 구분을 하기위한 에노테이션
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column (name = "item_id")
    private Long id;

    // 구현체가 바뀌어도 필요한 공통 속성
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany (mappedBy = "items")  // 예제를 위한 N:N 관계
    private List<Category> categories = new ArrayList<>();

    //== 비즈니스 로직 ==//
    // 재고를 추가하고 낮추는 로직은 stockQuantity 데이터만 사용하는데
    // 이런경우는 Service Class 를 생성해 setter 로 주입하는 것 보다,
    // Entity Class 에 로직을 만들어서 필드를 입력해주는 것이
    // 객체지향적으로 봤을 때 응집력도 높아지고 관리차원에서도 수월하다.

    // !!! 강의에서는 편의상 setter 를 생성했지만 setter 를 만들지 않는 것이 전체적으로 더 좋다.

    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
    /**
     * stock 감소
     * 재고가 마이너스가 되면 안되기 때문에 체크할 수 있는 기능이 필요하다.
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
