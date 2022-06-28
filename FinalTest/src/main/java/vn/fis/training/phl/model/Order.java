package vn.fis.training.phl.model;

import lombok.*;
import vn.fis.training.phl.model.enums.OrderStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_datetime")
    @NotNull
    // Not null, bằng thời gian tạo order
    private LocalDateTime orderDateTime;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @ToString.Exclude
    private Customer customer;


    //    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<OrderItem> orderItems;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "status")
    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    public Double auto(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(x -> x.getAmount())
                .reduce((double) 0, (a, b) -> a + b);
    }

}
