package dev.pawel.orderservice.domain.service.product;

import dev.pawel.orderservice.domain.product.model.Product;
import dev.pawel.orderservice.infrastucture.repository.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void findProductById() {
        //Given
        Product product = new Product("123", "product1", new BigDecimal("20"));
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        //When
        Product productResponse = productService.findProductById(product.getId());
        //Then
        verify(productRepository, times(1)).findById(productResponse.getId());
    }

    @Test
    void shouldUpgradeProduct() {
        //Given
        Product product = new Product("123", "product1", new BigDecimal("20"));
        Product productModify = new Product("123", "product2", new BigDecimal("12"));
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productRepository.save(productModify)).thenReturn(productModify);
        //when
        Product productResponse = productService.upgradeProduct(product.getId(), productModify);
        //then
        verify(productRepository, times(1)).save(productResponse);
//        verify(productService, times(1)).findProductById(product.getId());
    }
    @Test
    void shouldDeleteProduct() {
        //Given
        Product product1 = new Product("1", "product1", new BigDecimal("20"));
        //When
        productService.deleteProductFromDbById(product1.getId());
        //Then
        verify(productRepository, times(1)).deleteById(product1.getId());
    }

    @Test
    void shouldCreateProduct() {
        //Given

    }
    @Test
    void shouldFindAllProduct() {
        //Given
        Product product1 = new Product("1", "product1", new BigDecimal("20"));
        Product product2 = new Product("2", "product2", new BigDecimal("10"));
        Product product3 = new Product("3", "product3", new BigDecimal("12"));
        Product product4 = new Product("4", "product4", new BigDecimal("32"));
        List<Product> products = List.of(product1, product2, product3, product4);
        when(productRepository.findAll()).thenReturn(products);
        //When
        List<Product> responseProducts = productService.findAllProducts();
        //Then
        assertEquals(4, responseProducts.size());
        verify(productRepository, times(1)).findAll();
    }
}