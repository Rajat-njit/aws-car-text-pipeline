package com.njit.project.common;

import java.util.List;

import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsRequest;
import software.amazon.awssdk.services.rekognition.model.DetectLabelsResponse;
import software.amazon.awssdk.services.rekognition.model.DetectTextRequest;
import software.amazon.awssdk.services.rekognition.model.DetectTextResponse;
import software.amazon.awssdk.services.rekognition.model.Image;
import software.amazon.awssdk.services.rekognition.model.Label;
import software.amazon.awssdk.services.rekognition.model.S3Object;
import software.amazon.awssdk.services.rekognition.model.TextDetection;

public class RekognitionHelper {
    private final RekognitionClient rk;
    public RekognitionHelper(RekognitionClient rk){ this.rk = rk; }

    public boolean hasCarAbove(String bucket, String key, double threshold) {
        DetectLabelsRequest request = DetectLabelsRequest.builder()
                .image(Image.builder()
                        .s3Object(S3Object.builder().bucket(bucket).name(key).build())
                        .build())
                .maxLabels(10) // get top 10 labels
                .build();

        DetectLabelsResponse response = rk.detectLabels(request);
        List<Label> labels = response.labels();

        for (Label label : labels) {
            if (label.name().equalsIgnoreCase("Car") &&
                label.confidence() >= threshold * 100) {
                return true;
            }
        }
        return false;
    }

    public List<TextDetection> detectText(String bucket, String key, double threshold) {
        DetectTextRequest request = DetectTextRequest.builder()
            .image(Image.builder()
                    .s3Object(S3Object.builder().bucket(bucket).name(key).build())
                    .build())
            .build();

    DetectTextResponse response = rk.detectText(request);
    List<TextDetection> allDetections = response.textDetections();

    // Filter by confidence
    return allDetections.stream()
            .filter(td -> td.confidence() >= threshold * 100)
            .toList();
}

}

