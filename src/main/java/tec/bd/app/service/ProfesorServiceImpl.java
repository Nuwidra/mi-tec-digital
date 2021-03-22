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
    public Optional<Profesor> getById(int id) {
        return this.profesorDAO.findById(id);
    }

    @Override
    public void addNew(Profesor p) {
        Optional<Profesor> profesor = this.profesorDAO.findById(p.getId());
        if(!profesor.isPresent()) {
            this.profesorDAO.save(p);
        }
    }

    @Override
    public Optional<Profesor> updateProfesor(Profesor p) {
        return this.profesorDAO.update(p);
    }

    @Override
    public void deleteProfesor(int id) {
        this.profesorDAO.delete(id);
    }


    public List<Profesor> getProfesorByCity(String department) {
        if (department.isEmpty() || this.profesorDAO.findByCity(department).isEmpty()) {
            return Collections.emptyList();
        }
        return this.profesorDAO.findByCity(department);
    }
}