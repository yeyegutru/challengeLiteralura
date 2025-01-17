package com.example.alura.challenge.literalura.literalura.models;


import com.example.alura.challenge.literalura.literalura.dtos.DetallesLibro;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibro;

    @Column(unique = true, nullable = false)
    private String titulo;

    @Column(name = "nombre_autor")
    private String nombreAutor;

    @Column(nullable = false)
    private String idioma;

    @Column(name = "numero_descargas")
    private int numeroDescargas;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    public Libros() {
    }

    public Libros(DetallesLibro detallesLibro, Autor autor) {
        this.titulo = detallesLibro.titulo();
        this.nombreAutor = autor.getNombreCompleto();
        this.idioma = detallesLibro.idiomasDisponibles().get(0);
        this.numeroDescargas = detallesLibro.numeroDescargas();
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "******************************************************************\n" +
                "   Título: " + titulo + "\n" +
                "   Nombre del Autor: " + nombreAutor + "\n" +
                "   Idioma: " + idioma + "\n" +
                "   Número de Descargas: " + numeroDescargas + "\n" +
                "******************************************************************";
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(int numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
