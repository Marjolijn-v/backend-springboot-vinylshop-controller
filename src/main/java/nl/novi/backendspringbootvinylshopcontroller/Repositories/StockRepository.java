package nl.novi.backendspringbootvinylshopcontroller.Repositories;

import nl.novi.backendspringbootvinylshopcontroller.Entities.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
    Optional<StockEntity> findByIdAndAlbumEntity_Id(Long id, Long albumId);

    void deleteByIdAndAlbumEntity_Id(Long id, Long albumId);

    List<StockEntity> findByAlbumEntity_Id(Long albumId);
}
