package springDataWithES.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springDataWithES.models.DTO.Film;
import springDataWithES.services.FilmService;

import java.util.List;

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

    @GetMapping(path = "/{name}")
    public Film retrieveFilm(@PathVariable String name) {
        return this.filmService.retrieveFilmByName(name);
    }

    @GetMapping(path = "/all")
    public List<Film> retrieveAllFilms() {
        return this.filmService.retrieveAllFilms();
    }

    @PostMapping(path = "/add")
    public void addFilm(@RequestBody Film film) {
        this.filmService.addFilm(film);
    }

    @DeleteMapping(path = "/delete/{title}")
    public void deleteFilm(@PathVariable String title) {
        this.filmService.deleteFilm(title);
    }

    @DeleteMapping(path = "/delete/all")
    public void deleteAllFilms() {
        this.filmService.deleteAllFilms();
    }
}