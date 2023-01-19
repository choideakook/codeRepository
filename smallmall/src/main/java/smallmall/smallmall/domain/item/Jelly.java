package smallmall.smallmall.domain.item;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@DiscriminatorValue("Jelly")
public class Jelly extends Item{

    private String shape;
    private String taste;

    public void createSignature(String shape, String taste) {
        this.shape = shape;
        this.taste = taste;
    }
}
