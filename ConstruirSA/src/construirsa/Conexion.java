/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package construirsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Hugo
 */
public class Conexion {

    private static String url = "jdbc:mariadb://localhost/";
    private static String usuario = "root";
    private static String password = "";
    private static String db = "obrador";
    private static Conexion con = null;

    private Conexion() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public static Connection getConexion() {
        Connection conn = null;
        if (con == null) {
            con = new Conexion();
        }
        try {
            conn = DriverManager.getConnection(url+db, usuario, password);
            JOptionPane.showMessageDialog(null, "Conexion exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion");
        }
        return conn;
    }
}
