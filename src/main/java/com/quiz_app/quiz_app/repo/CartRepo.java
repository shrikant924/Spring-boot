package com.quiz_app.quiz_app.repo;

import com.quiz_app.quiz_app.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {}