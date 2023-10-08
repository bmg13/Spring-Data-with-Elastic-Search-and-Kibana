package springDataWithES.converters;

import springDataWithES.models.Entities.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorConverter implements BaseConverter<Author, springDataWithES.models.DTO.Author> {

    @Override
    public springDataWithES.models.DTO.Author convertToDto(Author toDto) {
        return new springDataWithES.models.DTO.Author(toDto.getName());
    }

    @Override
    public Author convertToEntity(springDataWithES.models.DTO.Author toEntity) {
        return new Author(toEntity.getName());
    }

    @Override
    public List<springDataWithES.models.DTO.Author> convertToDtoList(List<Author> toDto) {
        List<springDataWithES.models.DTO.Author> dtoList = new ArrayList<>();
        toDto.forEach(entity -> dtoList.add(convertToDto(entity)));
        return dtoList;
    }

    @Override
    public List<Author> convertToEntityList(List<springDataWithES.models.DTO.Author> toEntity) {
        List<Author> entityList = new ArrayList<>();
        toEntity.forEach(entity -> entityList.add(convertToEntity(entity)));
        return entityList;
    }
}
