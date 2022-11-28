package com.example.demo.services;

import com.example.demo.clients.MercadoLibreClientRest;
import com.example.demo.entities.Product;
import com.example.demo.entities.ProductsList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {

    @Mock
    private MercadoLibreClientRest clientRest;

    @InjectMocks
    private ProductServiceImpl productService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }



    @Test
    void getProductsExceptionAmount() {
        when(clientRest.getProducts("moto")).thenReturn(initProductsTest1());
        assertThrows(Exception.class, () -> productService.getProducts("moto"));
    }

    @Test
    void getProductsException() {
        when(clientRest.getProducts("moto")).thenReturn(initProductsTest2());
        List<Product> response = productService.getProducts("moto");
        assertEquals(response.size(), 1);
    }

    private ProductsList initProductsTest1() {
        Product product2 = new Product();
        product2.setPrice(2000.0);
        product2.setCondition("new");
        product2.setId("125");
        product2.setTitle("moto CBR");

        ProductsList productsList = new ProductsList();
        List<Product> products = new ArrayList<>();
        products.add(product2);

        productsList.setSite_id("site_id_prueba");
        productsList.setResults(products);
        return productsList;
    }

    private ProductsList initProductsTest2() {

        Product product = new Product();
        product.setPrice(5000.0);
        product.setCondition("new");
        product.setId("125");
        product.setTitle("moto CBR");

        Product product2 = new Product();
        product2.setPrice(5000.0);
        product2.setCondition("old");
        product2.setId("125");
        product2.setTitle("moto CBR");

        ProductsList productsList = new ProductsList();
        List<Product> products = new ArrayList<>();
        products.add(product2);
        products.add(product);

        productsList.setSite_id("site_id_prueba");
        productsList.setResults(products);
        return productsList;
    }

}