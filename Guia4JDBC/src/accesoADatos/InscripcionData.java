/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoADatos;

import entidades.Alumno;
import entidades.Inscripcion;
import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Hugo
 */
public class InscripcionData {

    private Connection con = null;
    private AlumnoData ad = new AlumnoData();
    private MateriaData md = new MateriaData();

    public void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public InscripcionData() {
        con = Conexion.getConexion();
    }

    public void guardarInscripcion(Inscripcion inscripcion) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO inscripcion(idAlumno, idMateria, nota) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, inscripcion.getAlumno().getIdAlumno());
            ps.setInt(2, inscripcion.getMateria().getIdMateria());
            ps.setDouble(3, inscripcion.getNota());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                inscripcion.setIdInscripcion(rs.getInt(1));
                mensaje("Se ha guardado correctamente la nota del alumno");
            } else {
                mensaje("No se ha guardado la nota del alumno");
            }
        } catch (SQLException ex) {
            mensaje("Error al guardar la nota del alumno: " + ex.getMessage());
        }
    }

    public List<Inscripcion> obtenerInscripciones() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        Inscripcion inscripcion;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM inscripcion");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                inscripcion = new Inscripcion();
                inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
                Alumno alum = ad.buscarAlumno(rs.getInt("idAlumno"));
                Materia mat = md.buscarMateria(rs.getInt("idMateria"));
                inscripcion.setAlumno(alum);
                inscripcion.setMateria(mat);
                inscripcion.setNota(rs.getDouble("nota"));
                inscripciones.add(inscripcion);
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al obtener las inscripciones: " + ex.getMessage());
        }
        if (inscripciones.isEmpty()) {
            mensaje("La base de datos se encuentra vacia");
        }
        return inscripciones;
    }

    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id) {
        List<Inscripcion> inscripciones = new ArrayList<>();
        Inscripcion inscripcion;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM inscripcion WHERE idAlumno = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                inscripcion = new Inscripcion();
                inscripcion.setIdInscripcion(rs.getInt("idInscripto"));
                Alumno alum = ad.buscarAlumno(id);
                inscripcion.setAlumno(alum);
                Materia mat = md.buscarMateria(rs.getInt("idMateria"));
                inscripcion.setMateria(mat);
                inscripcion.setNota(rs.getDouble("nota"));
                inscripciones.add(inscripcion);
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al buscar inscripciones por alumno: " + ex.getMessage());
        }
        if (inscripciones.isEmpty()) {
            mensaje("La base de datos se encuentra vacia");
        }
        return inscripciones;
    }

    public List<Materia> obtenerMateriasCursadas(int id) {
        List<Materia> materias = new ArrayList<>();
        Materia m;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT inscripcion.idMateria, nombre, año, estado FROM inscripcion, materia WHERE inscripcion.idMateria = materia.idMateria AND inscripcion.idAlumno = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                m = new Materia();
                m.setIdMateria(rs.getInt("idMateria"));
                m.setNombre(rs.getString("nombre"));
                m.setAño(rs.getInt("año"));
                m.setEstado(rs.getBoolean("estado"));
                materias.add(m);
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al buscar al alumno con la id ingresada: " + ex.getMessage());
        }
        if (materias.isEmpty()) {
            mensaje("La base de datos se encuentra vacia");
        }
        return materias;
    }

    public List<Materia> obtenerMateriasNOCursadas(int id) {
        List<Materia> noCursadas = new ArrayList<>();
        Materia m;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM materia WHERE estado = 1 AND idMateria NOT IN(SELECT idMateria FROM inscripcion WHERE inscripcion.idAlumno = ?)");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                m = new Materia();
                m.setIdMateria(rs.getInt("idMateria"));
                m.setNombre(rs.getString("nombre"));
                m.setAño(rs.getInt("año"));
                m.setEstado(rs.getBoolean("estado"));
                noCursadas.add(m);
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al consultar la tabla: " + ex.getMessage());
        }
        if (noCursadas.isEmpty()) {
            mensaje("La lista esta vacia.");
        }
        return noCursadas;
    }

    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?");
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            if (ps.executeUpdate() == 1) {
                mensaje("La nota del alumno ha sido borrada correctamente");
            } else {
                mensaje("La nota del alumno no ha sido borrada.");
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al borrar la nota del alumno: " + ex.getMessage());
        }
    }

    public void actualizarNota(int idAlumno, int idMateria, double nota) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE inscripcion SET nota = ? WHERE idAlumno = ? AND idMateria = ?");
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            if (ps.executeUpdate() == 1) {
                mensaje("La nota del alumno ha sido actualizada correctamente.");
            } else {
                mensaje("La nota del alumno no se actualizo.");
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar la nota de un alumno: " + ex.getMessage());
        }
    }

    public List<Alumno> obtenerAlumnosXMateria(int idMateria) {
        List<Alumno> alumnosMateria = new ArrayList<>();
        String sql = "SELECT a.idAlumno, dni, nombre, apellido, fechaNacimiento, estado FROM inscripcion i, alumno a WHERE i.idAlumno = a.idAlumno AND idMateria = ? AND a.estado = 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMateria);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
                alumnosMateria.add(alumno);
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar la nota de un alumno: " + ex.getMessage());
        }
        return alumnosMateria;
    }

}
