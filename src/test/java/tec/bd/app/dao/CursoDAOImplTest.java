package tec.bd.app.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Curso;
import tec.bd.app.domain.Entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;


public class CursoDAOImplTest {

    private CursoDAOImpl cursoDAO;

    @BeforeEach
    public void setUp() throws Exception {

        var mateId = new RowAttribute("id", 1);
        var mateNombre = new RowAttribute("nombre", "Matematica");
        var mateDepartamento = new RowAttribute("departamento", "Catedra");
        var mateCreditos = new RowAttribute("creditos", 3);
        var mateRow = new Row(new RowAttribute[]{ mateId, mateNombre, mateDepartamento, mateCreditos });

        var cienciasId = new RowAttribute("id", 2);
        var cienciasNombre = new RowAttribute("nombre", "Ciencias");
        var cienciasDepartamento = new RowAttribute("departamento", "Inventigacion");
        var cienciasCreditos = new RowAttribute("creditos", 4);
        var cienciasRow = new Row(new RowAttribute[]{ cienciasId, cienciasNombre, cienciasDepartamento, cienciasCreditos });

        var socialesId = new RowAttribute("id", 3);
        var socialesNombre = new RowAttribute("nombre", "Sociales");
        var socialesDepartamento = new RowAttribute("departamento", "Servicio Social");
        var socialesCreditos = new RowAttribute("creditos", 1);
        var socialesRow = new Row(new RowAttribute[]{ socialesId, socialesNombre, socialesDepartamento, socialesCreditos });

        var tables = new HashMap<Class<? extends Entity>, Set<Row>>();
        tables.put(Curso.class, new HashSet<>() {{
            add(mateRow);
            add(cienciasRow);
            add(socialesRow);
        }});
        var setDB = new SetDB(tables);
        this.cursoDAO = new CursoDAOImpl(setDB, Curso.class);
    }

    @Test
    public void findAll() throws Exception {
        var actual = this.cursoDAO.findAll();
        assertThat(actual).hasSize(3);
    }

    @Test
    public void findById() throws Exception {
        var curso = this.cursoDAO.findById(1);
        assertThat(curso.get().getId()).isEqualTo(1);
        assertThat(curso.get().getNombre()).isEqualTo("Matematica");
        assertThat(curso.get().getDepartamento()).isEqualTo("Catedra");
        assertThat(curso.get().getCreditos()).isEqualTo(3);
    }

    @Test
    public void save() throws Exception {
        this.cursoDAO.save(new Curso(4, "Musica", "RockStar", 4));
        var curso = this.cursoDAO.findById(4);
        assertThat(this.cursoDAO.findAll()).hasSize(4);
        assertThat(curso.isPresent()).isTrue();
        assertThat(curso.get().getId()).isEqualTo(4);
        assertThat(curso.get().getNombre()).isEqualTo("Musica");
        assertThat(curso.get().getDepartamento()).isEqualTo("RockStar");
        assertThat(curso.get().getCreditos()).isEqualTo(4);
    }

    @Test
    public void update() throws Exception {
        var current = this.cursoDAO.findById(3);
        current.get().setDepartamento("Servicio Social");
        current.get().setCreditos(1);
        var actual = this.cursoDAO.update(current.get());
        assertThat(this.cursoDAO.findAll()).hasSize(3);
        assertThat(actual.get().getId()).isEqualTo(3);
        assertThat(actual.get().getNombre()).isEqualTo("Sociales");
        assertThat(actual.get().getDepartamento()).isEqualTo("Servicio Social");
        assertThat(actual.get().getCreditos()).isEqualTo(1);
    }

    @Test
    public void delete() throws Exception {
        this.cursoDAO.delete(2);
        assertThat(this.cursoDAO.findAll()).hasSize(2);
    }
}