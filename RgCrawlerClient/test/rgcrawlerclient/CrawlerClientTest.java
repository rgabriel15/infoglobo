/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rgcrawlerclient;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * © RG Systems
 * @author RGA
 */
public class CrawlerClientTest {
    //Constants
    private static final String STR_OUTPUT_TEST_FILE_PATH
        = Paths.get("").toAbsolutePath().toString() + "\\test\\output_test.json";
    
    /**
     * Test of run method, of class CrawlerClient.
     * Reads input_test.xml, get it's JSON than compares with output_test.json.
     * @throws java.lang.Exception
     */
    @Test
    public void testRun() throws Exception {     
        System.out.println("CrawlerClientTest.testRun\nExpected result file: " + STR_OUTPUT_TEST_FILE_PATH + '\n');
        String strExpResult = FileUtils.readFileToString(new File(STR_OUTPUT_TEST_FILE_PATH)
            , java.nio.charset.StandardCharsets.UTF_8);
        String strResult = CrawlerClient.run();
        
        JsonParser jsonParser = new JsonParser();
        JsonObject expResultJsonObject = jsonParser.parse(strExpResult).getAsJsonObject();
        JsonObject resultJsonObject = jsonParser.parse(strResult).getAsJsonObject();

        assertEquals(expResultJsonObject, resultJsonObject);
    }
}
