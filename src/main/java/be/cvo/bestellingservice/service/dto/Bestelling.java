package be.cvo.bestellingservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bestelling {

    private Integer id;
    private String name;
    private Integer broodId;
    private Integer belegId;
    private Integer sausId;
    private Double price;
    private String description;

    private Brood brood;
    private Beleg beleg;
    private Saus saus;
}
