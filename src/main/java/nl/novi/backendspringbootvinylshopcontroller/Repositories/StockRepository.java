package nl.novi.backendspringbootvinylshopcontroller.Repositories;

import nl.novi.backendspringbootvinylshopcontroller.Entities.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
}
