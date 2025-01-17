package com.example.alura.challenge.literalura.literalura.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DetallesLibro(

        @JsonAlias("titulo") String titulo,
        @JsonAlias("autores") List<DetallesAutor> listaAutores,
        @JsonAlias("idiomas") List<String> idiomasDisponibles,
        @JsonAlias("numero_descargas") int numeroDescargas

) {
}
