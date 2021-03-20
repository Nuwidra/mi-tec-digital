package tec.bd.app.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Entity;
import tec.bd.app.domain.Estudiante;
import tec.bd.app.domain.Profesor;
import java.util.Comparator;
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
        var juanEdad = new RowAttribute("cuidad", "Quito");
        var juanRow = new Row(new RowAttribute[]{ juanId, juanNombre, juanApellido, juanEdad });

        var mariaId = new RowAttribute("id", 3);
        var mariaNombre = new RowAttribute("nombre", "Maria");
        var mariaApellido = new RowAttribute("apellido", "Rojas");
        var mariaEdad = new RowAttribute("cuidad", "Curtua");
        var mariaRow = new Row(new RowAttribute[]{ mariaId, mariaNombre, mariaApellido, mariaEdad });

        var pedroId = new RowAttribute("id", 2);
        var pedroNombre = new RowAttribute("nombre", "Pedro");
        var pedroApellido = new RowAttribute("apellido", "Infante");
        var pedroEdad = new RowAttribute("cuidad", "Cuarla");
        var pedroRow = new Row(new RowAttribute[]{ pedroId, pedroNombre, pedroApellido, pedroEdad });

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
    public void orderByLastName() throws Exception {

        var profesores = this.profesorDAO.findAll();
        Comparator<Profesor> comparator
                = (e1, e2) -> e1.getApellido().compareTo(e2.getApellido());

        profesores.stream().forEach(e -> System.out.println(e.getNombre() +" "+e.getApellido()));

        profesores.sort(comparator);

        System.out.println("-----------------------------------------");

        profesores.stream().forEach(e -> System.out.println(e.getNombre() +" "+e.getApellido()));

    }

    @Test
    public void findAll() throws Exception {
        var actual = this.profesorDAO.findAll();
        assertThat(actual).hasSize(3);
    }

    @Test
    public void findById() throws Exception {
        var student = this.profesorDAO.findById(3);
        assertThat(student.get().getId()).isEqualTo(3);
        assertThat(student.get().getNombre()).isEqualTo("Maria");
        assertThat(student.get().getApellido()).isEqualTo("Rojas");
        assertThat(student.get().getCiudad()).isEqualTo("Curtua");
    }

//    @Test
//    public void save() throws Exception {
//        this.profesorDAO.save(new Profesor(40, "Jorge", "Chacon", "B2"));
//        var profesor = this.profesorDAO.findById(4);
//        assertThat(this.profesorDAO.findAll()).hasSize(4);
//        assertThat(profesor.isPresent()).isTrue();
//        assertThat(profesor.get().getId()).isEqualTo(4);
//        assertThat(profesor.get().getNombre()).isEqualTo("Jorge");
//        assertThat(profesor.get().getApellido()).isEqualTo("Chacon");
//        assertThat(profesor.get().getCiudad()).isEqualTo("B2");
//    }
//
//    @Test
//    public void update() throws Exception {
//        var current = this.profesorDAO.findById(3);
//        current.get().setApellido("Rodriguez");
//        current.get().setCiudad("Turbia");
//        var actual = this.profesorDAO.update(current.get());
//        assertThat(this.profesorDAO.findAll()).hasSize(3);
//        assertThat(actual.get().getId()).isEqualTo(3);
//        assertThat(actual.get().getNombre()).isEqualTo("Maria");
//        assertThat(actual.get().getApellido()).isEqualTo("Guzo");
//        assertThat(actual.get().getCiudad()).isEqualTo("Turbia");
//    }

    @Test
    public void delete() throws Exception {
        this.profesorDAO.delete(2);
        assertThat(this.profesorDAO.findAll()).hasSize(2);
    }
}
