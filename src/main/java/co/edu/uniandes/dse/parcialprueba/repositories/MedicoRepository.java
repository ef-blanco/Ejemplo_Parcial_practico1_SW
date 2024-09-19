package co.edu.uniandes.dse.parcialprueba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniandes.dse.parcialprueba.entities.*;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
    List<MedicoEntity> findByNombre(String nombre);
    List<MedicoEntity> findByApellido(String apellido);
    List<MedicoEntity> findByRegistroMedico(String registroMedico);
    Optional<MedicoEntity> findById(Long id);

    


}
