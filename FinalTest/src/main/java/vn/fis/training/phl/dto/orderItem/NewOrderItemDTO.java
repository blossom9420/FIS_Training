package vn.fis.training.phl.dto.orderItem;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class NewOrderItemDTO {
    private Long orderId;
    private Long productId;
    private Long quantity;
}
