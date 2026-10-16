package com.greendogdelivery.springboot_greendogdelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greendogdelivery.springboot_greendogdelivery.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteNomeContainingIgnoreCase(String termo);
}
