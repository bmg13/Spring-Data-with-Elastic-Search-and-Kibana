package springDataWithES.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springDataWithES.converters.UserConverter;
import springDataWithES.models.DTO.User;
import springDataWithES.persistence.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserConverter userConverter = new UserConverter();
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User retrieveUserByName(String name) {
        return userConverter.convertToDto(this.userRepository.findByUserNameUsingCustomQuery(name));
    }

    @Override
    public List<User> retrieveAllUsers() {
        return userConverter.convertToDtoList(toList(this.userRepository.findAll()));
    }

    @Override
    public void addUser(User user) {
        if (user != null) {
            springDataWithES.models.Entities.User targetUser = userConverter.convertToEntity(user);
            this.userRepository.save(targetUser);
        }
    }

    @Override
    public void deleteUser(User user) {
        if (user != null) {
            springDataWithES.models.Entities.User targetUser = userConverter.convertToEntity(user);
            this.userRepository.delete(targetUser);
        }
    }

    private List<springDataWithES.models.Entities.User> toList(Iterable<springDataWithES.models.Entities.User> iterable) {
        List<springDataWithES.models.Entities.User> users = new ArrayList<>();
        iterable.iterator().forEachRemaining(users::add);
        return users;
    }
}
