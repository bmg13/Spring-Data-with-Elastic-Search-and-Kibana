package springDataWithES.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import springDataWithES.models.Entities.Film;

@Repository
public interface FilmRepository extends ElasticsearchRepository<Film, String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"film.name\": \"?0\"}}]}}")
    Page<Film> findByFilmNameUsingCustomQuery(String name, Pageable pageable);
}