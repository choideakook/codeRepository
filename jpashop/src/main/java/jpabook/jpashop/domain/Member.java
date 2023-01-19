package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    // PK 명을 @Column 으로 수정
    @Id @GeneratedValue
    @Column (name = "member_id")
    private Long id;

    @NotEmpty
    private String name;

    // 임베디드가 된 필드
    @Embedded
    private Address address;

    // Order Class 의 member 필드에 의해시 의존관계의 거울이됨
    @OneToMany (mappedBy = "member") // non Owner 의 표시 (읽기만 가능)
    private List<Order> orders = new ArrayList<>();

}
