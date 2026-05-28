package nl.novi.backendspringbootvinylshopcontroller.Services;

import nl.novi.backendspringbootvinylshopcontroller.Entities.PublisherEntity;
import nl.novi.backendspringbootvinylshopcontroller.Repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<PublisherEntity> findAllPublishers() {
        return publisherRepository.findAll();
    }



    public PublisherEntity findPublisherById(Long id){
        return getPublisherById(id);
    }

    public PublisherEntity createPublisher(PublisherEntity input){
        return publisherRepository.save(input);
    }

    public PublisherEntity updatePublisher(Long id, PublisherEntity input) {
        PublisherEntity oldPublisher = publisherRepository.findById(id).orElse(null);
        if(oldPublisher == null){
            return null;
        }

        oldPublisher.setName(input.getName());
        oldPublisher.setAddress(input.getAddress());
        oldPublisher.setContactDetails(input.getContactDetails());
        return publisherRepository.save(oldPublisher);
    }

    public void deletePublisher(Long id){
        if(publisherRepository.existsById(id)) {
            publisherRepository.deleteById(id);
        } else {
            IO.println("Publisher met id " + id + " kan niet verwijderd worden.");
        }
    }

    private PublisherEntity getPublisherById(Long id) {
        Optional<PublisherEntity> optionalPublisherEntity = publisherRepository.findById(id);
        return optionalPublisherEntity.orElse(null);
    }
}
