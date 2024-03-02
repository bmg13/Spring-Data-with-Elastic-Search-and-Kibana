package springDataWithES.converters;

import org.springframework.lang.Nullable;

import java.util.List;

public interface BaseConverter<T, G> {

    @Nullable
    G convertToDto(T toDto);

    @Nullable
    T convertToEntity(G toEntity);

    List<G> convertToDtoList(List<T> toDto);

    List<T> convertToEntityList(List<G> toEntity);
}
