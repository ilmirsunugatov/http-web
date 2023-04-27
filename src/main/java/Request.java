import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {
    private char[] body;

    public Request() { }

    public Request(char[] body) {
        if (body == null) {
            throw new IllegalArgumentException("Body cannot be null");
        }
        this.body = body;
    }

    public void setBody(char[] body) {
        if (body == null) {
            throw new IllegalArgumentException("Body cannot be null");
        }
        this.body = body;
    }

    public char[] getBody() {
        return this.body;
    }
    public static Map<String, String> getQueryParams(String extras) {
        Map<String, String> results = new HashMap<>();
        try {
            URI rawExtras = new URI("?" + extras);
            List<NameValuePair> extraList = URLEncodedUtils.parse(rawExtras, "UTF-8");
            for (NameValuePair item : extraList) {
                int i = 0;
                String name = item.getName();
                while (results.containsKey(name)) {
                    name = item.getName() + ++i;
                }
                results.put(name, item.getValue());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return results;
    }
}