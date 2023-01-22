package mallsmall.mallsmall.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotNull
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    //== input logic ==//

    public Member(String name, String city, String street) {
        Address address = new Address(city, street);
        this.name = name;
        this.address = address;
    }

    //== business logic ==//

    // name update
    public void updateName(String name) {
        this.name = name;
    }
}
