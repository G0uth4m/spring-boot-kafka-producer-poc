package com.example.springbootkafkaproducerpoc.kafka;

import com.example.common.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaEmailProducer {

  @Value("${topic.name}")
  private String topic;

  @Autowired
  private KafkaTemplate<String, Email> kafkaTemplate;

  public void send(Email email) {
    kafkaTemplate.send(topic, email);
  }

}
