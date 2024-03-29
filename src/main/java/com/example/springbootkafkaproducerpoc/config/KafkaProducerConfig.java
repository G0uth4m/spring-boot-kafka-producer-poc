package com.example.springbootkafkaproducerpoc.config;

import com.example.common.Email;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapAddress;

  @Value("${spring.kafka.producer.properties.enable.idempotence}")
  private Boolean enableIdempotence;

  @Value("${spring.kafka.properties.security.protocol}")
  private String securityProtocol;

  @Value("${spring.kafka.properties.sasl.mechanism}")
  private String saslMechanism;

  @Value("${spring.kafka.properties.sasl.jaas.config}")
  private String saslJaasConfig;

  @Bean
  public ProducerFactory<String, Email> greetingProducerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, enableIdempotence);
    configProps.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, securityProtocol);
    configProps.put(SaslConfigs.SASL_MECHANISM, saslMechanism);
    configProps.put(SaslConfigs.SASL_JAAS_CONFIG, saslJaasConfig);
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean
  public KafkaTemplate<String, Email> greetingKafkaTemplate() {
    return new KafkaTemplate<>(greetingProducerFactory());
  }
}
