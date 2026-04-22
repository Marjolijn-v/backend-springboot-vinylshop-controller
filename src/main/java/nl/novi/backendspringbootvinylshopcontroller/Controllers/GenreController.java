package nl.novi.backendspringbootvinylshopcontroller.Controllers;

import nl.novi.backendspringbootvinylshopcontroller.Entities.Genre;
import nl.novi.backendspringbootvinylshopcontroller.Services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/genres")

public class GenreController {

    private final ArrayList<Genre> genreRepository;

    public GenreService() {
        genreRepository = new ArrayList<>();
    }

    @Autowired
    public GenreController(GenreService genreService, ArrayList<Genre> genreRepository) {
        this.genreRepository = genreRepository;
        this.genreService = genreService;
    }

    @GetMapping("/genreList/{id}")
    public ResponseEntity<Genre> findGenreById(@RequestParam Long id) {
        Genre genre = genreService.findGenreById(id);

        if (genre == null) {
            return genreRepository.stream().filter(g -> g.getId().equals(id)).findFirst().orElseThrow(()->new IndexOutOfBoundsException("Genre met ID " + id + " niet gevonden"));
        }
        return ResponseEntity.ok(genre);
    }

    private Genre getGenreById(Long id) {
        for (Genre genre : genreRepository) {
            if (genre.getId().equals(id)) {
                return genre;
            }
        }
        return null;
    }

    @GetMapping("/genreList")
    public ResponseEntity<List<Genre>> findAllGenres() {
        return ResponseEntity.ok(genreRepository);
    }

    @PostMapping("/genreList")
    public ResponseEntity<Genre> createGenre (@RequestParam Genre genre) {
        genre.setId(findNextId(genreRepository));
        genreRepository.add(genre);
        return ResponseEntity.ok().body(genre);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre (@RequestParam Long id, Genre genreInput) {
        Genre existingGenreEntity = findGenreById(id).getBody();

        existingGenreEntity.setName(genreInput.getName());
        existingGenreEntity.setDescription(genreInput.getDescription());
        return ResponseEntity.ok().body(genreInput);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@RequestParam Long id) {
        try{
            Genre existingGenreEntity = findGenreById(id).getBody();
            genreRepository.remove(existingGenreEntity);
        } catch (IndexOutOfBoundsException _) {
        }
    }

    private Long findNextId(ArrayList<Genre> genreRepository) {
        Long highest = 0L;
        if(!genreRepository.isEmpty()){
            for(Genre genre : genreRepository){
                if(genre.getId() > highest){
                    highest = genre.getId();
                }
            }
        }
        return highest+1;
    }



}
