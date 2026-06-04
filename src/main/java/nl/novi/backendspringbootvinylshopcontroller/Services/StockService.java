package nl.novi.backendspringbootvinylshopcontroller.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import nl.novi.backendspringbootvinylshopcontroller.Entities.StockEntity;
import nl.novi.backendspringbootvinylshopcontroller.Repositories.StockRepository;
import nl.novi.backendspringbootvinylshopcontroller.dtos.stock.StockRequestDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.stock.StockResponseDto;
import nl.novi.backendspringbootvinylshopcontroller.exceptions.RecordNotFoundException;
import nl.novi.backendspringbootvinylshopcontroller.mappers.StockDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final StockDtoMapper stockDtoMapper;

    public StockService(StockRepository stockRepository, StockDtoMapper stockDtoMapper) {
        this.stockRepository = stockRepository;
        this.stockDtoMapper = stockDtoMapper;
    }

    public List<StockResponseDto> findAllStock() {
        return stockDtoMapper.mapToDto(stockRepository.findAll());
    }

    public StockResponseDto findStockById(Long id) {
        StockEntity stockEntity = stockRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Stock " + id + " not found"));

        return stockDtoMapper.mapToDto(stockEntity);
    }

    public StockResponseDto createStock(StockRequestDto stockDto) {
        StockEntity stockEntity = stockDtoMapper.mapToEntity(stockDto);
        stockEntity = stockRepository.save(stockEntity);
        return stockDtoMapper.mapToDto(stockEntity);
    }

    public StockResponseDto updateStock (Long id, @Valid StockRequestDto requestDto) {
        StockEntity existingStock = stockRepository.findById(id).orElse(null);
        if(existingStock == null) {
            return null;
        }

        existingStock.setCondition(requestDto.getCondition());
        existingStock.setPrice(requestDto.getPrice());

        return stockDtoMapper.mapToDto(existingStock);
    }

    public void deleteStock(Long id) {
        if(stockRepository.existsById(id)){
            stockRepository.deleteById(id);
        } else {
            IO.println("Stock with id " + id + " can't be removed");
        }
    }

    private StockEntity getStockById(Long id) {
        Optional<StockEntity> optionalStockEntity =stockRepository.findById(id);

        if(optionalStockEntity.isPresent()){
            return optionalStockEntity.get();
        } else {
            throw new RecordNotFoundException("Stock " + id + " not found.");
        }
    }


}
