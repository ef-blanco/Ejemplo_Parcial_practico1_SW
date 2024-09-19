package co.edu.uniandes.dse.parcialprueba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniandes.dse.parcialprueba.entities.*;
import java.util.List;
import java.util.Optional;

@Repository
public interface EspecialidadRepository extends JpaRepository<MedicoEntity, Long> {
    List<EspecialidadEntity> findByNombre(String nombre);
    List<EspecialidadEntity> findByDescripcion(String descripcion);
    Optional findById(Long id);

    


}

