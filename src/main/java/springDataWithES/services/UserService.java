package springDataWithES.services;

import springDataWithES.models.DTO.User;

public interface UserService {

    User retrieveUserByName(String name);

    void addUser(User user);

    void deleteUser(User user);
}
