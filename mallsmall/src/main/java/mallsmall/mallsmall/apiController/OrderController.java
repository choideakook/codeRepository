package mallsmall.mallsmall.apiController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import mallsmall.mallsmall.domain.Order;
import mallsmall.mallsmall.domain.item.Item;
import mallsmall.mallsmall.service.ItemService;
import mallsmall.mallsmall.service.MemberService;
import mallsmall.mallsmall.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @PostMapping("/orders")
    public CreateOrderResponse createOrder(
            @RequestBody @Valid CreateOrderRequest request
    ) {
        Long orderId = orderService.createOrder
                (request.getMember_id(), request.getItem_id(), request.getHow_many());
        Order findOrder = orderService.findOne(orderId);
        Item findItem = itemService.findOne(request.getItem_id());
        return new CreateOrderResponse(
                orderId, findOrder.getMember().getName(), findItem.getName(), findOrder.getTotalPrice());
    }

    @Data
    @AllArgsConstructor
    static class CreateOrderResponse{
        private Long id;
        private String member_name;
        private String item_name;
        private int total_price;
    }

    @Data
    static class CreateOrderRequest{
        private Long member_id;
        private Long item_id;
        private int how_many;
    }
}
