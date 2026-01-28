package com.quiz_app.quiz_app.controller;

import com.quiz_app.quiz_app.service.StudentService;
import com.quiz_app.quiz_app.model.Student;
import com.quiz_app.quiz_app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api")
public class HelloController {

    @Autowired
    private StudentService studentService;


    @GetMapping("hello")
    public String greet(){
        return "Hello world";
    }

    @GetMapping("/getStudents")
    public ResponseEntity<List<Student>> getAllStudents(){
       return studentService.findAll();
    }

    @GetMapping("/getStudent/{Id}")
    public Student getStudent(@PathVariable("Id") int id){
        return studentService.findById(id).getBody();
    }

    @GetMapping("/getStudentByName/{name}")
    public ResponseEntity<Student> findStudentByName(@PathVariable("name") String name){
        return studentService.findByName(name);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveStudent(@RequestBody Student student){
        return  studentService.save(student);
    }

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        return studentService.saveUser(user);
    }

    @PutMapping("update")
    public ResponseEntity<String> updateUser(@RequestBody User user){
        return studentService.saveUser(user);
    }


    @PostMapping("login")
    public ResponseEntity<String> doLogin(@RequestBody User user){
        return studentService.doLogin(user.getEmail() , user.getPassword());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<User> getUserDetails(@PathVariable int id){
        return studentService.findUserById(id);
    }
}
