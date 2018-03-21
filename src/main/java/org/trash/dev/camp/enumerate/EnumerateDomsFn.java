package org.trash.dev.camp.enumerate;

import org.apache.beam.sdk.transforms.DoFn;
import org.trash.dev.camp.clawled.ParsedResult;
import org.trash.dev.camp.clawled.RecievedMessage;

import java.io.IOException;

public class EnumerateDomsFn extends DoFn<RecievedMessage, ParsedResult> {

    @ProcessElement
    public void enumerate(ProcessContext c) throws IOException {
//        c.element().url;
    }

}
