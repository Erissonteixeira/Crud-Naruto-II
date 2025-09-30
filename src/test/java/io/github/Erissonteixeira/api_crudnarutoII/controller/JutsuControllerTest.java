package io.github.Erissonteixeira.api_crudnarutoII.controller;

import io.github.Erissonteixeira.api_crudnarutoII.dto.JutsuRequestDTO;
import io.github.Erissonteixeira.api_crudnarutoII.dto.JutsuResponseDTO;
import io.github.Erissonteixeira.api_crudnarutoII.service.JutsuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JutsuControllerTest {

    @Mock
    private JutsuService jutsuService;

    @InjectMocks
    private JutsuController jutsuController;

    private JutsuRequestDTO requestDTO;
    private JutsuResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        requestDTO = new JutsuRequestDTO();
        requestDTO.setNome("Rasengan");
        requestDTO.setDano(150);
        requestDTO.setConsumoChakra(50);
        requestDTO.setChakraNecessario(50);

        responseDTO = new JutsuResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setNome("Rasengan");
        responseDTO.setDano(150);
        responseDTO.setConsumoChakra(50);
        responseDTO.setChakraNecessario(50);
    }

    @Test
    void criarJutsu_deveRetornarJutsuCriado() {
        when(jutsuService.criar(any(JutsuRequestDTO.class))).thenReturn(responseDTO);

        ResponseEntity<JutsuResponseDTO> response = jutsuController.criar(requestDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(responseDTO);
        verify(jutsuService, times(1)).criar(requestDTO);
    }

    @Test
    void listarTodosJutsus_deveRetornarLista() {
        when(jutsuService.listarTodos()).thenReturn(List.of(responseDTO));

        ResponseEntity<List<JutsuResponseDTO>> response = jutsuController.listarTodos();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactly(responseDTO);
        verify(jutsuService, times(1)).listarTodos();
    }

    @Test
    void buscarJutsuPorId_deveRetornarJutsu() {
        when(jutsuService.buscarPorId(1L)).thenReturn(responseDTO);

        ResponseEntity<JutsuResponseDTO> response = jutsuController.buscarPorId(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(responseDTO);
        verify(jutsuService, times(1)).buscarPorId(1L);
    }

    @Test
    void atualizarJutsu_deveRetornarJutsuAtualizado() {
        JutsuResponseDTO updatedResponse = new JutsuResponseDTO();
        updatedResponse.setId(1L);
        updatedResponse.setNome("Rasengan Atualizado");
        updatedResponse.setDano(200);
        updatedResponse.setConsumoChakra(60);
        updatedResponse.setChakraNecessario(80);

        when(jutsuService.atualizar(eq(1L), any(JutsuRequestDTO.class))).thenReturn(updatedResponse);

        ResponseEntity<JutsuResponseDTO> response = jutsuController.atualizar(1L, requestDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(updatedResponse);
        verify(jutsuService, times(1)).atualizar(1L, requestDTO);
    }

    @Test
    void deletarJutsu_deveRetornarNoContent() {
        doNothing().when(jutsuService).deletar(1L);

        ResponseEntity<Void> response = jutsuController.deletar(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(jutsuService, times(1)).deletar(1L);
    }
}
