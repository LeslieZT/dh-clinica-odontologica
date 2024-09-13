package dh.backend.clinica.dto.response.paciente;

import dh.backend.clinica.dto.response.domicilio.DomicilioResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponseDto {
    private Integer id;
    private String apellido;
    private String nombre;
    private String dni;
    private DomicilioResponseDto domicilio;
    private String fechaIngreso;

}
