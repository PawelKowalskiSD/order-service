package dev.pawel.orderservice.infrastucture.mapper;

import dev.pawel.orderservice.controller.dto.OrderDto;
import dev.pawel.orderservice.controller.dto.ProductDto;
import dev.pawel.orderservice.domain.order.model.Order;
import dev.pawel.orderservice.domain.product.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DtoMapper {
    public List<ProductDto> mapToDtoProductList(List<Product> allProducts) {
        return allProducts.stream()
                .map(p -> new ProductDto(
                        p.getId(),
                        p.getName(),
                        p.getPrice()
                )).toList();
    }

    public Product mapToProduct(ProductDto productDto) {
        return new Product(
                productDto.name(),
                productDto.price());
    }

    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice());
    }

    public OrderDto mapToDtoOrder(Order order) {
        return new OrderDto(
                order.getId(),
                order.getOrderNumber(),
                order.getPiece(),
                order.getTotalCost(),
                mapToDtoProductList(order.getProducts()));
    }

    public Order mapToOrder(OrderDto orderDto) {
        return new Order(
                orderDto.orderNumber(),
                orderDto.piece(),
                orderDto.totalCost(),
                mapToProductList(orderDto.productsDto()));
    }

    private List<Product> mapToProductList(List<ProductDto> products) {
        return products.stream()
                .map(p -> new Product(
                        p.name(),
                        p.price()
                )).toList();
    }
}
