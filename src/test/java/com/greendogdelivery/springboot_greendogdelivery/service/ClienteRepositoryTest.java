package com.greendogdelivery.springboot_greendogdelivery.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.greendogdelivery.springboot_greendogdelivery.domain.Cliente;
import com.greendogdelivery.springboot_greendogdelivery.repository.ClienteRepository;

@SpringBootTest 
public class ClienteRepositoryTest {
    @Autowired 
    ClienteRepository repository;

    @Test 
    public void buscarClientesCadastrados() {
        Page<Cliente> clientes = this.repository.findAll(PageRequest.of(0, 10)); 
        assertThat(clientes.getTotalElements()).isGreaterThan(1L);
    }

    @Test
    public void buscarClienteFernando() {
        Cliente clienteNaoEncontrado = this.repository.findByNome("Fernando");
        assertThat(clienteNaoEncontrado).isNull();

        Cliente cliente = this.repository.findByNome("Fernando Boagilo");
        assertThat(cliente).isNotNull();
        assertThat(cliente.getNome()).isEqualTo("Fernando Boagilo");
        assertThat(cliente.getEndereco()).isEqualTo("Sampa");
    }
}
