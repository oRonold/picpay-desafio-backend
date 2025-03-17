package br.com.picpay.desafio.picpay.dto;

import br.com.picpay.desafio.picpay.model.Shopkeeper;

import java.math.BigDecimal;

public record ShopkeeperDetailsDTO(Long id, String fullName, BigDecimal bigDecimal, String email, String password, String CNPJ) {

    public ShopkeeperDetailsDTO(Shopkeeper shopkeeper){
        this(shopkeeper.getId(), shopkeeper.getFullName(), shopkeeper.getBalance(), shopkeeper.getEmail(), shopkeeper.getPassword(), shopkeeper.getCNPJ());
    }
}
