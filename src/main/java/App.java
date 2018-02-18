import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.transforms.ParDo;

import java.util.logging.Logger;

public class App {
    public static final Logger LOGGER = Logger.getAnonymousLogger();

    public static void main(String[] args) {
        Pipeline p = Pipeline.create();

        p.apply(PubsubIO.readMessagesWithAttributes().fromSubscription(""))
         .apply("parse JSON to Object", ParDo.of(new ParseCrawledResultJsonFn()))
         .apply("enumerate DOM", ParDo.of(new EnumerateDomsFn()));

        p.run();
    }
}
