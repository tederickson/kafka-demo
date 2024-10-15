package com.erickson.kafka_demo.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CustomerVisitEvent(String customerId, LocalDateTime dateTime) {
}
