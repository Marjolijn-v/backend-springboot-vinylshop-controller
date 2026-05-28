package nl.novi.backendspringbootvinylshopcontroller.Controllers;

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
    public ResponseEntity<GenreEntity> getGenreById(@PathVariable Long id) {
        GenreEntity genreEntity = genreService.findGenreById(id);

        return new ResponseEntity<>(genreEntity, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<GenreEntity>> getAllGenres() {

        return ResponseEntity.ok(genreService.findAllGenres());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GenreEntity> createGenre (@RequestBody GenreEntity genreInput) {

        GenreEntity newGenre = genreService.createGenre(genreInput);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newGenre.getId())).body(newGenre);

    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreEntity> updateGenre (@PathVariable Long id, @RequestBody GenreEntity genreInput) {
        GenreEntity updatedGenre = genreService.updateGenre(id, genreInput);
        return ResponseEntity.ok().body(updatedGenre);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
    }
}
