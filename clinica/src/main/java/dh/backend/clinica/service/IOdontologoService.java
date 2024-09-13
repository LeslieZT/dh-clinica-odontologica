package dh.backend.clinica.service;

import dh.backend.clinica.dto.request.odontologo.OdontologoCreateRequestDto;
import dh.backend.clinica.dto.request.odontologo.OdontologoUpdateRequestDto;
import dh.backend.clinica.dto.response.odontologo.OdontologoResponseDto;
import dh.backend.clinica.entity.Odontologo;
import dh.backend.clinica.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    OdontologoResponseDto guardarOdontologo (OdontologoCreateRequestDto dontologoCreateRequestDto);

    Optional<OdontologoResponseDto> buscarPorId(Integer id);

    List<OdontologoResponseDto> buscarTodos();

    void modificarOdontologo (OdontologoUpdateRequestDto odontologoUpdateRequestDto);

    void eliminarOdontologo(Integer id);

    List<OdontologoResponseDto> buscarPorNumeroMatricula(String numeroMatricula);

    List<OdontologoResponseDto> buscarPorNombre(String parteNombre);

    Optional<Odontologo> obtenerOdontologo(Integer id);

    OdontologoResponseDto convertirOdontologoEnResponse(Odontologo odontologo);

    List<OdontologoResponseDto> buscarOdontologosSinTurnos();

}
