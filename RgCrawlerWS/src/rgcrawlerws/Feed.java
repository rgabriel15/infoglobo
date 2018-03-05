/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rgcrawlerws;

import java.util.ArrayList;

/**
 * © RG Systems
 * @author RGA
 */
public class Feed {
    private ArrayList<FeedItem> feed = null;

    public ArrayList<FeedItem> getFeed() {
        return feed;
    }

    public void setFeed(ArrayList<FeedItem> listFeedItem) {
        this.feed = listFeedItem;
    }
}
