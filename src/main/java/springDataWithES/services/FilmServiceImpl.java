package springDataWithES.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springDataWithES.models.DTO.Film;
import springDataWithES.persistence.FilmRepository;

@Service
public class FilmServiceImpl implements FilmService {

    private FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Film retrieveFilmByName(String name) {
        // TODO: add converter for Film
        return null;
    }

    @Override
    public void addFilm(Film user) {

    }

    @Override
    public void deleteFilm(Film user) {

    }
}
