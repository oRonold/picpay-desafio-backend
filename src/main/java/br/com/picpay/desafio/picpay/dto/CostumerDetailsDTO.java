package br.com.picpay.desafio.picpay.dto;

import br.com.picpay.desafio.picpay.model.OrdinaryCostumer;

import java.math.BigDecimal;

public record CostumerDetailsDTO(Long id, String fullName, BigDecimal balance, String CPF, String email, String password) {

    public CostumerDetailsDTO(OrdinaryCostumer costumer){
        this(costumer.getId(), costumer.getFullName(), costumer.getBalance(), costumer.getCPF(), costumer.getEmail(), costumer.getPassword());
    }
}
