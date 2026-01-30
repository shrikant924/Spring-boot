package com.quiz_app.quiz_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;

    private Long price;

    @Column(name = "original_price")
    private Long originalPrice;

    private Integer discount;
    private Double rating;
    private Integer reviews;
    private String category;
    private Integer stock;
    private String image;

    @Column(length = 1000)
    private String description;
}
