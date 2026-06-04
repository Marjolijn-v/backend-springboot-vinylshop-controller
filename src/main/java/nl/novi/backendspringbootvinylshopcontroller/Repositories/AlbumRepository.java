package nl.novi.backendspringbootvinylshopcontroller.Repositories;

import nl.novi.backendspringbootvinylshopcontroller.Entities.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {

    List<AlbumEntity> findByStockItemsNotEmpty();

    List<AlbumEntity> findByStockItemsEmpty();

    List<AlbumEntity> findByGenreEntity_Id(Long genreId);

}
