package io.github.Erissonteixeira.api_crudnarutoII.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JutsuRequestDTO {

    @NotBlank(message = "O nome do jutsu não pode estar vazio")
    @Schema(description = "Nome do jutsu", example = "Rasengan")
    private String nome;

    @Min(value = 1, message = "O dano deve ser maior que 0")
    @Schema(description = "Dano do jutsu", example = "150")
    private int dano;

    @Min(value = 1, message = "O consumo de chakra deve ser maior que 0")
    @Schema(description = "Consumo de chakra do jutsu", example = "50")
    private int consumoChakra;

    @Min(value = 1, message = "O chakra necessário deve ser maior que 0")
    @Schema(description = "Chakra necessário do jutsu", example = "50")
    private int chakraNecessario;
}
