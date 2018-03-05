/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rgcrawlerws;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import static org.jsoup.helper.StringUtil.isBlank;
import org.jsoup.parser.Parser;

/**
 * © RG Systems
 * @author RGA
 */
@WebService(endpointInterface = "rgcrawlerws.ICrawlerServer")
public class CrawlerServer implements ICrawlerServer {
    //Constants
    private static final String STR_TAG_ITEM = "item";
    private static final String STR_TAG_TITLE = "title";
    private static final String STR_TAG_LINK = "link";
    private static final String STR_TAG_DESCRIPTION = "description";
    private static final String STR_TAG_BODY = "body";
    private static final String STR_TAG_P = "p";
    private static final String STR_TAG_DIV = "div";
    private static final String STR_TAG_IMAGE = "img";
    private static final String STR_TAG_DESCRIPTION_LINKS = "ul";
    private static final String STR_TAG_DESCRIPTION_LINK = "li>a";
    private static final String STR_ATTR_SRC = "src";
    private static final String STR_ATTR_HREF = "href";
    private static final String STR_TYPE_TEXT = "text";
    private static final String STR_TYPE_IMAGE = "image";
    private static final String STR_TYPE_LINKS = "links";

    @Resource
    WebServiceContext webServiceContext;
    
    @Override
    public String getFeedJson(String strUrl) throws Exception {
        if (!Authentication.authenticate(webServiceContext.getMessageContext()))
            return null;
        System.out.println("\nCrawlerServer.getFeedJson\nURL: " + strUrl);
        Document doc = readFeed(strUrl);
        return _getFeedJson(doc);
    }   
    
    @Override
    public String getFeedJsonLocal(String strFilePath) throws Exception {
        if (!Authentication.authenticate(webServiceContext.getMessageContext()))
            return null;
        System.out.println("\nCrawlerServer.getFeedJsonLocal\nFile: " + strFilePath);
        Document doc = readFeedLocal(strFilePath);
        return _getFeedJson(doc);
    }
    
    private Document readFeed(String strUrl) throws Exception {
        return Jsoup.connect(strUrl).get();
    }

    private Document readFeedLocal(String strFilePath) throws Exception {      
        return Jsoup.parse(new FileInputStream(new File(strFilePath))
            , StandardCharsets.UTF_8.toString()
            , ""
            , Parser.xmlParser());
    }

    private String _getFeedJson(Document doc) {
        Elements tagsItem = doc.select(STR_TAG_ITEM);
        ArrayList<FeedItem> listFeedItem = new ArrayList();

        for (Element elemItem : tagsItem) {
            FeedItem feedItem = new FeedItem();
            
            //Title
            Elements elemChilds = elemItem.select(STR_TAG_TITLE);
            if (elemChilds.size() > 0)
                feedItem.setTitle(clearText(elemChilds.first().text()));
            
            //Main link
            elemChilds = elemItem.select(STR_TAG_LINK);
            if (elemChilds.size() > 0)
                feedItem.setLink(clearText(elemChilds.first().text()));

            //Description
            elemChilds = elemItem.select(STR_TAG_DESCRIPTION);
            if (elemChilds.size() > 0) {
                Element elemDesc = Jsoup.parse(elemChilds.first().text());
                elemChilds = elemDesc.select(STR_TAG_BODY);
                
                ArrayList<FeedItemContent> listContent = new ArrayList<>();
 
                for (Element elem : elemChilds.first().children()) {
                    String strNodeContent = null;
                    FeedItemContent feedItemContent = null;
                    
                    switch (elem.tag().getName().toLowerCase()) {
                        case STR_TAG_P:
                            strNodeContent = clearText(elem.toString());
                            if (isBlank(strNodeContent))
                                continue;

                            feedItemContent = new FeedItemContent();
                            feedItemContent.setType(STR_TYPE_TEXT);
                            feedItemContent.setContent(strNodeContent);
                            listContent.add(feedItemContent);
                    
                            break;
                        case STR_TAG_DIV:
                            ArrayList<String> listLink = new ArrayList<>();
                            for (Element elemDivChild : elem.children()) {
                                switch (elemDivChild.tag().getName().toLowerCase()) {  
                                    case STR_TAG_IMAGE:                                   
                                        strNodeContent = elemDivChild.attr(STR_ATTR_SRC);
                                        if (isBlank(strNodeContent))
                                            continue;

                                        feedItemContent = new FeedItemContent();
                                        feedItemContent.setType(STR_TYPE_IMAGE);
                                        feedItemContent.setContent(strNodeContent);
                                        listContent.add(feedItemContent);

                                        break;
                                    case STR_TAG_DESCRIPTION_LINKS:
                                        Elements elemLinks = elemDivChild.select(STR_TAG_DESCRIPTION_LINK);
                                        for (Element elemLink : elemLinks) {
                                            strNodeContent = elemLink.attr(STR_ATTR_HREF);
                                            if (isBlank(strNodeContent))
                                                continue;
                                            listLink.add(strNodeContent);
                                        }
                                        
                                        if (!listLink.isEmpty()) {
                                            feedItemContent = new FeedItemContent();
                                            feedItemContent.setType(STR_TYPE_LINKS);
                                            feedItemContent.setContent(listLink);
                                            listContent.add(feedItemContent);
                                        }

                                        break;
                                    default:
                                        break;
                                }
                            }

                            break;
                        default:
                            break;
                    }
                }

                if (!listContent.isEmpty())
                    feedItem.setContent(listContent);
            }

            listFeedItem.add(feedItem);
        }

        Feed feed = new Feed();
        feed.setFeed(listFeedItem);
        Gson gson = (new GsonBuilder()).disableHtmlEscaping().setPrettyPrinting().create();
        String strJson = gson.toJson(feed);
        System.out.println(strJson);
        return strJson;
    }

    private String clearText(String strText) {
        return Jsoup.parse(strText).text();
    }
}
