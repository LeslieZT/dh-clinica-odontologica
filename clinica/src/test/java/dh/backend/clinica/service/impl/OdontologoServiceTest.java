package dh.backend.clinica.service.impl;

import dh.backend.clinica.dto.request.odontologo.OdontologoCreateRequestDto;
import dh.backend.clinica.dto.request.odontologo.OdontologoUpdateRequestDto;
import dh.backend.clinica.dto.response.odontologo.OdontologoResponseDto;
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
class OdontologoServiceTest {

    static final Logger logger = LoggerFactory.getLogger(OdontologoServiceTest.class);
    @Autowired
    OdontologoService odontologoService;
    OdontologoResponseDto odontologoDesdeDb;

    @BeforeEach
    void cargarDatos(){
        OdontologoCreateRequestDto  odontologoCreateDto = new OdontologoCreateRequestDto();
        odontologoCreateDto.setNumeroMatricula("0001");
        odontologoCreateDto.setNombre("Mauricio");
        odontologoCreateDto.setApellido("Rosas");
        odontologoDesdeDb = odontologoService.guardarOdontologo(odontologoCreateDto);
    }

    @Test
    @DisplayName("Testear que un odontologo fue creado exitosamente")
    void guardarOndotolgo(){
        assertNotNull(odontologoDesdeDb.getId());
    }

    @Test
    @DisplayName("Buscar odontologo por id")
    void buscarPorId(){
        //Dado
        Integer id = odontologoDesdeDb.getId();
        //cuando
        OdontologoResponseDto odontologoRecuperado = odontologoService.buscarPorId(id).get();
        // entonces
        assertEquals(id, odontologoRecuperado.getId());
    }

    @Test
    @DisplayName("Listar todos los odontologos")
    void buscarTodos(){
        //Dado
        List<OdontologoResponseDto> odontologos;
        // cuando
        odontologos = odontologoService.buscarTodos();
        // entonces
        assertFalse(odontologos.isEmpty());
    }

    @Test
    @DisplayName("Actualizar odontologo")
    void modificarOdontologo(){
        OdontologoUpdateRequestDto odontologoUpdateDto = new OdontologoUpdateRequestDto();
        odontologoUpdateDto.setId(odontologoDesdeDb.getId());
        odontologoUpdateDto.setNombre("Mauricio");
        odontologoUpdateDto.setApellido("Rosas");
        odontologoUpdateDto.setNumeroMatricula("0002");
        odontologoService.modificarOdontologo(odontologoUpdateDto);
        OdontologoResponseDto odontologoRecuperado = odontologoService.buscarPorId(odontologoDesdeDb.getId()).get();
        assertEquals(odontologoRecuperado.getNumeroMatricula(), odontologoUpdateDto.getNumeroMatricula());
    }

    @Test
    @DisplayName("Eliminar odontologo")
    void eliminarOdontologo(){
        Integer id = odontologoDesdeDb.getId();
        odontologoService.eliminarOdontologo(id);
        assertThrows(ResourceNotFoundException.class, () -> {
            odontologoService.buscarPorId(id);
        });
    }
}