package dh.backend.clinica.service.impl;

import dh.backend.clinica.dto.request.paciente.PacienteCreateRequestDto;
import dh.backend.clinica.dto.request.paciente.PacienteUpdateRequestDto;
import dh.backend.clinica.dto.response.paciente.PacienteResponseDto;
import dh.backend.clinica.entity.Paciente;
import dh.backend.clinica.exception.ResourceNotFoundException;
import dh.backend.clinica.repository.IPacienteRepository;
import dh.backend.clinica.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private final Logger logger = LoggerFactory.getLogger(PacienteService.class);

    private IPacienteRepository pacienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    private Paciente convertirEnPaciente(PacienteCreateRequestDto pacienteCreateRequestDto){
        Paciente paciente = modelMapper.map(pacienteCreateRequestDto, Paciente.class);
        return paciente;
    }

    private Paciente convertirEnPaciente(PacienteUpdateRequestDto pacienteUpdateRequestDto){
        Paciente paciente = modelMapper.map(pacienteUpdateRequestDto, Paciente.class);
        return paciente;
    }

    @Override
    public PacienteResponseDto guardarPaciente(PacienteCreateRequestDto pacienteCreateRequestDto) {
        Paciente paciente = convertirEnPaciente(pacienteCreateRequestDto);
        Paciente newPaciente = pacienteRepository.save(paciente);
        PacienteResponseDto pacienteResponseDto = convertirPacienteEnResponse(newPaciente);
        return pacienteResponseDto;
    }

    @Override
    public Optional<PacienteResponseDto> buscarPorId(Integer id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isEmpty()){
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
        PacienteResponseDto pacienteResponseDto = convertirPacienteEnResponse(paciente.get());
            logger.info("Paciente Encontrado " + pacienteResponseDto);
        return Optional.of(pacienteResponseDto);
    }

    @Override
    public List<PacienteResponseDto> buscarTodos() {
        List<Paciente> pacientedesdeDB = pacienteRepository.findAll();
        List<PacienteResponseDto> pacientesResponse = new ArrayList<>();
        for (Paciente t: pacientedesdeDB){
            PacienteResponseDto pacienteResponseDto = convertirPacienteEnResponse(t);
            logger.info("paciente " + pacienteResponseDto);
            pacientesResponse.add(pacienteResponseDto);
        }
        return pacientesResponse;
    }




    @Override
    public void modificarPaciente(PacienteUpdateRequestDto pacienteUpdateRequestDto) {
        Optional<Paciente> paciente = pacienteRepository.findById(pacienteUpdateRequestDto.getId());
        if (paciente.isEmpty()){
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
        Paciente pacienteUpdate = convertirEnPaciente(pacienteUpdateRequestDto);
            logger.info("Paciente Update"  + pacienteUpdate);
        pacienteRepository.save(pacienteUpdate);
    }




    @Override
    public void eliminarPaciente(Integer id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isEmpty()){
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
        pacienteRepository.deleteById(id);
    }


    @Override
    public List<Paciente> buscarPorApellidoyNombre(String apellido, String nombre) {
        return pacienteRepository.findByApellidoAndNombre(apellido,nombre);
    }


    @Override
    public List<Paciente> buscarPorUnaParteApellido(String parte) {

        return pacienteRepository.buscarPorParteApellido(parte);
    }


    @Override
    public Optional<Paciente> obtenerPaciente(Integer id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isEmpty()){
            throw new ResourceNotFoundException("Paciente no encontrado");
        }
        return paciente;
    }

    @Override
    public PacienteResponseDto convertirPacienteEnResponse(Paciente paciente) {
        PacienteResponseDto pacienteResponseDto = modelMapper.map(paciente, PacienteResponseDto.class);
        return pacienteResponseDto;
    }



}
