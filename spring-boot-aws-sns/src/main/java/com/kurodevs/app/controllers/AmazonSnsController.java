package com.kurodevs.app.controllers;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AmazonSnsController {

    @Autowired
    private AmazonSNSClient amazonSNSClient;

    @Value("${cloud.aws.topic-arn}")
    private String topicArn;

    @GetMapping("/subscribe/{sub}")
    public String subscribe(@PathVariable("sub") String sub){
        SubscribeRequest subscribeRequest = new SubscribeRequest(topicArn, "email", sub);
        amazonSNSClient.subscribe(subscribeRequest);
        return "Check your email";
    }

    @GetMapping("/publish/{title}/{message}")
    public String publishMessage(@PathVariable("title") String title, @PathVariable("message") String message){
        PublishRequest publishRequest = new PublishRequest(topicArn, title, message);
        amazonSNSClient.publish(publishRequest);
        return "Message send!";
    }
}
