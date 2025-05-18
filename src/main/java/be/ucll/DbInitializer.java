package be.ucll;

import be.ucll.model.Pony;
import be.ucll.repository.PonyRepository;
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
            ponyRepository.save(new Pony("Bella", 5, 135));
            ponyRepository.save(new Pony("Luna", 7, 140));
            ponyRepository.save(new Pony("Angel", 12, 147));

    }

}

