package be.cvo.bestellingservice.domain.repository;

import be.cvo.bestellingservice.domain.BestellingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BestellingRepository extends JpaRepository<BestellingEntity, Integer> {
}
