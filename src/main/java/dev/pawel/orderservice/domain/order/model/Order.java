package dev.pawel.orderservice.domain.order.model;

import dev.pawel.orderservice.domain.product.model.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String orderNumber;
    private long piece;
    private BigDecimal totalCost;
    @OneToMany
    private List<Product> products = new ArrayList<>();

    public Order(String orderNumber, long piece, BigDecimal totalCost, List<Product> products) {
        this.orderNumber = orderNumber;
        this.piece = piece;
        this.totalCost = totalCost;
        this.products = products;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public long getPiece() {
        return piece;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public List<Product> getProducts() {
        return products;
    }
}
