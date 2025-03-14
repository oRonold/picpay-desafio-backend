package br.com.picpay.desafio.picpay.service;

import br.com.picpay.desafio.picpay.dto.ShopkeeperEnrollDTO;
import br.com.picpay.desafio.picpay.model.Shopkeeper;
import br.com.picpay.desafio.picpay.repository.ShopkeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopkeeperService {

    @Autowired
    private ShopkeeperRepository repository;

    public Shopkeeper enroll(ShopkeeperEnrollDTO dto){
        return repository.save(new Shopkeeper(dto));
    }
}
