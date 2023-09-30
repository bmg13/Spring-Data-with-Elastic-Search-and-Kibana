package springDataWithES.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springDataWithES.models.DTO.Film;
import springDataWithES.services.FilmService;

@RestController
@RequestMapping(
        path = "/film",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class FilmController {

    private FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping(path = "")
    public Film retrieveFilm(@PathVariable String name) {
        return this.filmService.retrieveFilmByName(name);
    }
}