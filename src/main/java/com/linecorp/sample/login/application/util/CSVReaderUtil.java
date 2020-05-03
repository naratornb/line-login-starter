package com.application.HealthCheckReport.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CSVReaderUtil {
    public static ArrayList<String> getDataFromCSVFilePath(MultipartFile file) throws IOException {
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<String> resultArr = new ArrayList<>();
        if (file.isEmpty()) {
            return null;
        } else {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                while ((line = br.readLine()) != null) {
                    // use comma as separator
                    String[] url = line.split(cvsSplitBy);
                    System.out.println(url[0]);
                    resultArr.add(url[0]);
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException();
            }
            return resultArr;
        }
    }
}
