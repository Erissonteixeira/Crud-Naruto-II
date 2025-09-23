package io.github.Erissonteixeira.api_crudnarutoII.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JutsuRequestDTO {
    @NotBlank(message = "O nome do jutsu não pode estar vazio")
    private String nome;
    @Min(value = 1, message = "O dano deve ser maior que 0")
    private int dano;
    @Min(value = 1, message = "O consumo de chakra deve ser maior que 0")
    private int consumoChakra;
}
