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
        return ponyRepository.findAll();
    }

    public Pony getPonyByName(String name) {
        return ponyRepository.findByName(name);
    }

    public Pony addPony(Pony pony) {
        return ponyRepository.save(pony);
    }

    public Pony updatePony(String name, Pony newInformation) {
        Pony pony = getPonyByName(name);
        if (pony == null) {
            throw new RuntimeException("Pony not found with name: " + name);
        }
        pony.updateNameAndAge(newInformation.getName(), newInformation.getAge(),newInformation.getSize());
        ponyRepository.save(pony);
        return pony;
    }

    public void removePony(String name) {
        Pony pony = getPonyByName(name);
        if (pony != null) {
            ponyRepository.delete(pony);
        }
    }
}
