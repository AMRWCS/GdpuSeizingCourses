package org.amrwcs.gdpuseizingcourses.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;

/**
 * REST客户端配置类
 * 该类用于配置和初始化REST客户端的相关参数和设置
 */
@Slf4j
@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);   // 连接超时（毫秒）
        factory.setReadTimeout(60000);      // 读取超时（毫秒）
        return builder
                .requestInterceptor((request, body, execution) -> {
                    log.info("========== 请求报文 ==========");
                    log.info("请求行: {} {}", request.getMethod(), request.getURI());
                    log.info("请求头: {}", request.getHeaders());
                    log.info("请求体: {}", new String(body, StandardCharsets.UTF_8));
                    log.info("==============================");
                    return execution.execute(request, body);   // 放行：继续发送
                })
                .requestFactory(factory)
                .build();
    }
}
