package br.com.picpay.desafio.picpay.controller;

import br.com.picpay.desafio.picpay.dto.TransferDetailsDTO;
import br.com.picpay.desafio.picpay.dto.TransferInfoDTO;
import br.com.picpay.desafio.picpay.model.Transaction;
import br.com.picpay.desafio.picpay.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class SystemController {

    @Autowired
    private TransactionService service;

    @PostMapping("/transfer")
    @Transactional
    public ResponseEntity<TransferDetailsDTO> transfer(@RequestBody TransferInfoDTO dto, UriComponentsBuilder builder){
        Transaction transaction = service.transfer(dto);
        URI uri = builder.path("/{id}").buildAndExpand(transaction.getId()).toUri();
        return ResponseEntity.created(uri).body(new TransferDetailsDTO(transaction));
    }
}
