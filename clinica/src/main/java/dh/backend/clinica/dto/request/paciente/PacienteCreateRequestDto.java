package dh.backend.clinica.dto.request.paciente;

import dh.backend.clinica.utils.GsonProvider;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteCreateRequestDto {
	@NotBlank(message = "Apellido es obligatorio")
	private String apellido;

	@NotBlank(message = "Nombre es obligatorio")
	private String nombre;

	@NotBlank(message = "DNI es obligatorio")
	private String dni;

	@NotBlank(message = "La fecha de ingreso es obligatoria")
	private String fechaIngreso;

	@Override
	public String toString() {
		return GsonProvider.getGson().toJson(this);
	}
}

