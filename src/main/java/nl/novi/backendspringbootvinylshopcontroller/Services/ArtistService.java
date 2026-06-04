package nl.novi.backendspringbootvinylshopcontroller.Services;

import nl.novi.backendspringbootvinylshopcontroller.Repositories.ArtistRepository;
import nl.novi.backendspringbootvinylshopcontroller.dtos.artist.ArtistResponseDto;
import nl.novi.backendspringbootvinylshopcontroller.mappers.ArtistDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistDtoMapper artistDtoMapper;


    public ArtistService(ArtistRepository artistRepository, ArtistDtoMapper artistDtoMapper) {
        this.artistRepository = artistRepository;
        this.artistDtoMapper = artistDtoMapper;
    }

    public List<ArtistResponseDto> findAllArtists() {
        return artistDtoMapper.mapToDto(artistRepository.findAll());
    }
}
