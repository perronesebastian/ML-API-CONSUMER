package com.example.demo.services;

import com.example.demo.clients.MercadoLibreClientRest;
import com.example.demo.entities.Product;
import com.example.demo.entities.ProductsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private MercadoLibreClientRest clientRest;

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProducts(String value) {
        ProductsList products = clientRest.getProducts(value);
        if (!(products.getResults().stream().
                filter(product -> product.getPrice() < 3000.0).
                collect(Collectors.toList())).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "condition fails");
        }
        return products.getResults().stream().
                filter(product -> product.getCondition().
                        equals("new")).collect(Collectors.toList());
    }

}
