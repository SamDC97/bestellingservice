package be.cvo.bestellingservice.service;

import be.cvo.bestellingservice.domain.BestellingEntity;
import be.cvo.bestellingservice.domain.repository.BestellingRepository;
import be.cvo.bestellingservice.service.dto.Beleg;
import be.cvo.bestellingservice.service.dto.Bestelling;
import be.cvo.bestellingservice.service.dto.Brood;
import be.cvo.bestellingservice.service.dto.Saus;
import be.cvo.bestellingservice.service.mapper.BestellingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BestellingService {

    @Autowired
    private BestellingRepository bestellingRepository;
    @Autowired
    private BestellingMapper bestellingMapper;
    @Value("${url.brood}")
    private String urlBrood;
    @Value("${url.beleg}")
    private String urlBeleg;
    @Value("${url.saus}")
    private String urlSaus;

    @Autowired
    private RestTemplate restTemplate;

    public void addBestelling(Bestelling bestelling){
        bestellingRepository.save(bestellingMapper.toEntity(bestelling));
    }

    public List<Bestelling> getAll(){
        List<Bestelling> bestellingList = StreamSupport
                .stream(bestellingRepository.findAll().spliterator(), false)
                .map(b -> bestellingMapper.toDTO(b))
                .collect(Collectors.toList());
        for (Bestelling bestelling : bestellingList){
            setItems(bestelling);

            // Remove this if the price has to be set manually.
            // This code overrides the given price and calculates a new price, correct to the prices of the components.
            Double price = bestelling.getBrood().getPrice() + bestelling.getBeleg().getPrice() + bestelling.getSaus().getPrice();
            bestelling.setPrice(price);
        }
        return bestellingList;
    }

    private Bestelling setItems(Bestelling bestelling) {
        bestelling.setBrood(getRESTBrood(bestelling.getBroodId()));
        bestelling.setBeleg(getRESTBeleg(bestelling.getBelegId()));
        bestelling.setSaus(getRESTSaus(bestelling.getSausId()));

        return bestelling;
    }

    public Bestelling getById(Integer id){
        Optional<BestellingEntity> bestellingEntityOptional = bestellingRepository.findById(id);
        if (bestellingEntityOptional.isPresent()){
            Bestelling bestelling = bestellingMapper.toDTO(bestellingEntityOptional.get());
            setItems(bestelling);

            return bestelling;
        }
        return null;
    }

    private Brood getRESTBrood(Integer id){
        try{
            URI broodURI = new URI(urlBrood + id.toString());
            return restTemplate.getForObject(broodURI, Brood.class);
        } catch (URISyntaxException e){
            e.printStackTrace();
            return null;
        }
    }

    private Beleg getRESTBeleg(Integer id){
        try{
            URI belegURI = new URI(urlBeleg + id.toString());
            return restTemplate.getForObject(belegURI, Beleg.class);
        } catch (URISyntaxException e){
            e.printStackTrace();
            return null;
        }
    }

    private Saus getRESTSaus(Integer id){
        try{
            URI sausURI = new URI(urlSaus + id.toString());
            return restTemplate.getForObject(sausURI, Saus.class);
        } catch (URISyntaxException e){
            e.printStackTrace();
            return null;
        }
    }

}
