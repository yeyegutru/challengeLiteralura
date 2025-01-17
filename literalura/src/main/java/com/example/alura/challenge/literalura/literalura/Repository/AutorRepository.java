package com.example.alura.challenge.literalura.literalura.Repository;

import com.example.alura.challenge.literalura.literalura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Autor findByNombreCompletoIgnoreCase(String nombreCompleto);

    @Query("SELECT a FROM Autor a WHERE a.anioNacimiento <= :anio AND (:anio < a.anioMuerte OR a.anioMuerte = 0)")
    List<Autor> findAutoresVivosEnAnio(@Param("anio") int anio);

}