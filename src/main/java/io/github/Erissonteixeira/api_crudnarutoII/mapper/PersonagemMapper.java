package io.github.Erissonteixeira.api_crudnarutoII.mapper;

import io.github.Erissonteixeira.api_crudnarutoII.dto.PersonagemRequestDTO;
import io.github.Erissonteixeira.api_crudnarutoII.dto.PersonagemResponseDTO;
import io.github.Erissonteixeira.api_crudnarutoII.model.Personagem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {JutsuMapper.class})
public interface PersonagemMapper{
    @Mapping(target = "jutsus", source = "jutsus")
    Personagem toEntity(PersonagemRequestDTO dto, java.util.List<io.github.Erissonteixeira.api_crudnarutoII.model.Jutsu> jutsus);
    PersonagemResponseDTO toDTO(Personagem personagem);
}
