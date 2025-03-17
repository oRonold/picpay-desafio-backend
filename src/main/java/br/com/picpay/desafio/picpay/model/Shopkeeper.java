package br.com.picpay.desafio.picpay.model;

import br.com.picpay.desafio.picpay.dto.ShopkeeperEnrollDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_shopkeeper")
public class Shopkeeper extends User{

    @Column(nullable = false, unique = true)
    private String CNPJ;

    @OneToMany(mappedBy = "shopkeeper", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public Shopkeeper(ShopkeeperEnrollDTO dto){
        this.CNPJ = dto.CNPJ();
        this.setFullName(dto.fullName());
        this.setBalance(BigDecimal.ZERO);
        this.setEmail(dto.email());
        this.setPassword(dto.password());
        this.transactions = new ArrayList<>();
    }
}
