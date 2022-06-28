package vn.fis.training.phl.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="tbl_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "Product name must be not null")
    @Size(min = 11, max = 99, message = "Product name must be between 11 and 99 characters")
    @Column(name="name", length = 99)
    // Not null, độ dài >10 và nhỏ hơn 100 ký tự
    private String name;


    @NotNull(message = "Product price must be not null")
    @Size(min = 1, message = "Product price must be >0")
    // Not null và >0
    private Double price;


    @NotNull(message = "Product available must be not null")
    @Size(min = 0, message = "Product available must be >= 0")
    // Not null và >=0
    private Long available;
}
