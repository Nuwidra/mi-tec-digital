package tec.bd.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tec.bd.app.dao.EstudianteDAO;
import tec.bd.app.dao.ProfesorDAO;
import tec.bd.app.domain.Estudiante;
import org.mockito.junit.jupiter.MockitoExtension;
import tec.bd.app.domain.Profesor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProfesorServiceImplTest {

    @Mock
    private ProfesorDAO profesorDAO;

    @InjectMocks
    private ProfesorServiceImpl profesorService;


    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void whenNoDataInDB_thenNoResult() throws Exception {

        given(this.profesorDAO.findAll()).willReturn(Collections.emptyList());

        var profesores = this.profesorService.getAll();

        verify(this.profesorDAO, times(1)).findAll();

        assertThat(profesores).hasSize(0);
    }

    @Test
    public void getAllTest() throws Exception {

        given(this.profesorDAO.findAll()).willReturn(List.of(
                mock(Profesor.class), mock(Profesor.class), mock(Profesor.class)
        ));

        var profesores = this.profesorService.getAll();

        verify(this.profesorDAO, times(1)).findAll();

        assertThat(profesores).hasSize(3);

    }

    @Test
    public void addNewStudent() throws Exception {

        /*
        En la primera invocacion va a devolver una lista de 3 estudiantes. En la segunda una lista de 4
         */
        given(this.profesorDAO.findAll()).willReturn(
                List.of(mock(Profesor.class), mock(Profesor.class), mock(Profesor.class)),
                List.of(mock(Profesor.class), mock(Profesor.class), mock(Profesor.class), mock(Profesor.class))
        );

        var studentsBeforeSave = this.profesorService.getAll();

        var karol = new Profesor(2, "Karol", "Jimenez", "Montenegro");
        profesorService.addNew(karol);

        var studentsAfterSave = this.profesorService.getAll();

        verify(this.profesorDAO, times(1)).save(karol);
        assertThat(studentsAfterSave.size()).isGreaterThan(studentsBeforeSave.size());
    }

    @Test
    public void deleteStudent() throws Exception {

        /*
        En la primera invocacion va a devolver una lista de 3 estudiantes. En la segunda una lista de 2
         */
        given(this.profesorDAO.findAll()).willReturn(
                List.of(mock(Profesor.class), mock(Profesor.class), mock(Profesor.class)),
                List.of(mock(Profesor.class), mock(Profesor.class))
        );

        given(this.profesorDAO.findById(anyInt())).willReturn(Optional.of(mock(Profesor.class)));

        var studentsBeforeSave = this.profesorService.getAll();

        profesorService.deleteProfessor(2);

        var studentsAfterSave = this.profesorService.getAll();

        verify(this.profesorDAO, times(1)).delete(2);
        assertThat(studentsAfterSave.size()).isLessThan(studentsBeforeSave.size());
    }

    @Test
    public void updateStudent() throws Exception {

        /*
        En la primera invocacion va a devolver estudiante default y en la segunda invocacion el estudiante actualizado
         */
        var profesorActual = mock(Profesor.class);
        given(this.profesorDAO.findById(anyInt())).willReturn(
                Optional.of(profesorActual),
                Optional.of(mock(Profesor.class))
        );

        var studentBefore = this.profesorService.getById(2);

        var karol = new Profesor(2, "Karol", "Jimenez", "MonteSierra");
        profesorService.updateProfessor(karol);

        var studentAfter = this.profesorService.getById(2);

        verify(this.profesorDAO, times(1)).update(karol);
        assertThat(studentAfter).isNotSameAs(studentBefore);
    }

    @Test
    public void getStudentsSortedByLastName() throws Exception {
        //TODO: hay que implementarlo
    }

    @Test
    public void getStudentsByLastName() throws Exception {
        //TODO: hay que implementarlo
    }

}