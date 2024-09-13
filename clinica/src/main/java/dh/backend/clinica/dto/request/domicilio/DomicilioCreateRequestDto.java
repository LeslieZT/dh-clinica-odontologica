package dh.backend.clinica.dto.request.domicilio;


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
public class DomicilioCreateRequestDto {

    @NotBlank(message = "calle es obligatorio")
    private String calle;

    @NotNull(message = "numero es obligatorio")
    private int numero;

    @NotBlank(message = "localidad es obligatorio")
    private String localidad;

    @NotBlank(message = "provincia es obligatorio")
    private String provincia;
}
