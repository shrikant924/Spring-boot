package com.quiz_app.quiz_app.service;

import com.quiz_app.quiz_app.model.Student;
import com.quiz_app.quiz_app.model.User;
import com.quiz_app.quiz_app.repo.StudentRepo;
import com.quiz_app.quiz_app.repo.UserRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private UserRepo userRepo;

    public ResponseEntity<List<Student>> findAll() {
        return new ResponseEntity<>(studentRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Student> findById(int id) {
        return new ResponseEntity<>(studentRepo.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<Student> findByName(String name) {
        return new ResponseEntity<>(studentRepo.findByName(name), HttpStatus.OK);
    }

    public ResponseEntity<String> save(Student student) {
        studentRepo.save(student);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<String> saveUser(User user) {

        userRepo.save(user);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<String> doLogin(String email, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        User user = userRepo.findByEmail(email);
        if (user == null)
            return new ResponseEntity<>("Invalid username", HttpStatus.UNAUTHORIZED);
        if (!bCryptPasswordEncoder.matches(password, user.getPassword()))
            return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>("Logged in successfully", HttpStatus.OK);
    }

    public ResponseEntity<User> findUserById(int id) {
        User user = userRepo.findById(String.valueOf(id)).get();
        return new ResponseEntity<>( user, HttpStatus.OK);
    }
}