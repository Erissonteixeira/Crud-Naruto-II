package io.github.Erissonteixeira.api_crudnarutoII.service;

import io.github.Erissonteixeira.api_crudnarutoII.dto.PersonagemRequestDTO;
import io.github.Erissonteixeira.api_crudnarutoII.dto.PersonagemResponseDTO;
import io.github.Erissonteixeira.api_crudnarutoII.exception.InvalidActionException;
import io.github.Erissonteixeira.api_crudnarutoII.exception.ResourceNotFoundException;
import io.github.Erissonteixeira.api_crudnarutoII.mapper.PersonagemMapper;
import io.github.Erissonteixeira.api_crudnarutoII.model.Jutsu;
import io.github.Erissonteixeira.api_crudnarutoII.model.Personagem;
import io.github.Erissonteixeira.api_crudnarutoII.repository.JutsuRepository;
import io.github.Erissonteixeira.api_crudnarutoII.repository.PersonagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<PersonagemResponseDTO> listarTodos(){
        return personagemRepository.findAll()
                .stream()
                .map(personagemMapper::toResponseDTO)
                .toList();
    }
    public PersonagemResponseDTO buscarPorId(Long id){
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Personagem não encontrado com ID: " + id));
        return personagemMapper.toResponseDTO(personagem);
    }
    public PersonagemResponseDTO atualizar(Long id, PersonagemRequestDTO dto){
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personagem não encontrado com ID: " + id));
        personagem.setNome(dto.getNome());
        personagem.setChakra(dto.getChakra());
        personagem.setVida(dto.getVida());
        Personagem atualizado = personagemRepository.save(personagem);
        return personagemMapper.toResponseDTO(atualizado);
    }
    public void deletar(Long id){
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personagem não encontrado com ID: " + id));
        personagemRepository.delete(personagem);
    }
    public PersonagemResponseDTO adicionarJutsu(Long personagemId, Long jutsuId){
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new ResourceNotFoundException("Personagem não encontrado com ID: " + personagemId));
        Jutsu jutsu = jutsuRepository.findById(jutsuId)
                .orElseThrow(() -> new ResourceNotFoundException("Jutsu não encontrado com ID: " + jutsuId));
        if(personagem.getChakra() < 10){
            throw new InvalidActionException("Chakra insuficiente para aprender este Jutsu!");
        }
        personagem.getJutsus().add(jutsu);
        Personagem atualizado = personagemRepository.save(personagem);
        return personagemMapper.toResponseDTO(atualizado);
    }

}
