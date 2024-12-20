package dev.pawel.orderservice.controller;

import dev.pawel.orderservice.controller.dto.OrderDto;
import dev.pawel.orderservice.domain.service.order.OrderService;
import dev.pawel.orderservice.infrastucture.mapper.DtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
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
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(dtoMapper.mapToDtoOrder(orderService.create(dtoMapper.mapToOrder(orderDto))));
    }
}
