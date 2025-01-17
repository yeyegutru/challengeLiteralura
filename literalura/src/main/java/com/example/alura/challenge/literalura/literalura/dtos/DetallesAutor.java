package com.example.alura.challenge.literalura.literalura.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DetallesAutor(

        @JsonAlias("nombre") String nombreCompleto,
        @JsonAlias("anio_nacimiento") String anioNacimiento,
        @JsonAlias("anio_muerte") String anioMuerte

) {
}