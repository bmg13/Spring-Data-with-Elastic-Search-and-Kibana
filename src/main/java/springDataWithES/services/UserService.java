package springDataWithES.services;

import springDataWithES.models.DTO.User;
import java.util.List;

public interface UserService {

    User retrieveUserByName(String name);

    List<User> retrieveAllUsers();

    void addUser(User user);

    void deleteUser(User user);

    void deleteAllUsers();
}
