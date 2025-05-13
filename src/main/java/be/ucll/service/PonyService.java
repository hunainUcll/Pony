package be.ucll.service;

import be.ucll.model.Pony;
import be.ucll.repository.PonyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PonyService {

    private PonyRepository ponyRepository;

    @Autowired
    public PonyService(PonyRepository ponyRepository) {
        this.ponyRepository = ponyRepository;
    }

    public List<Pony> allPonies() {
        return ponyRepository.allPonies();
    }

    public Pony getPonyByName(String name) {
        for (Pony pony : ponyRepository.allPonies()) {
            if (pony.getName().equals(name)) {
                return pony;
            }
        }
        throw new RuntimeException("Pony with name '" + name + "' not found.");
    }

    public Pony addPony(Pony pony) {
        return ponyRepository.addPony(pony);
    }

    public Pony updatePony(String name, Pony newInformation) {
        Pony pony = getPonyByName(name); // will throw if not found
        pony.updateNameAndAge(newInformation.getName(), newInformation.getAge(),newInformation.getSize());
        return pony;
    }

    public void removePony(String name) {
        Pony pony = getPonyByName(name); // will throw if not found
        ponyRepository.removePony(pony);
    }
}
