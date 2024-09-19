package co.edu.uniandes.dse.parcialprueba.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.*;
import co.edu.uniandes.dse.parcialprueba.repositories.*;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service

public class MedicoEntityService {

        @Autowired
        private MedicoRepository medicoRepository;


        @Transactional
        public MedicoEntity createMedico(MedicoEntity nuevoMedico) throws IllegalOperationException {
        log.info("Inicia el proceso de crear un médico");

        if(nuevoMedico == null) {
            throw new IllegalOperationException("El médico no puede ser nulo");
        }
        if (medicoRepository.findByRegistroMedico(nuevoMedico.getRegistroMedico()) != null) {
            throw new IllegalOperationException("Ya existe un médico con el registro médico dado");
        }
    }
            
}
