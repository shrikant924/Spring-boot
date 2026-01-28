package com.quiz_app.quiz_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Products {
    @Id
    int id;

    String name;
    String brand;
    long price;
    @Column(name = "original_price")
    long originalPrice;
    int discount;
    double rating;
    int reviews;
    String category;
    int stock;
    String image;
    String description;
}
