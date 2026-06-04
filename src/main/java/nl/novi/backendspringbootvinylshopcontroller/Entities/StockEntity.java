package nl.novi.backendspringbootvinylshopcontroller.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "stock")
public class StockEntity extends BaseEntity{
    private String condition;
    private double price;

    public StockEntity(String condition, double price) {
        this.condition = condition;
        this.price = price;
    }

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
