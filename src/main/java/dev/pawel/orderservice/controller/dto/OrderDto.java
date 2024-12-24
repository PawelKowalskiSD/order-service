package dev.pawel.orderservice.controller.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record OrderDto(String id, Date dateOfCreatedOrder, String orderNumber, long piece, BigDecimal totalCost, List<ProductDto> productsDto) {
}
