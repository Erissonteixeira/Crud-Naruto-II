package io.github.Erissonteixeira.api_crudnarutoII.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
public class PersonagemRequestDTO {
    @NotBlank(message = "O nome do personagem não pode estar vazio")
    @Schema(description = "Nome do personagem", example = "Naruto Uzumaki")
    private String nome;
    @Min(value = 0, message = "A vida não pode ser negativa")
    @Schema(description = "Vida do personagem", example = "1000")
    private int vida;
    @Min(value = 0, message = "O chakra não pode ser negativo")
    @Schema(description = "Chakra do personagem", example = "5000")
    private int chakra;
    @Schema(description = "IDs dos jutsus associados", example = "[1, 2]")
    private List<@NotNull(message = "O ID do jutsu não pode ser nulo") Long> jutsusIds;
}
