package avicit.cmjt.utils;

import avicit.platform6.core.redisCacheManager.BaseCacheManager;
import avicit.platform6.core.spring.SpringFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 集团合同系统API调用工具类
 */
public class ContractSystemClient {
    // 配置信息
    private static  final String clientId="VvxuwlvbhN1y6SKmOV60";
    private static  final String clientSecret="LQMK8E1TwVzqgVFK";
    private static  final String username="29201856";
    //private static  final String tokenUrl="http://10.127.96.249:4000/oauth/token";
    private static  final String tokenUrl="http://10.110.32.81:30003/oauth/token";
    private static  final String scope="read,write";




    /**
     * 获取访问令牌
     * @return 有效的访问令牌
     */
    private synchronized static String getAccessToken() {
        BaseCacheManager baseCacheManager = SpringFactory.getBean("baseCacheManager");
        // 检查缓存中是否有未过期的token
        String tokenRedis = baseCacheManager.getFromRedis("contractSystemCMJT", "token");
        String expiresInRedis = baseCacheManager.getFromRedis("contractSystemCMJT", "expiresIn");

        if (tokenRedis != null && expiresInRedis != null) {
            long expiresIn = Long.parseLong(expiresInRedis);
            if(System.currentTimeMillis() < expiresIn)
            {
                return tokenRedis;
            }
        }
        // 创建HTTP客户端
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 构建请求URL
            String requestUrl = String.format("%s?client_id=%s&client_secret=%s&username=%s" +
                            "&grant_type=client_credentials&scope=%s",
                    tokenUrl, clientId, clientSecret, username, scope);

            // 创建POST请求
            HttpPost httpPost = new HttpPost(requestUrl);
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

            // 发送请求
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                String result = EntityUtils.toString(response.getEntity());
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> map = mapper.readValue(result, Map.class);

                if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                    String token = map.get("access_token").toString();
                    long expiresIn = Long.parseLong(String.valueOf(map.get("expires_in")));
                    // 更新token缓存
                    baseCacheManager.insert2redis("contractSystemCMJT", "token", token);
                    baseCacheManager.insert2redis("contractSystemCMJT", "expiresIn", String.valueOf(System.currentTimeMillis() + (expiresIn - 300) * 1000));
                    return token;
                } else {
                    throw new RuntimeException("获取token失败: " + map.get("error_description"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("获取token时发生异常", e);
        }
    }

    /**
     * GET请求调用API接口（带查询参数）
     * @param apiUrl API地址
     * @param params 查询参数Map
     * @return 响应结果字符串
     */
    public String get(String apiUrl, Map<String, String> params) {
        return callApi(apiUrl, "GET", params, null, null);
    }

    /**
            * POST请求调用API接口（表单格式）
            * @param apiUrl API地址
     * @param formParams 表单参数Map
     * @return 响应结果字符串
     */
    public String postForm(String apiUrl, Map<String, String> formParams) {
        return callApi(apiUrl, "POST", null, formParams, null);
    }

    /**
     * POST请求调用API接口（JSON格式）
     * @param apiUrl API地址
     * @param param JSON请求体
     * @return 响应结果字符串
     */
    public static String postJson(String apiUrl, Map<String,Object> param) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody;
        try {
            jsonBody = objectMapper.writeValueAsString(param);
        } catch (Exception e) {
            throw new RuntimeException("转换JSON请求体时发生异常", e);
        }
        return callApi(apiUrl, "POST", null, null, jsonBody);
    }

    /**
     * DELETE请求调用API接口（JSON格式）
     * @param apiUrl API地址
     * @param param JSON请求体
     * @return 响应结果字符串
     */
    public static String deleteJson(String apiUrl, Map<String,Object> param) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody;
        try {
            jsonBody = objectMapper.writeValueAsString(param);
        } catch (Exception e) {
            throw new RuntimeException("转换JSON请求体时发生异常", e);
        }
        return callApi(apiUrl, "DELETE", null, null, jsonBody);
    }

    /**
     * POST请求调用API接口（JSON格式）
     * @param apiUrl API地址
     * @param param JSON请求体
     * @return 响应结果字符串
     */
    public static String postByUrlParam(String apiUrl, Map<String,String> param) {
        return callApi(apiUrl, "POST", param, null, null);
    }
    /**
     * 通用API调用方法
     * @param apiUrl API地址
     * @param method 请求方法(GET/POST)
     * @param queryParams 查询参数(GET使用)
     * @param formParams 表单参数(POST表单使用)
     * @param jsonBody JSON请求体(POST JSON使用)
     * @return 响应结果
     */
    private static String callApi(String apiUrl, String method,
                           Map<String, String> queryParams,
                           Map<String, String> formParams,
                           String jsonBody) {
        int retryCount = 0;
        final int maxRetry = 2; // 最多重试2次
        BaseCacheManager baseCacheManager = SpringFactory.getBean("baseCacheManager");

        while (retryCount < maxRetry) {
            try {
                String token = getAccessToken();

                try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                    // 构建带查询参数的URL
                    URIBuilder uriBuilder = new URIBuilder(apiUrl);
                    if (queryParams != null) {
                        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                            uriBuilder.addParameter(entry.getKey(), entry.getValue());
                        }
                    }
                    URI uri = uriBuilder.build();
                    // 处理GET请求
                    if ("GET".equalsIgnoreCase(method)) {
                        HttpGet httpGet = new HttpGet(uri);
                        httpGet.setHeader("Authorization", "Bearer " + token);
                        httpGet.setHeader("Content-Type", "application/json");

                        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                            return handleResponse(response);
                        }
                    }
                    // 处理POST请求
                    else if ("POST".equalsIgnoreCase(method)) {
                        HttpPost httpPost = new HttpPost(uri);
                        httpPost.setHeader("Authorization", "Bearer " + token);

                        // 处理表单参数
                        if (formParams != null && !formParams.isEmpty()) {
                            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
                            List<NameValuePair> params = new ArrayList<>();
                            for (Map.Entry<String, String> entry : formParams.entrySet()) {
                                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                            }
                            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                        }
                        // 处理JSON请求体
                        else if (jsonBody != null) {
                            httpPost.setHeader("Content-Type", "application/json");
                            httpPost.setEntity(new StringEntity(jsonBody, "UTF-8"));
                        }

                        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                            return handleResponse(response);
                        }
                    }// 处理PUT请求
                    else if ("PUT".equalsIgnoreCase(method)) {
                        HttpPut httpPut = new HttpPut(uri);
                        httpPut.setHeader("Authorization", "Bearer " + token);

                        // 处理表单参数
                        if (formParams != null && !formParams.isEmpty()) {
                            httpPut.setHeader("Content-Type", "application/x-www-form-urlencoded");
                            List<NameValuePair> params = new ArrayList<>();
                            for (Map.Entry<String, String> entry : formParams.entrySet()) {
                                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                            }
                            httpPut.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                        }
                        // 处理JSON请求体
                        else if (jsonBody != null) {
                            httpPut.setHeader("Content-Type", "application/json");
                            httpPut.setEntity(new StringEntity(jsonBody, "UTF-8"));
                        }

                        try (CloseableHttpResponse response = httpClient.execute(httpPut)) {
                            return handleResponse(response);
                        }
                    }// 处理DELETE请求
                    else if ("DELETE".equalsIgnoreCase(method)) {
                        HttpDelete httpDelete = new HttpDelete(uri);
                        httpDelete.setHeader("Authorization", "Bearer " + token);
                        httpDelete.setHeader("Content-Type", "application/json");

                        try (CloseableHttpResponse response = httpClient.execute(httpDelete)) {
                            return handleResponse(response);
                        }
                    } else {
                        throw new IllegalArgumentException("不支持的HTTP方法: " + method);
                    }
                }
            } catch (Exception e) {
                // Token失效，清除缓存并重试
                if (e.getMessage() != null && e.getMessage().contains("401")) {
                    baseCacheManager.delByField("contractSystemCMJT", "token");
                    retryCount++;
                    continue;
                }
                throw new RuntimeException("调用API时发生异常", e);
            }
        }

        throw new RuntimeException("API调用失败，重试次数超过限制");
    }

    /**
     * 处理响应
     */
    private static String handleResponse(CloseableHttpResponse response) throws Exception {
        int code = response.getStatusLine().getStatusCode();

        // Token失效
        if (HttpStatus.SC_UNAUTHORIZED == code) {
            throw new RuntimeException("401 Unauthorized");
        }

        // 其他错误
        if (code != HttpStatus.SC_OK) {
            throw new RuntimeException("API调用失败，状态码: " + code);
        }

        return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
    }

    /**
     * 调用API接口并返回指定类型的对象
     * @param apiUrl API地址
     * @param method 请求方法
     * @param queryParams 查询参数
     * @param formParams 表单参数
     * @param jsonBody JSON请求体
     * @param responseType 返回类型
     * @return 响应对象
     */
    public <T> T callApi(String apiUrl, String method,
                         Map<String, String> queryParams,
                         Map<String, String> formParams,
                         String jsonBody,
                         Class<T> responseType) {
        String result = callApi(apiUrl, method, queryParams, formParams, jsonBody);
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(result, responseType);
        } catch (Exception e) {
            throw new RuntimeException("解析响应结果时发生异常", e);
        }
    }

}