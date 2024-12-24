package dev.pawel.orderservice.domain.service.product;

import dev.pawel.orderservice.domain.product.model.Product;
import dev.pawel.orderservice.infrastucture.repository.product.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public Product upgradeProduct(Product product) {
        if(product.getName() != null)
            return productRepository.save(product.getName())
        return ;
    }

    public Product findProductById(String productId) {
        return productRepository.findById(productId).orElseThrow();
    }
}
