package com.quiz_app.quiz_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class AddressDetails {
    @Id
    private Long id;
    private String fistName;
    private String lastName;
    private String mobile;
    private String address;
    private String city;
    private String state;
    private String zip;
}
