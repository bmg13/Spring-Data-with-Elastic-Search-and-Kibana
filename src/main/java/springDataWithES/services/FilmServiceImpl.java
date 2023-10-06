package springDataWithES.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springDataWithES.converters.FilmConverter;
import springDataWithES.models.DTO.Film;
import springDataWithES.persistence.FilmRepository;

import java.util.List;
import java.util.UUID;

import static springDataWithES.utils.FilmUtils.toList;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmConverter filmConverter = new FilmConverter();

    private FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Film retrieveFilmByName(String title) {
        return filmConverter.convertToDto(this.filmRepository.findByFilmNameUsingCustomQuery(title));
    }

    @Override
    public List<Film> retrieveAllFilms() {
        return filmConverter.convertToDtoList(toList(this.filmRepository.findAll()));
    }

    @Override
    public void addFilm(Film film) {
        if (film != null && this.filmRepository.findByFilmNameUsingCustomQuery(film.getTitle()) == null) {
            springDataWithES.models.Entities.Film targetFilm = filmConverter.convertToEntity(film);
            targetFilm.setId(UUID.randomUUID().toString());
            this.filmRepository.save(targetFilm);
        }
    }

    @Override
    public void deleteFilm(String title) {
        if (!title.isEmpty()) {
            springDataWithES.models.Entities.Film toDelete = this.filmRepository.findByFilmNameUsingCustomQuery(title);
            this.filmRepository.delete(toDelete);
        }
    }

    @Override
    public void deleteAllFilms() {
        this.filmRepository.deleteAll();
    }
}
