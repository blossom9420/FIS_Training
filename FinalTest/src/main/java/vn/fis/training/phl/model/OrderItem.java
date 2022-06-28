package vn.fis.training.phl.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_order_item")
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", nullable = false)
    @ToString.Exclude
    //@JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id")
    @ToString.Exclude
    private Product product;

    @Column(name="quantity",nullable = false)
    private Long quantity;

    @Column(name="amount",nullable = false)
    private Double amount;

//    public OrderItem() {
//    }
//
//    public OrderItem(Long id, Order order, Product product, Long quantity) {
//        this.id = id;
//        this.order = order;
//        this.product = product;
//        this.quantity = quantity;
//        this.amount = (double) quantity * product.getPrice();
//    }

    public double totalPrice(Long sl, Long price ){
        return (double) sl * price;
    }

}
