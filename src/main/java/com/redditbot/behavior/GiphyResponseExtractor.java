package com.redditbot.behavior;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class GiphyResponseExtractor {

    public GiphyResponseExtractor() {

    }

    public String extractGifUrl(String response) throws IOException {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Object> jsonAdapter = moshi.adapter(Object.class);
        Map<String, Object> giphyReponse = (Map<String, Object>) jsonAdapter.fromJson(response);
        ArrayList data = (ArrayList) giphyReponse.get("data");
        Map<String, String> firstgif = (Map<String, String>) data.get(randomNumber(data));
        return firstgif.get("url");
    }

    // Generate a random number and grab that gif from the list.
    private int randomNumber(ArrayList data) {
        Random r = new Random();
        return r.nextInt(data.size());
    }


}
