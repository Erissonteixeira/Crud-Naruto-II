package io.github.Erissonteixeira.api_crudnarutoII.service;

import io.github.Erissonteixeira.api_crudnarutoII.dto.JutsuRequestDTO;
import io.github.Erissonteixeira.api_crudnarutoII.exception.ResourceNotFoundException;
import io.github.Erissonteixeira.api_crudnarutoII.mapper.JutsuMapper;
import io.github.Erissonteixeira.api_crudnarutoII.repository.JutsuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class JutsuServiceExceptionTest{

    private JutsuRepository jutsuRepository;
    private JutsuMapper jutsuMapper;
    private JutsuService jutsuService;

    @BeforeEach
    void setUp() {
        jutsuRepository = mock(JutsuRepository.class);
        jutsuMapper = mock(JutsuMapper.class);
        jutsuService = new JutsuService(jutsuRepository, jutsuMapper);
    }

    @Test
    void buscarPorId_quandoNaoEncontrado_deveLancarResourceNotFoundException(){
        when(jutsuRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> jutsuService.buscarPorId(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Jutsu não encontrado com ID: 1");

        verify(jutsuRepository, times(1)).findById(1L);
    }

    @Test
    void atualizar_quandoNaoEncontrado_deveLancarResourceNotFoundException(){
        JutsuRequestDTO request = new JutsuRequestDTO();
        request.setNome("Chidori");
        request.setDano(100);
        request.setConsumoChakra(50);
        request.setChakraNecessario(40);

        when(jutsuRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> jutsuService.atualizar(99L, request))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Jutsu não encontrado com ID: 99");

        verify(jutsuRepository, times(1)).findById(99L);
    }

    @Test
    void deletar_quandoNaoEncontrado_deveLancarResourceNotFoundException(){
        when(jutsuRepository.findById(5L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> jutsuService.deletar(5L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Jutsu não encontrado com ID: 5");

        verify(jutsuRepository, times(1)).findById(5L);
    }
}
