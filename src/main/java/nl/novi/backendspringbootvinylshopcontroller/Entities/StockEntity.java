package nl.novi.backendspringbootvinylshopcontroller.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "stock")
public class StockEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "album_id")
    private AlbumEntity albumEntity;

    @Column(name = "condition")
    private String condition;

    @Column(name = "price", nullable = false)
    private double price;

    public AlbumEntity getAlbumEntity() {
        return albumEntity;
    }

    public void setAlbumEntity(AlbumEntity albumEntity) {
        this.albumEntity = albumEntity;
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
