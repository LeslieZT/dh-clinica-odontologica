package dh.backend.clinica.dto.response.turno;

import dh.backend.clinica.dto.response.odontologo.OdontologoResponseDto;
import dh.backend.clinica.dto.response.paciente.PacienteResponseDto;
import dh.backend.clinica.utils.GsonProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoResponseDto {
    private Integer id;
    // datos del paciente
    private PacienteResponseDto paciente;
    // datos del odontologo
    private OdontologoResponseDto odontologo;
    private String fecha;

    @Override
    public String toString() {
        return GsonProvider.getGson().toJson(this);
    }
}
