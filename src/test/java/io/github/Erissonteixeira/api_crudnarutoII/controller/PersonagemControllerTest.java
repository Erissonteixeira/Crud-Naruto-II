package io.github.Erissonteixeira.api_crudnarutoII.controller;

import io.github.Erissonteixeira.api_crudnarutoII.dto.PersonagemRequestDTO;
import io.github.Erissonteixeira.api_crudnarutoII.dto.PersonagemResponseDTO;
import io.github.Erissonteixeira.api_crudnarutoII.service.PersonagemService;
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

class PersonagemControllerTest {

    @Mock
    private PersonagemService personagemService; // Mockito puro

    @InjectMocks
    private PersonagemController personagemController;

    private PersonagemRequestDTO requestDTO;
    private PersonagemResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // DTOs de exemplo para testes
        requestDTO = new PersonagemRequestDTO();
        requestDTO.setNome("Naruto");
        requestDTO.setVida(1000);
        requestDTO.setChakra(5000);

        responseDTO = new PersonagemResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setNome("Naruto");
        responseDTO.setVida(1000);
        responseDTO.setChakra(5000);
        responseDTO.setJutsus(List.of());
    }

    @Test
    void deveCriarPersonagemComSucesso() {
        when(personagemService.criar(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<PersonagemResponseDTO> response = personagemController.criar(requestDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(responseDTO);
        verify(personagemService, times(1)).criar(requestDTO);
    }

    @Test
    void deveListarTodosPersonagens() {
        when(personagemService.listarTodos()).thenReturn(List.of(responseDTO));

        ResponseEntity<List<PersonagemResponseDTO>> response = personagemController.listarTodos();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactly(responseDTO);
        verify(personagemService, times(1)).listarTodos();
    }

    @Test
    void deveBuscarPersonagemPorId() {
        when(personagemService.buscarPorId(1L)).thenReturn(responseDTO);

        ResponseEntity<PersonagemResponseDTO> response = personagemController.buscarPorId(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(responseDTO);
        verify(personagemService, times(1)).buscarPorId(1L);
    }

    @Test
    void deveAtualizarPersonagem() {
        when(personagemService.atualizar(1L, requestDTO)).thenReturn(responseDTO);

        ResponseEntity<PersonagemResponseDTO> response = personagemController.atualizar(1L, requestDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(responseDTO);
        verify(personagemService, times(1)).atualizar(1L, requestDTO);
    }

    @Test
    void deveDeletarPersonagem() {
        doNothing().when(personagemService).deletar(1L);

        ResponseEntity<Void> response = personagemController.deletar(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(personagemService, times(1)).deletar(1L);
    }

    @Test
    void deveAdicionarJutsuAoPersonagem() {
        when(personagemService.adicionarJutsu(1L, 1L)).thenReturn(responseDTO);

        ResponseEntity<PersonagemResponseDTO> response = personagemController.adicionarJutsu(1L, 1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(responseDTO);
        verify(personagemService, times(1)).adicionarJutsu(1L, 1L);
    }

    @Test
    void deveRemoverJutsuDoPersonagem() {
        when(personagemService.removerJutsu(1L, "Rasengan")).thenReturn(responseDTO);

        ResponseEntity<PersonagemResponseDTO> response = personagemController.removerJutsu(1L, "Rasengan");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(responseDTO);
        verify(personagemService, times(1)).removerJutsu(1L, "Rasengan");
    }
}
