package dh.backend.clinica.controller;


import dh.backend.clinica.model.Odontologo;
import dh.backend.clinica.service.OdontologoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    //POST
    @PostMapping("/guardar")
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardar(odontologo);
    }

    //PUT
    @PutMapping("/modificar")
    public String modificarOdontologo(@RequestBody Odontologo odontologo){
        odontologoService.modificar(odontologo);
        return "el odontologo "+ odontologo.getId() + " fue modificado";
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public String eliminarOdontologo(@PathVariable Integer id){
        odontologoService.eliminar(id);
        return "el odontologo "+ id + " fue eliminado";
    }

    //GET
    @GetMapping("/buscar/{id}")
    public Odontologo buscarPorId(@PathVariable Integer id){

        return odontologoService.buscarPorId(id);
    }

    //GET
    @GetMapping("/buscartodos")
    public List<Odontologo> buscarTodos(){
        return odontologoService.listaTodos();
    }
}