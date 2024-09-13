package dh.backend.clinica.controller;

import dh.backend.clinica.dto.response.paciente.PacienteResponseDto;

import dh.backend.clinica.service.IPacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VistaController {

    private IPacienteService pacienteService;

    public VistaController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //localhost:8080/index?id=1&nombre=paciente1
    @GetMapping("/index")
    public String buscarPaciente(Model model, @RequestParam Integer id){
        PacienteResponseDto paciente = pacienteService.buscarPorId(id).get();

        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());
        return "vista/paciente";
    }


}
