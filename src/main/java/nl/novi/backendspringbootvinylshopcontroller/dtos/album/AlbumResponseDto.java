package nl.novi.backendspringbootvinylshopcontroller.dtos.album;

import nl.novi.backendspringbootvinylshopcontroller.Entities.GenreEntity;
import nl.novi.backendspringbootvinylshopcontroller.Entities.PublisherEntity;
import nl.novi.backendspringbootvinylshopcontroller.dtos.genre.GenreResponseDto;
import nl.novi.backendspringbootvinylshopcontroller.dtos.publisher.PublisherResponseDto;

public class AlbumResponseDto {
    private Long id;
    private String title;
    private int releaseYear;
    private GenreResponseDto genre;
    private PublisherResponseDto publisher;

    public GenreResponseDto getGenre() {
        return genre;
    }

    public void setGenre(GenreResponseDto genre) {
        this.genre = genre;
    }

    public PublisherResponseDto getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherResponseDto publisher) {
        this.publisher = publisher;
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
