package tec.bd.app.dao;

import tec.bd.app.database.set.Row;
import tec.bd.app.database.set.RowAttribute;
import tec.bd.app.database.set.SetDB;
import tec.bd.app.domain.Profesor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProfesorDAOImpl extends GenericSetDAOImpl<Profesor, Integer> implements ProfesorDAO {

    public ProfesorDAOImpl(SetDB setDB, Class<Profesor> clazz) {
        super(setDB, clazz);
    }

    @Override
    public List<Profesor> findByCity(String city) {
        List<Profesor> listaProfesores = this.table.stream().map(this::rowToEntity).collect(Collectors.toList());

        ArrayList<Profesor> profesoresPorCiudad = new ArrayList<>();

        for (Profesor profesor : listaProfesores) {
            if (profesor.getCiudad().equals(city)) {
                profesoresPorCiudad.add(profesor);
            }
        }

        return profesoresPorCiudad;
    }

    @Override
    protected Profesor rowToEntity(Row row) {
        var id = row.intAttributeValue("id");
        var nombre = row.stringAttributeValue("nombre");
        var apellido = row.stringAttributeValue("apellido");
        var ciudad = row.stringAttributeValue("ciudad");
        return new Profesor(id, nombre, apellido, ciudad);
    }

    @Override
    protected Row entityToRow(Profesor e) {
        return new Row(new RowAttribute[] {
                new RowAttribute("id", e.getId()),
                new RowAttribute("nombre", e.getNombre()),
                new RowAttribute("apellido", e.getApellido()),
                new RowAttribute("ciudad", e.getCiudad())
        });
    }

}