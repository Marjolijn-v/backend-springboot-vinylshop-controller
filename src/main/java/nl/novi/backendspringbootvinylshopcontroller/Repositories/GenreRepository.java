package nl.novi.backendspringbootvinylshopcontroller.Repositories;


import nl.novi.backendspringbootvinylshopcontroller.Entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity,Long> {
}
