package br.com.picpay.desafio.picpay.dto;

import br.com.picpay.desafio.picpay.model.Transaction;

import java.math.BigDecimal;

public record TransferDetailsDTO(BigDecimal value, Long payer, Long payee) {

    public TransferDetailsDTO(Transaction transaction){
        this(transaction.getAmount(), transaction.getOrdinaryCostumer().getId(), transaction.getShopkeeper().getId());
    }
}
