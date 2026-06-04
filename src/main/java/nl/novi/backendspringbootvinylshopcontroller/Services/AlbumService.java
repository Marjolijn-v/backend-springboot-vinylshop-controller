package nl.novi.backendspringbootvinylshopcontroller.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import nl.novi.backendspringbootvinylshopcontroller.Entities.AlbumEntity;
import nl.novi.backendspringbootvinylshopcontroller.Repositories.AlbumRepository;
import nl.novi.backendspringbootvinylshopcontroller.dtos.album.AlbumRequestDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.album.AlbumResponseDto;
import nl.novi.backendspringbootvinylshopcontroller.exceptions.RecordNotFoundException;
import nl.novi.backendspringbootvinylshopcontroller.mappers.AlbumDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumDtoMapper albumDtoMapper;

    public AlbumService(AlbumRepository albumRepository, AlbumDtoMapper albumDtoMapper) {
        this.albumRepository = albumRepository;
        this.albumDtoMapper = albumDtoMapper;
    }

    public List<AlbumResponseDto> findAllAlbums() {
        return albumDtoMapper.mapToDto(albumRepository.findAll());
    }

    public AlbumResponseDto findAlbumById(Long id) {
        AlbumEntity albumEntity = albumRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Album " + id + " not found."));

        return albumDtoMapper.mapToDto(albumEntity);
    }

    public AlbumResponseDto createAlbum(AlbumRequestDto albumDto) {
        AlbumEntity albumEntity = albumDtoMapper.mapToEntity(albumDto);
        albumEntity = albumRepository.save(albumEntity);
        return albumDtoMapper.mapToDto(albumEntity);

    }

    public AlbumResponseDto updateAlbum(Long id, @Valid AlbumRequestDto requestDto) {
        AlbumEntity existingAlbum = albumRepository.findById(id).orElse(null);
        if(existingAlbum == null){
            return null;
        }

        existingAlbum.setTitle(requestDto.getTitle());
        existingAlbum.setReleaseYear(requestDto.getReleaseYear());
        return albumDtoMapper.mapToDto(existingAlbum);

    }

    public void deleteAlbum(Long id){
        if(albumRepository.existsById(id)){
            albumRepository.deleteById(id);
        } else {
            IO.println("Album with id " + id + " can't be removed");
        }
    }

    private AlbumEntity getAlbumById(Long id) {
        Optional<AlbumEntity> optionalAlbumEntity = albumRepository.findById(id);
        if(optionalAlbumEntity.isPresent()){
            return optionalAlbumEntity.get();
        }else {
            throw new RecordNotFoundException("Album " + id + " not found");
        }
    }


}
