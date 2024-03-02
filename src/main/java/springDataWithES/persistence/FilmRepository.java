package springDataWithES.persistence;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import springDataWithES.models.Entities.Film;

import java.util.List;

@Repository
public interface FilmRepository extends ListCrudRepository<Film, String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"title\": \"?0\"}}]}}")
    Film findByFilmNameUsingCustomQuery(String title);

    @Override
    List<Film> findAll();
}