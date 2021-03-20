package tec.bd.app.service;

import tec.bd.app.dao.ProfesorDAO;
import tec.bd.app.domain.Profesor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProfesorServiceImpl implements ProfesorService {

    private ProfesorDAO profesorDAO;

    public ProfesorServiceImpl(ProfesorDAO profesorDAO) {
        this.profesorDAO = profesorDAO;
    }

    @Override
    public List<Profesor> getAll() {
        return this.profesorDAO.findAll();
    }

    @Override
    public Optional<Profesor> getById(int Id) {
        if (Id > 0) {
            return this.profesorDAO.findById(Id);
        }
        return Optional.empty();
    }

    @Override
    public void addNew(Profesor p) {
        Optional<Profesor> profesor = this.profesorDAO.findById(p.getId());
        if (!profesor.isPresent()) {
            this.profesorDAO.save(p);
        }

    }

    @Override
    public Optional<Profesor> updateProfessor(Profesor p) {
        if(this.profesorDAO.findById(p.getId()).isPresent()){
            return this.profesorDAO.update(p);
        }
        return Optional.empty();
    }

    @Override
    public void deleteProfessor(int id) {

        if(this.profesorDAO.findById(id).isPresent()){
            this.profesorDAO.delete(id);
        }
        else{
            System.out.println("No existe");
        }
    }

    @Override
    public List<Profesor> getProfessorByCity(String city) {
        if (city != null) {
            return this.profesorDAO.findByCity(city);
        } else {
            return null;
        }
    }
}
