package com.skovdev.alfabattle.kafka.consumer;

import lombok.Data;

import java.time.Instant;

@Data
public class KafkaMsgModel {
    String msg;
    Long number;
    Instant createdAt;
}
