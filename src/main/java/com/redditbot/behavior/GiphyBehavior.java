package com.redditbot.behavior;

import com.redditbot.service.ExternalService;
import com.redditbot.util.AppProperties;
import net.dean.jraw.models.Comment;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class GiphyBehavior {

    String GIPHY_URL = AppProperties.get("giphy.search.url");

    public GiphyBehavior() {

    }

    public String getGifForComment(String comment) throws IOException {
        ExternalService externalService = new ExternalService();
        String response = externalService.call(getFullUrl(comment));
        return response;
    }

    @NotNull
    private String getFullUrl(String comment) {
        return GIPHY_URL + "?" + "api_key=" + AppProperties.get("giphy.key") + "&" + "q=" + comment;
    }
}
