package io.github.Erissonteixeira.api_crudnarutoII.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @ManyToMany
    @JoinTable(
            name = "personagem_jutsu",
            joinColumns = @JoinColumn(name = "personagem_id"),
            inverseJoinColumns = @JoinColumn(name = "jutsu_id")
    )
    private List<Jutsu> jutsus = new ArrayList<>();

    @Transient
    private Map<String, Jutsu> jutsusMap = new HashMap<>();

    public Personagem() {
    }

    public Personagem(String nome, int vida, int chakra) {
        this.nome = nome;
        this.vida = vida;
        this.chakra = chakra;
    }

    public void adicionarJutsu(Jutsu jutsu) {
        if (jutsusMap.containsKey(jutsu.getNome())) {
            throw new IllegalArgumentException("Jutsu já adicionado ao personagem!");
        }
        this.jutsus.add(jutsu);
        this.jutsusMap.put(jutsu.getNome(), jutsu);
    }

    public void removerJutsu(String nomeJutsu) {
        Jutsu jutsu = jutsusMap.get(nomeJutsu);
        if (jutsu == null) {
            throw new IllegalArgumentException("Este personagem não possui o Jutsu informado!");
        }
        this.jutsus.remove(jutsu);
        this.jutsusMap.remove(nomeJutsu);
    }

    @PostLoad
    public void carregarMap() {
        jutsusMap.clear();
        for (Jutsu j : jutsus) {
            jutsusMap.put(j.getNome(), j);
        }
    }

    public boolean isDerrotado() {
        return this.vida <= 0;
    }
}