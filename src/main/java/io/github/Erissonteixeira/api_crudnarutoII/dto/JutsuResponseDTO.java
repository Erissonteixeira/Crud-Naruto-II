package io.github.Erissonteixeira.api_crudnarutoII.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class JutsuResponseDTO {
    @Schema(description = "ID do jutsu", example = "1")
    private Long id;
    @Schema(description = "Nome do jutsu", example = "Rasengan")
    private String nome;
    @Schema(description = "Dano do jutsu", example = "150")
    private int dano;
    @Schema(description = "Consumo de chakra do jutsu", example = "50")
    private int consumoChakra;
}
