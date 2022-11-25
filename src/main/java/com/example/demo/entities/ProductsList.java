package com.example.demo.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductsList implements Serializable {

    private String site_id;

    private List<Product> results;

    private static final long serialVersionUID = 1L;

}
