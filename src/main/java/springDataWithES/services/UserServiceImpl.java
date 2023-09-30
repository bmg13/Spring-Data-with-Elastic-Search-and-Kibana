package springDataWithES.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springDataWithES.models.DTO.User;
import springDataWithES.persistence.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User retrieveUserByName(String name) {
        // TODO: add converter for User
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }
}
