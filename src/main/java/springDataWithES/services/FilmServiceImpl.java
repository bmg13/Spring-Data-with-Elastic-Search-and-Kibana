package springDataWithES.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springDataWithES.converters.FilmConverter;
import springDataWithES.models.DTO.Film;
import springDataWithES.persistence.FilmRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static springDataWithES.utils.BaseUtils.isNull;
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
        return filmConverter.convertToDto(retrieveFilmByTitleFromRepo(title));
    }

    @Override
    public List<Film> retrieveAllFilms() {
        return filmConverter.convertToDtoList(toList(this.filmRepository.findAll()));
    }

    @Override
    public void addFilm(Film film) {
        springDataWithES.models.Entities.Film targetFilm = filmConverter.convertToEntity(film);
        if (isNull(retrieveFilmByTitleFromRepo(film.getTitle()))) {
            targetFilm.setId(UUID.randomUUID().toString());
            this.filmRepository.save(targetFilm);
        }
    }

    @Override
    public void updateFilm(Film film) {
        springDataWithES.models.Entities.Film targetFilm = filmConverter.convertToEntity(film);
        Optional<springDataWithES.models.Entities.Film> storedFilmOptional = retrieveFilmByIdFromRepo(film.getId());
        if(storedFilmOptional.isPresent()) {
            springDataWithES.models.Entities.Film storedFilm = storedFilmOptional.get();
            storedFilm.setTitle(targetFilm.getTitle());
            storedFilm.setDirector(targetFilm.getDirector());
            storedFilm.setGenre(targetFilm.getGenre());
            storedFilm.setDateOfRelease(targetFilm.getDateOfRelease());

            this.filmRepository.save(storedFilm);
        }
    }

    @Override
    public void deleteFilm(String title) {
        if (!title.isEmpty()) {
            springDataWithES.models.Entities.Film toDelete = retrieveFilmByTitleFromRepo(title);
            this.filmRepository.delete(toDelete);
        }
    }

    @Override
    public void deleteAllFilms() {
        this.filmRepository.deleteAll();
    }

    private springDataWithES.models.Entities.Film retrieveFilmByTitleFromRepo(String title) {
        return this.filmRepository.findByFilmNameUsingCustomQuery(title);
    }

    private Optional<springDataWithES.models.Entities.Film> retrieveFilmByIdFromRepo(String id) {
        return this.filmRepository.findById(id);
    }
}
