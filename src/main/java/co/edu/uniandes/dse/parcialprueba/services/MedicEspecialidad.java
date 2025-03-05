package co.edu.uniandes.dse.parcialprueba.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicRepository;
import jakarta.transaction.Transactional;

@Service
public class MedicEspecialidad {
    @Autowired
    private MedicRepository medicoRepository;
    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Transactional
    public void addEspecialidad(Long medicoId, Long especialidadId) {
        MedicEntity medico = medicoRepository.findById(medicoId)
            .orElseThrow(() -> new IllegalArgumentException("El médico no existe"));
        EspecialidadEntity especialidad = especialidadRepository.findById(especialidadId)
            .orElseThrow(() -> new IllegalArgumentException("La especialidad no existe"));
        
        medico.getEspecialidades().add(especialidad);
        medicoRepository.save(medico);
    }
}
