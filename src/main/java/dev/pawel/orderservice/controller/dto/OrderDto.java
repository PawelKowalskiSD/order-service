package dev.pawel.orderservice.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record OrderDto(String id, LocalDate dateOfCreatedOrder, String orderNumber, long piece, BigDecimal totalCost) {
}
