package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import({MedicService.class, EspecialidadService.class})
public class EspecialidadServiceTest {
    
    @Autowired
    private EspecialidadService especialidadService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();
    private List<MedicEntity> medicos = new ArrayList<>();
    private List<EspecialidadEntity> especialidades = new ArrayList<>();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("DELETE FROM MedicoEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("DELETE FROM EspecialidadEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            MedicEntity medicoEntity = factory.manufacturePojo(MedicEntity.class);
            medicoEntity.setRegistroMedico("RM" + (1000 + i));
            entityManager.persist(medicoEntity);
            medicos.add(medicoEntity);
        }

        for (int i = 0; i < 3; i++) {
            EspecialidadEntity especialidadEntity = factory.manufacturePojo(EspecialidadEntity.class);
            especialidadEntity.setDescripcion("Descripcion valida de especialidad");
            entityManager.persist(especialidadEntity);
            especialidades.add(especialidadEntity);
        }
    }
    
    @Test
    public void testCreateEspecialidad_Success() throws IllegalOperationException, EntityNotFoundException{
        EspecialidadEntity especialidad = factory.manufacturePojo(EspecialidadEntity.class);
        especialidad.setDescripcion("Descripcion valida");
        EspecialidadEntity result = especialidadService.createEspecialidad(especialidad);
        assertNotNull(result);
    }

    @Test
    public void testCreateEspecialidad_Failure_ShortDescription() {
        EspecialidadEntity especialidad = factory.manufacturePojo(EspecialidadEntity.class);
        especialidad.setDescripcion("Corta");
        assertThrows(IllegalOperationException.class, () -> especialidadService.createEspecialidad(especialidad));
    }



}
