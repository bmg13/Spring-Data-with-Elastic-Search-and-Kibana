package springDataWithES.persistence;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import springDataWithES.models.Entities.User;

import java.util.List;

@Repository
public interface UserRepository extends ListCrudRepository<User, String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}]}}")
    User findByUserNameUsingCustomQuery(String name);

    @Override
    List<User> findAll();
}