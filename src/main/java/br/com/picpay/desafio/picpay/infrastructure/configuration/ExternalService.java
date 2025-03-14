package br.com.picpay.desafio.picpay.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ExternalService {

    @Bean
    @Qualifier("authorizeWebClient")
    public WebClient authorizeWebClient(WebClient.Builder builder){
        return builder.baseUrl("https://util.devi.tools/api/v2/authorize").build();
    }

    @Bean
    @Qualifier("notificationWebClient")
    public WebClient notificationWebClient(WebClient.Builder builder){
        return builder.baseUrl("https://util.devi.tools/api/v1/notify").build();
    }
}
