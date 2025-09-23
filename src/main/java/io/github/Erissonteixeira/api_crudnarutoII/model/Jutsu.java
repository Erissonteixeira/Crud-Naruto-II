package io.github.Erissonteixeira.api_crudnarutoII.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "jutsus")
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
    @ManyToMany(mappedBy = "jutsus")
    private List<Personagem> personagens = new ArrayList<>();

    public Jutsu(){
    }

    public Jutsu(String nome, int dano, int consumoChakra){
        this.nome = nome;
        this.dano = dano;
        this.consumoChakra = consumoChakra;
    }
}
