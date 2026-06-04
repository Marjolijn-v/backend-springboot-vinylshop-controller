package nl.novi.backendspringbootvinylshopcontroller.Controllers;

import nl.novi.backendspringbootvinylshopcontroller.Services.ArtistService;
import nl.novi.backendspringbootvinylshopcontroller.dtos.artist.ArtistResponseDto;
import nl.novi.backendspringbootvinylshopcontroller.helpers.UrlHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/artist")

public class ArtistController {

    private final ArtistService artistService;
    private final UrlHelper urlHelper;

    public ArtistController(ArtistService artistService, UrlHelper urlHelper) {
        this.artistService = artistService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<ArtistResponseDto>> getAllArtist() {
        List<ArtistResponseDto> allArtist = artistService.findAllArtist();

        return new ResponseEntity<>(allArtist, HttpStatus.OK);
    }

}
