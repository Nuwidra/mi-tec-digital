package tec.bd.app.service;

import tec.bd.app.dao.CursoDAO;
import tec.bd.app.domain.Curso;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CursoServiceImpl implements CursoService{

    private CursoDAO cursoDAO;


    public CursoServiceImpl(CursoDAO cursoDAO){
        this.cursoDAO = cursoDAO;
    }


    @Override
    public List<Curso> getAll() {
        return this.cursoDAO.findAll();
    }

    @Override
    public Optional<Curso> getById(int id) {

        if (id<0){
            return Optional.empty();
        }
        return this.cursoDAO.findById(id);
    }

    @Override
    public void addNew(Curso c) {
        Optional<Curso> curso = this.cursoDAO.findById(c.getId());
        if(!curso.isPresent()) {
            this.cursoDAO.save(c);
        }
    }

    @Override
    public Optional<Curso> updateCourse(Curso c) {

        if(c.getId() == this.cursoDAO.findById(c.getId()).get().getId()){
            return this.cursoDAO.update(c);
        }
        else{
            return null;
        }
    }

    @Override
    public void deleteCourse(int id) {
        if(id == this.cursoDAO.findById(id).get().getId()){
            this.cursoDAO.delete(id);
        }
        else{
            System.out.println("No existe");
        }
    }

    @Override
    public List<Curso> findByDepartment(String department) {

        if (department != null) {
            return this.cursoDAO.findByDepartment(department);
        } else {
            return null;
        }
    }
}
