package nl.novi.backendspringbootvinylshopcontroller.Controllers;

import jakarta.validation.Valid;
import nl.novi.backendspringbootvinylshopcontroller.Entities.PublisherEntity;
import nl.novi.backendspringbootvinylshopcontroller.Services.PublisherService;
import nl.novi.backendspringbootvinylshopcontroller.dtos.publisher.PublisherRequestDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.publisher.PublisherResponseDto;
import nl.novi.backendspringbootvinylshopcontroller.helpers.UrlHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")

public class PublisherController {

    private final PublisherService publisherService;
    private final UrlHelper urlHelper;

    public PublisherController(PublisherService publisherService, UrlHelper urlHelper){
        this.publisherService = publisherService;
        this.urlHelper = urlHelper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResponseDto> findPublisherById(@PathVariable Long id) {
        PublisherResponseDto publisher = publisherService.findPublisherById(id);

        return new ResponseEntity<>(publisher, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PublisherResponseDto>> findAllPublishers(){
        List<PublisherResponseDto> allPublishers = publisherService.findAllPublishers();

        return new ResponseEntity<>(allPublishers, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PublisherResponseDto> createPublisher (@RequestBody @Valid PublisherRequestDto publisherDto) {

        PublisherResponseDto newPublisher = publisherService.createPublisher(publisherDto);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newPublisher.getId())).body(newPublisher);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherResponseDto> updatePublisher (@PathVariable Long id, @RequestBody @Valid PublisherRequestDto publisherDto) {
        PublisherResponseDto updatedPublisher = publisherService.updatePublisher(id, publisherDto);
        return new ResponseEntity<>(updatedPublisher, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
    }


}
