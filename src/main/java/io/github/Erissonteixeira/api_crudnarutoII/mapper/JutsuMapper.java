package io.github.Erissonteixeira.api_crudnarutoII.mapper;

import io.github.Erissonteixeira.api_crudnarutoII.dto.JutsuRequestDTO;
import io.github.Erissonteixeira.api_crudnarutoII.dto.JutsuResponseDTO;
import io.github.Erissonteixeira.api_crudnarutoII.model.Jutsu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JutsuMapper {
    Jutsu toEntity(JutsuRequestDTO dto);
    JutsuResponseDTO toResponseDTO(Jutsu jutsu);
}
