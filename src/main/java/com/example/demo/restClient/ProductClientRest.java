package com.example.demo.restClient;

import com.example.demo.entities.ProductsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import java.util.stream.Collectors;

@Service
public class ProductClientRest {

    @Autowired
    @Qualifier("MercadoLibreWebClient")
    private WebClient webClient;

    public ProductsList getProducts(String value) {
        try {
            ProductsList products = webClient.method(HttpMethod.GET)
                    .uri("/sites/MLA/search?q=" + value + "&limit=20")
                    .retrieve()
                    .bodyToMono(ProductsList.class)
                    .block();
            if (!(products.getResults().stream().
                    filter(product -> product.getPrice() <= 3000).collect(Collectors.toList())).isEmpty()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "condition fails");
            }
        return products;
        } catch (Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.CONFLICT, "request fails");
        }
    }
}

