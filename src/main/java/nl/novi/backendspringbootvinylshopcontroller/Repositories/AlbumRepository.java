package nl.novi.backendspringbootvinylshopcontroller.Repositories;

import nl.novi.backendspringbootvinylshopcontroller.Entities.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<ArtistEntity, Long> {
}
