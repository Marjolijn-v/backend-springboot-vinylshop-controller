package nl.novi.backendspringbootvinylshopcontroller.Controllers;

import jakarta.validation.Valid;
import nl.novi.backendspringbootvinylshopcontroller.Services.StockService;
import nl.novi.backendspringbootvinylshopcontroller.dtos.stock.StockRequestDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.stock.StockResponseDto;
import nl.novi.backendspringbootvinylshopcontroller.helpers.UrlHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums/{albumId}/stock")
public class StockController {

    private final StockService stockService;
    private final UrlHelper urlHelper;

    public StockController(StockService stockService, UrlHelper urlHelper) {
        this.stockService = stockService;
        this.urlHelper = urlHelper;
    }

    @GetMapping
    public ResponseEntity<List<StockResponseDto>> getAllStock() {
        List<StockResponseDto> allStock = stockService.findAllStock();

        return new ResponseEntity<>(allStock, HttpStatus.OK);
    }

    @GetMapping("{stockId}")
    public ResponseEntity<StockResponseDto> getStockById(@PathVariable Long id) {
        StockResponseDto stock = stockService.findStockById(id);

        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StockResponseDto> createStock(@RequestBody @Valid StockRequestDto requestDto) {
        StockResponseDto newStock = stockService.createStock(requestDto);

        return  ResponseEntity.created(urlHelper.getCurrentUrlWithId(newStock.getId())).body(newStock);
    }

    @PutMapping("/{stockId")
    public ResponseEntity<StockResponseDto> updateStock(@PathVariable Long id, @RequestBody @Valid StockRequestDto stockDto) {
        StockResponseDto updateStock= stockService.updateStock(id, stockDto);
        return new ResponseEntity<>(updateStock, HttpStatus.OK);
    }

    @DeleteMapping("{stockId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
    }


}
