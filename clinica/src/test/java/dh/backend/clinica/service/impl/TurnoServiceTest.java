package dh.backend.clinica.service.impl;

import dh.backend.clinica.dto.request.odontologo.OdontologoCreateRequestDto;
import dh.backend.clinica.dto.request.paciente.PacienteCreateRequestDto;
import dh.backend.clinica.dto.request.turno.TurnoModifyDto;
import dh.backend.clinica.dto.request.turno.TurnoRequestDto;
import dh.backend.clinica.dto.response.paciente.PacienteResponseDto;
import dh.backend.clinica.dto.response.turno.TurnoResponseDto;
import dh.backend.clinica.dto.response.odontologo.OdontologoResponseDto;
import dh.backend.clinica.entity.Domicilio;
import dh.backend.clinica.exception.ResourceNotFoundException;
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
class TurnoServiceTest {

    static final Logger logger = LoggerFactory.getLogger(TurnoServiceTest.class);
    @Autowired
    TurnoService turnoService;

    @Autowired
    OdontologoService odontologoService;

    @Autowired
    PacienteService pacienteService;


    TurnoResponseDto turnoDesdeDb;
    OdontologoResponseDto odontologoDesdeDb;
    PacienteResponseDto pacienteDesdeDb;

    @BeforeEach
    void cargarDatos(){
        OdontologoCreateRequestDto odontologoCreateDto = new OdontologoCreateRequestDto();
        odontologoCreateDto.setNumeroMatricula("0001");
        odontologoCreateDto.setNombre("Mauricio");
        odontologoCreateDto.setApellido("Rosas");
        odontologoDesdeDb = odontologoService.guardarOdontologo(odontologoCreateDto);

        Domicilio domicilio = new Domicilio(null,"Falsa",145,"CABA","Buenos Aires");
        PacienteCreateRequestDto paciente = new PacienteCreateRequestDto();
        paciente.setApellido("Castro");
        paciente.setNombre("Maria");
        paciente.setDni("48974646");
        paciente.setFechaIngreso("2024-08-11");
        // paciente.setDomicilio(domicilio);
        pacienteDesdeDb = pacienteService.guardarPaciente(paciente);

        TurnoRequestDto turnoRequestDto = new TurnoRequestDto();
        turnoRequestDto.setPaciente_id(pacienteDesdeDb.getId());
        turnoRequestDto.setOdontologo_id(odontologoDesdeDb.getId());
        turnoRequestDto.setFecha("2024-09-15");

        turnoDesdeDb = turnoService.guardarTurno(turnoRequestDto);

        logger.info("Turno desde la DB " + turnoDesdeDb );

    }

    @Test
    @DisplayName("Testear que un turno fue creado exitosamente")
    void guardarTurno() {
        assertNotNull(turnoDesdeDb.getId());
    }

    @Test
    @DisplayName("Buscar turno por id")
    void buscarPorId() {//Dado
        Integer id = turnoDesdeDb.getId();
        //cuando
         TurnoResponseDto turnoRecuperado = turnoService.buscarPorId(id).get();
        // entonces
        assertEquals(id, turnoRecuperado.getId());
    }

    @Test
    @DisplayName("Buscar turnos")
    void buscarTodos() {
        //Dado
        List<TurnoResponseDto> turnos;
        // cuando
        turnos = turnoService.buscarTodos();
        // entonces
        assertFalse(turnos.isEmpty());
    }

    @Test
    @DisplayName("Modificar Turno")
    void modificarTurno() {
        TurnoModifyDto turnoModifyDto = new TurnoModifyDto();
        turnoModifyDto.setId(turnoDesdeDb.getId());
        turnoModifyDto.setPaciente_id(pacienteDesdeDb.getId());
        turnoModifyDto.setOdontologo_id(odontologoDesdeDb.getId());
        turnoModifyDto.setFecha("2024-09-13");

        turnoService.modificarTurno(turnoModifyDto);
        TurnoResponseDto turnoRecuperado = turnoService.buscarPorId(turnoDesdeDb.getId()).get();
        assertEquals(turnoRecuperado.getFecha(), turnoModifyDto.getFecha());
    }

    @Test
    @DisplayName("Eliminar Turno")
    void eliminarTurno() {
        Integer id = turnoDesdeDb.getId();
        turnoService.eliminarTurno(id);
        assertThrows(ResourceNotFoundException.class, () -> {
            turnoService.buscarPorId(id);
        });
    }
}