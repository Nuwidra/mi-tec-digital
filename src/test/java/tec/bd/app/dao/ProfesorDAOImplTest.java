package tec.bd.app.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Entity;
import tec.bd.app.domain.Profesor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfesorDAOImplTest {

    private ProfesorDAOImpl profesorDAO;

    @BeforeEach
    public void setUp() throws Exception {

        var juanId = new RowAttribute("id", 1);
        var juanNombre = new RowAttribute("nombre", "Juan");
        var juanApellido = new RowAttribute("apellido", "Perez");
        var juanCiudad = new RowAttribute("ciudad", "Quito");
        var juanRow = new Row(new RowAttribute[]{ juanId, juanNombre, juanApellido, juanCiudad });

        var pedroId = new RowAttribute("id", 2);
        var pedroNombre = new RowAttribute("nombre", "Pedro");
        var pedroApellido = new RowAttribute("apellido", "Salas");
        var pedroCiudad = new RowAttribute("ciudad", "Montenegro");
        var pedroRow = new Row(new RowAttribute[]{ pedroId, pedroNombre, pedroApellido, pedroCiudad });


        var tables = new HashMap<Class<? extends Entity>, Set<Row>>();
        tables.put(Profesor.class, new HashSet<>() {{
            add(juanRow);
            add(pedroRow);
        }});
        var setDB = new SetDB(tables);
        this.profesorDAO = new ProfesorDAOImpl(setDB, Profesor.class);
    }

    @Test
    public void findAll() throws Exception {
        var actual = this.profesorDAO.findAll();
        assertThat(actual).hasSize(2);
    }

    @Test
    public void findById() throws Exception {
        var student = this.profesorDAO.findById(1);
        assertThat(student.get().getId()).isEqualTo(1);
        assertThat(student.get().getNombre()).isEqualTo("Juan");
        assertThat(student.get().getApellido()).isEqualTo("Perez");
        assertThat(student.get().getCiudad()).isEqualTo("Quito");
    }

    @Test
    public void save() throws Exception {
        this.profesorDAO.save(new Profesor(3, "San", "Pedro", "Brrrr"));
        var profesor = this.profesorDAO.findById(3);
        assertThat(this.profesorDAO.findAll()).hasSize(3);
        assertThat(profesor.isPresent()).isTrue();
        assertThat(profesor.get().getId()).isEqualTo(3);
        assertThat(profesor.get().getNombre()).isEqualTo("San");
        assertThat(profesor.get().getApellido()).isEqualTo("Pedro");
        assertThat(profesor.get().getCiudad()).isEqualTo("Brrrr");
    }

    @Test
    public void update() throws Exception {
        var current = this.profesorDAO.findById(1);
        current.get().setNombre("Samuel");
        current.get().setApellido("Guitierrez");
        var actual = this.profesorDAO.update(current.get());
        assertThat(this.profesorDAO.findAll()).hasSize(2);
        assertThat(actual.get().getId()).isEqualTo(1);
        assertThat(actual.get().getNombre()).isEqualTo("Samuel");
        assertThat(actual.get().getApellido()).isEqualTo("Guitierrez");
        assertThat(actual.get().getCiudad()).isEqualTo("Quito");
    }

    @Test
    public void delete() throws Exception {
        this.profesorDAO.delete(2);
        assertThat(this.profesorDAO.findAll()).hasSize(1);
    }

    @Test
    public void findByCity() throws  Exception {
        var profesores = this.profesorDAO.findByCity("Quito");
        for (Profesor profesor : profesores) {
            assertThat(profesor.getId()).isEqualTo(1);
            assertThat(profesor.getNombre()).isEqualTo("Juan");
            assertThat(profesor.getApellido()).isEqualTo("Perez");
            assertThat(profesor.getCiudad()).isEqualTo("Quito");
        }
    }

}