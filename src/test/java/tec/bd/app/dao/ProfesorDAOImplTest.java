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

import static org.assertj.core.api.Assertions.*;



public class ProfesorDAOImplTest {

    private ProfesorDAOImpl profesorDAO;

    @BeforeEach
    public void setUp() throws Exception {

        var juanId = new RowAttribute("id", 1);
        var juanNombre = new RowAttribute("nombre", "Juan");
        var juanApellido = new RowAttribute("apellido", "Solorzano");
        var juanCiudad = new RowAttribute("ciudad", "Cuacamaranga");
        var juanRow = new Row(new RowAttribute[]{juanId, juanNombre, juanApellido, juanCiudad});

        var mariaId = new RowAttribute("id", 2);
        var mariaNombre = new RowAttribute("nombre", "Maria");
        var mariaApellido = new RowAttribute("apellido", "Gucci");
        var mariaCiudad = new RowAttribute("ciudad", "Sierra");
        var mariaRow = new Row(new RowAttribute[]{mariaId, mariaNombre, mariaApellido, mariaCiudad});

        var pedroId = new RowAttribute("id", 3);
        var pedroNombre = new RowAttribute("nombre", "William");
        var pedroApellido = new RowAttribute("apellido", "Minerva");
        var pedroCiudad = new RowAttribute("ciudad", "Bombastic");
        var pedroRow = new Row(new RowAttribute[]{pedroId, pedroNombre, pedroApellido, pedroCiudad});

        var tables = new HashMap<Class<? extends Entity>, Set<Row>>();
        tables.put(Profesor.class, new HashSet<>() {{
            add(juanRow);
            add(mariaRow);
            add(pedroRow);
        }});
        var setDB = new SetDB(tables);
        this.profesorDAO = new ProfesorDAOImpl(setDB, Profesor.class);
    }

    @Test
    public void findAll() throws Exception {
        var actual = this.profesorDAO.findAll();
        assertThat(actual).hasSize(3);
    }

    @Test
    public void findById() throws Exception {
        var profe = this.profesorDAO.findById(3);
        assertThat(profe.get().getId()).isEqualTo(3);
        assertThat(profe.get().getNombre()).isEqualTo("William");
        assertThat(profe.get().getApellido()).isEqualTo("Minerva");
        assertThat(profe.get().getCiudad()).isEqualTo("Bombastic");
    }

    @Test
    public void save() throws Exception {
        this.profesorDAO.save(new Profesor(40, "Jorge", "Solorzano", "Cuacamaranga"));
        var profe = this.profesorDAO.findById(40);
        assertThat(this.profesorDAO.findAll()).hasSize(4);
        assertThat(profe.isPresent()).isTrue();
        assertThat(profe.get().getId()).isEqualTo(40);
        assertThat(profe.get().getNombre()).isEqualTo("Jorge");
        assertThat(profe.get().getApellido()).isEqualTo("Solorzano");
        assertThat(profe.get().getCiudad()).isEqualTo("Cuacamaranga");
    }

    @Test
    public void update() throws Exception {
        var current = this.profesorDAO.findById(2);
        current.get().setApellido("Gucci");
        current.get().setCiudad("Sierra");
        var actual = this.profesorDAO.update(current.get());
        assertThat(this.profesorDAO.findAll()).hasSize(3);
        assertThat(actual.get().getId()).isEqualTo(2);
        assertThat(actual.get().getNombre()).isEqualTo("Maria");
        assertThat(actual.get().getApellido()).isEqualTo("Gucci");
        assertThat(actual.get().getCiudad()).isEqualTo("Sierra");
    }

    @Test
    public void delete() throws Exception {
        this.profesorDAO.delete(2);
        assertThat(this.profesorDAO.findAll()).hasSize(2);
    }

    @Test
    public void getByCity() throws Exception {
        var profesores = this.profesorDAO.findByCity("Cuacamaranga");
        assertThat(profesores.get(0).getId()).isEqualTo(1);
        assertThat(profesores.get(0).getNombre()).isEqualTo("Juan");
        assertThat(profesores.get(0).getApellido()).isEqualTo("Solorzano");
        assertThat(profesores.get(0).getCiudad()).isEqualTo("Cuacamaranga");
    }
}