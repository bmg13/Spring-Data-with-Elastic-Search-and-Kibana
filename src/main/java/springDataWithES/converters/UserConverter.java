package springDataWithES.converters;

import springDataWithES.models.Entities.Author;
import springDataWithES.models.Entities.User;

import java.util.ArrayList;
import java.util.List;

import static springDataWithES.utils.BaseUtils.isNull;


public class UserConverter implements BaseConverter<User, springDataWithES.models.DTO.User> {

    private static final String NULL_EXCEPTION_MESSAGE = "Object provided cannot be null.";
    private final FilmConverter filmConverter = new FilmConverter();

    @Override
    public springDataWithES.models.DTO.User convertToDto(User toDto) {
        if(!isNull(toDto)){
            if (toDto instanceof Author) {
                return new springDataWithES.models.DTO.User(toDto.getName());
            }
            return new springDataWithES.models.DTO.User(
                    toDto.getId(), toDto.getName(), filmConverter.convertToDtoList(toDto.getFavouriteFilms()));
        }

        throw new NullPointerException(NULL_EXCEPTION_MESSAGE);
    }

    @Override
    public User convertToEntity(springDataWithES.models.DTO.User toEntity) {
        if(!isNull(toEntity)){
            if (toEntity instanceof springDataWithES.models.DTO.Author) {
                return new User(toEntity.getName());
            }

            return new User(toEntity.getId(), toEntity.getName(),
                    filmConverter.convertToEntityList(toEntity.getFavouriteFilms()));
        }

        throw new NullPointerException(NULL_EXCEPTION_MESSAGE);
    }

    @Override
    public List<springDataWithES.models.DTO.User> convertToDtoList(List<User> toDto) {
        if(!isNull(toDto)){
            List<springDataWithES.models.DTO.User> dtoList = new ArrayList<>();
            toDto.forEach(entity -> dtoList.add(convertToDto(entity)));
            return dtoList;
        }

        throw new NullPointerException(NULL_EXCEPTION_MESSAGE);
    }

    @Override
    public List<User> convertToEntityList(List<springDataWithES.models.DTO.User> toEntity) {
        if(!isNull(toEntity)){
            List<User> entityList = new ArrayList<>();
            toEntity.forEach(entity -> entityList.add(convertToEntity(entity)));
            return entityList;
        }

        throw new NullPointerException(NULL_EXCEPTION_MESSAGE);
    }
}
