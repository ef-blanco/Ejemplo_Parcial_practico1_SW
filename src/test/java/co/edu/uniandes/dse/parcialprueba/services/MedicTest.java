package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
@Import(MedicService.class)
public class MedicTest {
    @Autowired
    private MedicService medicService;

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
        entityManager.getEntityManager().createQuery("Delete from MedicEntity");
        entityManager.getEntityManager().createQuery("Delete from EspecialidadEntity");

    }

    private void insertData() {
        for (int i = 0; i<3; i++){
            MedicEntity medicEntity = factory.manufacturePojo(MedicEntity.class);
            entityManager.persist(medicEntity);
            medicos.add(medicEntity);
        }

        for (int i = 0; i<3; i++){
            EspecialidadEntity especialidadEntity = factory.manufacturePojo(EspecialidadEntity.class);
            entityManager.persist(especialidadEntity);
            especialidades.add(especialidadEntity);
        }

    }

    @Test
    void testCrearMedico() throws EntityNotFoundException, IllegalOperationException {
        MedicEntity newEntity = factory.manufacturePojo(MedicEntity.class);
        newEntity.setRegistroMedico("RM1234");
        MedicEntity result = medicService.createMedico(newEntity);

        assertNotNull(result);

        MedicEntity entity = entityManager.find(MedicEntity.class, result.getId());
        assertEquals(result.getId(), entity.getId());
        assertEquals(result.getNombre(), entity.getNombre());
        assertEquals(result.getApellido(), entity.getApellido());
        assertEquals(result.getRegistroMedico(), entity.getRegistroMedico());
    }

    @Test 
    void testCrearMedicoInvalido() throws EntityNotFoundException, IllegalOperationException {
        assertThrows(IllegalOperationException.class, () -> {
            MedicEntity newEntity = factory.manufacturePojo(MedicEntity.class);
            newEntity.setRegistroMedico("1234");
            medicService.createMedico(newEntity);
        });
    }
}
