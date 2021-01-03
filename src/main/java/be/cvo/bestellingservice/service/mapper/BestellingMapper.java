package be.cvo.bestellingservice.service.mapper;

import be.cvo.bestellingservice.domain.BestellingEntity;
import be.cvo.bestellingservice.service.dto.Beleg;
import be.cvo.bestellingservice.service.dto.Bestelling;
import be.cvo.bestellingservice.service.dto.Brood;
import be.cvo.bestellingservice.service.dto.Saus;
import org.springframework.stereotype.Component;

@Component
public class BestellingMapper {

    public BestellingEntity toEntity(Bestelling bestelling){
        return new BestellingEntity(bestelling.getId(), bestelling.getName(), bestelling.getBroodId(), bestelling.getBelegId(), bestelling.getSausId(), bestelling.getPrice(), bestelling.getDescription());
    }

    public Bestelling toDTO(BestellingEntity bestellingEntity){
        return new Bestelling(bestellingEntity.getId(), bestellingEntity.getName(), bestellingEntity.getBroodId(), bestellingEntity.getBelegId(), bestellingEntity.getSausId(), bestellingEntity.getPrice(), bestellingEntity.getDescription(), new Brood(), new Beleg(), new Saus());
    }
}
