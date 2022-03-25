package com.redditbot.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ExternalService {

    private final static Logger logger = LoggerFactory.getLogger(ExternalService.class);
    private final OkHttpClient httpClient = new OkHttpClient();

    public String call(String url) {

        Request request = new Request.Builder().url(url).build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            return response.body().string();

        } catch (IOException e) {
            logger.error("Exception sending ExternalService call!", e);
        }

        return null;
    }

}
