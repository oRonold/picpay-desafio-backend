package br.com.picpay.desafio.picpay.service;

import br.com.picpay.desafio.picpay.model.OrdinaryCostumer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class NotificationService {

    private final WebClient webClient;

    public NotificationService(@Qualifier("notificationWebClient") WebClient webClient){
        this.webClient = webClient;
    }

    public Mono<Boolean> notify(OrdinaryCostumer costumer){
        return webClient.post()
                .bodyValue(costumer)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> response.contains("sucesso"))
                .defaultIfEmpty(false);
    }
}
