package com.example.demo.services;

import com.example.demo.entities.Product;
import com.example.demo.entities.ProductsList;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(String value);

    List<ProductsList> findAll();
}
