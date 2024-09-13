package dh.backend.clinica.service.impl;


import dh.backend.clinica.dto.request.paciente.PacienteCreateRequestDto;
import dh.backend.clinica.dto.response.paciente.PacienteResponseDto;
import dh.backend.clinica.entity.Domicilio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class PacienteServiceTest {
    static final Logger logger = LoggerFactory.getLogger(PacienteServiceTest.class);
    @Autowired
    PacienteService pacienteService;
    PacienteCreateRequestDto paciente;
    PacienteResponseDto pacienteDesdeDb;

    @BeforeEach
    void cargarDatos(){
        Domicilio domicilio = new Domicilio(null,"Falsa",145,"CABA","Buenos Aires");
        paciente = new PacienteCreateRequestDto();
        paciente.setApellido("Castro");
        paciente.setNombre("Maria");
        paciente.setDni("48974646");
        paciente.setFechaIngreso("2024-08-11");
        // paciente.(domicilio);

        pacienteDesdeDb = pacienteService.guardarPaciente(paciente);
    }

    @Test
    @DisplayName("Testear que un paciente fue cargado correctamente con su domicilio")
    void crearPaciente(){
        assertNotNull(pacienteDesdeDb.getId());
    }

    @Test
    @DisplayName("Testear que un paciente pueda acceder por id")
    void buscarPorId(){
        //Dado
        Integer id = pacienteDesdeDb.getId();
        //cuando
        PacienteResponseDto pacienteRecuperado = pacienteService.buscarPorId(id).get();
        // entonces
        assertEquals(id, pacienteRecuperado.getId());
    }

    @Test
    @DisplayName("Listar todos los pacientes")
    void buscarTodos(){
        //Dado
        List<PacienteResponseDto> pacientes;
        // cuando
        pacientes = pacienteService.buscarTodos();
        // entonces
        assertFalse(pacientes.isEmpty());
    }

}