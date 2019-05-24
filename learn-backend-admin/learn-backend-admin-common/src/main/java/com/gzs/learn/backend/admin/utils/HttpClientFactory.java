package com.gzs.learn.backend.admin.utils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;

/**
 * @author guanzhisong
 * 
 * 封装httpclient常见的配置想,连接超时时间、socket超时时间、连接keepalive时间、http连接池参数相关设置
 * @see <a href="https://hc.apache.org/httpcomponents-client-4.5.x/httpclient/examples/org/apache/http/examples/client/ClientConfiguration.java">httpclient 配置</a>
 */
public class HttpClientFactory {
    public final static int CONNECTION_TIMEOUT = 10 * 1000;
    private static volatile CloseableHttpClient defaultClient;
    private static final int CONN_PER_ROUTE = 256;
    private static final int CONN_MAX_TOTAL = 1024;
    private static final String DEFAUL_USER_AGENT = "user-agent-hc";
    private static final int DEFAULT_RETRY = 3;
    private static final int KEEPALIVE = 30 * 1000;

    public static CloseableHttpClient get() {
        if (defaultClient != null) {
            return defaultClient;
        }
        synchronized (HttpClientFactory.class) {
            if (defaultClient == null) {
                RegistryBuilder<ConnectionSocketFactory> builder = RegistryBuilder.<ConnectionSocketFactory> create();
                builder.register("http", PlainConnectionSocketFactory.getSocketFactory());
                SSLContext sslcontext = SSLContexts.createSystemDefault();
                builder.register("https", new SSLConnectionSocketFactory(sslcontext, hostnameVerifier));
                Registry<ConnectionSocketFactory> socketFactoryRegistry = builder.build();

                PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
                manager.setMaxTotal(CONN_MAX_TOTAL);
                manager.setDefaultMaxPerRoute(CONN_PER_ROUTE);

                defaultClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).setUserAgent(DEFAUL_USER_AGENT)
                        .setRetryHandler(new StandardHttpRequestRetryHandler(DEFAULT_RETRY, true)).setKeepAliveStrategy(keepAliveStrategy)
                        .setServiceUnavailableRetryStrategy(new DefaultServiceUnavailableRetryStrategy()).setConnectionManager(manager)
                        .build();
            }
        }
        return defaultClient;
    }

    private static HostnameVerifier hostnameVerifier = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * GET Specified client
     *
     * @param sslContext
     * @return
     */
    public synchronized static CloseableHttpClient get(SSLContext sslContext) {
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1", "TLSv1.1" }, null,
                hostnameVerifier);
        CloseableHttpClient client = HttpClientBuilder.create().setSSLSocketFactory(sslsf).setDefaultRequestConfig(requestConfig)
                .setUserAgent(DEFAUL_USER_AGENT).setRetryHandler(new StandardHttpRequestRetryHandler(DEFAULT_RETRY, true))
                .setKeepAliveStrategy(keepAliveStrategy).setServiceUnavailableRetryStrategy(new DefaultServiceUnavailableRetryStrategy())
                .setMaxConnPerRoute(CONN_PER_ROUTE).setMaxConnTotal(CONN_MAX_TOTAL).build();
        return client;
    }

    static RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECTION_TIMEOUT)
            .setConnectTimeout(CONNECTION_TIMEOUT).setSocketTimeout(CONNECTION_TIMEOUT).build();

    static ConnectionKeepAliveStrategy keepAliveStrategy = new ConnectionKeepAliveStrategy() {
        @Override
        public long getKeepAliveDuration(HttpResponse resp, HttpContext context) {
            return KEEPALIVE;
        }
    };

    public static RequestConfig getDefaultRequestConfig() {
        return requestConfig;
    }
}

class DefaultServiceUnavailableRetryStrategy implements ServiceUnavailableRetryStrategy {
    public final static int MAX_RETRIES = 3;
    public final static int RETRY_INTERVAL = 1000;

    @Override
    public boolean retryRequest(HttpResponse response, int executionCount, HttpContext context) {
        return executionCount <= MAX_RETRIES && response.getStatusLine().getStatusCode() >= HttpStatus.SC_INTERNAL_SERVER_ERROR;
    }

    @Override
    public long getRetryInterval() {
        return RETRY_INTERVAL;
    }
}
