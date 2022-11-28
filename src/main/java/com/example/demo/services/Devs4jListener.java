package com.example.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Devs4jListener {

    private static final Logger log = LoggerFactory.getLogger(Devs4jListener.class);

    @KafkaListener(topics ="devs4j-topic", groupId ="devs4jGroup")
    public void listen(String message) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Processed in APP {}", message);
    }

}
