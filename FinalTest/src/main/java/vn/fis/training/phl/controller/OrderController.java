package vn.fis.training.phl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.fis.training.phl.dto.order.DetailOrderDTO;
import vn.fis.training.phl.dto.order.NewOrderDTO;
import vn.fis.training.phl.dto.order.OrderDTO;
import vn.fis.training.phl.dto.orderItem.NewOrderItemDTO;
import vn.fis.training.phl.dto.orderItem.RemoveItemDTO;
import vn.fis.training.phl.model.Order;
import vn.fis.training.phl.service.OrderService;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public Page<OrderDTO> findAll(@PathVariable("pageNumber") Integer pageNumber,
                                  @PathVariable("pageSize") Integer pageSize) {
        log.info("Response All Order. PageNumber: {}, PageSize: {}", pageNumber, pageSize);
        return orderService.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<DetailOrderDTO> findById(@PathVariable("orderId") Long orderId) {
        Order order = orderService.findById(orderId);
        DetailOrderDTO detailOrderDTO = DetailOrderDTO.Mapper.mapFromOrderEntity(order);
        log.info("DetailOrder with id {}: {}", orderId,detailOrderDTO);
        return new ResponseEntity<>(detailOrderDTO, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<DetailOrderDTO> create(@RequestBody NewOrderDTO newOrderDTO) {
        log.info("Create New OrderDTO: {}", newOrderDTO);
        Order savedOrder = orderService.create(newOrderDTO);
        DetailOrderDTO detailOrderDTO = DetailOrderDTO.Mapper.mapFromOrderEntity(savedOrder);
        log.info("Saved Detail Order: {}", detailOrderDTO);
        return new ResponseEntity<>(detailOrderDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> delete(@PathVariable("orderId") Long orderId) {
        orderService.delete(orderId);
        return new ResponseEntity<>("Order deleted successfully!", HttpStatus.OK);
    }

    @PostMapping("/addOrderItem")
    public DetailOrderDTO addOrderItem(@RequestBody NewOrderItemDTO newOrderItemDTO) {
        log.info("Order Item: {}", newOrderItemDTO);
        Order order = orderService.addOrderItem(newOrderItemDTO);
        log.info("Order (Added new OrderItem): {}", order);
        return DetailOrderDTO.Mapper.mapFromOrderEntity(order);
    }

    @PostMapping("/removeOrderItem")
    public DetailOrderDTO removeOrderItem(@RequestBody RemoveItemDTO removeItemDTO) {
        log.info("Remove Item: {}", removeItemDTO);
        Order order = orderService.removeOrderItem(removeItemDTO);
        log.info("Order (Removed): {}", order);
        return DetailOrderDTO.Mapper.mapFromOrderEntity(order);
    }

    @PostMapping("/paid/{orderId}")
    public DetailOrderDTO paid(@PathVariable("orderId") Long orderId) {
        Order order =  orderService.paid(orderId);
        log.info("Order (paid): {}", order);
        return DetailOrderDTO.Mapper.mapFromOrderEntity(order);
    }

    @PostMapping("/cancel/{orderId}")
    public DetailOrderDTO cancel(@PathVariable("orderId") Long orderId) {
        Order order =  orderService.cancel(orderId);
        log.info("Order (cancel): {}", order);
        return DetailOrderDTO.Mapper.mapFromOrderEntity(order);
    }
}
