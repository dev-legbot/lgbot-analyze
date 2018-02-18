import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * TODO 後で書く
 * 動作未検証
 */
public class RecievedResult {
    public final String url;
    public final List<String> headers;
    public final String body;

    /**
     * コンストラクタ
     * @param url
     * @param body
     * @param headers
     */
    private RecievedResult(String url, List<String> headers, String body) {
        this.url = url;
        this.headers = Collections.unmodifiableList(headers);
        this.body = body;
    }

    public static RecievedResult create(String url, List<String> headers, String body) {
        return new RecievedResult(url, headers, body);
    }

    public static RecievedResult createByJson(String json) throws IOException {
        if (json.isEmpty()) {
            throw new IllegalArgumentException("JSONがからっぽ");
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, RecievedResult.class);
    }

}
