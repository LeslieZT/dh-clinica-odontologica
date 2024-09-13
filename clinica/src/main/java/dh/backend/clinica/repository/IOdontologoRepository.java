package dh.backend.clinica.repository;

import dh.backend.clinica.entity.Odontologo;
import dh.backend.clinica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Integer> {

    List<Odontologo> findByNroMatricula(String nroMatricula);

    @Query("Select o from Odontologo o where LOWER(o.nombre) LIKE LOWER(CONCAT('%',:parteNombre,'%'))")
    List<Odontologo> buscarPorParteNombre(String parteNombre);


    @Query("SELECT o FROM Odontologo o WHERE o.turnoSet IS EMPTY")
    List<Odontologo> buscarOdontologosSinTurnos();

}
