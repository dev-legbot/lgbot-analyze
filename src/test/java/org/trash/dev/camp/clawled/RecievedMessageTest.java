package org.trash.dev.camp.clawled;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class RecievedMessageTest {

    private static final String DUMMY_JSON =
            "{\"url\":\"http://xxx.com\"," +
            "\"headers\": [{\"Content-Length\":\"1024\"},{\"Content-Type\":\"text/html;charset=utf_8\"}]," +
            "\"body\":\"<html><head></head><body>hoge fuga piyo hogera</body></html>\"}";

    @Test
    public void createByJsonTest() {
        try
        {
            RecievedMessage msg = RecievedMessage.createByJson(DUMMY_JSON);

        } catch (IOException ioe)
        { // IOException
            fail(ioe.getMessage());
        }
    }
}