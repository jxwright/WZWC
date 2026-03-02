package avicit.cmjt.utils;

import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class UrlTest {


    public static void main(String[] args) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder( "aip/text/v1");
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("creationDateBegin", "2024-04-01");
        queryParams.put("creationDateEnd", "2025-04-01");
        queryParams.put("orgCode", "13320");
        if (queryParams != null) {
            for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
                uriBuilder.addParameter(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        URI uri = uriBuilder.build();

        // 打印完整调用地址（调试用）
        String fullUrl = uri.toString();
        System.out.println("完整调用地址: " + fullUrl); // 控制台也打印一份
        System.out.println(uriBuilder.getPath());
    }
}
