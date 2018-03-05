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
class FeedItem {
    private String title = null;
    private String link = null;
    private ArrayList<FeedItemContent> content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String strTitle) {
        this.title = strTitle;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String strLink) {
        this.link = strLink;
    }

    public ArrayList<FeedItemContent> getContent() {
        return content;
    }

    public void setContent(ArrayList<FeedItemContent> listFeedItemContent) {
        this.content = listFeedItemContent;
    }
}
