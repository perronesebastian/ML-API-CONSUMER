package com.example.demo.controllers;

import com.example.demo.entities.Product;
import com.example.demo.entities.ProductsList;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("products/{value}")
    ResponseEntity<List<Product>> getProductsByApi(@PathVariable("value") String value) {
        return new ResponseEntity<List<Product>>(productService.getProducts(value), HttpStatus.OK);
    }

    @GetMapping("/products")
    ResponseEntity<List<ProductsList>> findAll() {
        return new ResponseEntity<List<ProductsList>>(productService.findAll(), HttpStatus.OK);
    }
}
