package springDataWithES.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springDataWithES.converters.UserConverter;
import springDataWithES.models.DTO.User;
import springDataWithES.persistence.FilmRepository;
import springDataWithES.persistence.UserRepository;

import java.util.List;
import java.util.UUID;

import static springDataWithES.utils.UserUtils.toList;

@Service
public class UserServiceImpl implements UserService {

    private final UserConverter userConverter = new UserConverter();
    private UserRepository userRepository;
    private FilmRepository filmRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, FilmRepository filmRepository) {
        this.userRepository = userRepository;
        this.filmRepository = filmRepository;
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
        if (user != null && this.userRepository.findByUserNameUsingCustomQuery(user.getName()) == null) {
            springDataWithES.models.Entities.User targetUser = userConverter.convertToEntity(user);
            targetUser.setId(UUID.randomUUID().toString());
            addFilmsIfNonExistent(targetUser.getFavouriteFilms());
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

    @Override
    public void deleteAllUsers() {
        this.userRepository.deleteAll();
    }

    private void addFilmsIfNonExistent(List<springDataWithES.models.Entities.Film> favouriteFilms) {
        favouriteFilms
                .stream()
                .filter(film -> this.filmRepository.findByFilmNameUsingCustomQuery(film.getTitle()) == null)
                .forEach(film -> this.filmRepository.save(film));
    }
}
