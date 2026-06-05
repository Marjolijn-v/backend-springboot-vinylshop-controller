package nl.novi.backendspringbootvinylshopcontroller.dtos.album;

import nl.novi.backendspringbootvinylshopcontroller.Entities.GenreEntity;
import nl.novi.backendspringbootvinylshopcontroller.Entities.PublisherEntity;

public class AlbumResponseDto {
    private Long id;
    private String title;
    private int releaseYear;
    private GenreEntity genreEntity;
    private PublisherEntity publisherEntity;

    public GenreEntity getGenreEntity() {
        return genreEntity;
    }

    public void setGenreEntity(GenreEntity genreEntity) {
        this.genreEntity = genreEntity;
    }

    public PublisherEntity getPublisherEntity() {
        return publisherEntity;
    }

    public void setPublisherEntity(PublisherEntity publisherEntity) {
        this.publisherEntity = publisherEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
