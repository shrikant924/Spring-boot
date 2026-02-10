package com.quiz_app.quiz_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Student {

  @Id
  //    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int rollNo;

  private String name;
  private int marks;
}