package org.trash.dev.camp.clawled;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.beam.sdk.coders.AvroCoder;
import org.apache.beam.sdk.coders.DefaultCoder;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * TODO 後で書く
 * 動作未検証
 */
@DefaultCoder(AvroCoder.class)
public class RecievedMessage {
    public final String url;
    public final List<String> headers;
    public final String body;

    /**
     * コンストラクタ
     * @param url
     * @param body
     * @param headers
     */
    private RecievedMessage(String url, List<String> headers, String body) {
        this.url = url;
        this.headers = Collections.unmodifiableList(headers);
        this.body = body;
    }

    public static RecievedMessage create(String url, List<String> headers, String body) {
        return new RecievedMessage(url, headers, body);
    }

    /**
     * JSON文字列から取得結果を生成する。
     * @param json
     * @return
     * @throws IOException
     */
    public static RecievedMessage createByJson(String json) throws IOException {
        if (json.isEmpty()) {
            throw new IllegalArgumentException("JSONがからっぽ");
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, RecievedMessage.class);
    }

    /**
     * JSON文字列を取得する。
     * @return
     * @throws JsonProcessingException
     */
    public String getJsonString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
