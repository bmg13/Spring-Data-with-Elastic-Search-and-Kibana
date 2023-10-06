package springDataWithES.converters;

import springDataWithES.models.Entities.Author;
import springDataWithES.models.Entities.User;

import java.util.ArrayList;
import java.util.List;


public class UserConverter implements BaseConverter<User, springDataWithES.models.DTO.User> {

    private final FilmConverter filmConverter = new FilmConverter();

    @Override
    public springDataWithES.models.DTO.User convertToDto(User toDto) {
        if (toDto instanceof Author) {
            return new springDataWithES.models.DTO.User(toDto.getName());
        }
        return new springDataWithES.models.DTO.User(
                toDto.getName(), filmConverter.convertToDtoList(toDto.getFavouriteFilms()));
    }

    @Override
    public User convertToEntity(springDataWithES.models.DTO.User toEntity) {
        if (toEntity instanceof springDataWithES.models.DTO.Author) {
            return new User(toEntity.getName());
        }
        return new User(toEntity.getName(), filmConverter.convertToEntityList(toEntity.getFavouriteFilms()));
    }

    @Override
    public List<springDataWithES.models.DTO.User> convertToDtoList(List<User> toDto) {
        List<springDataWithES.models.DTO.User> dtoList = new ArrayList<>();
        toDto.forEach(entity -> dtoList.add(convertToDto(entity)));
        return dtoList;
    }

    @Override
    public List<User> convertToEntityList(List<springDataWithES.models.DTO.User> toEntity) {
        List<User> entityList = new ArrayList<>();
        toEntity.forEach(entity -> entityList.add(convertToEntity(entity)));
        return entityList;
    }
}
