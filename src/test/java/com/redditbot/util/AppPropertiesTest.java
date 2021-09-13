package com.redditbot.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppPropertiesTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void get() {
        assertNotNull(AppProperties.get("reddit.username"));
    }
}