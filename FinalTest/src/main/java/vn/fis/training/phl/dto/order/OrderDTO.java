package vn.fis.training.phl.dto.order;

import lombok.*;
import vn.fis.training.phl.model.Order;
import vn.fis.training.phl.model.enums.OrderStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderDTO {

    private Long id;

    private LocalDateTime orderDateTime;

    private Long customerId;

    private Double totalAmount;

    private OrderStatus status;

    public static class Mapper {
        public static OrderDTO mapFromOrderEntity(Order order) {
            return OrderDTO.builder()
                    .id(order.getId())
                    .customerId(order.getCustomer().getId())
                    .orderDateTime(order.getOrderDateTime())
                    .status(order.getStatus())
                    .totalAmount(order.getTotalAmount())
                    .build();
        }
    }
}
