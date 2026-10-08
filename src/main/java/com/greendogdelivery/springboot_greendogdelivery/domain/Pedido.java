package com.greendogdelivery.springboot_greendogdelivery.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;

@Entity 
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne(optional = true)
    private Cliente cliente;

    @ManyToMany
    private List<Item> itens = new ArrayList<>();

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date data;
    
    @Min(1)
    private double valorTotal;

    public Pedido() {}

    public Pedido(Cliente cliente, List<Item> itens, double valorTotal) {
        this.cliente = cliente;
        this.itens = itens;
        this.valorTotal = valorTotal;
    }

    public long getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public List<Item> getItens() { return itens; }
    public Date getData() { return data; }
    public double getValorTotal() { return valorTotal; }
}