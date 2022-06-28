package vn.fis.training.phl.dto.orderItem;

import lombok.*;
import vn.fis.training.phl.dto.product.ProductDTO;
import vn.fis.training.phl.model.OrderItem;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderItemDTO {
    private Long id;

    private ProductDTO productDTO;

    private Long quantity;

    private Double amount;

    public static class Mapper {
        public static OrderItemDTO mapFromOrderItemEntity(OrderItem orderItem) {
            return OrderItemDTO.builder()
                    .id(orderItem.getId())
                    .productDTO(ProductDTO.Mapper.mapFromProductEntity(orderItem.getProduct()))
                    .quantity(orderItem.getQuantity())
                    .amount(orderItem.getAmount())
                    .build();
        }
    }
}
