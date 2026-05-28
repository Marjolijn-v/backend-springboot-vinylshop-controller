package nl.novi.backendspringbootvinylshopcontroller.Services;

import nl.novi.backendspringbootvinylshopcontroller.Entities.Genre;
import nl.novi.backendspringbootvinylshopcontroller.Entities.GenreEntity;
import nl.novi.backendspringbootvinylshopcontroller.Repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreEntity> findAllGenres() {
        return genreRepository.findAll();
    }

    public GenreEntity findGenreById(Long id) {
       return getGenreById(id);
    }

    public GenreEntity createGenre(GenreEntity input) {
        return genreRepository.save(input);
    }

    public GenreEntity updateGenre(Long id, GenreEntity input) {
        GenreEntity oldGenre = genreRepository.findById(id).orElse(null);
        if(oldGenre == null){
            return null;
        }

        oldGenre.setName(input.getName());
        oldGenre.setDescription(input.getDescription());
        return genreRepository.save(oldGenre);
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
            return null;
        }
    }
}
