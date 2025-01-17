package com.example.alura.challenge.literalura.literalura.models;

import com.example.alura.challenge.literalura.literalura.dtos.DetallesAutor;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutor;

    @Column(unique = true, nullable = false)
    private String nombreCompleto;

    @Column(name = "anio_nacimiento")
    private int anioNacimiento;

    @Column(name = "anio_muerte")
    private int anioMuerte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libros> coleccionLibros;

    public Autor() {
    }

    public Autor(DetallesAutor detallesAutor) {
        this.nombreCompleto = detallesAutor.nombreCompleto();
        this.anioNacimiento = Integer.parseInt(detallesAutor.anioNacimiento());
        this.anioMuerte = Integer.parseInt(detallesAutor.anioMuerte());
    }

    @Override
    public String toString() {
        return "-------------------------------------------------------------\n" +
                "   Nombre Completo: " + nombreCompleto + "\n" +
                "   Año de Nacimiento: " + anioNacimiento + "\n" +
                "   Año de Muerte: " + anioMuerte + "\n" +
                "-------------------------------------------------------------";
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(int anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public int getAnioMuerte() {
        return anioMuerte;
    }

    public void setAnioMuerte(int anioMuerte) {
        this.anioMuerte = anioMuerte;
    }

    public List<Libros> getColeccionLibros() {
        return coleccionLibros;
    }

    public void setColeccionLibros(List<Libros> coleccionLibros) {
        this.coleccionLibros = coleccionLibros;
    }
}