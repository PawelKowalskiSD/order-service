package dev.pawel.orderservice.controller.dto;

import java.util.List;

public record SaveOrderRequest(List<ProductQuantityRequest> products) {
}
