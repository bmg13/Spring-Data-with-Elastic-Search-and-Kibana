package springDataWithES.persistence;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import springDataWithES.models.Entities.Film;

@Repository
public interface FilmRepository extends ElasticsearchRepository<Film, String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"title\": \"?0\"}}]}}")
    Film findByFilmNameUsingCustomQuery(String title);
}