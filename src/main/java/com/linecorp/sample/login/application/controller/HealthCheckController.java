package com.linecorp.sample.login.application.controller;

import com.linecorp.sample.login.application.model.HealthCheckResult;
import com.linecorp.sample.login.application.service.HealthCheckService;
import com.linecorp.sample.login.application.util.CSVReaderUtil;
import com.linecorp.sample.login.application.util.TimeUtil;
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
        HealthCheckResult resp = new HealthCheckResult(result.getNocheckedSite(), result.getNosuccessSite(), result.getNofailSite(), (int) TimeUtil.getElapsedTime(startTime));


        HealthCheckService.logConsoleResult(resp);

        return "Success";
    }


}
