package com.njit.project.a;

import com.njit.project.common.AwsClients;
import com.njit.project.common.Config;
import com.njit.project.common.RekognitionHelper;
import com.njit.project.common.SqsHelper;

public class CarRecognition {
    public static void main(String[] args) {
        var sqsHelper = new SqsHelper(AwsClients.sqs());
        var rekog = new RekognitionHelper(AwsClients.rekognition());
        String qUrl = sqsHelper.ensureQueue(Config.queueName());

        System.out.println("CarRecognition started. Queue: " + qUrl);

        for (int i = 1; i <= 10; i++) {
            String key = i + ".jpg";
            boolean hasCar = rekog.hasCarAbove(Config.bucket(), key, Config.carThreshold());
            if (hasCar) {
                System.out.println("Detected car in " + key + " → sending to queue");
                sqsHelper.send(qUrl, key);
            } else {
                System.out.println("No car detected in " + key);
            }
        }

        // Signal termination
        sqsHelper.send(qUrl, "-1");
        System.out.println("CarRecognition finished. Sent termination signal.");
    }
}
