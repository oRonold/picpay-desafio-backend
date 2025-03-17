package br.com.picpay.desafio.picpay.controller;

import br.com.picpay.desafio.picpay.dto.ShopkeeperDetailsDTO;
import br.com.picpay.desafio.picpay.dto.ShopkeeperEnrollDTO;
import br.com.picpay.desafio.picpay.model.Shopkeeper;
import br.com.picpay.desafio.picpay.service.ShopkeeperService;
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
@RequestMapping("/shopkeepers")
public class ShopkeeperController {

    @Autowired
    private ShopkeeperService service;

    @PostMapping
    @Transactional
    public ResponseEntity<ShopkeeperDetailsDTO> enroll(@RequestBody @Valid ShopkeeperEnrollDTO dto, UriComponentsBuilder builder){
        Shopkeeper shopkeeper = service.enroll(dto);
        URI uri = builder.path("/{id}").buildAndExpand(shopkeeper.getId()).toUri();
        return ResponseEntity.created(uri).body(new ShopkeeperDetailsDTO(shopkeeper));
    }
}
