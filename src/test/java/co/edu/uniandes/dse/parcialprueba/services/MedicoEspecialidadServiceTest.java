package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(MedicoEspecialidadService.class)
public class MedicoEspecialidadServiceTest 
{
    @Autowired
	private MedicoEspecialidadService medicoEspecialidadService;
	
	@Autowired
	private TestEntityManager entityManager;

	private PodamFactory factory = new PodamFactoryImpl();

	private MedicoEntity medico = new MedicoEntity();
	private List<EspecialidadEntity> especilidadList = new ArrayList<>();

    @BeforeEach
	void setUp() 
    {
		clearData();
		insertData();
	}
	
	/**
	 * Limpia las tablas que están implicadas en la prueba.
	 */
	private void clearData() 
    {
		entityManager.getEntityManager().createQuery("delete from EspecialidadEntity").executeUpdate();
		entityManager.getEntityManager().createQuery("delete from MedicoEntity").executeUpdate();
	}

	/**
	 * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
	 */
	private void insertData() 
    {

		medico = factory.manufacturePojo(MedicoEntity.class);
		entityManager.persist(medico);

		for (int i = 0; i < 3; i++) {
			EspecialidadEntity entity = factory.manufacturePojo(EspecialidadEntity.class);
			entityManager.persist(entity);
			entity.getMedicos().add(medico);
			especilidadList.add(entity);
			medico.getEspecialidades().add(entity);
		}
	}

    @Test
	void testAddEspecialidad() throws EntityNotFoundException, IllegalOperationException 
    {
		MedicoEntity newMedico = factory.manufacturePojo(MedicoEntity.class);
		entityManager.persist(newMedico);
		
		EspecialidadEntity especialidad = factory.manufacturePojo(EspecialidadEntity.class);
		entityManager.persist(especialidad);
		
		medicoEspecialidadService.addEspecialidad(newMedico.getId(),especialidad.getId());
		
		EspecialidadEntity lastEspecialidad = medicoEspecialidadService.getEspecialidad(newMedico.getId(), especialidad.getId());
		assertEquals(especialidad.getId(), lastEspecialidad.getId());
		assertEquals(especialidad.getNombre(), lastEspecialidad.getNombre());
		assertEquals(especialidad.getDescripcion(), lastEspecialidad.getDescripcion());
		
	}

}
