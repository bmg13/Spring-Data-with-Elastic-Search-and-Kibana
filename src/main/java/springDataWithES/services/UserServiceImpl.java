package springDataWithES.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springDataWithES.converters.UserConverter;
import springDataWithES.models.DTO.User;
import springDataWithES.persistence.FilmRepository;
import springDataWithES.persistence.UserRepository;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static springDataWithES.utils.BaseUtils.isNull;
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
        springDataWithES.models.Entities.User targetUser = userConverter.convertToEntity(user);
        Optional<springDataWithES.models.Entities.User> userSaved = retrieveUser(user);
        if (isNull(userSaved) || userSaved.isEmpty()) {
            targetUser.setId(UUID.randomUUID().toString());
            addFilmsIfNonExistent(targetUser.getFavouriteFilms());
            this.userRepository.save(targetUser);
        } else {
            updateUser(user);
        }
    }

    @Override
    public void updateUser(User user) {
        springDataWithES.models.Entities.User targetUser = userConverter.convertToEntity(user);
        Optional<springDataWithES.models.Entities.User> storedUserOptional = retrieveUser(user);
        if (storedUserOptional.isPresent()) {
            springDataWithES.models.Entities.User storedUser = storedUserOptional.get();
            storedUser.setName(targetUser.getName());
            addFilmsIfNonExistent(targetUser.getFavouriteFilms());
            storedUser.setFavouriteFilms(targetUser.getFavouriteFilms());
            this.userRepository.save(storedUser);
        }
    }


    @Override
    public void deleteUser(User user) {
        springDataWithES.models.Entities.User targetUser = userConverter.convertToEntity(user);
        if (!isNull(targetUser)) {
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

    private Optional<springDataWithES.models.Entities.User> retrieveUser(User user) {
        if (!isNull(user.getId())) {
            return this.userRepository.findById(user.getId());
        } else if (!isNull(user.getName())) {
            return Optional.of(this.userRepository.findByUserNameUsingCustomQuery(user.getName()));
        }

        return Optional.empty();
    }
}
