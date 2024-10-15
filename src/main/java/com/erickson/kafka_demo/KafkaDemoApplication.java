package com.erickson.kafka_demo;

import com.erickson.kafka_demo.config.KafkaConfigProps;
import com.erickson.kafka_demo.domain.CustomerVisitEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@SpringBootApplication
@Slf4j
public class KafkaDemoApplication {
	private final ObjectMapper objectMapper;

	public static void main(String[] args) {
		SpringApplication.run(KafkaDemoApplication.class, args);
	}

	@Bean
	@Profile("!test")
	public ApplicationRunner runner(final KafkaTemplate<String, String> kafkaTemplate,
									final KafkaConfigProps kafkaConfigProps) throws JsonProcessingException {
		final CustomerVisitEvent event = CustomerVisitEvent.builder()
				.customerId(UUID.randomUUID().toString())
				.dateTime(LocalDateTime.now())
				.build();

		final String payload = objectMapper.writeValueAsString(event);
		log.info("Send {}", payload);

		return args -> kafkaTemplate.send(kafkaConfigProps.getTopic(), payload);
	}

	@KafkaListener(topics = "customer.visit")
	@Profile("!test")
	public String listens(final String in) {
		log.info("listens: {}", in);
		return in;
	}

}
