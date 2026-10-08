package com.greendogdelivery.springboot_greendogdelivery.domain;

import org.hibernate.validator.constraints.Length;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Item {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull 
    @Length(min = 2, max = 30, message = "O tamanho do nome deve ser entre {min} e {max} caracteres")
    private String nome;

    @NotNull 
    @Min(value=20, message = "O valor mínimo deve ser {value} reais")
    private double preco;

    public Item() {}

    public Item(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public long getId() { return this.id; }
    public String getNome() { return this.nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getPreco() { return this.preco; }
    public void setPreco(double preco) { this.preco = preco; }
}
