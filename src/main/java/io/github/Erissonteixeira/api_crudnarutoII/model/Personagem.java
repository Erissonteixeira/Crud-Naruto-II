package io.github.Erissonteixeira.api_crudnarutoII.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@Table(name = "personagens")
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private int vida = 100;
    @Column(nullable = false)
    private int chakra = 100;
    @Transient
    private Map<String, Jutsu> jutsus = new HashMap<>();

    public Personagem(){
    }

    public Personagem(String nome, int vida, int chakra){
        this.nome = nome;
        this.vida = vida;
        this.chakra = chakra;
    }
    public void adicionarJutsu(Jutsu jutsu){
        this.jutsus.put(jutsu.getNome(), jutsu);
    }


}
