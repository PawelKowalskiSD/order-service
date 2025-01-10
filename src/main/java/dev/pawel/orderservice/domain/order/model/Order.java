package dev.pawel.orderservice.domain.order.model;

import dev.pawel.orderservice.domain.ProductQuantity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Instant dateOfCreatedOrder;
    private String orderNumber;
    private BigDecimal totalCost;
    @ElementCollection
    private List<ProductQuantity> productQuantities;

    public Order() {
    }

    public Order(String id, Instant dateOfCreatedOrder, String orderNumber, BigDecimal totalCost, List<ProductQuantity> productQuantities) {
        this.id = id;
        this.dateOfCreatedOrder = dateOfCreatedOrder;
        this.orderNumber = orderNumber;
        this.totalCost = totalCost;
        this.productQuantities = productQuantities;
    }

    public Order(Instant dateOfCreatedOrder, String orderNumber, BigDecimal totalCost, List<ProductQuantity> productQuantities) {
        this.dateOfCreatedOrder = dateOfCreatedOrder;
        this.orderNumber = orderNumber;
        this.totalCost = totalCost;
        this.productQuantities = productQuantities;
    }

    public void setDateOfCreatedOrder(Instant dateOfCreatedOrder) {
        this.dateOfCreatedOrder = dateOfCreatedOrder;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public void setProductQuantities(List<ProductQuantity> productQuantities) {
        this.productQuantities = productQuantities;
    }

    public List<ProductQuantity> getProductQuantities() {
        return productQuantities;
    }

    public String getId() {
        return id;
    }

    public Instant getDateOfCreatedOrder() {
        return dateOfCreatedOrder;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(dateOfCreatedOrder, order.dateOfCreatedOrder) && Objects.equals(orderNumber, order.orderNumber) && Objects.equals(totalCost, order.totalCost) && Objects.equals(productQuantities, order.productQuantities);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(dateOfCreatedOrder);
        result = 31 * result + Objects.hashCode(orderNumber);
        result = 31 * result + Objects.hashCode(totalCost);
        result = 31 * result + Objects.hashCode(productQuantities);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", dateOfCreatedOrder=" + dateOfCreatedOrder +
                ", orderNumber='" + orderNumber + '\'' +
                ", totalCost=" + totalCost +
                ", productQuantities=" + productQuantities +
                '}';
    }
}
