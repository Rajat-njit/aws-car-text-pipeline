package com.njit.project.common;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

public class S3Helper {
    private final S3Client s3;
    public S3Helper(S3Client s3) { this.s3 = s3; }

    public byte[] getBytes(String bucket, String key) {
        try (InputStream in = s3.getObject(GetObjectRequest.builder()
                     .bucket(bucket).key(key).build());
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[8192];
            int n;
            while ((n = in.read(buffer)) > 0) {
                out.write(buffer, 0, n);
            }
            return out.toByteArray();
        } catch (Exception e) {
            // For Phase 0, you can just return empty array or rethrow
            return new byte[0];
        }
    }
}
