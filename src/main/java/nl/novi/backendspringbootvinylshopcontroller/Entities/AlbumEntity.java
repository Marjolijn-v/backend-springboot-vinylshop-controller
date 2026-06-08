package nl.novi.backendspringbootvinylshopcontroller.Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class AlbumEntity extends BaseEntity{
    @Column(name = "title", nullable = false )
    private String title;

    @Column(name = "release_year")
    private int releaseYear;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private PublisherEntity publisher;

    @OneToMany(mappedBy = "albumEntity")
    private Set<StockEntity> stockItems = new HashSet<>();

    @ManyToMany()
    @JoinTable(
            name = "album_artist",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private Set<ArtistEntity> artists = new HashSet<>();


    @OneToOne
    @JoinColumn(name = "genre_id")
    private GenreEntity genreEntity;

    public PublisherEntity getPublisher(PublisherEntity publisherEntity) {
        return publisher;
    }

    public void setPublisher(PublisherEntity publisher) {
        this.publisher = publisher;
    }

    public GenreEntity getGenre(GenreEntity genreEntity) {
        return genreEntity;
    }

    public void setGenre(GenreEntity genreEntity) {
        this.genreEntity = genreEntity;
    }

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
