package co.edu.uniandes.dse.parcialprueba.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class MedicEntity extends BaseEntity{
    private String nombre;
    private String apellido;
    private String registroMedico;

    @ManyToMany
    @PodamExclude
    @JoinTable(name = "medico_especialidad", joinColumns = @JoinColumn(name = "medic_id"), inverseJoinColumns = @JoinColumn(name = "especialidad_id"))
    private Set<EspecialidadEntity> especialidades;
    

}
