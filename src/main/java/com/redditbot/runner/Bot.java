package com.redditbot.runner;

import com.redditbot.util.AppProperties;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.Comment;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.pagination.BarebonesPaginator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bot {

    private RedditClient redditClient;
    private static Logger logger = LoggerFactory.getLogger(Bot.class);


    public Bot() {
        UserAgent userAgent = new UserAgent("bot",
                "com.example.usefulbot", "v0.1", AppProperties.get("reddit.username"));

        Credentials credentials = Credentials.script(AppProperties.get("reddit.username"),
                AppProperties.get("reddit.password"), AppProperties.get("reddit.clientId"),
                AppProperties.get("reddit.secret"));

        NetworkAdapter adapter = new OkHttpNetworkAdapter(userAgent);
        redditClient = OAuthHelper.automatic(adapter, credentials);
    }

    public void findCaller() {
        BarebonesPaginator<Comment> commentsPaginator = latestcomments("news");
        commentsPaginator.accumulateMerged(-1).forEach(comment -> reply(comment));
    }

    private boolean ifSummoned(Comment comment) {
        return comment.getBody().toUpperCase().contains(AppProperties.get("reddit.summonPhrase"));
    }

    private void reply(Comment comment) {
        if(ifSummoned(comment)) {
            // Reply with a Gif
        }
    }

    private BarebonesPaginator<Comment> latestcomments(String subReddit) {
        return redditClient.latestComments(subReddit).build();
    }

}
