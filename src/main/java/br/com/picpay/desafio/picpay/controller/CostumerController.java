package br.com.picpay.desafio.picpay.controller;

import br.com.picpay.desafio.picpay.dto.CostumerDetailsDTO;
import br.com.picpay.desafio.picpay.dto.CostumerEnrollDTO;
import br.com.picpay.desafio.picpay.model.OrdinaryCostumer;
import br.com.picpay.desafio.picpay.service.CostumerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/costumers")
public class CostumerController {

    @Autowired
    private CostumerService service;

    @PostMapping
    @Transactional
    public ResponseEntity<CostumerDetailsDTO> enroll(@RequestBody @Valid CostumerEnrollDTO dto, UriComponentsBuilder builder){
        OrdinaryCostumer costumer = service.enroll(dto);
        URI uri = builder.path("/{id}").buildAndExpand(costumer.getId()).toUri();
        return ResponseEntity.created(uri).body(new CostumerDetailsDTO(costumer));
    }
}
