package br.com.picpay.desafio.picpay.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthorizationService {

    private final WebClient webClient;

    public AuthorizationService(@Qualifier("authorizeWebClient") WebClient webClient){
        this.webClient = webClient;
    }

    public Mono<Boolean> authorize(){
        return webClient.get()
                .retrieve()
                .bodyToMono(String.class)
                .map(StringUtils::hasText)
                .defaultIfEmpty(false);
    }
}
