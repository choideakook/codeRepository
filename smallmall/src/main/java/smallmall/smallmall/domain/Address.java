package smallmall.smallmall.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String phone;
    private String myAddress;

    protected Address() {
    }

    public Address(String phone, String myAddress) {
        this.phone = phone;
        this.myAddress = myAddress;
    }
}
