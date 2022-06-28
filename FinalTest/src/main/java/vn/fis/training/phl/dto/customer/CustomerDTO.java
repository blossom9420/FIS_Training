package vn.fis.training.phl.dto.customer;

import lombok.*;
import vn.fis.training.phl.model.Customer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CustomerDTO {
    private Long id;
    private String name;
    private String mobile;
    private String address;

    public static class Mapper {
        public static CustomerDTO mapFromCustomerEntity(Customer customer) {
            return CustomerDTO.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .mobile(customer.getMobile())
                    .address(customer.getAddress())
                    .build();
        }
    }
}
