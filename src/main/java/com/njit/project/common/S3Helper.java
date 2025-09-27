package com.njit.project.common;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public class S3Helper {
    private final S3Client s3;
    public S3Helper(S3Client s3) { this.s3 = s3; }

    public byte[] getBytes(String bucket, String key) {
        ResponseBytes<GetObjectResponse> obj =
            s3.getObjectAsBytes(GetObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build());
        return obj.asByteArray();
    }
}
