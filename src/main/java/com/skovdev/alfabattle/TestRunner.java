//package com.skovdev.alfabattle;
//
//import com.skovdev.alfabattle.kafka.consumer.KafkaConsumer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//
//public class TestRunner implements CommandLineRunner {
//
//    @Autowired
//    private KafkaConsumer kafkaConsumer;
//
//    @Override
//    public void run(String... args) throws Exception {
//        wait(10000);
//        System.out.println("Messages consumed: "+ kafkaConsumer.getMsgsAccumulator().size());
//    }
//}
