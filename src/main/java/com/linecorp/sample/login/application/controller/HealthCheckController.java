package com.application.HealthCheckReport.controller;

import com.application.HealthCheckReport.model.HealthCheckResult;
import com.application.HealthCheckReport.service.HealthCheckService;
import com.application.HealthCheckReport.util.CSVReaderUtil;
import com.application.HealthCheckReport.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class HealthCheckController {

    @Autowired
    private LineAPIService lineAPIService;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("Perform website checking...");
        ArrayList<String> urlList = CSVReaderUtil.getDataFromCSVFilePath(file);
        HealthCheckResult result = HealthCheckService.validateListOfURL(urlList);
        HealthCheckResult resp = new HealthCheckResult(result.getNocheckedSite(), result.getNosuccessSite(), result.getNofailSite(), (int)TimeUtil.getElapsedTime(startTime));


        HealthCheckService.logConsoleResult(resp);

        return "Success";
    }


    private AccessToken getAccessToken(HttpSession httpSession) {
        return (AccessToken) httpSession.getAttribute(WebController.ACCESS_TOKEN);
    }

    private void setAccessToken(HttpSession httpSession, AccessToken accessToken) {
        httpSession.setAttribute(WebController.ACCESS_TOKEN, accessToken);
    }



}
