package br.com.picpay.desafio.picpay.repository;

import br.com.picpay.desafio.picpay.model.OrdinaryCostumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdinaryCostumerRepository extends JpaRepository<OrdinaryCostumer, Long> {

    OrdinaryCostumer findByEmail(String subject);
}
