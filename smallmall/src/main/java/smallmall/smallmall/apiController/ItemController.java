package smallmall.smallmall.apiController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import smallmall.smallmall.domain.item.Item;
import smallmall.smallmall.domain.item.Jelly;
import smallmall.smallmall.service.ItemService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;

    // 상품 등록
    @PostMapping("/items")
    public CreateItemResponse createJelly (
            @RequestBody @Valid CreateItemRequest request
    ){
        Jelly jelly = new Jelly();
        jelly.createItem(request.getName(), request.getPrice(), request.getStockQuantity());
        jelly.createSignature(request.getOption1(), request.getOption2());
        Long itemId = service.create(jelly);
        return new CreateItemResponse(itemId);
    }

    @Data
    @AllArgsConstructor
    static class CreateItemResponse{
        private Long id;
    }

    @Data
    static class CreateItemRequest{
        private String name;
        private int price;
        private int stockQuantity;
        private String option1;
        private String option2;
    }

    // 상품 수정
    @PutMapping ("/items/{id}")
    public UpdateItemResponse updateItem(
            @PathVariable ("id") Long id,
            @RequestBody @Valid UpdateItemRequest request
    ){
        service.updateName(id, request.getName(), request.getStockQuantity());
        Item findItem = service.findOne(id);
        return new UpdateItemResponse(id, findItem.getName(), findItem.getStockQuantity());
    }

    @Data
    @AllArgsConstructor
    static class UpdateItemResponse{
        private Long id;
        private String name;
        private int stockQuantity;
    }

    @Data
    static class UpdateItemRequest{
        private String name;
        private int stockQuantity;
    }

    // 모든 상품 조회
    @GetMapping("/items")
    public Result findAllItems() {
        List<Item> findItems = service.findAll();
        List<ItemDto> collect = findItems.stream()
                .map(i -> new ItemDto(i.getName(), i.getPrice(), i.getStockQuantity()))
                .collect(Collectors.toList());
        return new Result("젤리 리스트", collect.size(), collect);
    }

    @Data
    @AllArgsConstructor
    static class Result <T>{
        private String info;
        private int size;
        private List<T> data;
    }

    @Data
    @AllArgsConstructor
    static class ItemDto {
        private String name;
        private int price;
        private int stockQuantity;
    }

}
