package com.example.alura.challenge.literalura.literalura.Repository;

import com.example.alura.challenge.literalura.literalura.models.Autor;
import com.example.alura.challenge.literalura.literalura.models.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibrosRepository extends JpaRepository<Libros, Long> {

    Libros findByTitulo(String titulo);

    List<Libros> findByIdiomaContaining(String idioma);

    @Query("SELECT COUNT(l) FROM Libros l WHERE l.idioma = :idioma")
    int countByIdioma(@Param("idioma") String idioma);

    List<Libros> findTop10ByOrderByNumeroDescargasDesc();

    List<Libros> findByAutor(Autor autor);

}
