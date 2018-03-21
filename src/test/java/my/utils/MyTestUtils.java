package my.utils;

import org.apache.beam.sdk.io.gcp.pubsub.PubsubMessage;
import org.trash.dev.camp.clawled.RecievedMessage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * テスト用ユーティリティ
 */
public class MyTestUtils {

    private static final String DUMMY_URL = "http://dummy.moe/hoge";
    private static final Map<String, String> DUMMY_HEADERS;
    static {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Length", "1024");
        headers.put("Content-Type", "text/html; charset=utf_8");
        DUMMY_HEADERS = Collections.unmodifiableMap(headers);
    }
    private static final String DUMMY_BODY = "<html><head></head><body>hoge fuga piyo hogera</body></html>";

    /**
     * ダミーのメッセージをつくる
     * @return
     */
    public static RecievedMessage createDummy() {
        return new RecievedMessage.create(DUMMY_URL, DUMMY_HEADERS, DUMMY_BODY);
    }

    /**
     * PubSubメッセージの生成。
     * @param payload ペイロード文字列
     * @return
     */
    public static PubsubMessage createPubsubMessage(String payload) {
        Map<String, String> atterMap = new HashMap<>();
        return createPubsubMessage(payload, atterMap);
    }

    /**
     * PubSubメッセージの生成
     * @param payload ペイロード文字列
     * @param atterMap あったー
     * @return
     */
    public static PubsubMessage createPubsubMessage(String payload, Map<String, String> atterMap) {
        byte[] binarizedPayload = payload.getBytes();
        PubsubMessage message = new PubsubMessage(binarizedPayload, atterMap);
        return message;
    }

}
