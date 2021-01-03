package be.cvo.bestellingservice.web;

import be.cvo.bestellingservice.service.BestellingService;
import be.cvo.bestellingservice.service.dto.Bestelling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BestellingResource {

    @Autowired
    private BestellingService bestellingService;

    @PostMapping("/add-bestelling")
    public void addBestelling(@RequestBody Bestelling bestelling){
        bestellingService.addBestelling(bestelling);
    }

    @GetMapping("/bestellingen")
    public ResponseEntity<List<Bestelling>> getAll(){
        List<Bestelling> bestellingList = bestellingService.getAll();
        return new ResponseEntity<>(bestellingList, HttpStatus.OK);
    }

    @GetMapping("/bestelling/{id}")
    public ResponseEntity<Bestelling> getById(@PathVariable Integer id){
        Bestelling bestelling =bestellingService.getById(id);
        return new ResponseEntity<>(bestelling, HttpStatus.OK);
    }
}
