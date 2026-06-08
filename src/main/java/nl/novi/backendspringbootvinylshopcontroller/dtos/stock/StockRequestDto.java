package nl.novi.backendspringbootvinylshopcontroller.dtos.stock;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class StockRequestDto {

    private String condition;

    @NotNull(message = "price is required.")
    @Size(min = 0)
    private double price;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
