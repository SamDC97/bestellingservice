package be.cvo.bestellingservice.service.dto;

import lombok.Data;

@Data
public class Brood {

    private Integer id;
    private String name;
    private Double price;
    private String description;
}
