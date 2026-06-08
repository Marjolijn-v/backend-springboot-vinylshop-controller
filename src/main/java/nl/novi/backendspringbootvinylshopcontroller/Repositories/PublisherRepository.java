package nl.novi.backendspringbootvinylshopcontroller.Repositories;

import nl.novi.backendspringbootvinylshopcontroller.Entities.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<PublisherEntity, Long> {
}
