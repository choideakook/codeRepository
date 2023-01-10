package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item", // 중간테이블에 연결
            joinColumns = @JoinColumn(name = "category_id"), // 중간 테이블에 맵핑
            inverseJoinColumns = @JoinColumn(name = "item_id") // 중간 테이블에서 item 쪽으로 맵핑
    )
    private List<Item> items = new ArrayList<>();
    // 객체는 상관없지만
    // DB 는 Collection 을 양쪽에서 가질 수 없기 때문에
    // 1:N , N:1 로 풀어낼 수 있는 중간 Tabel 이 있어야한다.

    // 자기 자신을 맵핑하는 방법 (셀프 양방향 연관관계 설정)
    @ManyToOne (fetch = FetchType.LAZY) // 부모는 하나지만 자식은 많을 수 있으므로 N:1
    @JoinColumn(name = "parent_id")  // 부모
    private Category parent;

    @OneToMany(mappedBy = "parent") // 자식
    private List<Category> child = new ArrayList<>();

    //== 연관관계 편의 method ==//

    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
