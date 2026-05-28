package nl.novi.backendspringbootvinylshopcontroller.mappers;

import nl.novi.backendspringbootvinylshopcontroller.Entities.GenreEntity;
import nl.novi.backendspringbootvinylshopcontroller.Entities.PublisherEntity;
import nl.novi.backendspringbootvinylshopcontroller.dtos.publisher.PublisherRequestDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.publisher.PublisherResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PublisherDtoMapper implements DtoMapper<PublisherResponseDto, PublisherRequestDto, PublisherEntity> {

    @Override
    public PublisherResponseDto mapToDto(PublisherEntity publisher){
        PublisherResponseDto result = new PublisherResponseDto();
        result.setId(publisher.getId());
        result.setName(publisher.getName());
        result.setAddress(publisher.getAddress());
        result.setContactDetails(publisher.getContactDetails());

        return result;

    }

    @Override
    public List<PublisherResponseDto> mapToDto(List<PublisherEntity> publishers) {
        List<PublisherResponseDto> result = new ArrayList<>();
        for (PublisherEntity publisher : publishers) {
            result.add(mapToDto(publisher));
        }

        return result;
    }

    @Override
    public PublisherEntity mapToEntity(PublisherRequestDto dto){
      PublisherEntity publisher = new PublisherEntity();
      publisher.setName(dto.getName());
      publisher.setAddress(dto.getAddress());
      publisher.setContactDetails(dto.getContactDetails());

      return publisher;

    }


}
