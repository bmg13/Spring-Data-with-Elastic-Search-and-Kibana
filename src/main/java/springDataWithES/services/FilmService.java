package springDataWithES.services;

import springDataWithES.models.DTO.Film;

import java.util.List;

public interface FilmService {

    Film retrieveFilmByName(String name);

    List<Film> retrieveAllFilms();

    void addFilm(Film film);

    void deleteFilm(String title);

    void deleteAllFilms();
}
