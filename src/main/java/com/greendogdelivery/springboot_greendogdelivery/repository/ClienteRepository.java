package com.greendogdelivery.springboot_greendogdelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greendogdelivery.springboot_greendogdelivery.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNomeContainingIgnoreCase(String termo);
    Cliente findByNome(String nome);
}
