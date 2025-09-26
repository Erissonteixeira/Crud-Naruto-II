package io.github.Erissonteixeira.api_crudnarutoII.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
public class PersonagemResponseDTO {
    @Schema(description = "ID do personagem", example = "1")
    private Long id;
    @Schema(description = "Nome do personagem", example = "Naruto Uzumaki")
    private String nome;
    @Schema(description = "Vida do personagem", example = "1000")
    private int vida;
    @Schema(description = "Chakra do personagem", example = "5000")
    private int chakra;
    @Schema(description = "Lista de jutsus associados ao personagem")
    private List<JutsuResponseDTO> jutsus;
}
