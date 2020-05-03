package com.application.HealthCheckReport.service;

import com.application.HealthCheckReport.model.HealthCheckResult;
import com.application.HealthCheckReport.util.HTTPClientUtil;

import java.util.ArrayList;

public class HealthCheckService {
    private static int noAvailableURL = 0;
    private static int noUnavailableURL = 0;

    public static HealthCheckResult validateListOfURL (ArrayList<String> list) {
        int totalSite = list.size();
        if (list == null) {
            //Empty CSV File - do nothing return size (success:0, failed:0)
        } else {
            for (int i=0; i < list.size();i++){
                String url = list.get(i);
                // Wrong URL / HTTP check failed count as failed
                Boolean status = HTTPClientUtil.isURLAvailable(HTTPClientUtil.parseStrtoURL(url));
                if (status == true){
                    noAvailableURL++;
                } else{
                    noUnavailableURL++;
                }
            }
        }
        return new HealthCheckResult(totalSite,noAvailableURL,noUnavailableURL);
    }

    public static void logConsoleResult(HealthCheckResult result){
        System.out.println("Done");
        System.out.println("Checked webistes: " + result.getNocheckedSite());
        System.out.println("Successful webistes: " + result.getNosuccessSite());
        System.out.println("Failure webistes: " + result.getNofailSite());
        System.out.println("Total times to finished checking website: " + result.getTotalTime() + " ms");

    }
}
