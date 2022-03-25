package com.redditbot.runner;

import com.redditbot.behavior.GiphyBehavior;
import com.redditbot.util.AppProperties;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.*;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.pagination.Paginator;
import net.dean.jraw.pagination.SearchPaginator;
import net.dean.jraw.references.CommentReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Bot {

    private final RedditClient redditClient;
    private static final Logger logger = LoggerFactory.getLogger(Bot.class);

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
        SearchPaginator commentsPaginator = latestcomments("MemeAlleyway");
        commentsPaginator.accumulateMerged(-1).forEach(this::reply);
    }

    private boolean ifSummoned(Submission comment) {
        return comment.getBody().toUpperCase().contains(AppProperties.get("reddit.summonPhrase"));
    }

    private void reply(Submission comment) {
        if(ifSummoned(comment)) {
            // Reply with a Gif URL
            try {
                GiphyBehavior giphyBehavior = new GiphyBehavior();
                String gifUrlForComment = giphyBehavior.getGifForComment(
                        comment.getBody().replaceFirst(AppProperties.get("reddit.summonPhrase"), "").trim());
                CommentReference commentReference = new CommentReference(redditClient, comment.getId());
                commentReference.reply(gifUrlForComment);

            } catch (IOException e) {
                logger.error("Unable to retrieve from GIPHY!", e);
            }
        }
    }

    private SearchPaginator latestcomments(String subReddit) {
        SearchPaginator paginator = redditClient.subreddit(subReddit).search()
                .limit(Paginator.RECOMMENDED_MAX_LIMIT)
                .sorting(SearchSort.NEW)
                .timePeriod(TimePeriod.DAY)
                .syntax(SearchPaginator.QuerySyntax.PLAIN)
                .build();

        paginator.accumulate(-1);

        return paginator;

    }

    private void getComments(Listing<Submission> submission) {
        // Comment Tree Traversal: https://mattbdean.gitbooks.io/jraw/content/cookbook.html#links-and-comments

        //submission.getChildren().stream().forEach();
    }

}
