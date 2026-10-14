package com.greendogdelivery.springboot_greendogdelivery.repository;

import com.greendogdelivery.springboot_greendogdelivery.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByNomeContainingIgnoreCase(String termo);
}
