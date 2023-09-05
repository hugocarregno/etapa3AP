/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Hugo
 */
public class Empleado {
    private int id_empleado;
    private int dni;
    private String apellido;
    private String nombre;
    private int acceso;
    private boolean estado;

    public Empleado() {
        this.id_empleado=-1;
    }

    public Empleado(int id_empleado, int dni, String apellido, String nombre, int acceso, boolean estado) {
        this.id_empleado = id_empleado;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.acceso = acceso;
        this.estado = estado;
    }

    public Empleado(int dni, String apellido, String nombre, int acceso, boolean estado) {
        this.id_empleado=-1;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.acceso = acceso;
        this.estado = estado;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAcceso() {
        return acceso;
    }

    public void setAcceso(int acceso) {
        this.acceso = acceso;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id_empleado=" + id_empleado + ", dni=" + dni + ", apellido=" + apellido + ", nombre=" + nombre + ", acceso=" + acceso + ", estado=" + estado + '}';
    }
    
    
    
    
}
