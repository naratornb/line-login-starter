package com.application.HealthCheckReport.util;

public class TimeUtil {
    public static long getElapsedTime(long startTime){
        long finishTime = System.currentTimeMillis();
        long elapsedTime = finishTime - startTime;
        return elapsedTime;
    }
}
