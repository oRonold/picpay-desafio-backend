package br.com.picpay.desafio.picpay.service;

import br.com.picpay.desafio.picpay.model.OrdinaryCostumer;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.token.secret}")
    private String tokenSecret;

    public String createToken(OrdinaryCostumer costumer){
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            return JWT.create()
                    .withIssuer("PLUSOFT API")
                    .withSubject(costumer.getEmail())
                    .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                    .sign(algorithm);
        } catch (JWTCreationException e){
            throw new RuntimeException("error when generating token");
        }
    }

    public String getSubject(String token){
        try {
            var algoritmo = Algorithm.HMAC256(token);
            return JWT.require(algoritmo)
                    .withIssuer("PICPAY CHALLENGE")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT invalid or expired");
        }
    }
}
