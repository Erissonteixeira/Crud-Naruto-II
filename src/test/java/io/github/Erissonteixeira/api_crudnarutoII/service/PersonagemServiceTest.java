package io.github.Erissonteixeira.api_crudnarutoII.service;

import io.github.Erissonteixeira.api_crudnarutoII.dto.PersonagemRequestDTO;
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

public class PersonagemServiceTest {
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
    void setUp(){
        MockitoAnnotations.openMocks(this);
        personagem = new Personagem("Naruto", 100, 100); // atributo da classe
        jutsu = new Jutsu("Rasengan", 50, 50);           // atributo da classe
    }

    @Test
    void criarPersonagemComJutsu(){
        PersonagemRequestDTO dto = new PersonagemRequestDTO();
        dto.setNome("Naruto");
        dto.setVida(100);
        dto.setChakra(100);
        dto.setJutsusIds(List.of(1L));
    }
}
