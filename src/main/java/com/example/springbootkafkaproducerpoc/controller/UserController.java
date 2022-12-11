package com.example.springbootkafkaproducerpoc.controller;

import com.example.springbootkafkaproducerpoc.entity.User;
import com.example.springbootkafkaproducerpoc.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/ap1/users")
public class UserController {

  private final UserService userService;

  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }
}
