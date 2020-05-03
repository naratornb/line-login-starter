package com.linecorp.sample.login.application.util;


import com.linecorp.sample.login.application.model.HealthCheckResult;
import okhttp3.*;

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

    public static void postReportWithToken(String token, HealthCheckResult result) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("total_website",  toStr(result.getNocheckedSite()))
                .addFormDataPart("success", toStr(result.getNosuccessSite()))
                .addFormDataPart("failure", toStr(result.getNofailSite()))
                .addFormDataPart("total_time", toStr(result.getTotalTime()))
                .build();
        Request request = new Request.Builder()
                .url("https://backend-challenge.line-apps.com/healthcheck/report")
                .method("POST", body)
                .addHeader("Authorization", "Bearer " + token)
                .build();
        Response response = client.newCall(request).execute();
    }

    private static String toStr (int i){
        return Integer.toString(i);
    }
}
