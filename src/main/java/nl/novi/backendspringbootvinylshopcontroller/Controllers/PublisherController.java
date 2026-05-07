package nl.novi.backendspringbootvinylshopcontroller.Controllers;

import nl.novi.backendspringbootvinylshopcontroller.Entities.PublisherEntity;
import nl.novi.backendspringbootvinylshopcontroller.Services.PublisherService;
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
    public ResponseEntity<PublisherEntity> findPublisherById(@PathVariable Long id) {
        PublisherEntity publisherEntity = publisherService.findPublisherById(id);

        return new ResponseEntity<>(publisherEntity, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PublisherEntity>> findAllPublishers(){
        return ResponseEntity.ok(publisherService.findAllPublishers());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PublisherEntity> createPublisher (@RequestBody PublisherEntity publisherInput) {

        PublisherEntity newPublisher = publisherService.createPublisher(publisherInput);
        return ResponseEntity.created(urlHelper.getCurrentUrlWithId(newPublisher.getId())).body(newPublisher);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherEntity> updatePublisher (@PathVariable Long id, @RequestBody PublisherEntity publisherInput) {
        PublisherEntity updatedPublisher = publisherService.updatePublisher(id, publisherInput);
        return ResponseEntity.ok().body(updatedPublisher);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
    }


}
