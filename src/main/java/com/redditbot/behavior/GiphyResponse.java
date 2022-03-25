package com.redditbot.behavior;

public class GiphyResponse {

    public GiphyResponse() {

    }



    private class SingleGiphyResponse {

        private String type;
        private String id;
        private String url;

        public SingleGiphyResponse() {

        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }




}
