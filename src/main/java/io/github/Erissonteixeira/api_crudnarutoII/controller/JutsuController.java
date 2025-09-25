package io.github.Erissonteixeira.api_crudnarutoII.controller;

import io.github.Erissonteixeira.api_crudnarutoII.dto.JutsuRequestDTO;
import io.github.Erissonteixeira.api_crudnarutoII.dto.JutsuResponseDTO;
import io.github.Erissonteixeira.api_crudnarutoII.service.JutsuService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jutsus")
@RequiredArgsConstructor
@Tag(name = "Jutsu", description = "Endpoints para gerenciamento de Jutsus")
public class JutsuController{
    private final JutsuService jutsuService;

    @PostMapping
    @Operation(summary = "Cria um novo jutsu")
    public ResponseEntity<JutsuResponseDTO> criar(@RequestBody JutsuRequestDTO dto) {
        JutsuResponseDTO criado = jutsuService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }
}
