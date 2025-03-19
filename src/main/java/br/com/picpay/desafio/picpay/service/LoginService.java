package br.com.picpay.desafio.picpay.service;

import br.com.picpay.desafio.picpay.dto.LoginDTO;
import br.com.picpay.desafio.picpay.model.OrdinaryCostumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    public String login(LoginDTO dto){
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        Authentication auth = manager.authenticate(user);
        return tokenService.createToken((OrdinaryCostumer) auth.getPrincipal());
    }
}
