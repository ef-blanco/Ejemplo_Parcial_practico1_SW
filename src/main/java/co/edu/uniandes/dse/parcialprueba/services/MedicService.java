package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.MedicEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicService {

    @Autowired
    private MedicRepository medicRepository;

    @Transactional
    public MedicEntity createMedico(MedicEntity medico) throws EntityNotFoundException, IllegalOperationException{
        if (medico == null || medico.getNombre() == null || medico.getApellido() == null || medico.getRegistroMedico() == null || medico.getId() == null) {
            throw new IllegalArgumentException("Ningún campo del médico puede ser nulo");
        }
        if (!medico.getRegistroMedico().startsWith("RM")) {
            throw new IllegalArgumentException("El registro médico debe iniciar con 'RM'");
        }
        return medicRepository.save(medico);
    }
}
