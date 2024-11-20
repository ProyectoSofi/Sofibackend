package co.edu.javeriana.seshat.sofiplus.DataFacade.Entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaEntityRepository extends JpaRepository<PersonaEntity, String> {
}