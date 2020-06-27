package com.skovdev.alfabattle.kafka.consumer;

import com.skovdev.alfabattle.datasource.TemplatesRepository;
import com.skovdev.alfabattle.datasource.UsersRepository;
import com.skovdev.alfabattle.model.kafka.KafkaRecord;
import com.skovdev.alfabattle.model.rest.UserPaymentAnalytic;
import com.skovdev.alfabattle.model.rest.UserTemplate;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@EnableKafka
@Service
public class KafkaConsumer {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TemplatesRepository templatesRepository;

    @KafkaListener(topics = "RAW_PAYMENTS")
    public void readAll(ConsumerRecord<Long, KafkaRecord> record) {
//        System.out.println("Key " + record.key());
//        System.out.println("Val: " + record.value());
        processRecord(record.value());
    }

    private void processRecord(KafkaRecord kafkaRecord) {
        UserPaymentAnalytic user = usersRepository.getUserAnalytic(kafkaRecord.getUserId());
        if (user == null) {
            user = new UserPaymentAnalytic().setUserId(kafkaRecord.getUserId());
            usersRepository.getAllAnalytic().put(user.getUserId(), user);
        }

        user.addPayment(kafkaRecord.getCategoryId(), kafkaRecord.getAmount());
        templatesRepository.add(kafkaRecord.getUserId(), new UserTemplate(kafkaRecord.getAmount(), kafkaRecord.getCategoryId(), kafkaRecord.getRecipientId()));
    }

}
