package be.ucll.controller;

import be.ucll.model.Pony;
import be.ucll.service.PonyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // This annotation lets Spring know that this class is a "controller"
@RequestMapping("/ponies")
public class PonyController {

    // story 19
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({RuntimeException.class})
    public Map<String, String> handleRuntimeException(RuntimeException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return errors;
    }



    private PonyService ponyService;

    @Autowired
    public PonyController(PonyService ponyService) {
        this.ponyService = ponyService;
    }

    @GetMapping("/all") // http://localhost:8080/ponies/all
    public List<Pony> getPonies() {
        return ponyService.allPonies();
    }

    // A GET endpoint that returns all ponies with a certain name

    // USE : @PathVariable
    // http://localhost:8080/ponies/Tornado --> it returns all ponies with Tornado
    // http://localhost:8080/ponies/Bella --> it returns all ponies with name Bella

    // USE : @RequestParam but remove the ("/{name}")
    // http://localhost:8080/ponies?name=Tornado --> it returns all ponies with name Tornado
    // http://localhost:8080/ponies?name=Bella --> it returns all ponies with name Bella

    @GetMapping("/{name}")
    public Pony getPonyByName(@PathVariable String name) {
        return ponyService.getPonyByName(name);
    }

    // posting stuff
    @PostMapping
    public Pony addPony(@Valid @RequestBody Pony pony) {
        return ponyService.addPony(pony);
    }

    @PutMapping("/{name}")
    public Pony updatePony(@PathVariable String name, @Valid @RequestBody Pony pony) {
        return ponyService.updatePony(name, pony);
    }

    @DeleteMapping("/{name}")
    public void deletePony(@PathVariable String name) {
        ponyService.removePony(name);
    }



}