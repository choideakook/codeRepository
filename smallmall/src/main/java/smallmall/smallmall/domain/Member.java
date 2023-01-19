package smallmall.smallmall.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column (name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    //== set Member logic ==//
    public Member(String name) {
        this.name = name;
    }
    public void updateName (String name) {
        this.name = name;
    }

    public void setAddress(String phone, String myAddress) {
        this.address = new Address(phone, myAddress);
    }
}
