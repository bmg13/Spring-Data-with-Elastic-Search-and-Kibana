package springDataWithES.converters;

import springDataWithES.models.DTO.Author;
import springDataWithES.models.Entities.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmConverter implements BaseConverter<Film, springDataWithES.models.DTO.Film> {

    private final AuthorConverter authorConverter = new AuthorConverter();

    @Override
    public springDataWithES.models.DTO.Film convertToDto(Film toDto) {
        Author director = authorConverter.convertToDto(toDto.getDirector());
        return new springDataWithES.models.DTO.Film(
                toDto.getTitle(), toDto.getGenre(), director, toDto.getDateOfRelease());
    }

    @Override
    public Film convertToEntity(springDataWithES.models.DTO.Film toEntity) {
        springDataWithES.models.Entities.Author director = authorConverter.convertToEntity(toEntity.getDirector());
        return new Film(toEntity.getTitle(), toEntity.getGenre(), director, toEntity.getDateOfRelease());
    }

    @Override
    public List<springDataWithES.models.DTO.Film> convertToDtoList(List<Film> toDto) {
        List<springDataWithES.models.DTO.Film> dtoList = new ArrayList<>();
        toDto.forEach(entity -> dtoList.add(convertToDto(entity)));
        return dtoList;
    }

    @Override
    public List<Film> convertToEntityList(List<springDataWithES.models.DTO.Film> toEntity) {
        List<Film> entityList = new ArrayList<>();
        toEntity.forEach(entity -> entityList.add(convertToEntity(entity)));
        return entityList;
    }
}
