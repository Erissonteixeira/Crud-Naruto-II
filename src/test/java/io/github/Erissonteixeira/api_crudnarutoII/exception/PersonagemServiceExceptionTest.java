package io.github.Erissonteixeira.api_crudnarutoII.exception;

import io.github.Erissonteixeira.api_crudnarutoII.model.Jutsu;
import io.github.Erissonteixeira.api_crudnarutoII.model.Personagem;
import io.github.Erissonteixeira.api_crudnarutoII.repository.JutsuRepository;
import io.github.Erissonteixeira.api_crudnarutoII.repository.PersonagemRepository;
import io.github.Erissonteixeira.api_crudnarutoII.service.PersonagemService;
import io.github.Erissonteixeira.api_crudnarutoII.mapper.PersonagemMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PersonagemServiceExceptionTest{

    @Mock
    private PersonagemRepository personagemRepository;

    @Mock
    private JutsuRepository jutsuRepository;

    @Mock
    private PersonagemMapper personagemMapper;

    @InjectMocks
    private PersonagemService personagemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void adicionarJutsu_personagemNaoEncontrado_deveLancarResourceNotFoundException(){

        when(personagemRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> personagemService.adicionarJutsu(1L, 1L));

        verify(personagemRepository).findById(1L);
    }

    @Test
    void adicionarJutsu_jutsuNaoEncontrado_deveLancarResourceNotFoundException(){
        // Arrange
        Personagem personagem = new Personagem();
        personagem.setId(1L);
        personagem.setChakra(100);

        when(personagemRepository.findById(1L)).thenReturn(Optional.of(personagem));
        when(jutsuRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> personagemService.adicionarJutsu(1L, 1L));

        verify(personagemRepository).findById(1L);
        verify(jutsuRepository).findById(1L);
    }

    @Test
    void adicionarJutsu_chakraInsuficiente_deveLancarInvalidActionException(){
        // Arrange
        Personagem personagem = new Personagem();
        personagem.setId(1L);
        personagem.setChakra(5);

        Jutsu jutsu = new Jutsu();
        jutsu.setId(1L);
        jutsu.setNome("Rasengan");
        jutsu.setConsumoChakra(50);

        when(personagemRepository.findById(1L)).thenReturn(Optional.of(personagem));
        when(jutsuRepository.findById(1L)).thenReturn(Optional.of(jutsu));

        assertThrows(InvalidActionException.class,
                () -> personagemService.adicionarJutsu(1L, 1L));

        verify(personagemRepository).findById(1L);
        verify(jutsuRepository).findById(1L);
    }
}
