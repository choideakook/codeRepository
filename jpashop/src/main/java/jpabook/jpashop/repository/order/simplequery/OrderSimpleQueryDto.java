package jpabook.jpashop.repository.order.simplequery;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

    @Data
    public class OrderSimpleQueryDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;

        private Address address;

        // 필드값을 주입하기 위한 생성자 생성
        public OrderSimpleQueryDto(Long id, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
            this.orderId = id;
            this.name = name;
            this.orderDate = orderDate;
            this.orderStatus = orderStatus;
            this.address = address;
        }

    }