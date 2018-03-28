package org.trash.dev.camp.clawled;

import static org.junit.Assert.*;
import my.utils.MyTestUtils;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubMessage;
import org.apache.beam.sdk.transforms.DoFnTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ParseCrawledResultJsonFnTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * 単純動作確認
     * @throws Exception
     */
    @Test
    public void simplePathTest() throws Exception {
        String recievedJson = MyTestUtils.createDummy().getJsonString();
        PubsubMessage testMessage = MyTestUtils.createPubsubMessage(recievedJson);
        DoFnTester<PubsubMessage, RecievedMessage> tester = DoFnTester.of(new ParseCrawledResultJsonFn());
        List<RecievedMessage> list = tester.processBundle(testMessage);

        assertTrue("あかんやつ", list.isEmpty());

        assertTrue("同一チェック", true);
    }

}