package dh.backend.clinica.service;


import dh.backend.clinica.dao.IDao;
import dh.backend.clinica.model.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {
    private final IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> ondontologoIDao) {
        this.odontologoIDao = ondontologoIDao;
    }

    public Odontologo buscarPorId(Integer id){
        return odontologoIDao.buscarPorId(id);
    }

    public Odontologo guardar ( Odontologo odontologo) {
        return  odontologoIDao.guardar(odontologo);
    }
    public List<Odontologo> listaTodos() {
        return  odontologoIDao.listaTodos();
    }

    public void modificar(Odontologo odontologo){
        odontologoIDao.modificar(odontologo);
    }

    public void eliminar(Integer id){
        odontologoIDao.eliminar(id);
    }

}
