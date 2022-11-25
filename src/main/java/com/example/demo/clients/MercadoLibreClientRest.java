package com.example.demo.clients;

import com.example.demo.entities.ProductsList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MLAPI", url = "https://api.mercadolibre.com")
public interface MercadoLibreClientRest {

    @GetMapping("/sites/MLA/search?q={value}&limit=20")
    ProductsList getProducts(@PathVariable String value);
}
