package dh.backend.clinica.dto.request.paciente;

import dh.backend.clinica.dto.request.domicilio.DomicilioCreateRequestDto;
import dh.backend.clinica.utils.GsonProvider;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteUpdateRequestDto {
	@NotNull(message = "Id de paciente es obligatorio")
	private Integer id;

	@NotBlank(message = "Apellido es obligatorio")
	private String apellido;

	@NotBlank(message = "Nombre es obligatorio")
	private String nombre;

	@NotBlank(message = "DNI es obligatorio")
	private String dni;

	@NotBlank(message = "La fecha de ingreso es obligatorio")
	private String fechaIngreso;

	@NotNull(message = "El domicilio es obligatorio")
	@Valid
	private DomicilioCreateRequestDto domicilio;

	@Override
	public String toString() {
		return GsonProvider.getGson().toJson(this);
	}
}
