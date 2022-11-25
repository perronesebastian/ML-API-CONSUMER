package com.example.demo.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {

    private String id;

    private String title;

    private Double price;

    private String condition;

    private static final long serialVersionUID = 1L;

}
