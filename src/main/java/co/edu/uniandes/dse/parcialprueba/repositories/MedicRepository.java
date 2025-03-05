package co.edu.uniandes.dse.parcialprueba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.parcialprueba.entities.MedicEntity;
import java.util.List;


@Repository
public interface MedicRepository extends JpaRepository<MedicEntity, Long>{
    List<MedicEntity> findByNombre(String nombre);
    List<MedicEntity> findByApellido(String apellido);
    List<MedicEntity> findByRegistroMedico(String registroMedico); 
}
