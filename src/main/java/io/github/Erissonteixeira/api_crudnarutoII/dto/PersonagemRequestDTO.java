package io.github.Erissonteixeira.api_crudnarutoII.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
public class PersonagemRequestDTO {
    @NotBlank(message = "O nome do personagem não pode estar vazio")
    private String nome;
    @Min(value = 0, message = "A vida não pode ser negativa")
    private int vida;
    @Min(value = 0, message = "O chakra não pode ser negativo")
    private int chakra;
    private List<@NotNull(message = "O ID do jutsu não pode ser nulo") Long> jutsusIds;
}
