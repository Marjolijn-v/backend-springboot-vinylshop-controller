package nl.novi.backendspringbootvinylshopcontroller.mappers;

import nl.novi.backendspringbootvinylshopcontroller.Entities.AlbumEntity;
import nl.novi.backendspringbootvinylshopcontroller.dtos.album.AlbumRequestDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.album.AlbumResponseDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.artist.ArtistResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlbumDtoMapper implements DtoMapper<AlbumResponseDto, AlbumRequestDto, AlbumEntity> {

    @Override
    public AlbumResponseDto mapToDto(AlbumEntity model){
        AlbumResponseDto result = new AlbumResponseDto();
        result.setId(model.getId());
        result.setTitle(model.getTitle());
        result.setReleaseYear(model.getReleaseYear());
        return result;
    }

    @Override
    public List<AlbumResponseDto> mapToDto(List<AlbumEntity> models){
        var result = new ArrayList<AlbumResponseDto>();
        for (AlbumEntity model : models) {
            result.add(mapToDto(model));
        }
        return result;
    }

    @Override
    public AlbumEntity mapToEntity(AlbumRequestDto albumModel) {
        var result = new AlbumEntity();
        result.setTitle(albumModel.getTitle());
        result.setReleaseYear(albumModel.getReleaseYear());

        return result;
    }

}
