package com.njit.project.common;

import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

import java.util.List;

public class RekognitionHelper {
    private final RekognitionClient rk;
    public RekognitionHelper(RekognitionClient rk){ this.rk = rk; }

    public boolean hasCarAbove(String bucket, String key, double threshold) {
        // TODO: implement detectLabels call; scan for label "Car" >= threshold
        return false;
    }

    public List<TextDetection> detectText(String bucket, String key, double threshold) {
        // TODO: implement detectText call; return only detections above threshold
        return List.of();
    }
}

