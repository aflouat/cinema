package fr.aflouat.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import fr.aflouat.cinema.services.ICinemaInitService;
import fr.aflouat.cinema.services.ICinemaInitServiceImpl;
//@EntityScan(basePackages = {"fr.aflouat.cinema.entities"})  // scan JPA entities manually

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner{
	@Autowired
	ICinemaInitServiceImpl iCinemaInitServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
		System.out.println("Starting ....");
	}

	public void run(String... args) throws Exception {
		iCinemaInitServiceImpl.initVilles();
		iCinemaInitServiceImpl.initCinemas();
		iCinemaInitServiceImpl.initCategories();
		iCinemaInitServiceImpl.initFilms();
		iCinemaInitServiceImpl.initSalles();
		iCinemaInitServiceImpl.initPlaces();
		iCinemaInitServiceImpl.initProjectionFilms();
		iCinemaInitServiceImpl.initSeances();
		iCinemaInitServiceImpl.initTickets();
	}

}
