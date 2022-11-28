package com.example.demo.repository;

import com.example.demo.entities.ProductsList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductsList, String> {
}
