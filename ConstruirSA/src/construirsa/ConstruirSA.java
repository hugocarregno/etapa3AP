/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package construirsa;

import Modelo.Empleado;
import Modelo.Herramienta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author Hugo
 */
public class ConstruirSA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//h) Cargar el driver MariaDB.

//i) Establecer la conexión a la base de datos previamente creada.
        Connection con = null;
        PreparedStatement ps = null;
        Empleado e1, e2, e3 = null;
        ResultSet rs = null;

        con = Conexion.getConexion();
//j) Insertar 3 empleados.
        e1 = new Empleado(99878911, "Ola", "Claudio", 1, true);
        e2 = new Empleado(88888888, "Afas", "Cristian", 3, true);
        e3 = new Empleado(77777777, "Alin", "Federico", 2, true);
        ArrayList<Empleado> empleados = new ArrayList<>();
        empleados.add(e1);
        empleados.add(e2);
        empleados.add(e3);

        String sql = "INSERT INTO empleado VALUES (NULL, ?, ?, ?, ?, ?)";

        Iterator<Empleado> it = empleados.iterator();

        while (it.hasNext()) {
            Empleado emp = it.next();
            try {
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, emp.getDni());
                ps.setString(2, emp.getApellido());
                ps.setString(3, emp.getNombre());
                ps.setInt(4, emp.getAcceso());
                ps.setBoolean(5, emp.isEstado());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    emp.setId_empleado(rs.getInt(1));
                    JOptionPane.showMessageDialog(null, "Se ha guardado al empleado " + emp.getNombre() + " correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha guardado al empleado correctamente");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar al empleado: " + ex.getMessage());
            }

        }
//k) Insertar 2 herramientas.
        Herramienta destornillador = new Herramienta("Destornillador", "utilizado para extraer o insertar tornillos", 10, true);
        Herramienta martillo = new Herramienta("Martillo", "Utilizado para golpear objetos", 15, true);

        ArrayList<Herramienta> herramientas = new ArrayList<>();

        herramientas.add(destornillador);
        herramientas.add(martillo);

        sql = "INSERT INTO herramienta VALUES (NULL, ?, ?, ?, ?)";

        Iterator<Herramienta> it2 = herramientas.iterator();

        while (it2.hasNext()) {
            Herramienta h = it2.next();
            try {
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, h.getNombre());
                ps.setString(2, h.getDescripcion());
                ps.setInt(3, h.getStock());
                ps.setBoolean(4, h.isEstado());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    h.setId_herramienta(rs.getInt(1));
                    JOptionPane.showMessageDialog(null, "Se ha guardado la herramienta " + h.getNombre() + " correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha guardado la herramienta correctamente");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar la herramienta: " + ex.getMessage());
            }

        }
//l) Listar todas las herramientas con stock superior a 10.
        herramientas.clear();
        
        Herramienta herramienta;
        sql = "SELECT * FROM herramienta WHERE stock>10";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                herramienta = new Herramienta();
                herramienta.setNombre(rs.getString("nombre"));
                herramienta.setDescripcion(rs.getString("descripcion"));
                herramienta.setStock(rs.getInt("stock"));
                herramienta.setEstado(rs.getBoolean("estado"));
                herramientas.add(herramienta);
            }
            ps.close();
            it2 = herramientas.iterator();
            while (it2.hasNext()) {
                herramienta = it2.next();
                JOptionPane.showMessageDialog(null, herramienta);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener las materias: " + ex.getMessage());
        }
        if (herramientas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La base de datos se encuentra vacia");
        }

//m) Dar de baja al primer empleado ingresado a la base de datos.
        sql = "UPDATE empleado SET estado = false WHERE id_empleado = (SELECT MIN(id_empleado) FROM empleado)";
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (ps.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "Empleado dada de baja");
            } else {
                JOptionPane.showMessageDialog(null, "El empleado no se encuentra en la base de datos.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al dar de baja: " + ex.getMessage());
        }
    }

}
