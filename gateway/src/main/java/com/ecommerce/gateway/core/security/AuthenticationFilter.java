package com.ecommerce.gateway.core.security;

import com.ecommerce.gateway.exception.Problem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Qualifier("unsecuredEndpoints")
    private final List<String> unsecuredEndpoints;
    private final ObjectMapper objectMapper;
    private Predicate<ServerHttpRequest> isSecured;
    private final WebClient webClient = WebClient.builder().build();

    public AuthenticationFilter(List<String> unsecuredEndpoints, ObjectMapper objectMapper) {
        super(Config.class);
        this.unsecuredEndpoints = unsecuredEndpoints;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
         this.isSecured = request -> unsecuredEndpoints.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();
            String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if(isSecured.test(request)) {
                return webClient.get()
                        .uri("http://localhost:8080/validate-token")
                        .header(HttpHeaders.AUTHORIZATION, bearerToken)
                        .retrieve().toBodilessEntity()
                        .map(response -> {
                            exchange.getRequest().mutate().header("user", response.getHeaders().getFirst("user"));
                            exchange.getRequest().mutate().header("authority", response.getHeaders().getFirst("authority"));
                            return exchange;
                        }).flatMap(chain::filter).onErrorResume(error -> {
                            HttpStatusCode code;
                            String message;
                            if (error instanceof WebClientResponseException webClientResponseException) {
                                code = webClientResponseException.getStatusCode();
                                message = webClientResponseException.getStatusText();
                            } else {
                                code = HttpStatus.BAD_GATEWAY;
                                message = HttpStatus.BAD_GATEWAY.getReasonPhrase();
                            }
                            return onError(exchange,
                                    code.value(),
                                    message,
                                    code);
                        });
            }
            return chain.filter(exchange);
        };
    }


    private Mono<Void> onError(ServerWebExchange exchange,
                               Integer code,
                               String status,
                               HttpStatusCode httpStatusCode) {
        DataBufferFactory dataBufferFactory = exchange.getResponse().bufferFactory();
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatusCode);
        try {
            response.getHeaders().add("Content-Type", "application/json");
            Problem errorResponseBody = new Problem(code, status, "Falha na autenticação do token");
            byte[] byteData = objectMapper.writeValueAsBytes(errorResponseBody);
            return response.writeWith(Mono.just(byteData).map(dataBufferFactory::wrap));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response.setComplete();
    }

    public static class Config {
        public Config() {
            // Classe usada para configurar o filtro apenas
        }
    }

}
