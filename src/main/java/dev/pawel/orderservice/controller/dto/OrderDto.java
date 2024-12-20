package dev.pawel.orderservice.controller.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderDto(String id, String orderNumber, long piece, BigDecimal totalCost, List<ProductDto> productsDto) {
}
