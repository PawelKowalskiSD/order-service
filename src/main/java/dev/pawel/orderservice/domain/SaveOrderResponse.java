package dev.pawel.orderservice.domain;

import java.util.List;

public record SaveOrderResponse(List<ProductQuantityResponse> products) {
}
