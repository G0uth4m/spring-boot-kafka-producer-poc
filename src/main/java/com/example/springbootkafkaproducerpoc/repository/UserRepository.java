package com.example.springbootkafkaproducerpoc.repository;

import com.example.springbootkafkaproducerpoc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
