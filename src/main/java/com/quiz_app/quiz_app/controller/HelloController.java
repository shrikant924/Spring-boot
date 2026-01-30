package com.quiz_app.quiz_app.controller;

import com.quiz_app.quiz_app.model.dto.UserDto;
import com.quiz_app.quiz_app.service.JwtService;
import com.quiz_app.quiz_app.service.StudentService;
import com.quiz_app.quiz_app.model.Student;
import com.quiz_app.quiz_app.model.User;
import com.quiz_app.quiz_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api")
public class HelloController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("hello")
    public String greet() {
        return "Hello world";
    }

    @GetMapping("/getStudents")
    public ResponseEntity<List<Student>> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/getStudent/{Id}")
    public Student getStudent(@PathVariable("Id") int id) {
        return studentService.findById(id).getBody();
    }

    @GetMapping("/getStudentByName/{name}")
    public ResponseEntity<Student> findStudentByName(@PathVariable("name") String name) {
        return studentService.findByName(name);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PostMapping("register")
    public UserDto registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("update")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        return studentService.saveUser(user);
    }


    @PostMapping("login")
    public ResponseEntity<Map<String,String>> doLogin(@RequestBody User user) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        String token = jwtService.generateToken(user.getUsername());

        Map <String, String> res = new HashMap<>();
        res.put("token" , token);
        return ResponseEntity.ok(res);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<User> getUserDetails(@PathVariable int id) {
        return studentService.findUserById(id);
    }
}
