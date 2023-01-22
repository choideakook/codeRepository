package mallsmall.mallsmall.service;

import mallsmall.mallsmall.domain.Member;
import mallsmall.mallsmall.domain.Order;
import mallsmall.mallsmall.domain.OrderStatus;
import mallsmall.mallsmall.domain.item.Book;
import mallsmall.mallsmall.exception.NotEnoughStockException;
import mallsmall.mallsmall.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired OrderService service;
    @Autowired EntityManager em;




    private Member getMember() {
        Member member = new Member("memberA", "seoul", "11st");
        em.persist(member);
        return member;
    }

    private Book getBook() {
        Book book = new Book();
        book.updateItem("BookA",1000,10);
        em.persist(book);
        return book;
    }

    @Test
    void create_Order() {
        Member member = getMember();
        Book book = getBook();

        Long orderId = service.createOrder(member.getId(), book.getId(), 5);
        Order findOrder = service.findOne(orderId);

        assertThat(findOrder.getMember().getName()).isEqualTo(member.getName());
        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(book.getStockQuantity()).isEqualTo(5);
        assertThat(findOrder.getTotalPrice()).isEqualTo(5000);
    }

    @Test
    void cancel_Order() {
        Member member = getMember();
        Book book = getBook();
        Long orderId = service.createOrder(member.getId(), book.getId(), 5);

        service.cancelOrder(orderId);
        Order findOrder = service.findOne(orderId);

        assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(book.getStockQuantity()).isEqualTo(10);
    }

    @Test
    void over_buying_test() {
        Member member = getMember();
        Book book = getBook();

        NotEnoughStockException e = assertThrows(
                NotEnoughStockException.class,
                () -> service.createOrder(member.getId(), book.getId(), 11)
        );
    }
}