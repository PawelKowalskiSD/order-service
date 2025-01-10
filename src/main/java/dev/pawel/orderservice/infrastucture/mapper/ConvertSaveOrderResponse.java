package dev.pawel.orderservice.infrastucture.mapper;

import dev.pawel.orderservice.controller.dto.SaveOrderRequest;
import dev.pawel.orderservice.domain.ProductQuantityResponse;
import dev.pawel.orderservice.domain.SaveOrderResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConvertSaveOrderResponse implements Converter<SaveOrderRequest, SaveOrderResponse> {
    @Override
    public SaveOrderResponse convert(SaveOrderRequest saveOrderRequest) {
        return new SaveOrderResponse(saveOrderRequest.products().stream()
                .map(productQuantityRequest ->
                        new ProductQuantityResponse(
                                productQuantityRequest.id(),
                                productQuantityRequest.quantity()
                        )
                )
                .toList()
        );
    }
}
