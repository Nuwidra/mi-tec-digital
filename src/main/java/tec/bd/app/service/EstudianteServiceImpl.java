package tec.bd.app.service;

import tec.bd.app.dao.EstudianteDAO;
import tec.bd.app.domain.Estudiante;

import java.util.List;
import java.util.Optional;

public class EstudianteServiceImpl implements EstudianteService {

    private EstudianteDAO estudianteDAO;

    public EstudianteServiceImpl(EstudianteDAO estudianteDAO) {
        this.estudianteDAO = estudianteDAO;
    }

    @Override
    public List<Estudiante> getAll() {
        return this.estudianteDAO.findAll();
    }

    @Override
    public Optional<Estudiante> getById(int carne) {

        //TODO: validar el carne > 0. Si no cumple con eso se devuelve Optional.empty()
        if(carne < 0){
            return Optional.empty();
        }else{
            return this.estudianteDAO.findById(carne);
        }
    }

    public void addNew(Estudiante e) {
        Optional<Estudiante> estudiante = this.estudianteDAO.findById(e.getCarne());
        if(!estudiante.isPresent()) {
            this.estudianteDAO.save(e);
        }
    }

    public Optional<Estudiante> updateStudent(Estudiante e) {
        if(this.getById(e.getCarne()).isPresent()){
            return this.estudianteDAO.update(e);
        }
        return Optional.empty();
    }

    public void deleteStudent(int carne) {
        if(this.estudianteDAO.findById(carne).isPresent()){
            this.estudianteDAO.delete(carne);
        }
    }

    public List<Estudiante> getStudentsSortedByLastName(String SortByLastName) {
        if(SortByLastName != null){
            return this.estudianteDAO.findAllSortByLastName();
        }
        else{
            return null;
        }
    }

    @Override
    public List<Estudiante> getStudentsByLastName(String lastName) {
        if(lastName != null){
            return this.estudianteDAO.findByLastName(lastName);
        }
        else{
            return null;
        }
    }

    @Override
    public List<Estudiante> getStudentsSortedByLastName() {
        return null;
    }


}
