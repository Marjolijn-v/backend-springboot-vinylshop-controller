package nl.novi.backendspringbootvinylshopcontroller.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import nl.novi.backendspringbootvinylshopcontroller.Entities.AlbumEntity;
import nl.novi.backendspringbootvinylshopcontroller.Entities.PublisherEntity;
import nl.novi.backendspringbootvinylshopcontroller.Repositories.AlbumRepository;
import nl.novi.backendspringbootvinylshopcontroller.Repositories.PublisherRepository;
import nl.novi.backendspringbootvinylshopcontroller.dtos.publisher.PublisherRequestDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.publisher.PublisherResponseDto;
import nl.novi.backendspringbootvinylshopcontroller.exceptions.RecordNotFoundException;
import nl.novi.backendspringbootvinylshopcontroller.mappers.PublisherDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final AlbumRepository albumRepository;
    private final PublisherDtoMapper publisherDtoMapper;


    public PublisherService(PublisherRepository publisherRepository, AlbumRepository albumRepository, PublisherDtoMapper publisherDtoMapper) {
        this.publisherRepository = publisherRepository;
        this.albumRepository = albumRepository;
        this.publisherDtoMapper = publisherDtoMapper;
    }

    public List<PublisherResponseDto> findAllPublishers() {

        return publisherDtoMapper.mapToDto(publisherRepository.findAll());
    }


    public PublisherResponseDto findPublisherById(Long id){
        PublisherEntity publisherEntity = publisherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Publisher not found"));

        return publisherDtoMapper.mapToDto(publisherEntity);
    }

    public PublisherResponseDto createPublisher(PublisherRequestDto publisherDto){
        PublisherEntity publisherEntity = publisherDtoMapper.mapToEntity(publisherDto);
        publisherEntity = publisherRepository.save(publisherEntity);
        return publisherDtoMapper.mapToDto(publisherEntity);

    }

    public PublisherResponseDto updatePublisher(Long id, @Valid PublisherRequestDto requestDto) {
        PublisherEntity oldPublisher = publisherRepository.findById(id).orElse(null);
        if(oldPublisher == null){
            return null;
        }

        oldPublisher.setName(requestDto.getName());
        oldPublisher.setAddress(requestDto.getAddress());
        oldPublisher.setContactDetails(requestDto.getContactDetails());
        return publisherDtoMapper.mapToDto(oldPublisher);
    }

    public void deletePublisher(Long id){
       PublisherEntity publisherEntity = getPublisherEntity(id);

       for(AlbumEntity album : publisherEntity.getAlbums()){
           album.setPublisher(null);
           albumRepository.save(album);
       }
       publisherRepository.deleteById(id);
    }

    private PublisherEntity getPublisherEntity(Long id) {
        PublisherEntity publisherEntity = publisherRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Publisher " + id + " not found"));
        return publisherEntity;
    }

    private PublisherEntity getPublisherById(Long id) {
        Optional<PublisherEntity> optionalPublisherEntity = publisherRepository.findById(id);
        if(optionalPublisherEntity.isPresent()){
            return optionalPublisherEntity.get();
        } else {
            throw new RecordNotFoundException("Publisher " + id + " not found");
        }
    }
}
