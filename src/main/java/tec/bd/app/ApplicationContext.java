package tec.bd.app;

import tec.bd.app.dao.*;
import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Curso;
import tec.bd.app.domain.Entity;
import tec.bd.app.domain.Estudiante;
import tec.bd.app.domain.Profesor;
import tec.bd.app.service.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ApplicationContext {

    private SetDB setDB;
    private EstudianteDAO estudianteSetDAO;
    private EstudianteService estudianteServiceSet;

    private CursoDAO cursoSetDAO;
    private CursoService cursoService;

    private ProfesorDAO profesorDAO;
    private ProfesorService profesorService;

    private ApplicationContext() {

    }

    public static ApplicationContext init() {
        ApplicationContext applicationContext = new ApplicationContext();
        applicationContext.setDB = initSetDB();
        applicationContext.estudianteSetDAO = initEstudianteSetDAO(applicationContext.setDB);
        applicationContext.estudianteServiceSet = initEstudianteSetService(applicationContext.estudianteSetDAO);
        applicationContext.cursoSetDAO = initCursoSetDAO(applicationContext.setDB);
        applicationContext.cursoService = initCursoSetService(applicationContext.cursoSetDAO);
        applicationContext.profesorDAO = initProfesorSetDAO(applicationContext.setDB);
        applicationContext.profesorService = initProfesorSetService(applicationContext.profesorDAO);
        return applicationContext;
    }

    private static SetDB initSetDB() {
        // Registros de la tabla estudiante
        var juanId = new RowAttribute("id", 1);
        var juanNombre = new RowAttribute("nombre", "Juan");
        var juanApellido = new RowAttribute("apellido", "Perez");
        var juanEdad = new RowAttribute("edad", 20);
        var juanRow = new Row(new RowAttribute[]{ juanId, juanNombre, juanApellido, juanEdad });

        var mariaId = new RowAttribute("id", 3);
        var mariaNombre = new RowAttribute("nombre", "Maria");
        var mariaApellido = new RowAttribute("apellido", "Rojas");
        var mariaEdad = new RowAttribute("edad", 21);
        var mariaRow = new Row(new RowAttribute[]{ mariaId, mariaNombre, mariaApellido, mariaEdad });

        var pedroId = new RowAttribute("id", 2);
        var pedroNombre = new RowAttribute("nombre", "Pedro");
        var pedroApellido = new RowAttribute("apellido", "Infante");
        var pedroEdad = new RowAttribute("edad", 23);
        var pedroRow = new Row(new RowAttribute[]{ pedroId, pedroNombre, pedroApellido, pedroEdad });

        var raquelId = new RowAttribute("id", 10);
        var raquelNombre = new RowAttribute("nombre", "Raquel");
        var raquelApellido = new RowAttribute("apellido", "Rojas");
        var raquelEdad = new RowAttribute("edad", 25);
        var raquelRow = new Row(new RowAttribute[]{ raquelId, raquelNombre, raquelApellido, raquelEdad });

        // ---------------------------------------------------------------
        // Registros de la tabla curso
        // ---------------------------------------------------------------
        var religionId = new RowAttribute("id", 1);
        var religionNombre = new RowAttribute("nombre","Religion");
        var religionDepartamento = new RowAttribute("departamento","Humanidades");
        var religionCreditos= new RowAttribute("creditos",1);
        var religionRow = new Row(new RowAttribute[]{religionId, religionNombre, religionDepartamento, religionCreditos});

        var civicaId = new RowAttribute("id", 2);
        var civicaNombre = new RowAttribute("nombre","Civica");
        var civicaDepartamento = new RowAttribute("departamento","Social");
        var civicaCreditos= new RowAttribute("creditos",2);
        var civicaRow = new Row(new RowAttribute[]{civicaId, civicaNombre, civicaDepartamento, civicaCreditos});

        var fisicaId = new RowAttribute("id", 3);
        var fisicaNombre = new RowAttribute("nombre","Fisica");
        var fisicaDepartamento = new RowAttribute("departamento","Ciencias Puras");
        var fisicaCreditos= new RowAttribute("creditos",3);
        var fisicaRow = new Row(new RowAttribute[]{fisicaId, fisicaNombre, fisicaDepartamento, fisicaCreditos});

        var quimicaId = new RowAttribute("id", 4);
        var quimicaNombre = new RowAttribute("nombre","Quimica");
        var quimicaDepartamento = new RowAttribute("departamento","Ciencias Puras");
        var quimicaCreditos= new RowAttribute("creditos",4);
        var quimicaRow = new Row(new RowAttribute[]{quimicaId, quimicaNombre, quimicaDepartamento, quimicaCreditos});
        // ---------------------------------------------------------------
        // Registros de la tabla profesor
        // ---------------------------------------------------------------
        var AguilarId = new RowAttribute("id", 1);
        var AguilarNombre = new RowAttribute("nombre","Jorge");
        var AguilarApellido = new RowAttribute("Apellido","Aguilar");
        var AguilarCuidad= new RowAttribute("ciudad","San Carlos");
        var AguilarRow = new Row(new RowAttribute[]{AguilarId, AguilarNombre, AguilarApellido, AguilarCuidad});

        var TorresID = new RowAttribute("id", 1);
        var TorresNombre = new RowAttribute("nombre","Mario");
        var TorresApellido = new RowAttribute("Apellido","Torres");
        var TorresAguilarCuidad= new RowAttribute("ciudad","Palmares");
        var TorresRow = new Row(new RowAttribute[]{TorresID, TorresNombre, TorresApellido, TorresAguilarCuidad});

        var FuentesID = new RowAttribute("id", 1);
        var FuentesNombre = new RowAttribute("nombre","Sara");
        var FuentesApellido = new RowAttribute("Apellido","Fuentes");
        var FuentesAguilarCuidad= new RowAttribute("ciudad","Guanacaste");
        var FuentesRow = new Row(new RowAttribute[]{FuentesID, FuentesNombre, FuentesApellido, FuentesAguilarCuidad});

        var SalasID = new RowAttribute("id", 1);
        var SalasNombre = new RowAttribute("nombre","Jorge");
        var SalasApellido = new RowAttribute("Apellido","Aguilar");
        var SalasAguilarCuidad= new RowAttribute("ciudad","San Ramon");
        var SalasRow = new Row(new RowAttribute[]{SalasID, SalasNombre, SalasApellido, SalasAguilarCuidad});

        var tables = new HashMap<Class<? extends Entity>, Set<Row>>();
        tables.put(Estudiante.class, new HashSet<>() {{
            add(juanRow);
            add(mariaRow);
            add(pedroRow);
            add(raquelRow);
        }});

        tables.put(Curso.class, new HashSet<>() {{
            add(religionRow);
            add(civicaRow);
            add(fisicaRow);
            add(quimicaRow);
        }});

        tables.put(Profesor.class, new HashSet<>() {{
            add(AguilarRow);
            add(TorresRow);
            add(FuentesRow);
            add(SalasRow);
        }});

        return new SetDB(tables);
    }

    private static EstudianteDAO initEstudianteSetDAO(SetDB setDB) {
        return new EstudianteDAOImpl(setDB, Estudiante.class);
    }

    private static EstudianteService initEstudianteSetService(EstudianteDAO estudianteDAO) {
        return new EstudianteServiceImpl(estudianteDAO);
    }

    private static CursoDAO initCursoSetDAO(SetDB setDB){
        return new CursoDAOImpl(setDB, Curso.class);
    }

    private static CursoService initCursoSetService(CursoDAO cursoDAO){
        return new CursoServiceImpl(cursoDAO);
    }

    private static ProfesorDAO initProfesorSetDAO(SetDB setDB){
        return new ProfesorDAOImpl(setDB, Profesor.class);
    }

    private static ProfesorService initProfesorSetService(ProfesorDAO profesorDAO){
        return new ProfesorServiceImpl(profesorDAO);
    }

    public SetDB getSetDB() {
        return this.setDB;
    }

    public EstudianteDAO getEstudianteSetDAO() {
        return this.estudianteSetDAO;
    }

    public EstudianteService getEstudianteServiceSet() {
        return this.estudianteServiceSet;
    }

    public CursoDAO getCursoSetDAO(){
        return this.cursoSetDAO;
    }

    public CursoService getCursoService(){
        return this.cursoService;
    }

    public ProfesorService getProfesorService() {
        return this.profesorService;
    }
}