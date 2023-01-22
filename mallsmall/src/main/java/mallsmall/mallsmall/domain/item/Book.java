package mallsmall.mallsmall.domain.item;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("B")
public class Book extends Item {

    private String author;

    //== input logic ==//

    public void createBook(String author) {
        this.author = author;
    }
}
