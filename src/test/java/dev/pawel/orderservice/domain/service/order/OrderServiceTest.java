package dev.pawel.orderservice.domain.service.order;

import dev.pawel.orderservice.controller.dto.ProductQuantityRequest;
import dev.pawel.orderservice.controller.dto.SaveOrderRequest;
import dev.pawel.orderservice.domain.ProductQuantity;
import dev.pawel.orderservice.domain.order.model.Order;
import dev.pawel.orderservice.domain.product.model.Product;
import dev.pawel.orderservice.infrastucture.repository.order.OrderRepository;
import dev.pawel.orderservice.infrastucture.repository.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    private final Clock clock = Clock.system(ZoneId.of("Europe/Warsaw"));

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository, clock, productRepository);
    }

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
        assertEquals("1", result.getId());
        assertEquals("111230", result.getOrderNumber());
        assertEquals(new BigDecimal("24"), result.getTotalCost());
        assertEquals(1, result.getProductQuantities().size());
        verify(orderRepository, times(1)).findById(result.getId());
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
        assertEquals("1", result.getFirst().getId());
        assertEquals("111230", result.getFirst().getOrderNumber());
        assertEquals(new BigDecimal("24"), result.getFirst().getTotalCost());
        assertEquals(2, result.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void shouldCreateOrder() {
        //Given
        List<ProductQuantity> productsQuantity = new ArrayList<>();
        Product bag = new Product("1", "bag", new BigDecimal("12"));
        productsQuantity.add(new ProductQuantity(bag, 2));

        List<ProductQuantityRequest> productsQuantityRequest = new ArrayList<>();
        productsQuantityRequest.add(new ProductQuantityRequest("1", 2));

        SaveOrderRequest saveOrder = new SaveOrderRequest(productsQuantityRequest);
        Order firstOrder = new Order("1", clock.instant(), "111230", new BigDecimal("24"), productsQuantity);

        when(productRepository.findById(bag.getId())).thenReturn(Optional.of(bag));
        when(orderRepository.save(any(Order.class))).thenReturn(firstOrder);
        //When
        Order result = orderService.create(saveOrder);
        //Then
        assertEquals("1", result.getId());
        assertEquals("111230", result.getOrderNumber());
        assertEquals(new BigDecimal("24"), result.getTotalCost());
        assertEquals(1, result.getProductQuantities().size());
        verify(productRepository, times(1)).findById(bag.getId());
        verify(orderRepository, times(1)).save(any(Order.class));
        System.out.println(result);
    }
}