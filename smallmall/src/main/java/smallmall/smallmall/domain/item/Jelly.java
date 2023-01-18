package smallmall.smallmall.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@DiscriminatorValue("Jelly")
public class Jelly extends Item{

    private String shape;
    private String taste;


}
