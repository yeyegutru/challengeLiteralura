package com.example.alura.challenge.literalura.literalura;

import com.example.alura.challenge.literalura.literalura.Repository.AutorRepository;
import com.example.alura.challenge.literalura.literalura.Repository.LibrosRepository;
import com.example.alura.challenge.literalura.literalura.dtos.Datos;
import com.example.alura.challenge.literalura.literalura.dtos.DetallesAutor;
import com.example.alura.challenge.literalura.literalura.dtos.DetallesLibro;
import com.example.alura.challenge.literalura.literalura.models.Autor;
import com.example.alura.challenge.literalura.literalura.models.Libros;
import com.example.alura.challenge.literalura.literalura.service.ConsumoAPI;
import com.example.alura.challenge.literalura.literalura.service.ConvierteDatos;

import java.util.*;

public class GestionPrincipal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<Libros> librosRegistrados = new ArrayList<>();
    private List<Autor> autoresRegistrados = new ArrayList<>();
    private Datos datos;
    private AutorRepository autorRepositorio;
    private LibrosRepository librosRepositorio;

    public GestionPrincipal(AutorRepository autorRepositorio, LibrosRepository librosRepositorio) {
        this.autorRepositorio = autorRepositorio;
        this.librosRepositorio = librosRepositorio;
    }

    public void mostrarMenu() {
        int opcion = 1;
        do {
            var menu = """
                    ELIJA UNA OPCIÓN:
                    1- BUSCAR LIBROS POR TÍTULO
                    2- LISTAR LIBROS
                    3- LISTAR AUTORES
                    4- LISTAR AUTORES VIVOS EN UN AÑO DETERMINADO
                    5- LISTAR LIBROS POR IDIOMA
                    6- CONSULTAR AUTOR POR NOMBRE
                    0- SALIR
                    """;

            try {
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1 -> buscarLibroPorTituloWeb();
                    case 2 -> listarLibrosRegistrados();
                    case 3 -> listarAutoresRegistrados();
                    case 4 -> listarAutoresVivosEnUnDeterminadoAnio();
                    case 5 -> listarLibrosPorIdioma();
                    case 6 -> consultarAutorPorNombre();
                    case 0 -> System.out.println("\nCERRANDO APP....................\n");
                    default -> System.out.println("\n-------------------- ELIJA UNA OPCIÓN VÁLIDA --------------------\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n---------------------- OPCIÓN NO VÁLIDA ----------------------\n");
                teclado.nextLine();
            }
        } while (opcion != 0);
    }

    private Datos obtenerDatosLibro() {
        System.out.println("¿Qué libro deseas consultar?");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatosAPI(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        return conversor.obtenerDatos(json, Datos.class);
    }

    private Libros crearLibro(DetallesLibro detallesLibro, Autor autor) {
        Libros libro = new Libros(detallesLibro, autor);
        return librosRepositorio.save(libro);
    }

    private void buscarLibroPorTituloWeb() {
        Datos datos = obtenerDatosLibro();
        if (!datos.listaLibros().isEmpty()) {
            DetallesLibro detallesLibro = datos.listaLibros().get(0);
            DetallesAutor detallesAutor = detallesLibro.listaAutores().get(0);
            Libros libroDb = librosRepositorio.findByTitulo(detallesLibro.titulo());

            if (libroDb != null) {
                System.out.println(libroDb);
            } else {
                Autor autorDb = autorRepositorio.findByNombreCompletoIgnoreCase(detallesAutor.nombreCompleto());
                if (autorDb == null) {
                    Autor autor = new Autor(detallesAutor);
                    autor = autorRepositorio.save(autor);
                    Libros libro = crearLibro(detallesLibro, autor);
                    System.out.println(libro);
                } else {
                    Libros libro = crearLibro(detallesLibro, autorDb);
                    System.out.println(libro);
                }
            }
        } else {
            System.out.println("\nEL LIBRO NO EXISTE..............\n");
        }
    }

    private void listarLibrosRegistrados() {
        librosRegistrados = librosRepositorio.findAll();
        librosRegistrados.stream()
                .sorted(Comparator.comparing(libro -> libro.getAutor().getNombreCompleto()))
                .forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        autoresRegistrados = autorRepositorio.findAll();
        autoresRegistrados.stream()
                .sorted(Comparator.comparing(Autor::getNombreCompleto))
                .forEach(System.out::println);
    }

    private void listarAutoresVivosEnUnDeterminadoAnio() {
        System.out.println("INGRESE UN AÑO PARA VALIDAR AUTORES VIVOS: ");
        try {
            int anioConsulta = teclado.nextInt();
            teclado.nextLine();
            List<Autor> autoresVivos = autorRepositorio.findAutoresVivosEnAnio(anioConsulta);

            if (autoresVivos.isEmpty()) {
                System.out.println("\n------------------* NO HAY AUTORES VIVOS EN EL AÑO SELECCIONADO ------------------*\n");
            } else {
                autoresVivos.forEach(System.out::println);
            }

        } catch (InputMismatchException e) {
            System.out.println("\n----------------------* INGRESE EL AÑO COMO UN NÚMERO ----------------------*\n");
            teclado.nextLine();
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("\nIDIOMAS DISPONIBLES:\n1 - Español\n2 - Inglés\n3 - Francés\n4 - Portugués\n");
        var opcion = teclado.nextInt();
        teclado.nextLine();

        String idioma = switch (opcion) {
            case 1 -> "es";
            case 2 -> "en";
            case 3 -> "fr";
            case 4 -> "pt";
            default -> null;
        };

        if (idioma == null) {
            System.out.println("\n------------------------* OPCIÓN NO VÁLIDA ------------------------\n");
            return;
        }

        List<Libros> librosPorIdioma = librosRepositorio.findByIdiomaContaining(idioma);
        if (librosPorIdioma.isEmpty()) {
            System.out.println("\n------------ NO HAY LIBROS PARA EL IDIOMA SELECCIONADO ------------\n");
        } else {
            System.out.println("\n---------------- HAY " + librosPorIdioma.size() + " LIBROS EN ESTE IDIOMA ----------------\n");
            librosPorIdioma.forEach(System.out::println);
        }
    }

 

    private void consultarAutorPorNombre() {
        System.out.println("INGRESE UN AUTOR A CONSULTAR: ");
        var nombreAutor = teclado.nextLine();
        Autor autor = autorRepositorio.findByNombreCompletoIgnoreCase(nombreAutor);

        if (autor != null) {
            List<Libros> librosDelAutor = librosRepositorio.findByAutor(autor);
            System.out.println("\n-------------------------- AUTOR --------------------------\n" + autor);
            System.out.println("\n--------------------* LIBROS ESCRITOS --------------------*\n");
            librosDelAutor.forEach(System.out::println);
        } else {
            System.out.println("\n---------- NO SE ENCONTRÓ UN AUTOR CON ESE NOMBRE ----------\n");
        }
    }
}
