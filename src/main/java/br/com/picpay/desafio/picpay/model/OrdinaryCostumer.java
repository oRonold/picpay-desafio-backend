package br.com.picpay.desafio.picpay.model;

import br.com.picpay.desafio.picpay.dto.CostumerEnrollDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_ordinary_costumer")
public class OrdinaryCostumer extends User implements UserDetails {

    @Column(nullable = false, unique = true)
    private String CPF;

    @OneToMany(mappedBy = "ordinaryCostumer", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public OrdinaryCostumer(CostumerEnrollDTO dto){
        this.CPF = dto.CPF();
        this.setFullName(dto.fullName());
        this.setBalance(dto.balance());
        this.setEmail(dto.email());
        this.transactions = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return getEmail();
    }
}
