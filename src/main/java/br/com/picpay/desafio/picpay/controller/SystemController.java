package br.com.picpay.desafio.picpay.controller;

import br.com.picpay.desafio.picpay.dto.LoginDTO;
import br.com.picpay.desafio.picpay.dto.TokenGivenDTO;
import br.com.picpay.desafio.picpay.dto.TransferDetailsDTO;
import br.com.picpay.desafio.picpay.dto.TransferInfoDTO;
import br.com.picpay.desafio.picpay.model.Transaction;
import br.com.picpay.desafio.picpay.service.LoginService;
import br.com.picpay.desafio.picpay.service.TransactionService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class SystemController {

    @Autowired
    private TransactionService service;

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<TokenGivenDTO> login(@RequestBody @Valid LoginDTO dto){
        String token = loginService.login(dto);
        return ResponseEntity.ok(new TokenGivenDTO(token));
    }

    @PostMapping("/transfer")
    @Transactional
    public ResponseEntity<TransferDetailsDTO> transfer(@RequestBody TransferInfoDTO dto, UriComponentsBuilder builder){
        Transaction transaction = service.transfer(dto);
        URI uri = builder.path("/{id}").buildAndExpand(transaction.getId()).toUri();
        return ResponseEntity.created(uri).body(new TransferDetailsDTO(transaction));
    }
}
