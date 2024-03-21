package springDataWithES.converters;

import springDataWithES.models.DTO.Author;
import springDataWithES.models.Entities.Film;

import java.util.ArrayList;
import java.util.List;

import static springDataWithES.utils.BaseUtils.isNull;

public class FilmConverter implements BaseConverter<Film, springDataWithES.models.DTO.Film> {

    private static final String NULL_EXCEPTION_MESSAGE = "Object provided cannot be null.";
    private final AuthorConverter authorConverter = new AuthorConverter();

    @Override
    public springDataWithES.models.DTO.Film convertToDto(Film toDto) {
        if(!isNull(toDto)){
            Author director = authorConverter.convertToDto(toDto.getDirector());
            return new springDataWithES.models.DTO.Film(
                    toDto.getId(), toDto.getTitle(), toDto.getGenre(), director, toDto.getDateOfRelease());
        }

        throw new NullPointerException(NULL_EXCEPTION_MESSAGE);
    }

    @Override
    public Film convertToEntity(springDataWithES.models.DTO.Film toEntity) {
        if(!isNull(toEntity)){
            springDataWithES.models.Entities.Author director = authorConverter.convertToEntity(toEntity.director());
            return new Film(
                    toEntity.id(), toEntity.title(), toEntity.genre(), director, toEntity.dateOfRelease());
        }

        throw new NullPointerException(NULL_EXCEPTION_MESSAGE);
    }

    @Override
    public List<springDataWithES.models.DTO.Film> convertToDtoList(List<Film> toDto) {
        if(!isNull(toDto)){
            List<springDataWithES.models.DTO.Film> dtoList = new ArrayList<>();
            toDto.forEach(entity -> dtoList.add(convertToDto(entity)));
            return dtoList;
        }

        throw new NullPointerException(NULL_EXCEPTION_MESSAGE);
    }

    @Override
    public List<Film> convertToEntityList(List<springDataWithES.models.DTO.Film> toEntity) {
        if(!isNull(toEntity)){
            List<Film> entityList = new ArrayList<>();
            toEntity.forEach(entity -> entityList.add(convertToEntity(entity)));
            return entityList;
        }

        throw new NullPointerException(NULL_EXCEPTION_MESSAGE);
    }
}
