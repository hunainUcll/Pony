package be.ucll.repository;

import be.ucll.model.Pony;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PonyRepository {

    // Class that interact with Database
    // DB --> Final topic of the course
    // For now --> data in memory

    private List<Pony> ponies;

    public PonyRepository() {
        this.ponies = new ArrayList<>();
        this.ponies.add(new Pony("Tornado", 5,140));
        this.ponies.add(new Pony("Bella", 6,135));
    }

    public void resetRepositoryData() {
        this.ponies = new ArrayList<>();
        this.ponies.add(new Pony("Tornado", 5,140));
        this.ponies.add(new Pony("Bella", 6,135));
    }

    public List<Pony> allPonies() {
        return ponies;
    }

    public Pony addPony(Pony pony) {
        ponies.add(pony);
        return pony;
    }

    public void removePony(Pony pony) {
        ponies.remove(pony);
    }

}