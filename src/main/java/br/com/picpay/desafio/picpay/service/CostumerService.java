package br.com.picpay.desafio.picpay.service;

import br.com.picpay.desafio.picpay.dto.CostumerDetailsDTO;
import br.com.picpay.desafio.picpay.dto.CostumerEnrollDTO;
import br.com.picpay.desafio.picpay.model.OrdinaryCostumer;
import br.com.picpay.desafio.picpay.repository.OrdinaryCostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CostumerService {

    @Autowired
    private OrdinaryCostumerRepository costumerRepository;

    @Autowired
    private PasswordEncoder encoder;

    public OrdinaryCostumer enroll(CostumerEnrollDTO dto){
        OrdinaryCostumer costumer = new OrdinaryCostumer(dto);
        costumer.setPassword(encoder.encode(dto.password()));
        return costumerRepository.save(costumer);
    }

    @Cacheable(value = "costumersList", key = "'allCostumers'")
    public Page<CostumerDetailsDTO> getAllCostumers(Pageable pageable){
        return costumerRepository.findAll(pageable).map(CostumerDetailsDTO::new);
    }
}
