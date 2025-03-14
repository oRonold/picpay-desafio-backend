package br.com.picpay.desafio.picpay.service;

import br.com.picpay.desafio.picpay.dto.CostumerEnrollDTO;
import br.com.picpay.desafio.picpay.model.OrdinaryCostumer;
import br.com.picpay.desafio.picpay.repository.OrdinaryCostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CostumerService {

    @Autowired
    private OrdinaryCostumerRepository costumerRepository;

    public OrdinaryCostumer enroll(CostumerEnrollDTO dto){
        return costumerRepository.save(new OrdinaryCostumer(dto));
    }
}
