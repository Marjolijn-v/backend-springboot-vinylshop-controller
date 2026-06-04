package nl.novi.backendspringbootvinylshopcontroller.Controllers;

import jakarta.validation.Valid;
import nl.novi.backendspringbootvinylshopcontroller.Services.AlbumService;
import nl.novi.backendspringbootvinylshopcontroller.dtos.album.AlbumRequestDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.album.AlbumResponseDto;
import nl.novi.backendspringbootvinylshopcontroller.helpers.UrlHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final UrlHelper urlHelper;

    public AlbumController(AlbumService albumService, UrlHelper urlHelper) {
        this.albumService = albumService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<AlbumResponseDto>> getAllAlbums() {
        List<AlbumResponseDto> allAlbums = albumService.findAllAlbums();

        return new ResponseEntity<>(allAlbums, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumResponseDto> getAlbumById(@PathVariable Long id) {
        AlbumResponseDto album = albumService.findAlbumById(id);

        return new ResponseEntity<>(album, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AlbumResponseDto> createAlbum (@RequestBody @Valid AlbumRequestDto albumModel) {
        AlbumResponseDto newAlbum = albumService.createAlbum(albumModel);

        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newAlbum.getId())).body(newAlbum);

    }

    @PutMapping("{id}")
    public ResponseEntity<AlbumResponseDto> updateAlbum (@PathVariable Long id, @RequestBody @Valid AlbumRequestDto requestDto) {
        AlbumResponseDto updatedAlbum = albumService.updateAlbum(id, requestDto);
        return new ResponseEntity<>(updatedAlbum,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
    }


}
