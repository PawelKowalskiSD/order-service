package dev.pawel.orderservice.infrastucture.mapper;

import dev.pawel.orderservice.controller.dto.OrderDto;
import dev.pawel.orderservice.domain.order.model.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderToOrderDto implements Converter<Order, OrderDto> {
    @Override
    public OrderDto convert(Order order) {
        return new OrderDto(
                order.getId(),
                order.getDateOfCreatedOrder(),
                order.getOrderNumber(),
                order.getTotalCost(),
                order.getProductQuantities()
        );
    }
}
