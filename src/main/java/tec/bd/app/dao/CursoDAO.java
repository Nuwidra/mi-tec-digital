package tec.bd.app.dao;

import tec.bd.app.domain.Curso;

import java.util.List;

public interface CursoDAO extends GenericDAO<Curso, Integer>{

    default List<Curso> findByDepartment(String department) {
        return null;
    }

}
