package dh.backend.clinica.controller;

import dh.backend.clinica.dto.request.odontologo.OdontologoCreateRequestDto;
import dh.backend.clinica.dto.request.odontologo.OdontologoUpdateRequestDto;
import dh.backend.clinica.dto.response.odontologo.OdontologoResponseDto;

import dh.backend.clinica.service.IOdontologoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }


    //GET
    @GetMapping("/buscar/{id}")
    public ResponseEntity<OdontologoResponseDto>  buscarPorId(@PathVariable Integer id){
        Optional<OdontologoResponseDto> odontologo = odontologoService.buscarPorId(id);
        return ResponseEntity.ok(odontologo.get());
    }

    //GET
    @GetMapping("/buscartodos")
    public ResponseEntity<List<OdontologoResponseDto>>  buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    //POST
    @PostMapping("/guardar")
    public ResponseEntity<OdontologoResponseDto> guardarOdontologo(@Valid @RequestBody OdontologoCreateRequestDto odontologoCreateRequestDto){
        OdontologoResponseDto odontologoResponseDto = odontologoService.guardarOdontologo(odontologoCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologoResponseDto);
    }

    //PUT
    @PutMapping("/modificar")
    public ResponseEntity<String>  modificarOdontologo(@Valid @RequestBody OdontologoUpdateRequestDto odontologoUpdateRequestDto ){
        odontologoService.modificarOdontologo(odontologoUpdateRequestDto);
        String jsonResponse = "{\"mensaje\": \"El odontologo fue modificado\"}";
        return ResponseEntity.ok(jsonResponse);
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id){
        odontologoService.eliminarOdontologo(id);
        String jsonResponse = "{\"mensaje\": \"El odontologo fue eliminado\"}";
        return ResponseEntity.ok(jsonResponse);
    }

    @GetMapping("/buscarNumeroMatricula/{numeroMatricula}")
    public ResponseEntity<List<OdontologoResponseDto>> buscarPorNumeroMatricula(@PathVariable String numeroMatricula){
        return ResponseEntity.ok(odontologoService.buscarPorNumeroMatricula(numeroMatricula));
    }

    @GetMapping("/buscarNombre/{parte}")
    public ResponseEntity<List<OdontologoResponseDto>> buscarParteNombre(@PathVariable String parte){
        return ResponseEntity.ok(odontologoService.buscarPorNombre(parte));
    }
}
