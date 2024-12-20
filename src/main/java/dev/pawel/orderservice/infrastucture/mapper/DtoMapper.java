package dev.pawel.orderservice.infrastucture.mapper;

import dev.pawel.orderservice.controller.dto.ProductDto;
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
}
