package com.ecommerce.payment.core.cloud.client;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ClientConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            try {
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();
                requestTemplate.header("user", request.getHeader("user"));
                requestTemplate.header("authority", request.getHeader("authority"));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        };
    }

}
