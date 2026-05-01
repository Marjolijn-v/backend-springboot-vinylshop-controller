package nl.novi.backendspringbootvinylshopcontroller.Controllers;

import nl.novi.backendspringbootvinylshopcontroller.Entities.Genre;
import nl.novi.backendspringbootvinylshopcontroller.Entities.GenreEntity;
import nl.novi.backendspringbootvinylshopcontroller.Services.GenreService;
import nl.novi.backendspringbootvinylshopcontroller.helpers.UrlHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")

public class GenreController {

    private final GenreService genreService;
    private final UrlHelper urlHelper;

    public GenreController(GenreService genreService, UrlHelper urlHelper) {
        this.genreService = genreService;
        this.urlHelper = urlHelper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
        GenreEntity genre = genreService.findGenreById(id);

        return new ResponseEntity<>(genre, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres() {

        return ResponseEntity.ok(genreService.findAllGenres());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Genre> createGenre (@RequestBody Genre genreInput) {

        Genre newGenre = genreService.createGenre(genreInput);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newGenre.getId())).body(newGenre);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre (@PathVariable Long id, @RequestBody Genre genreInput) {
        Genre updatedGenre = genreService.updateGenre(id, genreInput);
        return ResponseEntity.ok().body(updatedGenre);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
    }
}
