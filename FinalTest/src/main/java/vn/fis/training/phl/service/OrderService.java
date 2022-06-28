package vn.fis.training.phl.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.fis.training.phl.dto.order.NewOrderDTO;
import vn.fis.training.phl.dto.order.OrderDTO;
import vn.fis.training.phl.dto.orderItem.NewOrderItemDTO;
import vn.fis.training.phl.dto.orderItem.RemoveItemDTO;
import vn.fis.training.phl.model.Order;

public interface OrderService {
    Page<OrderDTO> findAll(Pageable pageable);
    Order findById(Long orderId);
    Order create(NewOrderDTO createOrderDTO);
    void delete(Long orderId);
    Order addOrderItem(NewOrderItemDTO newOrderItemDTO);

    Order removeOrderItem(RemoveItemDTO removeItemDTO);

    Order paid(Long orderId);

    Order cancel(Long orderId);
}
