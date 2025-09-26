package com.njit.project;

import com.njit.project.common.Config;

public class Main {
    public static void main(String[] args) {
        System.out.println("Region: " + Config.region());
        System.out.println("Bucket: " + Config.bucket());
        System.out.println("Queue: " + Config.queueName());
        System.out.println("Car Threshold: " + Config.carThreshold());
        System.out.println("Text Threshold: " + Config.textThreshold());
    }
}
