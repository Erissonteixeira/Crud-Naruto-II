package io.github.Erissonteixeira.api_crudnarutoII.service;

import io.github.Erissonteixeira.api_crudnarutoII.dto.PersonagemRequestDTO;
import io.github.Erissonteixeira.api_crudnarutoII.dto.PersonagemResponseDTO;
import io.github.Erissonteixeira.api_crudnarutoII.mapper.PersonagemMapper;
import io.github.Erissonteixeira.api_crudnarutoII.model.Personagem;
import io.github.Erissonteixeira.api_crudnarutoII.repository.JutsuRepository;
import io.github.Erissonteixeira.api_crudnarutoII.repository.PersonagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonagemService {
    private final PersonagemRepository personagemRepository;
    private final JutsuRepository jutsuRepository;
    private final PersonagemMapper personagemMapper;

    public PersonagemResponseDTO criar(PersonagemRequestDTO dto){
        Personagem personagem = personagemMapper.toEntity(dto);
        Personagem salvo = personagemRepository.save(personagem);
        return personagemMapper.toResponseDTO(salvo);
    }
}
