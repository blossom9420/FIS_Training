package vn.fis.training.phl.dto.orderItem;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RemoveItemDTO {
    private Long orderId;
    private Long orderItemId;
}
