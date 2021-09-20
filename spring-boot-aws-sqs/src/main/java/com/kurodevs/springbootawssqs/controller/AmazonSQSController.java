package com.kurodevs.springbootawssqs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;

@RestController
public class AmazonSQSController {
    
    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String endPoint;

    @GetMapping("/send/{message}")
    public void putMessagedToQueue(@PathVariable("message") String message) {
        queueMessagingTemplate.send(endPoint, MessageBuilder.withPayload(message).build());
    }

    @SqsListener("sqs-queue")
    public void loadMessagesFromQueue(String message) {
        System.out.println("Queue Messages: " + message);
    }
}
