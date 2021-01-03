package be.cvo.bestellingservice.service.dto;

import lombok.Data;

@Data
public class Beleg {

    private Integer id;
    private String name;
    private Double price;
    private String description;
}
