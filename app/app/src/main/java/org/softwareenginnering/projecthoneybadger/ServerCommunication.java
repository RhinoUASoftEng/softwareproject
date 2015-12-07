package org.softwareenginnering.projecthoneybadger;

import com.loopj.android.http.HttpGet;


import java.io.IOException;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpDelete;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.client.methods.HttpPut;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by temet on 2015-11-16.
 */

public class ServerCommunication {

    static final String PATH = "http://54.187.159.168:8080/hb_server/api0/";

    static public String get(String table) throws IOException {
        HttpGet httpGet = new HttpGet(PATH + table);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == 200) {
            System.out.println("Response OK...");
            HttpEntity entity = response.getEntity();
            String data = EntityUtils.toString(entity);
            return data;
        }

        return null;
    }

    static public void post(String table, String json) throws IOException {
        HttpPost httpPost = new HttpPost(PATH + table);
        HttpClient httpClient = new DefaultHttpClient();
        HttpEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        HttpResponse response = httpClient.execute(httpPost);

        if (response.getStatusLine().getStatusCode() == 200)
            System.out.println("Response OK...");
    }

    static public void put(String table) throws IOException {
        HttpPut httpPut = new HttpPut(PATH + table);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(httpPut);
        if (response.getStatusLine().getStatusCode() == 200)
            System.out.println("Response OK...");
    }

    static public void delete(String table) throws IOException {
        HttpDelete httpDelete = new HttpDelete(PATH + table);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(httpDelete);
        if (response.getStatusLine().getStatusCode() == 200)
            System.out.println("Response OK...");
    }

}