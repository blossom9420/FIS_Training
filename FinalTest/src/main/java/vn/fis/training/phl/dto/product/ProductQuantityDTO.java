package vn.fis.training.phl.dto.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductQuantityDTO {
    private Long productId;
    private Long quantity;
}
