import org.apache.beam.sdk.io.gcp.pubsub.PubsubMessage;
import org.apache.beam.sdk.transforms.DoFn;

import java.io.IOException;
import java.util.logging.Logger;

public class ParseCrawledResultJsonFn extends DoFn<PubsubMessage, RecievedResult> {

    public static final Logger LOGGER = Logger.getAnonymousLogger();

    @ProcessElement
    public void parse(ProcessContext c) throws IOException {
        String recievedMessage = new String(c.element().getPayload());
        c.output(RecievedResult.createByJson(recievedMessage));
    }
}
