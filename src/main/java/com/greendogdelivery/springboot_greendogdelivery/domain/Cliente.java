package com.greendogdelivery.springboot_greendogdelivery.domain;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity 
public class Cliente {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Length(min = 2, max = 30, message = "O tamanho do nome deve ser entre {min} e {max} caracteres")
    private String nome;

    @NotNull
    @Length(min=2, max=300, message = "O tamanho do endereço deve ser entre {min} e {max} caracteres")
    private String endereco;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = jakarta.persistence.CascadeType.ALL)
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {}

    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public long getId(){ return this.id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public List<Pedido> getPedidos() { return pedidos; }
    public void novoPedido(Pedido pedido) { this.pedidos.add(pedido); }
}
