package dh.backend.clinica.service;
import dh.backend.clinica.dto.request.turno.TurnoModifyDto;
import dh.backend.clinica.dto.request.turno.TurnoRequestDto;
import dh.backend.clinica.dto.response.turno.TurnoResponseDto;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    TurnoResponseDto guardarTurno(TurnoRequestDto turnoRequestDto);

    Optional<TurnoResponseDto> buscarPorId(Integer id);

    List<TurnoResponseDto> buscarTodos();

    void modificarTurno(TurnoModifyDto turnoModifyDto);

    void eliminarTurno(Integer id);
    
    List<TurnoResponseDto> buscarTurnosPorPaciente(String pacienteApellido);
}
