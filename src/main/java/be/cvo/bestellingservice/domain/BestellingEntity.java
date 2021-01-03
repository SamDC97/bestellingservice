package be.cvo.bestellingservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestellingEntity {

    @Id
    private Integer id;
    private String name;
    private Integer broodId;
    private Integer belegId;
    private Integer sausId;
    private Double price;
    private String description;
}
