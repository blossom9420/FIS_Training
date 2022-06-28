package vn.fis.training.phl.dto.order;

import lombok.*;
import vn.fis.training.phl.dto.customer.CustomerDTO;
import vn.fis.training.phl.dto.orderItem.OrderItemDTO;
import vn.fis.training.phl.model.Order;
import vn.fis.training.phl.model.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DetailOrderDTO {
    private Long id;

    private LocalDateTime orderDateTime;

    private CustomerDTO customerDTO;

    private Double totalAmount;

    private List<OrderItemDTO> orderItemDTOs;

    private OrderStatus status;

    public static class Mapper {
        public static DetailOrderDTO mapFromOrderEntity(Order order) {
            return DetailOrderDTO.builder()
                    .id(order.getId())
                    .orderDateTime(order.getOrderDateTime())
                    .customerDTO(CustomerDTO.Mapper.mapFromCustomerEntity(order.getCustomer()))
                    .totalAmount(order.getTotalAmount())
                    .orderItemDTOs(order.getOrderItems().stream()
                            .map(OrderItemDTO.Mapper::mapFromOrderItemEntity)
                            .collect(Collectors.toList()))
                    .status(order.getStatus())
                    .build();
        }
    }
}
