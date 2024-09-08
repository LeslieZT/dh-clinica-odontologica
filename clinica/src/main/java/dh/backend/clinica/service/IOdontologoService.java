package dh.backend.clinica.service;

import dh.backend.clinica.dto.request.odontologo.OdontologoCreateRequestDto;
import dh.backend.clinica.dto.request.odontologo.OdontologoUpdateRequestDto;
import dh.backend.clinica.dto.response.odontologo.OdontologoResponseDto;
import dh.backend.clinica.entity.Odontologo;
import dh.backend.clinica.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    OdontologoResponseDto create (OdontologoCreateRequestDto dontologoCreateRequestDto);
    Optional<OdontologoResponseDto> findOne(Integer id);
    List<OdontologoResponseDto> findAll();
    void update (OdontologoUpdateRequestDto odontologoUpdateRequestDto);
    void delete(Integer id);

    List<OdontologoResponseDto> searchByRegistrationNumber(String numeroMatricula);

    List<OdontologoResponseDto> searchByName(String parteNombre);

    Optional<Odontologo> getOdontologoById(Integer id);

    OdontologoResponseDto convertirOdontologoEnResponse(Odontologo odontologo);

}
