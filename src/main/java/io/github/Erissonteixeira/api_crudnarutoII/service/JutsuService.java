package io.github.Erissonteixeira.api_crudnarutoII.service;

import io.github.Erissonteixeira.api_crudnarutoII.dto.JutsuRequestDTO;
import io.github.Erissonteixeira.api_crudnarutoII.dto.JutsuResponseDTO;
import io.github.Erissonteixeira.api_crudnarutoII.exception.ResourceNotFoundException;
import io.github.Erissonteixeira.api_crudnarutoII.mapper.JutsuMapper;
import io.github.Erissonteixeira.api_crudnarutoII.model.Jutsu;
import io.github.Erissonteixeira.api_crudnarutoII.repository.JutsuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JutsuService {

    private final JutsuRepository jutsuRepository;
    private final JutsuMapper jutsuMapper;

    public JutsuResponseDTO criar(JutsuRequestDTO dto) {
        Jutsu jutsu = jutsuMapper.toEntity(dto);
        Jutsu salvo = jutsuRepository.save(jutsu);
        return jutsuMapper.toResponseDTO(salvo);
    }

    public List<JutsuResponseDTO> listarTodos() {
        return jutsuRepository.findAll()
                .stream()
                .map(jutsuMapper::toResponseDTO)
                .toList();
    }

    public JutsuResponseDTO buscarPorId(Long id) {
        Jutsu jutsu = jutsuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jutsu não encontrado com ID: " + id));
        return jutsuMapper.toResponseDTO(jutsu);
    }

    public JutsuResponseDTO atualizar(Long id, JutsuRequestDTO dto) {
        Jutsu jutsu = jutsuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jutsu não encontrado com ID: " + id));

        jutsu.setNome(dto.getNome());
        jutsu.setDano(dto.getDano());
        jutsu.setConsumoChakra(dto.getConsumoChakra());

        Jutsu atualizado = jutsuRepository.save(jutsu);
        return jutsuMapper.toResponseDTO(atualizado);
    }

    public void deletar(Long id) {
        Jutsu jutsu = jutsuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jutsu não encontrado com ID: " + id));
        jutsuRepository.delete(jutsu);
    }
}