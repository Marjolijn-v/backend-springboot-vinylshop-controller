package nl.novi.backendspringbootvinylshopcontroller.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import nl.novi.backendspringbootvinylshopcontroller.Entities.GenreEntity;
import nl.novi.backendspringbootvinylshopcontroller.Repositories.GenreRepository;
import nl.novi.backendspringbootvinylshopcontroller.dtos.genre.GenreRequestDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.genre.GenreResponseDto;
import nl.novi.backendspringbootvinylshopcontroller.exceptions.RecordNotFoundException;
import nl.novi.backendspringbootvinylshopcontroller.mappers.GenreDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreDtoMapper genreDtoMapper;


    public GenreService(GenreRepository genreRepository, GenreDtoMapper genreDtoMapper) {
        this.genreRepository = genreRepository;
        this.genreDtoMapper = genreDtoMapper;
    }

    public List<GenreResponseDto> findAllGenres() {
        return genreDtoMapper.mapToDto(genreRepository.findAll());

    }

    public GenreResponseDto findGenreById(Long id) {
       GenreEntity genreEntity = genreRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Genre not found"));

        return genreDtoMapper.mapToDto(genreEntity);
    }

    public GenreResponseDto createGenre(GenreRequestDto genreDto) {
        GenreEntity genreEntity = genreDtoMapper.mapToEntity(genreDto);
        genreEntity = genreRepository.save(genreEntity);
        return genreDtoMapper.mapToDto(genreEntity);

    }

    public GenreResponseDto updateGenre(Long id, @Valid GenreRequestDto requestDto) {
        GenreEntity oldGenre = genreRepository.findById(id).orElse(null);
        if(oldGenre == null){
            return null;
        }

        oldGenre.setName(requestDto.getName());
        oldGenre.setDescription(requestDto.getDescription());
        return genreDtoMapper.mapToDto(oldGenre);
    }

    public void deleteGenre(Long id){
        if(genreRepository.existsById(id)){
            genreRepository.deleteById(id);
        } else {
            IO.println("Genre met id " + id + " kan niet verwijderd worden.");
        }

    }

    private GenreEntity getGenreById(Long id) {
        Optional<GenreEntity> optionalGenreEntity = genreRepository.findById(id);
        if(optionalGenreEntity.isPresent()){
            return optionalGenreEntity.get();
        } else {
            throw new RecordNotFoundException("Genre " + id +" not found");
        }
    }
}
