package dev.pawel.orderservice.controller.dto;

import dev.pawel.orderservice.domain.ProductQuantity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public record OrderDto(String id, Instant dateOfCreatedOrder, String orderNumber, BigDecimal totalCost, List<ProductQuantity> products) {
}
