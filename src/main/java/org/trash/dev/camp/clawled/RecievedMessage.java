package org.trash.dev.camp.clawled;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import org.apache.beam.sdk.coders.AvroCoder;
import org.apache.beam.sdk.coders.DefaultCoder;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * クローラ受信メッセージ
 */
@DefaultCoder(AvroCoder.class)
public class RecievedMessage implements Serializable{
    public static final String URL_KEY = "url";
    public static final String HEADERS_KEY = "headers";
    public static final String BODY_KEY = "body";

    public final String url;
    public final List<Header> headers;
    public final String body;

    /**
     * コンストラクタ
     * @param url
     * @param body
     * @param headers
     */
    private RecievedMessage(String url, List<Header> headers, String body) {
        this.url = url;
        this.headers = Collections.unmodifiableList(headers);
        this.body = body;
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
        ObjectMapper objMapper = new ObjectMapper();
        Map<String, Object> mappedJson = objMapper.readValue(json, new TypeReference<Map>(){});

        String url = mappedJson.get(URL_KEY).toString();
        List<Header> headers = Header.createList((List) mappedJson.get(HEADERS_KEY));
        String body = mappedJson.get(BODY_KEY).toString();

        return new RecievedMessage(url, headers, body);
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

    /**
     * {@inheritDoc}
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
        { // check null
            return false;
        }
        if (obj instanceof RecievedMessage)
        { // Type check
            return false;
        }

        RecievedMessage target = (RecievedMessage) obj;

        if (!isSameHeaders(target.headers))
        { // Header check
            return false;
        }

        if (!isSameURL(target.url))
        { // URL check
            return false;
        }

        if (!isSameBody(target.body))
        { // Body check っていうと、出国審査ぽい。
            return false;
        }
        // pass all checks
        return true;
    }

    /**
     * Compere url same or not.
     * XXX take outside with url object.
     * @param otherSide
     * @return
     */
    private boolean isSameURL(String otherSide) {
        if (this.url == null && otherSide != null)
        {
            return false;
        }
        return this.url.equals(otherSide);
    }

    /**
     * Compere headers same or not.
     * XXX take outside with headers object.
     * @param otherSide
     * @return
     */
    private boolean isSameHeaders(List<Header> otherSide) {
        // check null
        if (this.headers == null && otherSide == null) return true;
        if (this.headers == null && otherSide != null) return false;
        if (this.headers != null && otherSide == null) return false;

        // check list contains
        if (this.headers.size() != otherSide.size()) return false;
        if (this.headers.stream().allMatch(header -> (otherSide.contains(header)))) return true;

        return false;
    }

    /**
     * Compere body same or not.
     * XXX take outside with url object.
     * @param otherSide
     * @return
     */
    private boolean isSameBody(String otherSide) {
        if (this.url == null && otherSide != null)
        {
            return false;
        }
        return this.url.equals(otherSide);
    }

    public static class Header {

        private final String type;
        private final String value;

        private Header(String type, String value) {
            if (Strings.isNullOrEmpty(type)) throw new NullPointerException("ぬるぽだよ");
            if (Strings.isNullOrEmpty(value)) throw new NullPointerException("ぬるぽだよ");

            this.type = type;
            this.value = value;
        }

        /**
         * create Map
         * @param mapList
         * @return
         */
        private static List<Header> createList(List<Map<String, String>> mapList) {
            List list = mapList.stream()
                    .flatMap(inMap -> inMap.entrySet().stream())
                    .map(header -> new Header(header.getKey(), header.getValue()))
                    .collect(Collectors.toList());

            return Collections.unmodifiableList(list);
        }

        /**
         * {@inheritDoc}
         * @param obj
         * @return
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof Header)) return false;

            Header target = (Header) obj;
            if (!this.type.equals(target.type)) return false;
            if (!this.value.equals(target.value)) return false;

            return true;
        }
    }

}
