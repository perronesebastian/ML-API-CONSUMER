package com.example.demo.services;

import com.example.demo.entities.Product;
import com.example.demo.entities.ProductsList;
import com.example.demo.restClient.ProductClientRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductClientRest productClientRest;

    @Override
    public List<Product> getProducts(String value) {
        ProductsList products = productClientRest.getProducts(value);
        return products.getResults().stream().
                filter(product -> product.getCondition().
                        equals("new")).collect(Collectors.toList());
    }
}
