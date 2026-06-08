package nl.novi.backendspringbootvinylshopcontroller.dtos.album;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AlbumRequestDto {

    @NotNull(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title needs to be at least 3 characters and max 100 characters")
    private String title;

    @Size(min = 1877, max = 2100)
    private int releaseYear;

    private Long genreId;
    private Long publisherId;

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
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
