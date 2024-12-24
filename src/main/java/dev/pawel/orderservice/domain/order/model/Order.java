package dev.pawel.orderservice.domain.order.model;

import dev.pawel.orderservice.domain.product.model.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Order {
    @Id
    private String id;
    private LocalDate dateOfCreatedOrder;
    private String orderNumber;
    private long piece;
    private BigDecimal totalCost;


    public Order(LocalDate dateOfCreatedOrder, String orderNumber, long piece, BigDecimal totalCost) {
        this.dateOfCreatedOrder = dateOfCreatedOrder;
        this.orderNumber = orderNumber;
        this.piece = piece;
        this.totalCost = totalCost;
    }

    public Order(String id, LocalDate dateOfCreatedOrder, String orderNumber, long piece, BigDecimal totalCost) {
        this.id = id;
        this.dateOfCreatedOrder = dateOfCreatedOrder;
        this.orderNumber = orderNumber;
        this.piece = piece;
        this.totalCost = totalCost;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public LocalDate getDateOfCreatedOrder() {
        return dateOfCreatedOrder;
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
}
