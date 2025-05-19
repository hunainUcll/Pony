package be.ucll.repository;

import be.ucll.model.Pony;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer {

    private final PonyRepository ponyRepository;

    @Autowired
    public DbInitializer(PonyRepository ponyRepository) {
        this.ponyRepository = ponyRepository;
    }

    @PostConstruct
    public void initialize() {
        ponyRepository.deleteAll(); // ensure a clean state if using a stub or in-memory db

        ponyRepository.save(new Pony("Tornado", 5, 140));
        ponyRepository.save(new Pony("Bella", 6, 135));
    }
}
