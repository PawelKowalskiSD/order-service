package dev.pawel.orderservice.domain.service.product;

import dev.pawel.orderservice.domain.product.model.Product;
import dev.pawel.orderservice.infrastucture.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product create(Product product) {
        if(product.getName().trim().length() < 3)
            throw new RuntimeException("Too short product name: " + product.getName());
        if(product.getPrice().compareTo(BigDecimal.ZERO) < 1 || product.getPrice().toString().split("\\.")[1].length() > 2)
            throw new RuntimeException("Invalid product price: " + product.getPrice());
        return productRepository.save(product);
    }

    public void deleteProductFromDbById(String productId) {
        productRepository.deleteById(productId);
    }

    public Product updateProduct(String productId, Product product) {
        Product productInDatabase = productRepository.findById(productId).orElseThrow();
        String name = product.getName() != null ? product.getName() : productInDatabase.getName();
        BigDecimal price = product.getPrice() != null ? product.getPrice() : productInDatabase.getPrice();
        Product updatedProduct = new Product(productId, name, price);
        return productRepository.save(updatedProduct);
    }

    public Product findProductById(String productId) {
        return productRepository.findById(productId).orElseThrow();
    }
}