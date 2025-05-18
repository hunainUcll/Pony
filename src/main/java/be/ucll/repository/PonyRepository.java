package be.ucll.repository;

import be.ucll.model.Pony;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PonyRepository extends JpaRepository<Pony, Long> {

    Pony findByName(String name);
}
