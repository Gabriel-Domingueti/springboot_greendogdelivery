package com.greendogdelivery.springboot_greendogdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greendogdelivery.springboot_greendogdelivery.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
 
}
