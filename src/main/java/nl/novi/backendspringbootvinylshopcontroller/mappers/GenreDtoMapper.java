package nl.novi.backendspringbootvinylshopcontroller.mappers;

import nl.novi.backendspringbootvinylshopcontroller.Entities.GenreEntity;
import nl.novi.backendspringbootvinylshopcontroller.dtos.genre.GenreRequestDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.genre.GenreResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreDtoMapper implements DtoMapper<GenreResponseDto, GenreRequestDto, GenreEntity> {

    @Override
    public GenreResponseDto mapToDto(GenreEntity model){
        GenreResponseDto result = new GenreResponseDto();
        result.setId(model.getId());
        result.setName(model.getName());
        result.setDescription(model.getDescription());
        return result;
    }


    @Override
    public List<GenreResponseDto> mapToDto(List<GenreEntity> models){
        var result = new ArrayList<GenreResponseDto>();
        for (GenreEntity model : models) {
            result.add(mapToDto(model));
        }
        return result;

    }

    @Override
    public GenreEntity mapToEntity(GenreRequestDto genreModel) {
        var result = new GenreEntity();
        result.setName(genreModel.getName());
        result.setDescription(genreModel.getDescription());
        return result;
    }
}
