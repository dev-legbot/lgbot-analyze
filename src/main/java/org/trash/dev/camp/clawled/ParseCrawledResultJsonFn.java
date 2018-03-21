package org.trash.dev.camp.clawled;

import org.apache.beam.sdk.io.gcp.pubsub.PubsubMessage;
import org.apache.beam.sdk.transforms.DoFn;

import java.io.IOException;
import java.util.logging.Logger;

public class ParseCrawledResultJsonFn extends DoFn<PubsubMessage, RecievedMessage> {

    public static final Logger LOGGER = Logger.getAnonymousLogger();

    @ProcessElement
    public void parse(ProcessContext c) throws IOException {
        String recievedMessage = new String(c.element().getPayload());
        c.output(RecievedMessage.createByJson(recievedMessage));
    }
}
