/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rgcrawlerws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * © RG Systems
 * @author RGA
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ICrawlerServer {
    /**
     * Get JSON from online XML feed file.
     * @param strUrl Feed file URL.
     * @return JSON string.
     * @throws Exception
     */
    @WebMethod String getFeedJson(String strUrl) throws Exception;
    
    /**
     * Get JSON from local XML feed file.
     * @param strFilePath Feed file local path.
     * @return JSON string.
     * @throws Exception
     */
    @WebMethod String getFeedJsonLocal(String strFilePath) throws Exception;
}
