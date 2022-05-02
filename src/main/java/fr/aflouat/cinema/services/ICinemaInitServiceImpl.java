package fr.aflouat.cinema.services;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aflouat.cinema.dao.CategoryRepository;
import fr.aflouat.cinema.dao.CinemaRepository;
import fr.aflouat.cinema.dao.FilmRepository;
import fr.aflouat.cinema.dao.PlaceRepository;
import fr.aflouat.cinema.dao.ProjectionFilmRepository;
import fr.aflouat.cinema.dao.SalleRepository;
import fr.aflouat.cinema.dao.SeanceRepository;
import fr.aflouat.cinema.dao.TicketRepository;
import fr.aflouat.cinema.dao.VilleRepository;
import fr.aflouat.cinema.entities.Category;
import fr.aflouat.cinema.entities.Cinema;
import fr.aflouat.cinema.entities.Film;
import fr.aflouat.cinema.entities.Place;
import fr.aflouat.cinema.entities.ProjectionFilm;
import fr.aflouat.cinema.entities.Salle;
import fr.aflouat.cinema.entities.Seance;
import fr.aflouat.cinema.entities.Ticket;
import fr.aflouat.cinema.entities.Ville;
@Service
public class ICinemaInitServiceImpl implements ICinemaInitService {
	@Autowired
	VilleRepository villeRepository;
	
	@Autowired
	CinemaRepository cinemaRepository;
	@Autowired
	SalleRepository salleRepository;
	@Autowired
	PlaceRepository placeRepository;
	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	ProjectionFilmRepository projectionFilmRepository;
	@Autowired
	FilmRepository filmRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	SeanceRepository seanceRepository;

	@Override
	public void initVilles() {
		Stream.of("Casa","Marrakech","Oujda","Rabat").forEach(nom ->{
			Ville ville = new Ville();
			ville.setNom(nom);
			villeRepository.save(ville);
		});
		
	}

	@Override
	public void initCinemas() {
		villeRepository.findAll().forEach(ville -> {
					
		Stream.of("Megarama","IMAX","Cinéma la Renaissance","Cineatlas Colisée").forEach(nom ->{
			Cinema cinema = new Cinema();
			cinema.setNom(nom);
			cinema.setNombreSalles(  (int) (3+Math.random()*7) );
			cinema.setVille( ville);
			cinemaRepository.save(cinema);
		});	
		});
	}

	public void initSalles() {
		 cinemaRepository.findAll().forEach(cinema -> {
			 for (int i=0; i<cinema.getNombreSalles();i++) {
				 Salle salle = new Salle();
				 salle.setName("Salle "+i);
				 salle.setCinema(cinema);
				 salle.setNombrePlaces(10 + (int) Math.random()*15);
				 salleRepository.save(salle);
			 }
		 });
		
	}

	public void initPlaces() {
		salleRepository.findAll().forEach(salle ->{
			Place place = new Place();
			place.setNumero( 1+(int) Math.random()*9 );
			place.setSalle(salle);
			placeRepository.save(place);
		});
		
	}

	@Override
	public void initCategories() {
		Stream.of("Action","Horreur","Comédie","Avanture").forEach(nom ->{
			Category category = new Category();
			category.setNom(nom);
			categoryRepository.save(category);
		});

		 
		
	}

	@Override
	public void initFilms() {
		double durees[] = new double[] {1,1.5,2,2.5,3};
		List<Category> categories = categoryRepository.findAll();
		Stream.of("The batman","The Innocents","Les Vedettes","The Chef").forEach(titre ->{
			Film film = new Film();
			film.setTitre(titre);
			film.setPhoto(film.getTitre().replaceAll(" ", "_"));
			film.setCategory(categories.get(new Random().nextInt(categories.size()) ));
			film.setDuree(durees[new Random().nextInt( durees.length)]);
			filmRepository.save(film);
		});
	 
		
	}

	@Override
	public void initProjectionFilms() {
		double[] prixList = new double[]{40,50,30,60,90};
		villeRepository.findAll().forEach(ville -> {
			cinemaRepository.findAll().forEach(cinema -> {
				filmRepository.findAll().forEach(film -> {
					salleRepository.findAll().forEach(salle -> {
						ProjectionFilm projectionFilm = new ProjectionFilm();
						projectionFilm.setDateProjection(new Date());
						projectionFilm.setSalle(salle);
						projectionFilm.setFilm(film);
						projectionFilm.setPrix(prixList[new Random().nextInt(prixList.length)]);
						projectionFilmRepository.save(projectionFilm);
					});
				});
			});
		});
 		
	}

	@Override
	public void initTickets() {
		String[] nomClients = {"Samir","Ismael","Yanis"};
		projectionFilmRepository.findAll().forEach(projectionFilm -> {
				Ticket ticket = new Ticket();
				ticket.setProjectionFilm(projectionFilm);
				ticket.setNomClient(nomClients[new Random().nextInt(nomClients.length)]);
				ticketRepository.save(ticket);
		});
		
	}

	public void initSeances() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Stream.of("12:00","15:00","17:00","19:00").forEach(heureDebut -> {
			Seance seance = new Seance();
			try {
				seance.setHeureDebut(dateFormat.parse(heureDebut));
				seanceRepository.save(seance);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
 		
	}

}
