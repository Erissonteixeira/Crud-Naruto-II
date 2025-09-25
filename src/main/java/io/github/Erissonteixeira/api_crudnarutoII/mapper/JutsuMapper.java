package io.github.Erissonteixeira.api_crudnarutoII.mapper;

import io.github.Erissonteixeira.api_crudnarutoII.dto.JutsuRequestDTO;
import io.github.Erissonteixeira.api_crudnarutoII.dto.JutsuResponseDTO;
import io.github.Erissonteixeira.api_crudnarutoII.model.Jutsu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JutsuMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "personagens", ignore = true)
    Jutsu toEntity(JutsuRequestDTO dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "dano", source = "dano")
    @Mapping(target = "consumoChakra", source = "consumoChakra")
    JutsuResponseDTO toResponseDTO(Jutsu jutsu);
}