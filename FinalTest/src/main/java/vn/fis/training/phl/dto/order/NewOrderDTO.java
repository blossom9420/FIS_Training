package vn.fis.training.phl.dto.order;

import lombok.*;
import vn.fis.training.phl.dto.product.ProductQuantityDTO;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class NewOrderDTO {
    private Long customerId;
    private ArrayList<ProductQuantityDTO> orderItemInfo;
}
