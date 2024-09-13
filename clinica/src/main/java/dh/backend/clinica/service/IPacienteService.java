package dh.backend.clinica.service;

import dh.backend.clinica.dto.request.paciente.PacienteCreateRequestDto;
import dh.backend.clinica.dto.request.paciente.PacienteUpdateRequestDto;
import dh.backend.clinica.dto.response.PacienteResponseDto;
import dh.backend.clinica.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    PacienteResponseDto guardarPaciente(PacienteCreateRequestDto pacienteCreateRequestDto);

    Optional<PacienteResponseDto> buscarPorId(Integer id);

    List<PacienteResponseDto> buscarTodos();

    void modificarPaciente(PacienteUpdateRequestDto pacienteUpdateRequestDto);

    void eliminarPaciente(Integer id);

    List<Paciente> buscarPorApellidoyNombre(String apellido, String nombre);

    List<Paciente> buscarPorUnaParteApellido(String parte);

    Optional<Paciente> obtenerPaciente(Integer id);

    PacienteResponseDto convertirPacienteEnResponse(Paciente paciente);
}
