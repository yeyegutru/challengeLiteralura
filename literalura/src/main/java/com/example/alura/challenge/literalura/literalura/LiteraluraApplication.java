package com.example.alura.challenge.literalura.literalura;

import com.example.alura.challenge.literalura.literalura.Repository.AutorRepository;
import com.example.alura.challenge.literalura.literalura.Repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication  implements CommandLineRunner {
	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private LibrosRepository libroRepository;
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		GestionPrincipal principal = new GestionPrincipal(autorRepository, libroRepository);
		principal.mostrarMenu();
	}
}