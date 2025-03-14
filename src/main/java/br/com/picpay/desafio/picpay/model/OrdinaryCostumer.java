package br.com.picpay.desafio.picpay.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_ordinary_costumer")
public class OrdinaryCostumer extends User{

    @Column(nullable = false, unique = true)
    private String CPF;

    @OneToMany(mappedBy = "ordinaryCostumer", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

}
