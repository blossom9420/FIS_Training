package vn.fis.training.phl.dto.product;

import lombok.*;
import vn.fis.training.phl.model.Product;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDTO {
    private Long id;

    private String name;

    private Double price;

    private Long available;

    public static class Mapper {
        public static ProductDTO mapFromProductEntity(Product product) {
            return ProductDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .available(product.getAvailable())
                    .build();
        }
    }
}
