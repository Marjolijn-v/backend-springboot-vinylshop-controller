package nl.novi.backendspringbootvinylshopcontroller.Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class AlbumEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private PublisherEntity publisherEntity;

    @OneToMany(mappedBy = "album")
    private Set<StockEntity> stockItems = new HashSet<>();

    @ManyToMany(mappedBy = "albums")
    private Set<ArtistEntity> artists = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genreEntity;

    @Column(name = "title", nullable = false )
    private String title;

    @Column(name = "release_year")
    private int releaseYear;


    public Set<ArtistEntity> getArtists() {
        return artists;
    }

    public void setArtists(Set<ArtistEntity> artists) {
        this.artists = artists;
    }

    public Set<StockEntity> getStockItems() {
        return stockItems;
    }

    public void setStockItems(Set<StockEntity> stockItems) {
        this.stockItems = stockItems;
    }

    public PublisherEntity getPublisherEntity() {
        return publisherEntity;
    }

    public void setPublisherEntity(PublisherEntity publisherEntity) {
        this.publisherEntity = publisherEntity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
