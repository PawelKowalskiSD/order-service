package dev.pawel.orderservice.infrastucture.repository.product;

import dev.pawel.orderservice.domain.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
