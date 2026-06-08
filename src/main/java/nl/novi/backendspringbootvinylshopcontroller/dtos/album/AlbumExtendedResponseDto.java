package nl.novi.backendspringbootvinylshopcontroller.dtos.album;

import nl.novi.backendspringbootvinylshopcontroller.dtos.stock.StockResponseDto;

import java.util.ArrayList;
import java.util.List;

public class AlbumExtendedResponseDto extends AlbumResponseDto {

    private List<StockResponseDto> stock = new ArrayList<>();

    public List<StockResponseDto> getStock() {
        return stock;
    }

    public void setStock(List<StockResponseDto> stock) {
        this.stock = stock;
    }
}
