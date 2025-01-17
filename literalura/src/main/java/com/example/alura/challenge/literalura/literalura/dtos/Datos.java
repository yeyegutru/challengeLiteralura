package com.example.alura.challenge.literalura.literalura.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos(

        @JsonAlias("results") List<DetallesLibro> listaLibros
) {
}
