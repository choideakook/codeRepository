package smallmall.smallmall.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@DiscriminatorValue("Snack")
public class Snack extends Item{

    private String seasoning;
    private String snackType;
}
