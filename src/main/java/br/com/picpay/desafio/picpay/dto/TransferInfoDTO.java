package br.com.picpay.desafio.picpay.dto;

import java.math.BigDecimal;

public record TransferInfoDTO(BigDecimal value, Long payer, Long payee) {
}
