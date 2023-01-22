package mallsmall.mallsmall.apiController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import mallsmall.mallsmall.domain.item.Book;
import mallsmall.mallsmall.repository.ItemRepository;
import mallsmall.mallsmall.service.ItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;

    @PostMapping("/items")
    public CreateItemResponse createItem (
            @RequestBody @Valid CreateItemRequest request
    ) {
        Book book = new Book();
        book.updateItem(request.getName(), request.getPrice(), request.getStockQuantity());
        Long itemId = service.saveItem(book);
        return new CreateItemResponse(itemId, request.getName());
    }

    @Data
    @AllArgsConstructor
    static class CreateItemResponse{
        private Long id;
        private String name;
    }
    @Data
    static class CreateItemRequest{
        private String name;
        private int price;
        private int stockQuantity;
    }
}
