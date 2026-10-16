package com.greendogdelivery.springboot_greendogdelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.greendogdelivery.springboot_greendogdelivery.domain.Item;

/**
 * ItemRepository
 */
@RepositoryRestResource(collectionResourceRel="itens", path="itens")
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByNomeContainingIgnoreCase(String termo);
}
