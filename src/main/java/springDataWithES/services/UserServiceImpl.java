package springDataWithES.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springDataWithES.converters.UserConverter;
import springDataWithES.models.DTO.User;
import springDataWithES.persistence.FilmRepository;
import springDataWithES.persistence.UserRepository;

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
        return userConverter.convertToDto(retrieveUserByNameFromRepo(name));
    }

    @Override
    public List<User> retrieveAllUsers() {
        return userConverter.convertToDtoList(toList(this.userRepository.findAll()));
    }

    @Override
    public void addUser(User user) {
        springDataWithES.models.Entities.User targetUser = userConverter.convertToEntity(user);
        if (isNull(retrieveUserByNameFromRepo(user.getName()))) {
            targetUser.setId(UUID.randomUUID().toString());
            addFilmsIfNonExistent(targetUser.getFavouriteFilms());
            this.userRepository.save(targetUser);
        }
    }

    @Override
    public void updateUser(User user) {
        springDataWithES.models.Entities.User targetUser = userConverter.convertToEntity(user);
        Optional<springDataWithES.models.Entities.User> storedUserOptional = retrieveUserByIdFromRepo(user.getId());
        if(storedUserOptional.isPresent()){
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

    private springDataWithES.models.Entities.User retrieveUserByNameFromRepo(String name) {
        return this.userRepository.findByUserNameUsingCustomQuery(name);
    }

    private Optional<springDataWithES.models.Entities.User> retrieveUserByIdFromRepo(String id) {
        return this.userRepository.findById(id);
    }
}
