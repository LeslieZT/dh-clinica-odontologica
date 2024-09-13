package dh.backend.clinica.service.impl;


import dh.backend.clinica.dto.request.turno.TurnoModifyDto;
import dh.backend.clinica.dto.request.turno.TurnoRequestDto;
import dh.backend.clinica.dto.response.paciente.PacienteResponseDto;
import dh.backend.clinica.dto.response.turno.TurnoResponseDto;
import dh.backend.clinica.dto.response.odontologo.OdontologoResponseDto;
import dh.backend.clinica.entity.Odontologo;
import dh.backend.clinica.entity.Paciente;
import dh.backend.clinica.entity.Turno;
import dh.backend.clinica.exception.BadRequestException;
import dh.backend.clinica.exception.ResourceNotFoundException;
import dh.backend.clinica.repository.ITurnoRepository;
import dh.backend.clinica.service.IOdontologoService;
import dh.backend.clinica.service.IPacienteService;
import dh.backend.clinica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private final Logger logger = LoggerFactory.getLogger(TurnoService.class);
    private ITurnoRepository turnoRepository;
    private IPacienteService pacienteService;
    private IOdontologoService odontologoService;
    @Autowired
    private ModelMapper modelMapper;

    public TurnoService(ITurnoRepository turnoRepository, IPacienteService pacienteService, IOdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    private TurnoResponseDto convertirTurnoEnResponse(Turno turno){
        TurnoResponseDto turnoResponseDto = modelMapper.map(turno, TurnoResponseDto.class);
        turnoResponseDto.setOdontologo(odontologoService.convertirOdontologoEnResponse(turno.getOdontologo()));
        turnoResponseDto.setPaciente(modelMapper.map(turno.getPaciente(), PacienteResponseDto.class));
        return turnoResponseDto;
    }

    private TurnoResponseDto obtenerTurnoResponse(Turno turnoDesdeBD){
        OdontologoResponseDto odontologoResponseDto = new OdontologoResponseDto(
                turnoDesdeBD.getOdontologo().getId(), turnoDesdeBD.getOdontologo().getNroMatricula(),
                turnoDesdeBD.getOdontologo().getApellido(), turnoDesdeBD.getOdontologo().getNombre()
        );
        PacienteResponseDto pacienteResponseDto = modelMapper.map(turnoDesdeBD.getPaciente(), PacienteResponseDto.class);

        TurnoResponseDto turnoResponseDto = new TurnoResponseDto(
                turnoDesdeBD.getId(),
                pacienteResponseDto, odontologoResponseDto,
                turnoDesdeBD.getFecha().toString()
        );
        return turnoResponseDto;
    }

    @Override
    public TurnoResponseDto guardarTurno(TurnoRequestDto turnoRequestDto){
        try{
            Optional<Paciente> paciente = pacienteService.obtenerPaciente(turnoRequestDto.getPaciente_id());
            Optional<Odontologo> odontologo = odontologoService.obtenerOdontologo(turnoRequestDto.getOdontologo_id());
            Turno turno = new Turno();
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            turno.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            Turno turnoDesdeBD = turnoRepository.save(turno);
            TurnoResponseDto turnoResponseDto = convertirTurnoEnResponse(turnoDesdeBD);
            return turnoResponseDto;
        } catch (ResourceNotFoundException e){
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public Optional<TurnoResponseDto> buscarPorId(Integer id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        if(turno.isEmpty()){
            throw new ResourceNotFoundException("El turno no fue encontrado");
        }
        TurnoResponseDto turnoRespuesta = convertirTurnoEnResponse(turno.get());
        logger.info("Turno encontrado: " + turnoRespuesta);
        return Optional.of(turnoRespuesta);
    }

    @Override
    public List<TurnoResponseDto> buscarTodos() {
        List<Turno> turnosDesdeBD = turnoRepository.findAll();
        List<TurnoResponseDto> turnosRespuesta = new ArrayList<>();
        for(Turno t: turnosDesdeBD){
            TurnoResponseDto turnoRespuesta =convertirTurnoEnResponse(t);
            logger.info("turno dto"+ turnoRespuesta);
            turnosRespuesta.add(turnoRespuesta);
        }
        return turnosRespuesta;
    }

    @Override
    public void modificarTurno(TurnoModifyDto turnoModifyDto) {
        try{
            Optional<Turno> turno = turnoRepository.findById(turnoModifyDto.getId());
            if(turno.isEmpty()){
                throw new ResourceNotFoundException("El turno no fue encontrado");
            }
            Optional<Paciente> paciente = pacienteService.obtenerPaciente(turnoModifyDto.getPaciente_id());
            Optional<Odontologo> odontologo = odontologoService.obtenerOdontologo(turnoModifyDto.getOdontologo_id());
            Turno turnoUpdate = new Turno(
                turnoModifyDto.getId(), paciente.get(), odontologo.get(), LocalDate.parse(turnoModifyDto.getFecha())
            );
            turnoRepository.save(turnoUpdate);
        } catch (ResourceNotFoundException e){
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public void eliminarTurno(Integer id){
        Optional<Turno> turno = turnoRepository.findById(id);
        if(turno.isEmpty()){
            throw new ResourceNotFoundException("El turno no fue encontrado");
        }
        turnoRepository.deleteById(id);
    }

    @Override
    public List<TurnoResponseDto> buscarTurnosPorPaciente(String pacienteApellido) {
        List<Turno> turnosDesdeBD = turnoRepository.buscarPorApellidoPaciente(pacienteApellido);
        List<TurnoResponseDto> turnosRespuesta = new ArrayList<>();
        for (Turno t : turnosDesdeBD) {
            TurnoResponseDto turnoRespuesta = convertirTurnoEnResponse(t);
            logger.info("turno dto" + turnoRespuesta);
            turnosRespuesta.add(turnoRespuesta);
        }
        return turnosRespuesta;
    }

}
