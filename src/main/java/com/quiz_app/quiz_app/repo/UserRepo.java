package com.quiz_app.quiz_app.repo;

import com.quiz_app.quiz_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    User findByEmail(String email);
}
