/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rgcrawlerws;

import javax.xml.ws.Endpoint;

/**
 * © RG Systems
 * @author RGA
 */
public class CrawlerServerPublisher {    
    public static void publish(String strUrl) {
        System.out.println("\nCrawlerServerPublisher.publish\nURL: " + strUrl);
        Endpoint.publish(strUrl, new CrawlerServer());
    }
}
