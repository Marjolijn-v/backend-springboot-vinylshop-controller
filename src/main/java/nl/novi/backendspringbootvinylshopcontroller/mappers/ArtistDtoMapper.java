package nl.novi.backendspringbootvinylshopcontroller.mappers;

import nl.novi.backendspringbootvinylshopcontroller.Entities.ArtistEntity;
import nl.novi.backendspringbootvinylshopcontroller.Repositories.ArtistRepository;
import nl.novi.backendspringbootvinylshopcontroller.dtos.artist.ArtistRequestDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.artist.ArtistResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArtistDtoMapper implements DtoMapper<ArtistResponseDto, ArtistRequestDto, ArtistEntity> {

    @Override
    public ArtistResponseDto mapToDto(ArtistEntity model){
        ArtistResponseDto result = new ArtistResponseDto();
        result.setId(model.getId());
        result.setName(model.getName());
        result.setBiography(model.getBiography());
        return result;
    }

    @Override
    public List<ArtistResponseDto> mapToDto(List<ArtistEntity> models){
        var result = new ArrayList<ArtistResponseDto>();
        for (ArtistEntity model : models) {
            result.add(mapToDto(model));
        }
        return result;
    }

    @Override
    public ArtistEntity mapToEntity(ArtistRequestDto artistModel) {
        var result = new ArtistEntity();
        result.setName(artistModel.getName());
        result.setBiography(artistModel.getBiography());
        return result;

    }


}

