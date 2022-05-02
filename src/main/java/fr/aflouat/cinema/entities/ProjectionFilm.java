package fr.aflouat.cinema.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity 
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class ProjectionFilm {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dateProjection;
	private double prix;
	@OneToMany(mappedBy = "projectionFilm")
	private Collection<Seance> seances;
	
	@OneToMany(mappedBy = "projectionFilm")
	private Collection<Ticket> tickets;
	@ManyToOne
	private Salle salle;
	@ManyToOne
	private Film film;
	

}
