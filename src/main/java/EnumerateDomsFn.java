import org.apache.beam.sdk.transforms.DoFn;

import java.io.IOException;

public class EnumerateDomsFn extends DoFn<RecievedResult, ParsedResult> {

    @ProcessElement
    public void enumerate(ProcessContext c) throws IOException {
//        c.element().url;
        c.
    }

}
