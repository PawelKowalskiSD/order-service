package dev.pawel.orderservice.controller;

import dev.pawel.orderservice.controller.dto.OrderDto;
import dev.pawel.orderservice.controller.dto.SaveOrderRequest;
import dev.pawel.orderservice.domain.service.order.OrderService;
import dev.pawel.orderservice.infrastucture.mapper.DtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final DtoMapper dtoMapper;
    private final OrderService orderService;

    public OrderController(DtoMapper dtoMapper, OrderService orderService) {
        this.dtoMapper = dtoMapper;
        this.orderService = orderService;
    }

    @GetMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable String orderId) {
        return ResponseEntity.ok(dtoMapper.mapToDtoOrder(orderService.findOrderById(orderId)));
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody SaveOrderRequest saveOrderRequest) {
        return ResponseEntity.ok(dtoMapper.mapToDtoOrder(orderService.create(saveOrderRequest)));
    }

//    @PostMapping(value = "add")
//    public ResponseEntity<OrderDto> addProductToOrder(@RequestBody OrderDto orderDto) {
//        return ResponseEntity.ok(dtoMapper.mapToDtoOrder(orderService.addProduct(dtoMapper.mapToOrder(orderDto))));
//    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<OrderDto> deleteProduct(@PathVariable String productId) {
        return ResponseEntity.ok(dtoMapper.mapToDtoOrder(orderService.deleteProductFromOrderByProductId(productId)));
    }
}
