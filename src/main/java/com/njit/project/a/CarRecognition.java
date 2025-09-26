package com.njit.project.a;

import com.njit.project.common.*;

public class CarRecognition {
    public static void main(String[] args) {
        var sqs = new SqsHelper(AwsClients.sqs());
        String qUrl = sqs.ensureQueue(Config.queueName());
        // TODO Phase 3: loop 1.jpg..10.jpg, if hasCarAbove -> sqs.send(qUrl, "N.jpg")
        // finally send termination: sqs.send(qUrl, "-1");
        System.out.println("CarRecognition stub ready. Queue: " + qUrl);
    }
}

