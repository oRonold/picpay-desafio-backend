package br.com.picpay.desafio.picpay.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ordinary_costumer", nullable = false)
    private OrdinaryCostumer ordinaryCostumer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_shopkeeper", nullable = false)
    private Shopkeeper shopkeeper;
}
