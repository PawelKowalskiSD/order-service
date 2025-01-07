package dev.pawel.orderservice.domain.service.order;

import dev.pawel.orderservice.controller.dto.ProductQuantityRequest;
import dev.pawel.orderservice.controller.dto.SaveOrderRequest;
import dev.pawel.orderservice.domain.ProductQuantity;
import dev.pawel.orderservice.domain.order.model.Order;
import dev.pawel.orderservice.domain.product.model.Product;
import dev.pawel.orderservice.infrastucture.repository.order.OrderRepository;
import dev.pawel.orderservice.infrastucture.repository.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Clock clock;

    @Test
    void shouldFindOrderById() {
        //Given
        List<ProductQuantity> products = new ArrayList<>();
        products.add(new ProductQuantity(new Product("1", "bag", new BigDecimal("12")), 2));
        Order order1 = new Order("1", clock.instant(), "111230", new BigDecimal("24"), products);
        when(orderRepository.findById(order1.getId())).thenReturn(Optional.of(order1)).thenThrow();
        //When
        Order result = orderService.findOrderById(order1.getId());
        //Then
        verify(orderRepository, times(1)).findById(result.getId());
        verify(clock, times(1)).instant();
    }

    @Test
    void shouldFindAllOrders() {
        //Given
        List<ProductQuantity> orderedProductsToFirstOrder = new ArrayList<>();
        orderedProductsToFirstOrder.add(
                new ProductQuantity(
                        new Product(
                                "1",
                                "Bag",
                                new BigDecimal("12")
                        ),
                        2
                )
        );
        Order firstOrder = new Order(
                "1",
                clock.instant(),
                "111230",
                new BigDecimal("24"),
                orderedProductsToFirstOrder
        );
        List<ProductQuantity> orderedProductsToSecondOrder = new ArrayList<>();
        orderedProductsToSecondOrder.add(
                new ProductQuantity(
                        new Product(
                                "2",
                                "Ticket",
                                new BigDecimal("41")
                        ),
                        2
                )
        );
        Order secondOrder = new Order(
                "2",
                clock.instant(),
                "213230",
                new BigDecimal("82"),
                orderedProductsToSecondOrder
        );
        when(orderRepository.findAll()).thenReturn(List.of(firstOrder, secondOrder));
        //When
        List<Order> result = orderService.findAllOrders();
        //Then
        assertEquals(2, result.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void shouldCreateOrder() {
        //Given
        List<ProductQuantityRequest> products = new ArrayList<>();
        Product bag = new Product("1", "bag", new BigDecimal("12"));
        SaveOrderRequest saveOrder = new SaveOrderRequest(products);
        products.add(new ProductQuantityRequest("1", 2));
//        Order order1 = new Order("1", clock.instant(), "111230", new BigDecimal("24"), products);
        when(productRepository.findById(bag.getId())).thenReturn(Optional.of(bag));
//        when(orderRepository.save(order1)).thenReturn(order1);
        //When
        Order result = orderService.create(saveOrder);
        //Then
        verify(productRepository, times(1)).findById(bag.getId());
        verify(orderRepository, times(1)).save(result);
    }
}