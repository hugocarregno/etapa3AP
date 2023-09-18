/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoADatos;

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
public class MateriaData {

    private Connection con = null;

    public void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public MateriaData() {
        con = Conexion.getConexion();
    }

    public void guardarMateria(Materia materia) {
        String sql = "INSERT INTO materia (nombre, año, estado) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAño());
            ps.setBoolean(3, materia.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                materia.setIdMateria(rs.getInt(1));
                mensaje("Se ha guardado la materia " + materia.getNombre() + " correctamente");
            } else {
                mensaje("No se ha guardado la materia");
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al guardar la materia: " + ex.getMessage());
        }
    }

    public Materia buscarMateria(int id) {
        Materia materia = null;
        String sql = "SELECT idMateria, nombre, año, estado FROM materia WHERE idMateria=? AND estado=1";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAño(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
            }else{
                mensaje("No existe materia con ese código");
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al buscar la materia con idMateria: " + id + ". Error: " + ex.getMessage());
        }
        return materia;
    }

    public List<Materia> listarMaterias() {
        List<Materia> materias = new ArrayList<>();
        Materia m;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM materia WHERE estado = 1");
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
            mensaje("Error al obtener las materias: " + ex.getMessage());
        }
        if (materias.isEmpty()) {
            mensaje("La base de datos se encuentra vacia");
        }
        return materias;
    }

    public void eliminarMateria(int id) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE materia SET estado = 0 WHERE idMateria = ?");
            ps.setInt(1, id);
            if (ps.executeUpdate() == 1) {
                mensaje("Materia borrada correctamente.");
            } else {
                mensaje("La materia no se encuentra en la base de datos.");
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al dar de baja la materia con id: " + id + ". Error: " + ex.getMessage());
        }
    }

    public void modificarMateria(Materia materia) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE materia SET nombre = ?, año = ? WHERE idMateria = ?");
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAño());
            ps.setInt(3, materia.getIdMateria());
            if (ps.executeUpdate() == 1) {
                mensaje("Materia con id: " + materia.getIdMateria() + " modificada correctamente.");
            } else {
                mensaje("Materia con id: " + materia.getIdMateria() + " no pudo ser actualizada correctamente.");
            }
            ps.close();
        } catch (SQLException ex) {
            mensaje("Error al actualizar la materia con id_materia: " + materia.getIdMateria() + ". Error: " + ex.getMessage());
        }
    }
}
