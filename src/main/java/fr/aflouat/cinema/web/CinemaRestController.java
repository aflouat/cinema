package fr.aflouat.cinema.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.aflouat.cinema.dao.FilmRepository;
import fr.aflouat.cinema.entities.Film;

@RestController
public class CinemaRestController {
	
	@Autowired
	FilmRepository filmRepository;
	@GetMapping(path="/image/film/{id}",produces=MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable (name="id") Long id) throws Exception {
		Film f = filmRepository.findById(id).get();
		String photoName = f.getPhoto();
		File file = new File(System.getProperty("user.home")+"/cinema/images/"+photoName+".jpg");
		
		Path path = Paths.get(file.toURI());
		return Files.readAllBytes(path );
	}

}
