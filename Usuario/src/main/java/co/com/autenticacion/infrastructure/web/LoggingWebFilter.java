package co.com.autenticacion.infrastructure.web;

import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Configuration
public class LoggingWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, org.springframework.web.server.WebFilterChain chain) {
        String reqId = UUID.randomUUID().toString().substring(0, 8);
        MDC.put("reqId", reqId);
        return chain.filter(exchange)
                .doFinally(signal -> MDC.remove("reqId"));
    }
}
