package com.skovdev.alfabattle.kafka.consumer;

import com.skovdev.alfabattle.model.kafka.KafkaRecord;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

public class ErrorHandlingDeserializerCustom extends ErrorHandlingDeserializer<KafkaRecord>  {
//    ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS
//    JsonDeserializer.TRUSTED_PACKAGES
//    JsonDeserializer.VALUE_DEFAULT_TYPE
}
