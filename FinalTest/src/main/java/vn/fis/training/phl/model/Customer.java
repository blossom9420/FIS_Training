package vn.fis.training.phl.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@Table(name="tbl_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    // Not null, độ dài >10 và nhỏ hơn 100 ký tự
    @NotNull(message = "Customer name must be not null")
    @Size(min = 11, max = 99, message = "CustomerName must be between 11 and 99 characters")
    @Column(name="name", length = 99)
    private String name;

    // Not null và định dạng đúng chuẩn 10 số của Việt Nam VD (0912345678).
    // Unique của bảng
    @NotNull(message = "Customer mobile must be not null")
    @Column(name="mobile", length = 10, unique=true)
    private String mobile;

    // Địa chỉ của khách hàng. Not null, độ dài 10 – 100 ký tự
    @NotNull(message = "Customer address must be not null")
    @Size(min = 10, max = 100, message = "CustomerAddress must be between 10 and 100 characters")
    @Column(name="address", length = 100)
    private String address;

    // List<Orders> : Danh sách orders của khách hàng, có thể null
    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private List<Order> orders;
}
