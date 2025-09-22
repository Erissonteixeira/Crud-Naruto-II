package io.github.Erissonteixeira.api_crudnarutoII.model;

import jakarta.persistence.*;

@Entity
public class Jutsu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private int dano;
    @Column(nullable = false)
    private int consumoChakra;

    public Jutsu(){
    }

    public Jutsu(String nome, int dano, int consumoChakra){
        this.nome = nome;
        this.dano = dano;
        this.consumoChakra = consumoChakra;
    }
}
