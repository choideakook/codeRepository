package mallsmall.mallsmall.service;

import mallsmall.mallsmall.domain.item.Book;
import mallsmall.mallsmall.domain.item.Item;
import mallsmall.mallsmall.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired ItemRepository itemRepository;
    @Autowired ItemService itemService;

    @Test
    void save_item() {
        Book book1 = new Book();
        book1.updateItem("bookA", 1000, 10);
        book1.createBook("A");
        Book book2 = new Book();
        book2.updateItem("bookB", 2000, 5);

        Long bookId1 = itemService.saveItem(book1);
        Long bookId2 = itemService.saveItem(book2);


        Item findItem1 = itemService.findOne(bookId1);
        Item findItem2 = itemService.findOne(bookId2);
        assertThat(itemRepository.findOne(bookId1)).isSameAs(findItem1);
        assertThat(itemRepository.findOne(bookId2)).isSameAs(findItem2);
        assertThat(findItem1.getPrice()).isEqualTo(book1.getPrice());
        assertThat(findItem2.getPrice()).isEqualTo(book2.getPrice());
    }

    @Test
    void name_duplicate() {
        Book book1 = new Book();
        book1.updateItem("bookA", 1000, 10);
        Book book2 = new Book();
        book2.updateItem("bookA", 2000, 5);

        itemService.saveItem(book1);

        IllegalStateException e = assertThrows(
                IllegalStateException.class, () -> itemService.saveItem(book2)
        );
    }

    @Test
    void update_item() {
        Book book1 = new Book();
        int price = 1000;
        book1.updateItem("bookA", price, 10);
        Long itemId = itemService.saveItem(book1);
        Item findItem = itemService.findOne(itemId);
        assertThat(findItem.getPrice()).isEqualTo(price);

        itemService.update(itemId,"bookB", 5000, 5);

        assertThat(findItem.getPrice()).isEqualTo(5000);
        assertThat(findItem.getName()).isEqualTo("bookB");
        assertThat(findItem.getStockQuantity()).isNotEqualTo(10);
    }
}