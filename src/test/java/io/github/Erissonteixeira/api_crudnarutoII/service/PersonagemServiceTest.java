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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonagemServiceTest {

    @Mock
    private PersonagemRepository personagemRepository;

    @Mock
    private JutsuRepository jutsuRepository;

    @Mock
    private PersonagemMapper personagemMapper;

    @InjectMocks
    private PersonagemService personagemService;

    private Personagem personagem;
    private Jutsu jutsu;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        personagem = new Personagem();
        personagem.setId(1L);
        personagem.setNome("Naruto");
        personagem.setVida(100);
        personagem.setChakra(200);

        jutsu = new Jutsu();
        jutsu.setId(1L);
        jutsu.setNome("Rasengan");
    }

    @Test
    void deveCriarPersonagemComSucesso() {
        PersonagemRequestDTO dto = new PersonagemRequestDTO();
        dto.setNome("Naruto");
        dto.setVida(100);
        dto.setChakra(200);

        when(personagemMapper.toEntity(dto)).thenReturn(personagem);
        when(personagemRepository.save(any(Personagem.class))).thenReturn(personagem);
        when(personagemMapper.toResponseDTO(any(Personagem.class))).thenReturn(new PersonagemResponseDTO());

        PersonagemResponseDTO response = personagemService.criar(dto);

        assertNotNull(response);
        verify(personagemRepository, times(1)).save(personagem);
    }

    @Test
    void deveBuscarPersonagemPorId() {
        when(personagemRepository.findById(1L)).thenReturn(Optional.of(personagem));
        when(personagemMapper.toResponseDTO(personagem)).thenReturn(new PersonagemResponseDTO());

        PersonagemResponseDTO response = personagemService.buscarPorId(1L);

        assertNotNull(response);
        verify(personagemRepository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoPersonagemNaoEncontrado() {
        when(personagemRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> personagemService.buscarPorId(99L));
    }

    @Test
    void deveAdicionarJutsuAoPersonagem() {
        when(personagemRepository.findById(1L)).thenReturn(Optional.of(personagem));
        when(jutsuRepository.findById(1L)).thenReturn(Optional.of(jutsu));
        when(personagemRepository.save(personagem)).thenReturn(personagem);
        when(personagemMapper.toResponseDTO(any(Personagem.class))).thenReturn(new PersonagemResponseDTO());

        PersonagemResponseDTO response = personagemService.adicionarJutsu(1L, 1L);

        assertNotNull(response);
        assertTrue(personagem.getJutsus().contains(jutsu));
    }

    @Test
    void deveLancarExcecaoQuandoChakraInsuficiente() {
        personagem.setChakra(5); // chakra insuficiente
        when(personagemRepository.findById(1L)).thenReturn(Optional.of(personagem));
        when(jutsuRepository.findById(1L)).thenReturn(Optional.of(jutsu));

        assertThrows(InvalidActionException.class, () -> personagemService.adicionarJutsu(1L, 1L));
    }

    @Test
    void deveRemoverJutsuDoPersonagem() {
        personagem.adicionarJutsu(jutsu);
        when(personagemRepository.findById(1L)).thenReturn(Optional.of(personagem));
        when(personagemRepository.save(personagem)).thenReturn(personagem);
        when(personagemMapper.toResponseDTO(any(Personagem.class))).thenReturn(new PersonagemResponseDTO());

        PersonagemResponseDTO response = personagemService.removerJutsu(1L, "Rasengan"); // CORRETO → String

        assertNotNull(response);
        assertFalse(personagem.getJutsus().contains(jutsu));
    }

    @Test
    void deveListarTodosPersonagens() {
        when(personagemRepository.findAll()).thenReturn(List.of(personagem));
        when(personagemMapper.toResponseDTO(any(Personagem.class))).thenReturn(new PersonagemResponseDTO());

        List<PersonagemResponseDTO> lista = personagemService.listarTodos();

        assertEquals(1, lista.size());
    }
}
