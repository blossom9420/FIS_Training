package vn.fis.training.phl.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.fis.training.phl.exception.*;

import java.time.LocalDateTime;

import static vn.fis.training.phl.constant.Constant.*;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {CustomerNotFoundException.class})
    protected ResponseEntity<ErrorMessage> handlerCustomerNotFoundException(CustomerNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now()).code(CUSTOMER_NOT_FOUND)
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {MobileExistedException.class})
    protected ResponseEntity<ErrorMessage> handlerMobileExistedException(MobileExistedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now()).code(MOBILE_IS_EXISTED)
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {OrderNotFoundException.class})
    protected ResponseEntity<ErrorMessage> handlerOrderNotFoundException(OrderNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now()).code(ORDER_NOT_FOUND)
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {ProductNotFoundException.class})
    protected ResponseEntity<ErrorMessage> handlerProductNotFoundException(ProductNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now()).code(PRODUCT_NOT_FOUND)
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {ProductNotEnoughtException.class})
    protected ResponseEntity<ErrorMessage> handlerProductNotEnoughtException(ProductNotEnoughtException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now()).code(PRODUCT_NOT_ENOUGHT)
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {CanNotDeleteCreatedStatusOrderException.class})
    protected ResponseEntity<ErrorMessage> handlerCanNotDeleteCreatedStatusOrderException(
            CanNotDeleteCreatedStatusOrderException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now()).code(ORDER_CAN_NOT_DELETE)
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {CanNotDeleteCancelledStatusOrderException.class})
    protected ResponseEntity<ErrorMessage> handlerCanNotDeleteCancelledStatusOrderException(
            CanNotDeleteCancelledStatusOrderException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now()).code(ORDER_CAN_NOT_DELETE)
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {CanOnlyAddOrderItemToCreatedOrderException.class})
    protected ResponseEntity<ErrorMessage> handlerCanOnlyAddOrderItemToCreatedOrderException(
            CanOnlyAddOrderItemToCreatedOrderException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now())
                        .code(CAN_ONLY_ADD_ORDER_ITEM_TO_CREATED_ORDER)
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {OrderItemNotFoundException.class})
    protected ResponseEntity<ErrorMessage> handlerOrderItemNotFoundException(OrderItemNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now()).code(ORDER_ITEM_NOT_FOUND)
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {CanOnlyRemoveOrderItemOnCreatedOrderException.class})
    protected ResponseEntity<ErrorMessage> handlerCanOnlyRemoveOrderItemOnCreatedOrderException(
            CanOnlyRemoveOrderItemOnCreatedOrderException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now())
                        .code(CAN_ONLY_REMOVE_ORDER_ITEM_ON_CREATED_ORDER)
                        .message(e.getMessage()).build());
    }

    @ExceptionHandler(value = {CanNotCancelTheOrderStatusHasAlreadyCancelException.class})
    protected ResponseEntity<ErrorMessage> handlerCanNotCancelTheOrderStatusHasAlreadyCancelException(
            CanNotCancelTheOrderStatusHasAlreadyCancelException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now())
                        .code(CANNOT_CANCEL_THE_ORDER_STATUS_HAS_NOT_CREATE)
                        .message(e.getMessage()).build());
    }


    @ExceptionHandler(value = {CanNotPaidTheOrderStatusHasAlreadyPaidException.class})
    protected ResponseEntity<ErrorMessage> handlerCanNotPaidTheOrderStatusHasAlreadyPaidException(
            CanNotPaidTheOrderStatusHasAlreadyPaidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder().timestamp(LocalDateTime.now())
                        .code(CANNOT_PAID_THE_ORDER_STATUS_HAS_NOT_CREATE)
                        .message(e.getMessage()).build());
    }
}
