/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guia4jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import modelo.Alumno;
import modelo.Inscripcion;
import modelo.Materia;

/**
 *
 * @author Hugo
 */
public class Guia4JDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement ps = null;
        Alumno a1, a2, a3 = null;
        ResultSet rs = null;

        //b) Establecer la conexión a la base de datos previamente creada.
        con = Conexion.getConexion();

        //c) Insertar 3 alumnos
        a1 = new Alumno(99878911, "ola", "claudio", LocalDate.of(1977, 7, 4), true);
        a2 = new Alumno(88888888, "afas", "cristian", LocalDate.of(1989, 5, 15), true);
        a3 = new Alumno(77777777, "alincafsdfdsstro", "federico", LocalDate.of(2001, 9, 22), true);
        ArrayList<Alumno> alumnos = new ArrayList<>();
        alumnos.add(a1);
        alumnos.add(a2);
        alumnos.add(a3);

        String sql = "INSERT INTO alumno VALUES (NULL, ?, ?, ?, ?, ?)";

        Iterator<Alumno> it = alumnos.iterator();

        while (it.hasNext()) {
            Alumno alum = it.next();
            try {
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, alum.getDni());
                ps.setString(2, alum.getApellido());
                ps.setString(3, alum.getNombre());
                ps.setDate(4, Date.valueOf(alum.getFechaNacimiento()));
                ps.setBoolean(5, alum.isEstado());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    alum.setIdAlumno(rs.getInt(1));
                    JOptionPane.showMessageDialog(null, "Se ha guardado al alumno " + alum.getNombre() + " correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha guardado al alumno " + alum.getNombre() + " correctamente");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar al alumno: " + ex.getMessage());
            }

        }

        //d) Insertar 4 materias
        Materia portugues = new Materia("Portugues", 1, true);
        Materia web1 = new Materia("Web 1", 1, true);
        Materia eda = new Materia("EDA", 1, true);
        Materia web2 = new Materia("Web 2", 1, true);
        ArrayList<Materia> materias = new ArrayList<>();

        materias.add(portugues);
        materias.add(web1);
        materias.add(eda);
        materias.add(web2);

        sql = "INSERT INTO materia VALUES (NULL, ?, ?, ?)";

        Iterator<Materia> it2 = materias.iterator();

        while (it2.hasNext()) {
            Materia mat = it2.next();
            try {
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, mat.getNombre());
                ps.setInt(2, mat.getAño());
                ps.setBoolean(3, mat.isEstado());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    mat.setIdMateria(rs.getInt(1));
                    JOptionPane.showMessageDialog(null, "Se ha guardado la materia " + mat.getNombre() + " correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha guardado la materia " + mat.getNombre() + " correctamente");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar la materia: " + ex.getMessage());
            }

        }
        //e) Inscribir a los 3 alumnos en 2 materias cada uno.
        Inscripcion i1 = new Inscripcion(5, a1.getIdAlumno(), portugues.getIdMateria());
        Inscripcion i2 = new Inscripcion(7, a1.getIdAlumno(), eda.getIdMateria());
        Inscripcion i3 = new Inscripcion(4, a2.getIdAlumno(), portugues.getIdMateria());
        Inscripcion i4 = new Inscripcion(8, a2.getIdAlumno(), web1.getIdMateria());
        Inscripcion i5 = new Inscripcion(9, a3.getIdAlumno(), eda.getIdMateria());
        Inscripcion i6 = new Inscripcion(10, a3.getIdAlumno(), web2.getIdMateria());

        ArrayList<Inscripcion> inscripciones = new ArrayList<>();

        inscripciones.add(i1);
        inscripciones.add(i2);
        inscripciones.add(i3);
        inscripciones.add(i4);
        inscripciones.add(i5);
        inscripciones.add(i6);

        sql = "INSERT INTO inscripcion VALUES (NULL, ?, ?, ?)";

        Iterator<Inscripcion> it3 = inscripciones.iterator();

        while (it3.hasNext()) {
            Inscripcion inscripto = it3.next();
            try {
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, inscripto.getNota());
                ps.setInt(2, inscripto.getIdAlumno());
                ps.setInt(3, inscripto.getIdMateria());

                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    inscripto.setIdInscripcion(rs.getInt(1));
                    JOptionPane.showMessageDialog(null, "Se ha guardado la nota " + inscripto.getNota() + " correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No Se ha guardado la nota correctamente");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar la nota correctamente: " + ex.getMessage());
            }
        }
        //f) Listar los datos de los alumnos con calificaciones superiores a 8.
        ArrayList<Alumno> lista = new ArrayList<>();
        Alumno alumno;
        try {
            ps = con.prepareStatement("SELECT * FROM alumno JOIN inscripcion ON alumno.idAlumno = inscripcion.idAlumno JOIN materia ON inscripcion.idMateria=materia.idMateria WHERE inscripcion.nota>8");
            rs = ps.executeQuery();
            while (rs.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
                lista.add(alumno);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener los alumnos: " + ex.getMessage());
        }
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La base de datos se encuentra vacia");
        }
        
        //g) Desinscribir un alumno de una de la materias.
        sql = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, a2.getIdAlumno());
            ps.setInt(2, web1.getIdMateria());
            if (ps.executeUpdate()==1) {
                JOptionPane.showMessageDialog(null, "El alumno " + a2.getNombre()+ " se desinscribio de la materia "+web1.getNombre());
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo desinscribir al alumno de la materia.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar la nota del alumno: " + ex.getMessage());
        }

    }
}
