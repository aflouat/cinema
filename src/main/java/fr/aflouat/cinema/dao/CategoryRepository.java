package fr.aflouat.cinema.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.aflouat.cinema.entities.Category;
import fr.aflouat.cinema.entities.Cinema;
import fr.aflouat.cinema.entities.Salle;
@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
