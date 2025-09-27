package com.njit.project.b;

import java.io.FileWriter;
import java.util.List;
import java.util.Optional;

import com.njit.project.common.AwsClients;
import com.njit.project.common.Config;
import com.njit.project.common.RekognitionHelper;
import com.njit.project.common.SqsHelper;

import software.amazon.awssdk.services.rekognition.model.TextDetection;
import software.amazon.awssdk.services.sqs.model.Message;

public class TextRecognition {
    public static void main(String[] args) {
        var sqsHelper = new SqsHelper(AwsClients.sqs());
        var rekog = new RekognitionHelper(AwsClients.rekognition());
        String qUrl = sqsHelper.ensureQueue(Config.queueName());

        System.out.println("TextRecognition started. Queue: " + qUrl);

        try (FileWriter fw = new FileWriter("output.txt", true)) {
            while (true) {
                Optional<Message> maybeMsg = sqsHelper.receiveOne(qUrl);
                if (maybeMsg.isEmpty()) continue; // long polling will wait up to 10s

                Message msg = maybeMsg.get();
                String body = msg.body();
                sqsHelper.delete(qUrl, msg); // always delete after processing

                if (body.equals("-1")) {
                    System.out.println("Termination signal received. Stopping.");
                    break;
                }

                System.out.println("Processing image: " + body);

                List<TextDetection> texts = rekog.detectText(Config.bucket(), body, Config.textThreshold());

                if (!texts.isEmpty()) {
                    fw.write(body + " → ");
                    for (TextDetection t : texts) {
                        fw.write("\"" + t.detectedText() + "\" (conf=" + t.confidence() + ") ");
                    }
                    fw.write("\n");
                    fw.flush();
                    System.out.println("Text found in " + body);
                } else {
                    System.out.println("No text detected in " + body);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("TextRecognition finished. Results saved to output.txt");
    }
}
