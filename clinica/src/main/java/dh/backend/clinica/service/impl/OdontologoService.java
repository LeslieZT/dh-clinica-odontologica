package dh.backend.clinica.service.impl;

import dh.backend.clinica.dto.request.odontologo.OdontologoCreateRequestDto;
import dh.backend.clinica.dto.request.odontologo.OdontologoUpdateRequestDto;
import dh.backend.clinica.dto.response.odontologo.OdontologoResponseDto;
import dh.backend.clinica.entity.Odontologo;
import dh.backend.clinica.exception.ResourceNotFoundException;
import dh.backend.clinica.repository.IOdontologoRepository;
import dh.backend.clinica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private final Logger logger = LoggerFactory.getLogger(OdontologoService.class);
    
    private IOdontologoRepository odontologoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    private Odontologo convertirEnOdontologo(OdontologoCreateRequestDto odontologoCreateRequestDto) {
        Odontologo odontologo = modelMapper.map(odontologoCreateRequestDto, Odontologo.class);
        odontologo.setNroMatricula(odontologoCreateRequestDto.getNumeroMatricula());
        return  odontologo;
    }

    private Odontologo convertirEnOdontologo(OdontologoUpdateRequestDto odontologoUpdateRequestDto) {
        Odontologo odontologo = modelMapper.map(odontologoUpdateRequestDto, Odontologo.class);
        odontologo.setNroMatricula(odontologoUpdateRequestDto.getNumeroMatricula());
        return  odontologo;
    }

    @Override
    public OdontologoResponseDto convertirOdontologoEnResponse(Odontologo odontologo) {
        OdontologoResponseDto odontologoResponseDto = modelMapper.map(odontologo, OdontologoResponseDto.class);
        odontologoResponseDto.setNumeroMatricula(odontologo.getNroMatricula());
        return  odontologoResponseDto;
    }

    @Override
    public Optional<OdontologoResponseDto> buscarPorId(Integer id) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if(odontologo.isEmpty()) {
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }      
        OdontologoResponseDto odontologoResponse = convertirOdontologoEnResponse(odontologo.get());
          logger.info("Odontologo Encontrado" + odontologoResponse);
        return Optional.of(odontologoResponse);
    }

    @Override
    public List<OdontologoResponseDto> buscarTodos() {
        List<Odontologo> odontologosDesdeBD = odontologoRepository.findAll() ;
        List<OdontologoResponseDto>  odontologosResponse = new ArrayList<>();
        for(Odontologo t: odontologosDesdeBD){
            OdontologoResponseDto odontologoResponseDto = convertirOdontologoEnResponse(t);
            logger.info("odontologo "+ odontologoResponseDto);
            odontologosResponse.add(odontologoResponseDto);
        }
        return odontologosResponse;
    }

    @Override
    public OdontologoResponseDto guardarOdontologo(OdontologoCreateRequestDto odontologoCreateRequestDto) {
        Odontologo odontologo = convertirEnOdontologo(odontologoCreateRequestDto);
        Odontologo newOdontologo = odontologoRepository.save(odontologo);
        OdontologoResponseDto odontologoResponse =  convertirOdontologoEnResponse(newOdontologo);
        return odontologoResponse;
    }

    @Override
    public void modificarOdontologo(OdontologoUpdateRequestDto odontologoUpdateRequestDto ) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(odontologoUpdateRequestDto.getId());
        if(odontologo.isEmpty()) {
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }    
        Odontologo odontologoUpdate = convertirEnOdontologo(odontologoUpdateRequestDto);
            logger.info("Odontologo Update " + odontologoUpdate);
        odontologoRepository.save(odontologoUpdate);
    }

   @Override
    public void eliminarOdontologo(Integer id) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if(odontologo.isEmpty()) {
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }
        odontologoRepository.deleteById(id);
    }

    @Override
    public List<OdontologoResponseDto> buscarPorNumeroMatricula(String numeroMatricula) {
        List<Odontologo> odontologosDesdeBD = odontologoRepository.findByNroMatricula(numeroMatricula) ;
        List<OdontologoResponseDto>  odontologosResponse = new ArrayList<>();
        for(Odontologo t: odontologosDesdeBD){
            OdontologoResponseDto odontologoResponseDto = convertirOdontologoEnResponse(t);
            logger.info("odontologo"+ odontologoResponseDto);
            odontologosResponse.add(odontologoResponseDto);
        }
        return odontologosResponse;
    }

    @Override
    public List<OdontologoResponseDto> buscarPorNombre(String parteNombre) {
        List<Odontologo> odontologosDesdeBD = odontologoRepository.buscarPorParteNombre(parteNombre) ;
        List<OdontologoResponseDto>  odontologosResponse = new ArrayList<>();
        for(Odontologo t: odontologosDesdeBD){
            OdontologoResponseDto odontologoResponseDto = convertirOdontologoEnResponse(t);
            logger.info("odontologo"+ odontologoResponseDto);
            odontologosResponse.add(odontologoResponseDto);
        }
        return odontologosResponse;
    }

    @Override
    public Optional<Odontologo> obtenerOdontologo (Integer id) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if(odontologo.isEmpty()) {
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }
        return odontologo;
    }

}
