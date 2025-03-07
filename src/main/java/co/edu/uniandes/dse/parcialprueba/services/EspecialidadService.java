package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EspecialidadService 
{
    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Transactional
    private EspecialidadEntity createEspecialidad(EspecialidadEntity especialidadEntity) throws IllegalOperationException
    {
        log.info("Inicia el proceso de crear una Especialida");

        if(!validarDescripcion(especialidadEntity.getDescripcion())) throw new IllegalOperationException("La descripcion no es valida");

        log.info("Termina el proceso de crear una Especialida");

        return especialidadRepository.save(especialidadEntity);
    }


    private boolean validarDescripcion(String descrp)
    {
        boolean descripcionAlMenos10 = descrp.length() >= 10;
        return descripcionAlMenos10 && (!(descrp==null||descrp.isEmpty()));
    }

}
