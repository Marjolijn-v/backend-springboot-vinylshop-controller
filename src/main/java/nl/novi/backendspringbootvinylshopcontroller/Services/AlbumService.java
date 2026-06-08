package nl.novi.backendspringbootvinylshopcontroller.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import nl.novi.backendspringbootvinylshopcontroller.Entities.AlbumEntity;
import nl.novi.backendspringbootvinylshopcontroller.Entities.ArtistEntity;
import nl.novi.backendspringbootvinylshopcontroller.Entities.GenreEntity;
import nl.novi.backendspringbootvinylshopcontroller.Entities.PublisherEntity;
import nl.novi.backendspringbootvinylshopcontroller.Repositories.AlbumRepository;
import nl.novi.backendspringbootvinylshopcontroller.Repositories.ArtistRepository;
import nl.novi.backendspringbootvinylshopcontroller.Repositories.GenreRepository;
import nl.novi.backendspringbootvinylshopcontroller.Repositories.PublisherRepository;
import nl.novi.backendspringbootvinylshopcontroller.dtos.album.AlbumExtendedResponseDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.album.AlbumRequestDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.album.AlbumResponseDto;
import nl.novi.backendspringbootvinylshopcontroller.exceptions.RecordNotFoundException;
import nl.novi.backendspringbootvinylshopcontroller.mappers.AlbumDtoMapper;
import nl.novi.backendspringbootvinylshopcontroller.mappers.AlbumExtendedDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumDtoMapper albumDtoMapper;
    private final GenreRepository genreRepository;
    private final ArtistRepository artistRepository;
    private final AlbumExtendedDtoMapper albumExtendedDtoMapper;
    private final PublisherRepository publisherRepository;
    

    public AlbumService(AlbumRepository albumRepository, AlbumDtoMapper albumDtoMapper, GenreRepository genreRepository, ArtistRepository artistRepository, AlbumExtendedDtoMapper albumExtendedDtoMapper, PublisherRepository publisherRepository) {
        this.albumRepository = albumRepository;
        this.albumDtoMapper = albumDtoMapper;
        this.genreRepository = genreRepository;
        this.artistRepository = artistRepository;
        this.albumExtendedDtoMapper = albumExtendedDtoMapper;
        this.publisherRepository = publisherRepository;
    }

    public List<AlbumResponseDto> findAllAlbums() {
        return albumDtoMapper.mapToDto(albumRepository.findAll());
    }

    public AlbumExtendedResponseDto findAlbumById(Long id) {
        AlbumEntity albumEntity = albumRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Album " + id + " not found."));

        return albumExtendedDtoMapper.mapToDto(albumEntity);
    }

    
    public AlbumResponseDto createAlbum(AlbumRequestDto albumDto) {
        AlbumEntity albumEntity = albumDtoMapper.mapToEntity(albumDto);
        if(albumDto.getGenreId() != null) {
            albumEntity.setGenre(getGenreEntity(albumDto.getGenreId()));
        } else {
            albumEntity.getGenre(new GenreEntity());
        }

        if(albumDto.getPublisherId() != null) {
            albumEntity.setPublisher(getPublisherEntity(albumDto.getPublisherId()));
        } else {
            albumEntity.getPublisher(new PublisherEntity());
        }


        albumEntity = albumRepository.save(albumEntity);
        return albumDtoMapper.mapToDto(albumEntity);

    }

    private PublisherEntity getPublisherEntity(Long publisherId) {
        return publisherRepository.findById(publisherId).orElseThrow(() -> new RecordNotFoundException("publisher " + publisherId + " not found"));
    }

    private GenreEntity getGenreEntity(Long genreId) {
        return genreRepository.findById(genreId).orElseThrow(() -> new RecordNotFoundException("genre " + genreId + " not found"));
    }

    public AlbumResponseDto updateAlbum(Long id, @Valid AlbumRequestDto requestDto) {
        AlbumEntity existingAlbum = albumRepository.findById(id).orElse(null);
        if(existingAlbum == null){
            return null;
        }
        existingAlbum.setTitle(requestDto.getTitle());
        existingAlbum.setReleaseYear(requestDto.getReleaseYear());
        existingAlbum.setGenre(getGenreEntity(requestDto.getGenreId()));
        existingAlbum.setPublisher(getPublisherEntity(requestDto.getPublisherId()));
        return albumDtoMapper.mapToDto(existingAlbum);

    }

    public void deleteAlbum(Long id){
        AlbumEntity albumEntity = getAlbumEntity(id);
        if(albumEntity.getStockItems().isEmpty()){
            albumRepository.deleteById(id);
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


    public void linkArtist(Long albumId, Long artistId) {
        AlbumEntity existingAlbumEntity = getAlbumEntity(albumId);
        ArtistEntity existingArtistEntity = artistRepository.findById(artistId).orElseThrow(() -> new RecordNotFoundException("artist " + artistId + " not found"));
        existingArtistEntity.getAlbums().add(existingAlbumEntity);
        existingAlbumEntity.getArtists().add(existingArtistEntity);
        albumRepository.save(existingAlbumEntity);
    }

    public void unlinkArtist(Long albumId, Long artistId) {
        AlbumEntity existingAlbumEntity = getAlbumEntity(albumId);
        ArtistEntity existingArtistEntity = artistRepository.findById(artistId).orElseThrow(() -> new RecordNotFoundException("artist " + artistId + " not found"));
        existingArtistEntity.getAlbums().remove(existingAlbumEntity);
        existingAlbumEntity.getArtists().remove(existingArtistEntity);
        albumRepository.save(existingAlbumEntity);
    }

    private AlbumEntity getAlbumEntity(Long albumId) {
        return albumRepository.findById(albumId).orElseThrow(()-> new RecordNotFoundException("album " + albumId + " not found"));
    }

    public List<AlbumResponseDto> getAlbumsWithStock(Boolean stock) {
        if(stock == true) {
            return albumDtoMapper.mapToDto(albumRepository.findByStockItemsNotEmpty());
        } else {
            return albumDtoMapper.mapToDto(albumRepository.findByStockItemsEmpty());
        }
    }
}
