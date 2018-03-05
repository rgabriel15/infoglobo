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
public class Main {
    //Constants
    private static final String STR_SERVER_IP = "127.0.0.1"; //localhost 127.0.0.1
    private static final int INT_SERVER_PORT = 9876;
    private static final String STR_PAGE_NAME = "crawler";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            CrawlerServerPublisher.publish("http://" + STR_SERVER_IP + ":" + INT_SERVER_PORT + "/" + STR_PAGE_NAME);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
