package nl.novi.backendspringbootvinylshopcontroller.Controllers;

import jakarta.validation.Valid;
import nl.novi.backendspringbootvinylshopcontroller.Entities.GenreEntity;
import nl.novi.backendspringbootvinylshopcontroller.Services.GenreService;
import nl.novi.backendspringbootvinylshopcontroller.dtos.genre.GenreRequestDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.genre.GenreResponseDto;
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
    @GetMapping
    public ResponseEntity<List<GenreResponseDto>> getAllGenres() {
        List<GenreResponseDto> allGenres = genreService.findAllGenres();

        return new ResponseEntity<>(allGenres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponseDto> getGenreById(@PathVariable Long id) {
        GenreResponseDto genre = genreService.findGenreById(id);

        return new ResponseEntity<>(genre, HttpStatus.OK);
    }




    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GenreResponseDto> createGenre (@RequestBody @Valid GenreRequestDto genreModel) {

        GenreResponseDto newGenre = genreService.createGenre(genreModel);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newGenre.getId())).body(newGenre);

    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreResponseDto> updateGenre (@PathVariable Long id, @RequestBody @Valid GenreRequestDto genreModel) {
        GenreResponseDto updatedGenre = genreService.updateGenre(id, genreModel);
        return new ResponseEntity<>(updatedGenre,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
    }
}
