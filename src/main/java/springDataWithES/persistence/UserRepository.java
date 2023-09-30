package springDataWithES.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.stereotype.Repository;
import springDataWithES.models.Entities.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"user.name\": \"?0\"}}]}}")
    Page<User> findByUserNameUsingCustomQuery(String name, Pageable pageable);
}