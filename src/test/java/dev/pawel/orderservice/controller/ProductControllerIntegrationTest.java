package dev.pawel.orderservice.controller;

import dev.pawel.orderservice.controller.dto.ProductDto;
import dev.pawel.orderservice.domain.product.model.Product;
import dev.pawel.orderservice.infrastucture.repository.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ProductControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldCreateProduct() {
        //Given
        ProductDto productDto = new ProductDto(null, "pen", new BigDecimal("10.1"));
        //When
        webTestClient.post()
                .uri("/products")
                .bodyValue(productDto)
                .exchange().expectStatus().is2xxSuccessful();
        //Then
        List<Product> products = productRepository.findAll();
        System.out.println(products);
        assertEquals(1, products.size());
    }

    @Test
    void shouldNotCreateProduct() {
        //Given
        ProductDto productDto = new ProductDto(null, "pe", new BigDecimal("10.1"));
        //When & Then
        webTestClient.post()
                .uri("/products")
                .bodyValue(productDto)
                .exchange().expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.message")
                .value(message -> assertEquals("Too short product name: " + productDto.name(), message));
    }

    @Test
    void shouldFindProduct() {
        //Given
        ProductDto productDto = new ProductDto(null, "pe", new BigDecimal("10.1"));
        //When & Then
        webTestClient.get()
                .exchange().expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.message")
                .value(message -> assertEquals())
    }
}