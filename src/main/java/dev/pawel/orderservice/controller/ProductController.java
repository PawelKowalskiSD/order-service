package dev.pawel.orderservice.controller;

import dev.pawel.orderservice.controller.dto.ProductDto;
import dev.pawel.orderservice.domain.service.product.ProductService;
import dev.pawel.orderservice.infrastucture.mapper.DtoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    private final DtoMapper dtoMapper;
    private final ProductService productService;

    public ProductController(DtoMapper dtoMapper, ProductService productService) {
        this.dtoMapper = dtoMapper;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(dtoMapper.mapToDtoProductList(productService.findAllProducts()));
    }

    @GetMapping(value = "{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String productId) {
        return ResponseEntity.ok(dtoMapper.mapToProductDto(productService.findProductById(productId)));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(dtoMapper.mapToProductDto(productService.create(dtoMapper.mapToProduct(productDto))));
    }

    @PutMapping
    public ResponseEntity<ProductDto> editProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(dtoMapper.mapToProductDto(productService.upgradeProduct(dtoMapper.mapToProduct(productDto))));
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productId) {
        productService.deleteProductFromDbById(productId);
        return ResponseEntity.ok().build();
    }
}
