package com.njit.project.common;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;
import java.util.Optional;

public class SqsHelper {
    private final SqsClient sqs;
    public SqsHelper(SqsClient sqs){ this.sqs = sqs; }

    public String ensureQueue(String name) {
        try {
            return sqs.getQueueUrl(GetQueueUrlRequest.builder().queueName(name).build()).queueUrl();
        } catch (QueueDoesNotExistException e) {
            return sqs.createQueue(CreateQueueRequest.builder().queueName(name).build()).queueUrl();
        }
    }

    public void send(String queueUrl, String body) {
        sqs.sendMessage(SendMessageRequest.builder().queueUrl(queueUrl).messageBody(body).build());
    }

    public Optional<Message> receiveOne(String queueUrl) {
        List<Message> msgs = sqs.receiveMessage(ReceiveMessageRequest.builder()
                .queueUrl(queueUrl).maxNumberOfMessages(1).waitTimeSeconds(10).build()).messages();
        return msgs.isEmpty()? Optional.empty(): Optional.of(msgs.get(0));
    }

    public void delete(String queueUrl, Message m) {
        sqs.deleteMessage(DeleteMessageRequest.builder()
                .queueUrl(queueUrl).receiptHandle(m.receiptHandle()).build());
    }
}

