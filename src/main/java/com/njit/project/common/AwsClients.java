package com.njit.project.common;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sqs.SqsClient;

public class AwsClients {
    public static Region region() { return Region.of(Config.region()); }
    public static S3Client s3() {
        return S3Client.builder().region(region())
                .credentialsProvider(DefaultCredentialsProvider.create()).build();
    }
    public static SqsClient sqs() {
        return SqsClient.builder().region(region())
                .credentialsProvider(DefaultCredentialsProvider.create()).build();
    }
    public static RekognitionClient rekognition() {
        return RekognitionClient.builder().region(region())
                .credentialsProvider(DefaultCredentialsProvider.create()).build();
    }
}

