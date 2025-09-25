package io.github.Erissonteixeira.api_crudnarutoII.controller;

import io.github.Erissonteixeira.api_crudnarutoII.dto.PersonagemRequestDTO;
import io.github.Erissonteixeira.api_crudnarutoII.dto.PersonagemResponseDTO;
import io.github.Erissonteixeira.api_crudnarutoII.service.PersonagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personagens")
@RequiredArgsConstructor
@Tag(name = "Personagem", description = "Endpoints para gerenciamento de personagens")
public class PersonagemController{
    private final PersonagemService personagemService;

    @PostMapping
    @Operation(summary = "Cria um novo personagem")
    public ResponseEntity<PersonagemResponseDTO> criar(@RequestBody PersonagemRequestDTO dto){
        PersonagemResponseDTO criado = personagemService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }
}
