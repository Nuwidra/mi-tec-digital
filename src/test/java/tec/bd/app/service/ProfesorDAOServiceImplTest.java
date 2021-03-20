//package tec.bd.app.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import tec.bd.app.dao.CursoDAO;
//import tec.bd.app.dao.ProfesorDAO;
//import tec.bd.app.domain.Curso;
//import tec.bd.app.domain.Profesor;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.times;
//
//public class ProfesorDAOServiceImplTest {
//
//    @Mock
//    private ProfesorDAO profesorDAO;
//
//    @InjectMocks
//    private ProfesorServiceImpl profesorService;
//
//
//    @BeforeEach
//    public void setUp() throws Exception {
//
//    }
//
//    @Test
//    public void whenNoDataInDB_thenNoResult() throws Exception {
//
//        given(this.profesorDAO.findAll()).willReturn(Collections.emptyList());
//
//        var cursos = this.profesorService.getAll();
//
//        verify(this.profesorDAO, times(1)).findAll();
//
//        assertThat(cursos).hasSize(0);
//    }
//
//    @Test
//    public void getAllTest() throws Exception {
//
//        given(this.profesorDAO.findAll()).willReturn(List.of(
//                mock(Profesor.class), mock(Profesor.class), mock(Profesor.class)
//        ));
//
//        var profesores = this.profesorService.getAll();
//
//        verify(this.profesorDAO, times(1)).findAll();
//
//        assertThat(profesores).hasSize(3);
//
//    }
//
//    @Test
//    public void addNewStudent() throws Exception {
//
//        /*
//        En la primera invocacion va a devolver una lista de 3 estudiantes. En la segunda una lista de 4
//         */
//        given(this.profesorDAO.findAll()).willReturn(
//                List.of(mock(Profesor.class), mock(Profesor.class), mock(Profesor.class)),
//                List.of(mock(Profesor.class), mock(Profesor.class), mock(Profesor.class), mock(Profesor.class))
//        );
//
//        var professorsBeforeSave = this.profesorService.getAll();
//
//        var karol = new Curso(2, "Religion", "Sociales", 1);
//        profesorService.addNew(karol);
//
//        var professorsAfterSave = this.profesorService.getAll();
//
//        verify(this.profesorDAO, times(1)).save(karol);
//        assertThat(professorsAfterSave.size()).isGreaterThan(professorsBeforeSave.size());
//    }
//
//    @Test
//    public void deleteCourse() throws Exception {
//
//        /*
//        En la primera invocacion va a devolver una lista de 3 estudiantes. En la segunda una lista de 2
//         */
//        given(this.profesorDAO.findAll()).willReturn(
//                List.of(mock(Profesor.class), mock(Profesor.class), mock(Profesor.class)),
//                List.of(mock(Profesor.class), mock(Profesor.class))
//        );
//
//        var studentsBeforeSave = this.profesorService.getAll();
//
//        profesorDAO.delete(2);
//
//        var studentsAfterSave = this.profesorService.getAll();
//
//        verify(this.profesorDAO, times(1)).delete(2);
//        assertThat(studentsAfterSave.size()).isLessThan(studentsBeforeSave.size());
//    }
//
//    @Test
//    public void updateCourse() throws Exception {
//
//        /*
//        En la primera invocacion va a devolver estudiante default y en la segunda invocacion el estudiante actualizado
//         */
//        given(this.profesorDAO.findById(anyInt())).willReturn(
//                Optional.of(mock(Profesor.class)),
//                Optional.of(mock(Profesor.class))
//        );
//
//        var studentBefore = this.profesorService.getById(2);
//
//        var karol = new Profesor(2, "Quimica", "Ciencias Exactas", 21);
//        profesorService.updateProfessor(karol);
//
//        var studentAfter = this.profesorService.getById(2);
//
//        verify(this.profesorDAO, times(1)).update(karol);
//        assertThat(studentAfter).isNotSameAs(studentBefore);
//    }
//}