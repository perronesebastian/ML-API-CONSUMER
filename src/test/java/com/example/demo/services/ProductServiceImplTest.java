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

    @Mock
    private ProductServiceImpl productService;

    private ProductsList productsList;
    private Product product;
    private Product product1;
    private Product product2;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        product = new Product();
        product.setPrice(5000.0);
        product.setCondition("new");
        product.setId("123");
        product.setTitle("moto");

        product1 = new Product();
        product1.setPrice(10000.0);
        product1.setCondition("new");
        product1.setId("124");
        product1.setTitle("moto g20");

        product2 = new Product();
        product2.setPrice(10000.0);
        product2.setCondition("new");
        product2.setId("125");
        product2.setTitle("moto CBR");


        productsList = new ProductsList();
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product1);
        products.add(product2);


        productsList.setSite_id("site_id_prueba");
        productsList.setResults(products);
    }

    @Test
    void getProducts() {
        when(clientRest.getProducts("moto")).thenReturn(productsList);
        assertNotNull(productService.getProducts("moto"));
    }

}