package io.github.Erissonteixeira.api_crudnarutoII.controller;

import io.github.Erissonteixeira.api_crudnarutoII.service.JutsuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jutsus")
@RequiredArgsConstructor
@Tag(name = "Jutsu", description = "Endpoints para gerenciamento de Jutsus")
public class JutsuController{
    private final JutsuService jutsuService;
}
