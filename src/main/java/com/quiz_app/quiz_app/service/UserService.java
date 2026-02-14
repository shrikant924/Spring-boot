package com.quiz_app.quiz_app.service;

import com.quiz_app.quiz_app.model.User;
import com.quiz_app.quiz_app.model.dto.UserDto;
import com.quiz_app.quiz_app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
  @Autowired private UserRepo userRepo;

  public UserDto saveUser(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    userRepo.save(user);
    UserDto userDto = new UserDto();
    userDto.setUsername(user.getUsername());
    userDto.setPassword(user.getPassword());
    return userDto;
  }

  public Long getUserId(String username) {
    User userDetails = userRepo.findByUsername(username);
    return userDetails.getId();
  }
}