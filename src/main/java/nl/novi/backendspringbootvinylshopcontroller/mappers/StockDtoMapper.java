package nl.novi.backendspringbootvinylshopcontroller.mappers;

import nl.novi.backendspringbootvinylshopcontroller.Entities.StockEntity;
import nl.novi.backendspringbootvinylshopcontroller.dtos.album.AlbumRequestDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.album.AlbumResponseDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.stock.StockRequestDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.stock.StockResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StockDtoMapper implements DtoMapper<StockResponseDto, StockRequestDto, StockEntity>{

    @Override
    public StockResponseDto mapToDto(StockEntity model) {
        StockResponseDto result = new StockResponseDto();
        result.setId(model.getId());
        result.setCondition(model.getCondition());
        result.setPrice(model.getPrice());
        return result;
    }

    @Override
    public List<StockResponseDto> mapToDto(List<StockEntity> models){
        var result = new ArrayList<StockResponseDto>();
        for (StockEntity model : models) {
            result.add(mapToDto(model));
        }
        return result;
    }

    @Override
    public StockEntity mapToEntity(StockRequestDto stockModel) {
        var result = new StockEntity();
        result.setCondition(stockModel.getCondition());
        result.setPrice(stockModel.getPrice());
        return result;
    }
}
