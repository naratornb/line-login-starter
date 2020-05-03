package com.application.HealthCheckReport.util;

import com.application.HealthCheckReport.model.HealthCheckResult;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPClientUtil {

    public static URL parseStrtoURL (String urlStr){
        try{
            return new URL(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isURLAvailable (URL url) {
        try{
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //get "HEAD" only
            connection.setRequestMethod("HEAD");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");

            int responseCode = connection.getResponseCode();

            return true;
        } catch (IOException e){
            return false;
        }
    }


    private void sendPost(HealthCheckResult result) throws Exception {

        String url = "https://backend-challenge.line-apps.com/healthcheck/report";

        HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();

        //add request header
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-Type", "application/json; utf-8");
        httpClient.setRequestProperty("Accept", "application/json");

        // Input data here
        String jsonInputString = " {\"total_websites\": \"" + result.getNocheckedSite() + "\", " +
                "\"success\": \"" + result.getNosuccessSite() + "\"," +
                "\"failure\": \"" + result.getNofailSite() + "\"," +
                "\"total_time\": \"" + result.getTotalTime() + "\", } ";

        // Send post request
        httpClient.setDoOutput(true);
        try (OutputStream os = httpClient.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
            os.flush();
        }

        int responseCode = httpClient.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
//        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            //print result
            System.out.println(response.toString());

        }

    }
}
