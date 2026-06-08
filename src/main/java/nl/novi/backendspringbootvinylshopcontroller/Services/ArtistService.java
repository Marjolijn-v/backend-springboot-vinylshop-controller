package nl.novi.backendspringbootvinylshopcontroller.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import nl.novi.backendspringbootvinylshopcontroller.Entities.ArtistEntity;
import nl.novi.backendspringbootvinylshopcontroller.Repositories.ArtistRepository;
import nl.novi.backendspringbootvinylshopcontroller.dtos.artist.ArtistRequestDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.artist.ArtistResponseDto;
import nl.novi.backendspringbootvinylshopcontroller.exceptions.RecordNotFoundException;
import nl.novi.backendspringbootvinylshopcontroller.mappers.ArtistDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ArtistResponseDto findArtistById(Long id) {
        ArtistEntity artistEntity = artistRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Artist " + id + " not found"));

        return artistDtoMapper.mapToDto(artistEntity);
    }

    public ArtistResponseDto createArtist(ArtistRequestDto artistDto) {
        ArtistEntity artistEntity = artistDtoMapper.mapToEntity(artistDto);
        artistEntity = artistRepository.save(artistEntity);
        return artistDtoMapper.mapToDto(artistEntity);
    }

    public ArtistResponseDto updateArtist(Long id, @Valid ArtistRequestDto requestDto) throws EntityNotFoundException {
        ArtistEntity existingArtist = artistRepository.findById(id).orElse(null);

        if(existingArtist == null) {
            return null;
        }

        existingArtist.setName(requestDto.getName());
        existingArtist.setBiography(requestDto.getBiography());
        return artistDtoMapper.mapToDto(existingArtist);
    }

    public void deleteArtist(Long id){
        if(artistRepository.existsById(id)){
            artistRepository.deleteById(id);
        } else {
            IO.println("Artist with id " + id + " can't be removed");
        }
    }

    private ArtistEntity getArtistById(Long id) {
        Optional<ArtistEntity> optionalArtistEntity = artistRepository.findById(id);
        if(optionalArtistEntity.isPresent()){
            return optionalArtistEntity.get();
        } else {
            throw new RecordNotFoundException("Artist " + id + " not found");
        }
    }


    public List<ArtistResponseDto> getArtistsForAlbum(Long albumId) {
        return artistDtoMapper.mapToDto(artistRepository.findArtistsByAlbumsId(albumId));
    }
}
