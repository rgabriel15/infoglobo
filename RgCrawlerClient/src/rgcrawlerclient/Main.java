/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rgcrawlerclient;

import java.nio.file.Paths;

/**
 * © RG Systems
 * @author RGA
 */
public class Main {   
    //Constants
    public static final String STR_URL = "http://revistaautoesporte.globo.com/rss/ultimas/feed.xml";
    public static final String STR_FILE_PATH = Paths.get("").toAbsolutePath().toString() + "\\test\\input_test.xml";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String strJson = CrawlerClient.run();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
