package br.com.picpay.desafio.picpay.repository;

import br.com.picpay.desafio.picpay.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
