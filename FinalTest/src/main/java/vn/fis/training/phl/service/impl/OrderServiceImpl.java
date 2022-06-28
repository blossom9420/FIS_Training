package vn.fis.training.phl.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.fis.training.phl.dto.order.NewOrderDTO;
import vn.fis.training.phl.dto.order.OrderDTO;
import vn.fis.training.phl.dto.orderItem.NewOrderItemDTO;
import vn.fis.training.phl.dto.orderItem.RemoveItemDTO;
import vn.fis.training.phl.exception.*;
import vn.fis.training.phl.model.Customer;
import vn.fis.training.phl.model.Order;
import vn.fis.training.phl.model.OrderItem;
import vn.fis.training.phl.model.Product;
import vn.fis.training.phl.model.enums.OrderStatus;
import vn.fis.training.phl.repository.OrderItemRepository;
import vn.fis.training.phl.repository.OrderRepoitory;
import vn.fis.training.phl.service.CustomerService;
import vn.fis.training.phl.service.OrderService;
import vn.fis.training.phl.service.ProductService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepoitory orderRepoitory;
    private final CustomerService customerService;

    private final ProductService productService;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderServiceImpl(OrderRepoitory orderRepoitory, CustomerService customerService,
                            ProductService productService) {
        this.orderRepoitory = orderRepoitory;
        this.customerService = customerService;
        this.productService = productService;
    }

    @Override
    public Page<OrderDTO> findAll(Pageable pageable) {
        log.info("Query all order: PageNumber: {}, PageSize: {}", pageable.getPageNumber(), pageable.getPageSize());
        return orderRepoitory.findAll(pageable).map(OrderDTO.Mapper::mapFromOrderEntity);
    }

    @Override
    public Order findById(Long orderId) {
        Order order = orderRepoitory.findById(orderId).orElseThrow(() -> {
            try {
                throw new OrderNotFoundException(String.format("id Order %s Not Found", orderId));
            } catch (OrderNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Service: Order: {}", order);
        return order;
    }

    @Override
    public Order create(NewOrderDTO createOrderDTO) {
        Customer customer = customerService.findById(createOrderDTO.getCustomerId());
        List<OrderItem> orderItemList = new ArrayList<>();
        createOrderDTO.getOrderItemInfo().forEach(productQuantityDTO -> {
            Product product = productService.findById(productQuantityDTO.getProductId());
            if (product.getAvailable() < productQuantityDTO.getQuantity()) {
                try {
                    throw new ProductNotEnoughtException(String.format("Số lượng tồn: %s không đủ !!", product.getName()));
                } catch (ProductNotEnoughtException e) {
                    throw new RuntimeException(e);
                }
            }
            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(productQuantityDTO.getQuantity())
                    .amount(product.getPrice() * productQuantityDTO.getQuantity())
                    .build();
            orderItemList.add(orderItem);
        });
        Order order = Order.builder()
                .orderDateTime(LocalDateTime.now())
                .status(OrderStatus.CREATED)
                .customer(customer)
                .orderItems(orderItemList)
                .totalAmount(orderItemList.stream().mapToDouble(OrderItem::getAmount).sum())
                .build();
        orderRepoitory.save(order);
        return order;
    }

    @Override
    public void delete(Long orderId) {
        Order order = findById(orderId);
        if (OrderStatus.CREATED.equals(order.getStatus())) {
            try {
                throw new CanNotDeleteCreatedStatusOrderException(
                        "Can not delete Order has status is CREATED!");
            } catch (CanNotDeleteCreatedStatusOrderException e) {
                throw new RuntimeException(e);
            }
        }
        if (OrderStatus.CANCELLED.equals(order.getStatus())) {
            try {
                throw new CanNotDeleteCancelledStatusOrderException(
                        "This status order has already CANCELLED!");
            } catch (CanNotDeleteCancelledStatusOrderException e) {
                throw new RuntimeException(e);
            }
        }
        orderRepoitory.deleteById(orderId);
    }

    @Override
    public Order addOrderItem(NewOrderItemDTO addOrderItemDTO) {
        Order order = findById(addOrderItemDTO.getOrderId());
        if (null == order) {
            try {
                throw new OrderNotFoundException(String.format("id Order %s Not Found", addOrderItemDTO.getOrderId()));
            } catch (OrderNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if (!OrderStatus.CREATED.equals(order.getStatus())) {
            try {
                throw new CanOnlyAddOrderItemToCreatedOrderException("This order status must be CREATED!");
            } catch (CanOnlyAddOrderItemToCreatedOrderException e) {
                throw new RuntimeException(e);
            }
        }
        Product product = productService.findById(addOrderItemDTO.getProductId());
        if (product.getAvailable() < addOrderItemDTO.getQuantity()) {
            try {
                throw new ProductNotEnoughtException(String.format(" Số lượng tồn: %s không đủ !!", product.getName()));
            } catch (ProductNotEnoughtException e) {
                throw new RuntimeException(e);
            }
        }
        OrderItem newOrderItem = OrderItem.builder()
                .amount( product.getPrice() * addOrderItemDTO.getQuantity())
                .quantity(addOrderItemDTO.getQuantity())
                .order(order)
                .product(product)
                .build();
        order.setTotalAmount(order.getTotalAmount() + newOrderItem.getAmount());
        order.getOrderItems().add(newOrderItem);
        orderRepoitory.save(order);
        product.setAvailable(product.getAvailable() - addOrderItemDTO.getQuantity());
        productService.update(product);
        return findById(addOrderItemDTO.getOrderId());
    }

    @Override
    public Order removeOrderItem(RemoveItemDTO removeItemDTO) {
        Order order = findById(removeItemDTO.getOrderId());
        if (null == order) {
            try {
                throw new OrderNotFoundException(String.format(" id Order %s Not Found ", removeItemDTO.getOrderId()));
            } catch (OrderNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if (!OrderStatus.CREATED.equals(order.getStatus())) {
            try {
                throw new CanOnlyRemoveOrderItemOnCreatedOrderException(
                        "This order status must be CREATED!");
            } catch (CanOnlyRemoveOrderItemOnCreatedOrderException e) {
                throw new RuntimeException(e);
            }
        }
        OrderItem orderItem = orderItemRepository.findById(removeItemDTO.getOrderItemId()).orElseThrow(() -> {
            try {
                throw new OrderItemNotFoundException(String.format(" id Order Item %s Not Found",
                        removeItemDTO.getOrderItemId()));
            } catch (OrderItemNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        order.setTotalAmount(order.getTotalAmount() - orderItem.getAmount());
        Product product = productService.findById(orderItem.getProduct().getId());
        product.setAvailable(product.getAvailable() + orderItem.getQuantity());
        orderItemRepository.deleteById(removeItemDTO.getOrderItemId());

        return findById(removeItemDTO.getOrderId());
    }

    @Override
    public Order paid(Long orderId) {
        Order order = findById(orderId);
        if (null == order) {
            try {
                throw new OrderNotFoundException(String.format(" id Order %s Not Found ", orderId));
            } catch (OrderNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if (!OrderStatus.CREATED.equals(order.getStatus())) {
            try {
                throw new CanNotPaidTheOrderStatusHasAlreadyPaidException(
                        "This order status must be CREATED!");
            } catch (CanNotPaidTheOrderStatusHasAlreadyPaidException e) {
                throw new RuntimeException(e);
            }
        }
        order.setStatus(OrderStatus.PAID);

        orderRepoitory.save(order);

        return findById(order.getId());
    }

    @Override
    public Order cancel(Long orderId) {
        Order order = findById(orderId);
        if (null == order) {
            try {
                throw new OrderNotFoundException(String.format(" id Order %s Not Found ", orderId));
            } catch (OrderNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if (!OrderStatus.CREATED.equals(order.getStatus())) {
            try {
                throw new CanNotCancelTheOrderStatusHasAlreadyCancelException(
                        "This order status must be CREATED!");
            } catch (CanNotCancelTheOrderStatusHasAlreadyCancelException e) {
                throw new RuntimeException(e);
            }
        }
        order.setStatus(OrderStatus.CANCELLED);

        orderRepoitory.save(order);

        return findById(order.getId());
    }


}
