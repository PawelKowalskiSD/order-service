package dev.pawel.orderservice.controller;

import dev.pawel.orderservice.controller.dto.OrderDto;
import dev.pawel.orderservice.controller.dto.SaveOrderRequest;
import dev.pawel.orderservice.domain.service.order.OrderService;
import dev.pawel.orderservice.infrastucture.mapper.DtoMapper;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final DtoMapper dtoMapper;
    private final OrderService orderService;
    private final ConversionService conversionService;

    public OrderController(DtoMapper dtoMapper, OrderService orderService, ConversionService conversionService) {
        this.dtoMapper = dtoMapper;
        this.orderService = orderService;
        this.conversionService = conversionService;
    }

    @GetMapping(value = "{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable String orderId) {
        return ResponseEntity.ok(dtoMapper.mapToDtoOrder(orderService.findOrderById(orderId)));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> findAllOrder() {
        return ResponseEntity.ok(dtoMapper.mapToListOrderDto(orderService.findAllOrders()));
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody SaveOrderRequest saveOrderRequest) {
        return ResponseEntity.ok(conversionService.convert(orderService.create(saveOrderRequest), OrderDto.class));
    }

}
