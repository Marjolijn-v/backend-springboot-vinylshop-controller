package nl.novi.backendspringbootvinylshopcontroller.Controllers;

import jakarta.validation.Valid;
import nl.novi.backendspringbootvinylshopcontroller.Services.AlbumService;
import nl.novi.backendspringbootvinylshopcontroller.Services.ArtistService;
import nl.novi.backendspringbootvinylshopcontroller.dtos.artist.ArtistRequestDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.artist.ArtistResponseDto;
import nl.novi.backendspringbootvinylshopcontroller.helpers.UrlHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist")

public class ArtistController {

    private final ArtistService artistService;
    private final AlbumService albumService;
    private final UrlHelper urlHelper;

    public ArtistController(ArtistService artistService, AlbumService albumService, UrlHelper urlHelper) {
        this.artistService = artistService;
        this.albumService = albumService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<ArtistResponseDto>> getAllArtist() {
        List<ArtistResponseDto> allArtist = artistService.findAllArtists();

        return new ResponseEntity<>(allArtist, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponseDto> getArtistById(@PathVariable Long id) {
        ArtistResponseDto artist = artistService.findArtistById(id);

        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ArtistResponseDto> createArtist(@RequestBody @Valid ArtistRequestDto artistModel) {
        ArtistResponseDto newArtist = artistService.createArtist(artistModel);

        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newArtist.getId())).body(newArtist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistResponseDto> updateArtist(@PathVariable Long id, @RequestBody @Valid ArtistRequestDto artistModel) {
        ArtistResponseDto updateArtist = artistService.updateArtist(id, artistModel);
        return new ResponseEntity<>(updateArtist,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable Long id){
        artistService.deleteArtist(id);
    }

    @PostMapping("/{albumId}/artists/{artistId}")
    public ResponseEntity<Void> linkArtist(@PathVariable Long albumId, @PathVariable Long artistId) {
        albumService.linkArtist(albumId, artistId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{albumId}/artists/{artistId}")
    public ResponseEntity<Void> unlinkArtist(@PathVariable Long albumId, @PathVariable Long artistId) {
        albumService.unlinkArtist(albumId, artistId);
        return ResponseEntity.ok().build();
    }

}
