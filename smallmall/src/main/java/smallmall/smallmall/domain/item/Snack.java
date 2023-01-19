package smallmall.smallmall.domain.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("Snack")
public class Snack extends Item{

    private String seasoning;
    private String snackType;

    public void createSignature(String seasoning, String snackType) {
        this.seasoning = seasoning;
        this.snackType = snackType;
    }
}
