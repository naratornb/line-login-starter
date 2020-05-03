package com.linecorp.sample.login.application.service;

import com.linecorp.sample.login.application.model.HealthCheckResult;
import com.linecorp.sample.login.application.util.HTTPClientUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public class SendReportService {

    public static String sendReport (String accessoken, HealthCheckResult result){

        try{
            HTTPClientUtil.postReportWithToken(accessoken,result);
        } catch (IOException e) {
            return "400";
        }

        return "200 OK";
    }

}
