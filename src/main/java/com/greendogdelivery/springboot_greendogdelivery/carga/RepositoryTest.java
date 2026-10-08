package com.greendogdelivery.springboot_greendogdelivery.carga;

import com.greendogdelivery.springboot_greendogdelivery.domain.Item;
import com.greendogdelivery.springboot_greendogdelivery.domain.Pedido;
import com.greendogdelivery.springboot_greendogdelivery.domain.Cliente;
import com.greendogdelivery.springboot_greendogdelivery.repository.ClienteRepository;
import com.greendogdelivery.springboot_greendogdelivery.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RepositoryTest implements ApplicationRunner {
    @Autowired 
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ItemRepository itemRepository;

    @Override 
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println(">>> Iniciando carga de dados...");
        Cliente fernando = new Cliente("Fernando Boaglio", "Sampa");
        Cliente zePequeno = new Cliente("Zé Pequeno", "Cidade de Deus");

        Item dog1 = new Item("Green dog tradicional", 25d);
        Item dog2 = new Item("Green Dog tradicional picante", 27d);
        Item dog3 = new Item("Green Dog max salada", 30d);

        itemRepository.saveAll(Arrays.asList(dog1, dog2, dog3));

        List<Item> listaPedidoFernando1 = new ArrayList<>();
        listaPedidoFernando1.add(dog1);

        List<Item> listaPedidoZePequeno1 = new ArrayList<>();
        listaPedidoZePequeno1.add(dog2);
        listaPedidoZePequeno1.add(dog3);

        Pedido pedidoDoFernando = new Pedido(fernando, listaPedidoFernando1, dog1.getPreco());
        fernando.novoPedido(pedidoDoFernando);

        Pedido pedidoDoZepequeno = new Pedido(zePequeno, listaPedidoZePequeno1, dog2.getPreco() + dog3.getPreco());
        zePequeno.novoPedido(pedidoDoZepequeno);

        System.out.println(">>> Pedido 1 - Fernando: "+ pedidoDoFernando);
        System.out.println(">>> Pedido 2 - Ze Pequeno: "+ pedidoDoZepequeno);

        clienteRepository.saveAndFlush(zePequeno);
        System.out.println(">>> Gravado cliente 2: "+zePequeno);

        List<Item> listaPedidoFernando2 = new ArrayList<>();
        listaPedidoFernando2.add(dog2);

        Pedido pedido2DoFernando = new Pedido(fernando, listaPedidoFernando2, dog2.getPreco());
        fernando.novoPedido(pedido2DoFernando);

        clienteRepository.saveAndFlush(fernando);
        System.out.println(">>> Pedido 2 - Fernando: " + pedido2DoFernando);
        System.out.println(">>> Gravado cliente 1: " + fernando);
    }
}