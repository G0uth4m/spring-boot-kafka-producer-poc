package com.example.springbootkafkaproducerpoc.service.impl;

import com.example.common.Email;
import com.example.springbootkafkaproducerpoc.kafka.KafkaEmailProducer;
import com.example.springbootkafkaproducerpoc.entity.User;
import com.example.springbootkafkaproducerpoc.repository.UserRepository;
import com.example.springbootkafkaproducerpoc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Value("${mail.from.default}")
  private String fromEmailAddress;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private KafkaEmailProducer kafkaEmailProducer;

  @Override
  public User createUser(User user) {
    User savedUser = userRepository.save(user);
    triggerUserSignUpEmail(savedUser);
    return savedUser;
  }

  private void triggerUserSignUpEmail(User user) {
    Email email = Email.builder()
        .from(fromEmailAddress)
        .to(user.getEmail())
        .body("Welcome " + user.getName() + ". Your userid is: " + user.getId())
        .build();
    kafkaEmailProducer.send(email);
  }
}
