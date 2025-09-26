package com.njit.project.b;

import com.njit.project.common.*;

public class TextRecognition {
    public static void main(String[] args) {
        var sqs = new SqsHelper(AwsClients.sqs());
        String qUrl = sqs.ensureQueue(Config.queueName());
        // TODO Phase 4: poll messages until "-1", for each N.jpg:
        // download bytes from S3, call detectText, append to output.txt if present
        System.out.println("TextRecognition stub ready. Queue: " + qUrl);
    }
}

