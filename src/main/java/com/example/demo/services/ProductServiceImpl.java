package com.example.demo.services;

import com.example.demo.clients.MercadoLibreClientRest;
import com.example.demo.entities.Product;
import com.example.demo.entities.ProductsList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private MercadoLibreClientRest clientRest;

    //@Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    private ProductRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    @Transactional(readOnly = true)
    @Cacheable("productsApi")
    public List<Product> getProducts(String value) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Getting product by value {}", value);
        ProductsList products = clientRest.getProducts(value);

        conditionPrice(products);

        //sendKafka(products);

        repository.save(products);
        return products.getResults().stream().
                filter(product -> product.getCondition().
                        equals("new")).collect(Collectors.toList());
    }

    private void sendKafka(ProductsList products) {
        try {
            kafkaTemplate.send("devs4j-topic", mapper.writeValueAsString(products.getSite_id()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void conditionPrice(ProductsList products) {
        if (!(products.getResults().stream().
                filter(product -> product.getPrice() < 3000.0).
                collect(Collectors.toList())).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "condition fails");
        }
    }

    @Override
    @Cacheable("productsBd")
    public List<ProductsList> findAll() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return repository.findAll();
    }

}
