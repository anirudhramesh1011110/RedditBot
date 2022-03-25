package com.redditbot.runner;

import net.dean.jraw.RedditClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {

    public static final String SUBREDDIT = "MemeAlleyway";
    Bot redditBot;

    @BeforeEach
    public void setup() {
        redditBot = new Bot();
    }

    @Test
    public void findCaller() {
        redditBot.findCaller();
    }


}