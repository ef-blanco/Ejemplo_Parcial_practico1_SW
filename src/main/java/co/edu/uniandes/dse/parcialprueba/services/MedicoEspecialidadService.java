package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Optional;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoEspecialidadService 
{
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Transactional
    public MedicoEntity addEspecialidad(Long idMedico, Long idEspecialidad) throws EntityNotFoundException
    {
        log.info("Inicia el proceso de asociar una especialidad al medico con id = {0}",idMedico);

        Optional<MedicoEntity> medicoEntity = medicoRepository.findById(idMedico);
        Optional<EspecialidadEntity> especialidadEntity = especialidadRepository.findById(idEspecialidad);

        if(medicoEntity.isEmpty()) throw new EntityNotFoundException("No se encontro el Medico");
        if(especialidadEntity.isEmpty()) throw new EntityNotFoundException("No se encontro la especialidad");

        medicoEntity.get().getEspecialidades().add(especialidadEntity.get());
        log.info("Termina el proceso de asociar una especialidad al medico con id = {0}",idMedico);

        return medicoEntity.get();
    }


    @Transactional
	public EspecialidadEntity getEspecialidad(Long medicoId, Long especialidadId)
			throws EntityNotFoundException, IllegalOperationException 
    {
		log.info("Inicia proceso de consultar una especialidad del medico con id = {0}", medicoId);
		Optional<EspecialidadEntity> especialidadEntity = especialidadRepository.findById(especialidadId);
		Optional<MedicoEntity> medicoEntity = medicoRepository.findById(medicoId);

		if (especialidadEntity.isEmpty())
			throw new EntityNotFoundException("La especialidad no se encontro");

		if (medicoEntity.isEmpty())
			throw new EntityNotFoundException("El medico no se encontro");
		log.info("Termina proceso de consultar una especialidad del medico con id = {0}", medicoId);
		if (!medicoEntity.get().getEspecialidades().contains(especialidadEntity.get()))
			throw new IllegalOperationException("La especialidad no esta asociada al medico");
		
		return especialidadEntity.get();
	}
}
