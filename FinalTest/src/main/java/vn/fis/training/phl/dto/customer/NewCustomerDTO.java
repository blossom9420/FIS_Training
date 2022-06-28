package vn.fis.training.phl.dto.customer;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class NewCustomerDTO {
    private String name;
    private String mobile;
    private String address;
}
