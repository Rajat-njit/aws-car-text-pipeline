package com.njit.project.common;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties P = new Properties();
    static {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("app.properties")) {
            if (in != null) P.load(in);
            else throw new RuntimeException("app.properties not found");
        } catch (Exception e) { throw new RuntimeException(e); }
    }
    public static String region() { return P.getProperty("aws.region"); }
    public static String bucket() { return P.getProperty("s3.bucket"); }
    public static String queueName() { return P.getProperty("sqs.queueName"); }
    public static double carThreshold() { return Double.parseDouble(P.getProperty("confidence.car")); }
    public static double textThreshold() { return Double.parseDouble(P.getProperty("confidence.text")); }
}

