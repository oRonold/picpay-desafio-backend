package br.com.picpay.desafio.picpay.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record CostumerEnrollDTO(
        @NotBlank
        String fullName,
        @NotNull
        BigDecimal balance,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
        @Pattern(regexp = "\\d+", message = "CPF only accepts numbers")
        String CPF) {
}
