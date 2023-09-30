package springDataWithES.services;

import springDataWithES.models.DTO.Film;

public interface FilmService {

    Film retrieveFilmByName(String name);

    void addFilm(Film user);

    void deleteFilm(Film user);
}
