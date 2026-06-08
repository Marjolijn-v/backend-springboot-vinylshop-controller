package nl.novi.backendspringbootvinylshopcontroller.mappers;

import nl.novi.backendspringbootvinylshopcontroller.Entities.AlbumEntity;
import nl.novi.backendspringbootvinylshopcontroller.Entities.GenreEntity;
import nl.novi.backendspringbootvinylshopcontroller.Entities.PublisherEntity;
import nl.novi.backendspringbootvinylshopcontroller.dtos.album.AlbumRequestDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.album.AlbumResponseDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class AlbumDtoMapper implements DtoMapper<AlbumResponseDto, AlbumRequestDto, AlbumEntity> {

    private final PublisherDtoMapper publisherDtoMapper;
    private final GenreDtoMapper genreDtoMapper;

    public AlbumDtoMapper(PublisherDtoMapper publisherDtoMapper, GenreDtoMapper genreDtoMapper) {
        this.publisherDtoMapper = publisherDtoMapper;
        this.genreDtoMapper = genreDtoMapper;
    }

    @Override
    public AlbumResponseDto mapToDto(AlbumEntity model) {
        return mapToDto(model, new AlbumResponseDto());
    }

    public <D extends AlbumResponseDto> D mapToDto(AlbumEntity model, D target){
        target.setId(model.getId());
        target.setTitle(model.getTitle());
        target.setReleaseYear(model.getReleaseYear());
        if(model.getGenre(new GenreEntity()) != null){
            target.setGenre(genreDtoMapper.mapToDto(model.getGenre(new GenreEntity())));
        }
        if(model.getPublisher(new PublisherEntity()) !=null) {
            target.setPublisher(publisherDtoMapper.mapToDto(model.getPublisher(new PublisherEntity())));
        }

        return target;
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
