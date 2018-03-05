/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rgcrawlerws;

/**
 * © RG Systems
 * @author RGA
 */
class FeedItemContent {
    private String type = null;
    private Object content = null;

    public String getType() {
        return type;
    }

    public void setType(String strType) {
        this.type = strType;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object objContent) {
        this.content = objContent;
    }
}
