package mallsmall.mallsmall.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    //== 연관관계 편의 method ==//
    protected void connectOrder(Order order) {
        this.order = order;
    }

    //== business logic ==//

    // create delivery info
    public static Delivery inputAddress(Address address) {
        Delivery delivery = new Delivery();
        delivery.address = address;
        return delivery;
    }
}
