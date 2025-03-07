package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoService 
{
    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    public MedicoEntity createMedico(MedicoEntity medicoEntity) throws IllegalOperationException
    {
        log.info("Inicia el proceso de crear un Medico");

        if(!validarRegistroMedico(medicoEntity.getRegistroMedico())) throw new IllegalOperationException("El registro medico no es valido");

        log.info("Termina el proceso de crear un Medico");

        return medicoRepository.save(medicoEntity);
    }

    private boolean validarRegistroMedico(String registroMed)
    {
        boolean empiezaPorRM = registroMed.substring(0,2).equals("RM");
        return empiezaPorRM &&(!(registroMed == null)||(registroMed.isEmpty()));
    }
}
