package com.greendogdelivery.springboot_greendogdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greendogdelivery.springboot_greendogdelivery.domain.Pedido;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteNomeContainingIgnoreCase(String termo);
}
