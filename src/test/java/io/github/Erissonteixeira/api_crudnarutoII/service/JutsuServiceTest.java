package io.github.Erissonteixeira.api_crudnarutoII.service;

import io.github.Erissonteixeira.api_crudnarutoII.dto.JutsuRequestDTO;
import io.github.Erissonteixeira.api_crudnarutoII.dto.JutsuResponseDTO;
import io.github.Erissonteixeira.api_crudnarutoII.exception.ResourceNotFoundException;
import io.github.Erissonteixeira.api_crudnarutoII.mapper.JutsuMapper;
import io.github.Erissonteixeira.api_crudnarutoII.model.Jutsu;
import io.github.Erissonteixeira.api_crudnarutoII.repository.JutsuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JutsuServiceTest {

    @Mock
    private JutsuRepository jutsuRepository;

    @Mock
    private JutsuMapper jutsuMapper;

    @InjectMocks
    private JutsuService jutsuService;

    private Jutsu jutsu;
    private JutsuRequestDTO dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        jutsu = new Jutsu();
        jutsu.setId(1L);
        jutsu.setNome("Rasengan");
        jutsu.setDano(150);
        jutsu.setConsumoChakra(50);
        jutsu.setChakraNecessario(50);

        dto = new JutsuRequestDTO();
        dto.setNome("Rasengan");
        dto.setDano(150);
        dto.setConsumoChakra(50);
        dto.setChakraNecessario(50);
    }

    @Test
    void deveCriarJutsuComSucesso() {
        when(jutsuMapper.toEntity(dto)).thenReturn(jutsu);
        when(jutsuRepository.save(any(Jutsu.class))).thenReturn(jutsu);
        when(jutsuMapper.toResponseDTO(any(Jutsu.class))).thenReturn(new JutsuResponseDTO());

        JutsuResponseDTO response = jutsuService.criar(dto);

        assertNotNull(response);
        assertEquals(50, jutsu.getChakraNecessario());
        verify(jutsuRepository, times(1)).save(jutsu);
    }

    @Test
    void deveBuscarJutsuPorId() {
        when(jutsuRepository.findById(1L)).thenReturn(Optional.of(jutsu));
        when(jutsuMapper.toResponseDTO(jutsu)).thenReturn(new JutsuResponseDTO());

        JutsuResponseDTO response = jutsuService.buscarPorId(1L);

        assertNotNull(response);
        verify(jutsuRepository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoJutsuNaoEncontrado() {
        when(jutsuRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> jutsuService.buscarPorId(99L));
    }

    @Test
    void deveListarTodosJutsus() {
        when(jutsuRepository.findAll()).thenReturn(List.of(jutsu));
        when(jutsuMapper.toResponseDTO(any(Jutsu.class))).thenReturn(new JutsuResponseDTO());

        List<JutsuResponseDTO> lista = jutsuService.listarTodos();

        assertEquals(1, lista.size());
    }
}
