/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rgcrawlerws;

import java.util.List;
import java.util.Map;
import javax.xml.ws.handler.MessageContext;

/**
 * © RG Systems
 * @author RGA
 */
public class Authentication {
    //Constants
    private static final String STR_HEADER_USERNAME = "Username";
    private static final String STR_HEADER_PASSWORD = "Password";
    private static final String STR_USERNAME_TEST = "user"; //Test
    private static final String STR_PASSWORD_TEST = "12345"; //Test
            
    public static boolean authenticate(MessageContext messageContext)
    {
        //TODO Should validate username and password with database
        try {
            System.out.println("\nAuthentication.authenticate");
            Map http_headers = (Map) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
            List userList = (List) http_headers.get(STR_HEADER_USERNAME);
            List passwordList = (List) http_headers.get(STR_HEADER_PASSWORD);
        
            String strUsername = userList.get(0).toString();
            String strPassword = passwordList.get(0).toString();

            if (strUsername.equals(STR_USERNAME_TEST)
            && strPassword.equals(STR_PASSWORD_TEST)) {
                System.out.println("User " + strUsername + " authenticated.");
                return true;
            }

            System.out.println("User " + strUsername + " authentication fail.");
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
