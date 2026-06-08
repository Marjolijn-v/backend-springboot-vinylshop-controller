package nl.novi.backendspringbootvinylshopcontroller.mappers;

import nl.novi.backendspringbootvinylshopcontroller.Entities.AlbumEntity;
import nl.novi.backendspringbootvinylshopcontroller.dtos.album.AlbumExtendedResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AlbumExtendedDtoMapper extends AlbumDtoMapper  {

    private final StockDtoMapper stockDtoMapper;

    public AlbumExtendedDtoMapper(PublisherDtoMapper publisherDtoMapper, StockDtoMapper stockDtoMapper, GenreDtoMapper genreDtoMapper) {

        super(publisherDtoMapper,genreDtoMapper);
        this.stockDtoMapper = stockDtoMapper;
    }

    @Override
    public AlbumExtendedResponseDto mapToDto(AlbumEntity model) {
        AlbumExtendedResponseDto result = (AlbumExtendedResponseDto) super.mapToDto(model);
        result.setStock(stockDtoMapper.mapToDto(model.getStockItems().stream().toList()));
        return result;
    }
}
