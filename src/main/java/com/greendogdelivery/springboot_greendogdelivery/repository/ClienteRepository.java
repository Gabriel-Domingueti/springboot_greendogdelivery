package com.greendogdelivery.springboot_greendogdelivery.repository;

import com.greendogdelivery.springboot_greendogdelivery.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
