package springDataWithES.persistence;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.stereotype.Repository;
import springDataWithES.models.Entities.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}]}}")
    User findByUserNameUsingCustomQuery(String name);
}