package dh.backend.clinica.service;
import dh.backend.clinica.dto.request.turno.TurnoModifyDto;
import dh.backend.clinica.dto.request.turno.TurnoRequestDto;
import dh.backend.clinica.dto.response.TurnoResponseDto;
import dh.backend.clinica.entity.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    TurnoResponseDto create(TurnoRequestDto turnoRequestDto);

    Optional<TurnoResponseDto> findOne(Integer id);

    List<TurnoResponseDto> findAll();

    void update(TurnoModifyDto turnoModifyDto);

    void delete(Integer id);
    Optional<TurnoResponseDto> buscarTurnosPorPaciente(String pacienteApellido);
}
