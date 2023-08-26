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
public class Tarea {
    private String descripcion;
    private boolean completado;

    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.completado = false;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    @Override
    public String toString() {
        return "Tarea{" + "descripcion=" + descripcion + ", completado=" + completado + '}';
    }
    
    
    
    
}
