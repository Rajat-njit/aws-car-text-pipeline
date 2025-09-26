package com.njit.project.common;

public class Setup {
    public static void main(String[] args) {
        var helper = new SqsHelper(AwsClients.sqs());
        String url = helper.ensureQueue(Config.queueName());
        System.out.println("SQS ready: " + url);
    }
}

