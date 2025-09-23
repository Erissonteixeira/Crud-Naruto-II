package io.github.Erissonteixeira.api_crudnarutoII.dto;

import lombok.Data;

@Data
public class JutsuResponseDTO {
    private Long id;
    private String nome;
    private int dano;
    private int consumoChakra;
}
