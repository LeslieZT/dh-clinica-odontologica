package dh.backend.clinica.controller;

import dh.backend.clinica.dto.request.paciente.PacienteCreateRequestDto;
import dh.backend.clinica.dto.request.paciente.PacienteUpdateRequestDto;
import dh.backend.clinica.dto.response.PacienteResponseDto;
import dh.backend.clinica.entity.Paciente;
import dh.backend.clinica.service.IPacienteService;
import dh.backend.clinica.service.impl.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //POST
    @PostMapping("/guardar")
    public ResponseEntity<PacienteResponseDto> guardarPaciente(@Valid @RequestBody PacienteCreateRequestDto pacienteCreateRequestDto){
        PacienteResponseDto pacienteResponseDto = pacienteService.guardarPaciente(pacienteCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponseDto);
    }

    //PUT
    @PutMapping("/modificar")
    public ResponseEntity<String>  modificarPaciente(@Valid @RequestBody PacienteUpdateRequestDto pacienteUpdateRequestDto){
        pacienteService.modificarPaciente(pacienteUpdateRequestDto);
        String jsonResponse = "{\"mensaje\": \"El paciente fue modificado\"}";
        return ResponseEntity.ok(jsonResponse);
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Integer id){
         pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("{\"mensaje\": \"El paciente fue eliminado\"}");
    }

    //GET
    @GetMapping("/buscar/{id}")
    public ResponseEntity<PacienteResponseDto>  buscarPorId(@PathVariable Integer id){
        Optional<PacienteResponseDto>  pacienteEncontrado = pacienteService.buscarPorId(id);
        if(pacienteEncontrado.isPresent()) {
            return ResponseEntity.ok(pacienteEncontrado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //GET
    @GetMapping("/buscartodos")
    public ResponseEntity<List<PacienteResponseDto>>  buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @GetMapping("/buscarApellidoNombre/{apellido}/{nombre}")
    public ResponseEntity<List<Paciente>> buscarApellido(@PathVariable String apellido, @PathVariable String nombre){
        return ResponseEntity.ok(pacienteService.buscarPorApellidoyNombre(apellido, nombre));
    }

    @GetMapping("/buscarApellido/{parte}")
    public ResponseEntity<List<Paciente>> buscarParteApellido(@PathVariable String parte){
        return ResponseEntity.ok(pacienteService.buscarPorUnaParteApellido(parte));
    }

}
