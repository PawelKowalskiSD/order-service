package dev.pawel.orderservice.controller.dto;

import java.math.BigDecimal;

public record ProductDto(String id, String name, BigDecimal price) {
}
