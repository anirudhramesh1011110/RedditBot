package com.redditbot.behavior;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class GiphyBehaviorTest {

    GiphyBehavior giphy;

    @BeforeEach
    public void setup() {
        giphy = new GiphyBehavior();

    }

    @Test
    public void getGifForComment() throws IOException {
        String comment = "WTF";
        String response = giphy.getGifForComment(comment);
        assertNotNull(response);
    }

}