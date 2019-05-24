package com.gzs.learn.backend.admin.utils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * http 请求工具类，针对get/post等常见请求进行封装,附带一个额外的JSON反序列化方法,如需自行反序列化返回值,传入的Class type 为java.lang.String即可
 * @author guanzhisong
 *
 */
public class HttpUtil {
    public static final Header XML_HEADER = new BasicHeader(HTTP.CONTENT_TYPE, "application/xml;charset=utf-8");
    public static final Header JSON_HEADER = new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=utf-8");
    public static final Header ENCODING_HEADER = new BasicHeader(HTTP.CONTENT_ENCODING, "UTF-8");
    private static final Logger log = LoggerFactory.getLogger(HttpClientFactory.class);
    private static CloseableHttpClient client = HttpClientFactory.get();

    /**
     * Get请求
     *
     * @param url http url 请求地址
     * @param headers 请求头
     * @return
     */
    public static <T> Response<T> doGet(String url, Class<? extends T> cls, Header... headers) {
        return doGet(url, cls, null, headers);
    }

    /**
     * Get请求
     * @param url http url请求地址
     * @param cls 响应实体
     * @param requestConfig http请求参数(链接超时时间、响应时间等)配置
     * @param headers http请求头
     * @return
     */
    public static <T> Response<T> doGet(String url, Class<? extends T> cls, RequestConfig requestConfig, Header... headers) {
        requestConfig = requestConfig == null ? HttpClientFactory.getDefaultRequestConfig() : requestConfig;
        HttpUriRequest request = RequestBuilder.get().setUri(url).setConfig(requestConfig).build();
        return execute(request, cls, headers);
    }

    /**
     * doGet获取list列表
     * 
     * @param url http请求url地址
     * @param reference 响应实体对象类型
     * @param headers http请求头信息
     * @return
     */
    public static <T> Response<T> doGet(String url, TypeReference<List<T>> reference, Header... headers) {
        return doGet(url, reference, null, headers);
    }

    /**
     * doGet获取list列表
     * 
     * @param url http请求url地址
     * @param reference 响应实体对象类型
     * @param requestConfig http请求参数(链接超时时间、响应时间等)配置
     * @param headers http请求头信息
     * @return
     */
    public static <T> Response<T> doGet(String url, TypeReference<List<T>> reference, RequestConfig requestConfig, Header... headers) {
        requestConfig = requestConfig == null ? HttpClientFactory.getDefaultRequestConfig() : requestConfig;
        HttpUriRequest request = RequestBuilder.get().setUri(url).setConfig(requestConfig).build();
        return execute(request, reference, headers);
    }

    /**
     * post请求
     * 
     * @param url http请求url地址
     * @param pairs form表单数据
     * @param cls 响应实体类型
     * @param requestConfig 请求参数配置信息
     * @param headers 请求头信息
     * @return
     */
    public static <T> Response<T> doPost(String url, List<? extends NameValuePair> pairs, Class<? extends T> cls, Header... headers) {
        return doPost(url, pairs, cls, null, headers);
    }

    /**
     * post请求
     * 
     * @param url http请求url地址
     * @param pairs form表单数据
     * @param cls 响应实体类型
     * @param requestConfig 请求参数配置信息
     * @param headers 请求头信息
     * @return
     */
    public static <T> Response<T> doPost(String url, List<? extends NameValuePair> pairs, Class<? extends T> cls,
            RequestConfig requestConfig, Header... headers) {
        HttpUriRequest request;
        requestConfig = requestConfig == null ? HttpClientFactory.getDefaultRequestConfig() : requestConfig;
        if (pairs != null && pairs.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, StandardCharsets.UTF_8);
            request = RequestBuilder.post().setUri(url).setEntity(entity).setConfig(requestConfig).build();
        } else {
            request = RequestBuilder.post().setUri(url).setConfig(requestConfig).build();
        }
        return execute(request, cls, headers);
    }

    /**
     * post请求
     * 
     * @param url http请求url地址
     * @param pairs Form表单数据
     * @param cls 响应实体类型
     * @param headers 请求头信息
     * @return
     */
    public static <T> Response<T> doPost(String url, List<? extends NameValuePair> pairs, TypeReference<List<T>> reference,
            Header... headers) {
        return doPost(url, pairs, reference, null, headers);
    }

    /**
     * post请求
     * 
     * @param url http请求url地址
     * @param pairs Form表单数据
     * @param cls 响应实体类型
     * @param requestConfig 请求参数配置信息
     * @param headers 请求头信息
     * @return
     */
    public static <T> Response<T> doPost(String url, List<? extends NameValuePair> pairs, TypeReference<List<T>> reference,
            RequestConfig requestConfig, Header... headers) {
        HttpUriRequest request;
        requestConfig = requestConfig == null ? HttpClientFactory.getDefaultRequestConfig() : requestConfig;
        if (pairs != null && pairs.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, StandardCharsets.UTF_8);
            request = RequestBuilder.post().setUri(url).setEntity(entity).setConfig(requestConfig).build();
        } else {
            request = RequestBuilder.post().setUri(url).setConfig(requestConfig).build();
        }
        return execute(request, reference, headers);
    }

    /**
     * post body请求体
     * 
     * @param url http请求url地址
     * @param pairs Form表单数据
     * @param cls 响应实体类型
     * @param headers 请求头信息
     * @return
     */
    public static <T> Response<T> doPost(String url, String requestBody, Class<? extends T> cls, Header... headers) {
        return doPost(url, requestBody, cls, null, headers);
    }

    /**
     * post body请求体
     * 
     * @param url http请求url地址
     * @param pairs Form表单数据
     * @param cls 响应实体类型
     * @param requestConfig 请求参数配置信息
     * @param headers 请求头信息
     * @return
     */
    public static <T> Response<T> doPost(String url, String requestBody, Class<? extends T> cls, RequestConfig requestConfig,
            Header... headers) {
        HttpUriRequest request = null;
        requestConfig = requestConfig == null ? HttpClientFactory.getDefaultRequestConfig() : requestConfig;
        if (StringUtils.isNotBlank(requestBody)) {
            StringEntity entity = new StringEntity(requestBody, StandardCharsets.UTF_8);
            request = RequestBuilder.post().setUri(url).setEntity(entity).setConfig(requestConfig).build();
        } else {
            request = RequestBuilder.post().setUri(url).setConfig(requestConfig).build();
        }
        return execute(request, cls, headers);
    }

    /**
     * post body请求体
     * 
     * @param url
     * @param requestBody
     * @param reference
     * @param headers
     * @return
     */
    public static <T> Response<T> doPost(String url, String requestBody, TypeReference<List<T>> reference, Header... headers) {
        return doPost(url, requestBody, reference, null, headers);
    }

    /**
     * 
     * @param url
     * @param requestBody
     * @param reference
     * @param requestConfig
     * @param headers
     * @return
     */
    public static <T> Response<T> doPost(String url, String requestBody, TypeReference<List<T>> reference, RequestConfig requestConfig,
            Header... headers) {
        HttpUriRequest request = null;
        requestConfig = requestConfig == null ? HttpClientFactory.getDefaultRequestConfig() : requestConfig;
        if (StringUtils.isNotBlank(requestBody)) {
            StringEntity entity = new StringEntity(requestBody, StandardCharsets.UTF_8);
            request = RequestBuilder.post().setUri(url).setEntity(entity).setConfig(requestConfig).build();
        } else {
            request = RequestBuilder.post().setUri(url).setConfig(requestConfig).build();
        }
        return execute(request, reference, headers);
    }

    /**
     * put请求
     * 
     * @param url
     * @param pairs
     * @param cls
     * @param headers
     * @return
     */
    public static <T> Response<T> doPut(String url, List<? extends NameValuePair> pairs, Class<? extends T> cls, Header... headers) {
        return doPut(url, pairs, cls, null, headers);
    }

    /**
     * put请求
     *  
     * @param url
     * @param pairs
     * @param cls
     * @param requestConfig
     * @param headers
     * @return
     */
    public static <T> Response<T> doPut(String url, List<? extends NameValuePair> pairs, Class<? extends T> cls,
            RequestConfig requestConfig, Header... headers) {
        HttpUriRequest request = null;
        requestConfig = requestConfig == null ? HttpClientFactory.getDefaultRequestConfig() : requestConfig;
        if (pairs != null && pairs.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, StandardCharsets.UTF_8);
            request = RequestBuilder.put().setUri(url).setEntity(entity).setConfig(requestConfig).build();
        } else {
            request = RequestBuilder.put().setUri(url).setConfig(requestConfig).build();
        }

        return execute(request, cls, headers);
    }

    /**
     * put请求
     * 
     * @param url
     * @param pairs
     * @param cls
     * @param headers
     * @return
     */
    public static <T> Response<T> doPut(String url, List<? extends NameValuePair> pairs, TypeReference<List<T>> reference,
            Header... headers) {
        return doPut(url, pairs, reference, null, headers);
    }

    /**
     * 
     * @param url
     * @param pairs
     * @param reference
     * @param requestConfig
     * @param headers
     * @return
     */
    public static <T> Response<T> doPut(String url, List<? extends NameValuePair> pairs, TypeReference<List<T>> reference,
            RequestConfig requestConfig, Header... headers) {
        HttpUriRequest request;
        requestConfig = requestConfig == null ? HttpClientFactory.getDefaultRequestConfig() : requestConfig;
        if (pairs != null && pairs.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, StandardCharsets.UTF_8);
            request = RequestBuilder.put().setUri(url).setEntity(entity).setConfig(requestConfig).build();
        } else {
            request = RequestBuilder.put().setUri(url).setConfig(requestConfig).build();
        }

        return execute(request, reference, headers);
    }

    /**
     * do put请求体
     * 
     * @param url
     * @param requestBody
     * @param cls
     * @param headers
     * @return
     */
    public static <T> Response<T> doPut(String url, String requestBody, Class<? extends T> cls, Header... headers) {
        return doPut(url, requestBody, cls, null, headers);
    }

    public static <T> Response<T> doPut(String url, String requestBody, Class<? extends T> cls, RequestConfig requestConfig,
            Header... headers) {
        HttpUriRequest request;
        requestConfig = requestConfig == null ? HttpClientFactory.getDefaultRequestConfig() : requestConfig;
        if (StringUtils.isNotBlank(requestBody)) {
            StringEntity entity = new StringEntity(requestBody, StandardCharsets.UTF_8);
            request = RequestBuilder.put().setUri(url).setEntity(entity).setConfig(requestConfig).build();
        } else {
            request = RequestBuilder.put().setUri(url).setConfig(requestConfig).build();
        }

        return execute(request, cls, headers);
    }

    /**
     * do put请求体
     * 
     * @param url
     * @param requestBody
     * @param cls
     * @param headers
     * @return
     */
    public static <T> Response<T> doPut(String url, String requestBody, TypeReference<List<T>> reference, Header... headers) {
        return doPut(url, requestBody, reference, null, headers);
    }

    public static <T> Response<T> doPut(String url, String requestBody, TypeReference<List<T>> reference, RequestConfig requestConfig,
            Header... headers) {
        HttpUriRequest request;
        requestConfig = requestConfig == null ? HttpClientFactory.getDefaultRequestConfig() : requestConfig;
        if (StringUtils.isNotBlank(requestBody)) {
            StringEntity entity = new StringEntity(requestBody, StandardCharsets.UTF_8);
            request = RequestBuilder.put().setUri(url).setEntity(entity).setConfig(requestConfig).build();
        } else {
            request = RequestBuilder.put().setUri(url).setConfig(requestConfig).build();
        }

        return execute(request, reference, headers);
    }

    /**
     * delete方法
     *
     * @param url
     * @param headers
     * @return
     */
    public static <T> Response<T> doDelete(String url, Class<? extends T> cls, Header... headers) {
        HttpUriRequest request = RequestBuilder.delete().setUri(url).setConfig(HttpClientFactory.getDefaultRequestConfig()).build();
        return execute(request, cls, headers);
    }

    /**
     * head 请求
     * 
     * @param url
     * @param headers
     * @return
     */
    public static Integer doHead(String url, Header... headers) {
        HttpUriRequest request = RequestBuilder.head().setUri(url).setConfig(HttpClientFactory.getDefaultRequestConfig()).build();
        return execute(request, String.class, headers).httpCode;
    }

    /**
     * 构造http url get 请求串
     *
     * @param url
     * @param pairs
     * @return
     */
    public static String buildUrl(String url, List<? extends NameValuePair> pairs) {
        if (CollectionUtils.isEmpty(pairs)) {
            return url;
        }
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        List<String> kvPairs = Lists.newArrayListWithCapacity(pairs.size());
        for (NameValuePair pair : pairs) {
            String k = pair.getName();
            String v = pair.getValue();
            if (StringUtils.isNotBlank(v)) {
                kvPairs.add(k + "=" + v);
            }
        }
        return url + "?" + Joiner.on("&").join(kvPairs);
    }

    /**
     * map to list<NameValuePair>
     *
     * @param params
     * @return
     */
    public static List<NameValuePair> convertMap2Pair(Map<String, Object> params) {
        if (params == null || params.size() == 0) {
            return null;
        }
        List<NameValuePair> pairs = Lists.newArrayList();
        for (Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
        }
        return pairs;
    }

    /**
     * map to list<NameValuePair>
     *
     * @param params
     * @return
     */
    public static List<NameValuePair> obj2Pair(Object object) {
        if (object == null) {
            return null;
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> map = BeanMap.create(object);
        return convertMap2Pair(map);
    }

    @SuppressWarnings("unchecked")
    private static <T> Response<T> execute(HttpUriRequest request, Class<? extends T> cls, Header... headers) {
        if (headers != null && headers.length > 0) {
            request.setHeaders(headers);
        }
        int code = 0;
        boolean success = false;
        T obj = null;
        String errMsg = null;
        try {
            HttpResponse response = client.execute(request);
            code = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            String resp = entity == null ? "" : EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            if (code / 100 == 2) {
                success = true;
                if (cls.getName().equals("java.lang.String")) {
                    obj = (T) resp;
                } else {
                    obj = JSON.parseObject(resp, cls);
                }
            } else {
                errMsg = resp;
            }
        } catch (Exception e) {
            log.error("Catch exception when getResult {},", request.getURI(), e);
        }
        return new Response<T>(success, code, obj, errMsg);
    }

    /**
     * 返回结果是list类型
     * @param request
     * @param reference
     * @return
     */
    private static <T> Response<T> execute(HttpUriRequest request, TypeReference<List<T>> reference, Header... headers) {
        if (headers != null && headers.length > 0) {
            request.setHeaders(headers);
        }
        int code = 0;
        boolean success = false;
        List<T> list = null;
        String errMsg = null;
        try (CloseableHttpResponse response = client.execute(request)) {
            code = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            String resp = entity == null ? "" : EntityUtils.toString(entity, StandardCharsets.UTF_8);
            if (code / 100 == 2) {
                success = true;
                list = JSON.parseObject(resp, reference);
            } else {
                errMsg = resp;
            }
        } catch (Exception e) {
            log.error("Catch exception when getResult List {},", request.getURI(), e);
        }
        return new Response<T>(success, code, list, errMsg);
    }

    public static class Response<T> {
        public boolean success;
        public int httpCode;
        public T entity;
        public List<T> list;
        public String errMsg;

        public Response() {
        }

        public Response(boolean success, int httpCode, T entity, String errMsg) {
            this.success = success;
            this.httpCode = httpCode;
            this.entity = entity;
            this.errMsg = errMsg;
        }

        public Response(boolean success, int httpCode, List<T> list, String errMsg) {
            this.success = success;
            this.httpCode = httpCode;
            this.list = list;
            this.errMsg = errMsg;
        }
    }
}
