package dh.backend.clinica.dto.request.odontologo;

import dh.backend.clinica.utils.GsonProvider;
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
public class OdontologoUpdateRequestDto  {
    @NotNull(message = "Id de odontologo es obligatorio")
    private Integer id;

    @NotBlank(message = "Numero de matricula es obligatorio")
    private String numeroMatricula;

    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Apellido es obligatorio")
    private String apellido;


    @Override
    public String toString() {
        return GsonProvider.getGson().toJson(this);
    }
}
