package co.edu.uniandes.dse.parcialprueba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import java.util.List;


@Repository
public interface EspecialidadRepository extends JpaRepository<EspecialidadEntity, Long>{
    List<EspecialidadEntity> findByNombre(String nombre);
    List<EspecialidadEntity> findByDescripcion(String descripcion);
}
