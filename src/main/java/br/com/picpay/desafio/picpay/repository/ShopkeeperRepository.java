package br.com.picpay.desafio.picpay.repository;

import br.com.picpay.desafio.picpay.model.Shopkeeper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopkeeperRepository extends JpaRepository<Shopkeeper, Long> {
}
