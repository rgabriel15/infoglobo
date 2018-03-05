/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rgcrawlerclient;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import rgcrawlerservice.Exception_Exception;

/**
 * © RG Systems
 * @author RGA
 */
public class CrawlerClient {
    //Constants
    private static final String STR_HEADER_USERNAME = "Username";
    private static final String STR_HEADER_PASSWORD = "Password";
    private static final String STR_USERNAME_TEST = "user"; //Test
    private static final String STR_PASSWORD_TEST = "12345"; //Test
    
    public static String run() throws Exception_Exception {
        rgcrawlerservice.CrawlerServerService service = new rgcrawlerservice.CrawlerServerService();
        rgcrawlerservice.ICrawlerServer port = service.getCrawlerServerPort();
        Map<String, Object> requestContext = ((BindingProvider)port).getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, service.getWSDLDocumentLocation().toString());
        Map<String, List<String>> hmHeaders = new HashMap<>();
        hmHeaders.put(STR_HEADER_USERNAME, Collections.singletonList(STR_USERNAME_TEST));
        hmHeaders.put(STR_HEADER_PASSWORD, Collections.singletonList(STR_PASSWORD_TEST));
        requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, hmHeaders);

        //String strJson = port.getFeedJson(Main.STR_URL); //Disable to test.
        String strJson = port.getFeedJsonLocal(Main.STR_FILE_PATH); //Enable to test.
        
        System.out.println(strJson);
        return strJson;
    }
}
