package com.greendogdelivery.springboot_greendogdelivery.repository;

import com.greendogdelivery.springboot_greendogdelivery.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNomeContainingIgnoreCase(String termo);
}
