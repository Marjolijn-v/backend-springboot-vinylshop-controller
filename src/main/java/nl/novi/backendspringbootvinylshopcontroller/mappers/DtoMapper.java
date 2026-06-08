package nl.novi.backendspringbootvinylshopcontroller.mappers;

import nl.novi.backendspringbootvinylshopcontroller.Entities.BaseEntity;

import java.util.List;

public interface DtoMapper<RESPONSE, REQUEST, T extends BaseEntity> {
    RESPONSE mapToDto(T model);

    List<RESPONSE> mapToDto(List<T> models);

    T mapToEntity(REQUEST genreModel);
}
