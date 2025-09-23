package io.github.Erissonteixeira.api_crudnarutoII.dto;

import lombok.Data;
import java.util.List;

@Data
public class PersonagemResponseDTO {
    private Long id;
    private String nome;
    private int vida;
    private int chakra;
    private List<JutsuResponseDTO> jutsus;
}
