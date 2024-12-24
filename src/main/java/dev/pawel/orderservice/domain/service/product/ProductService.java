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
        return productRepository.save(product);
    }

    public void deleteProductFromDbById(String productId) {
        productRepository.deleteById(productId);
    }

    public Product upgradeProduct(String productId, Product product) {
        Product productInDatabase = findProductById(productId);
        String name = product.getName() != null ? product.getName() : productInDatabase.getName();
        BigDecimal price = product.getPrice() != null ? product.getPrice() : productInDatabase.getPrice();
        Product upgradedProduct = new Product(productId, name, price);
        return productRepository.save(upgradedProduct);
    }

    public Product findProductById(String productId) {
        return productRepository.findById(productId).orElseThrow();
    }
}
