/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author Hugo
 */
public class Movimiento {

    private int id_movimiento;
    private int id_empleado;
    private int id_herramienta;
    private LocalDate fechap;
    private LocalDate fechad;
    private boolean estado;

    public Movimiento() {
        this.id_movimiento = -1;
    }

    public Movimiento(int id_movimiento, int id_empleado, int id_herramienta, LocalDate fechap, LocalDate fechad, boolean estado) {
        this.id_movimiento = -1;
        this.id_movimiento = id_movimiento;
        this.id_empleado = id_empleado;
        this.id_herramienta = id_herramienta;
        this.fechap = fechap;
        this.fechad = fechad;
        this.estado = estado;
    }

    public Movimiento(int id_empleado, int id_herramienta, LocalDate fechap, LocalDate fechad, boolean estado) {
        this.id_empleado = id_empleado;
        this.id_herramienta = id_herramienta;
        this.fechap = fechap;
        this.fechad = fechad;
        this.estado = estado;
    }

    public int getId_movimiento() {
        return id_movimiento;
    }

    public void setId_movimiento(int id_movimiento) {
        this.id_movimiento = id_movimiento;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getId_herramienta() {
        return id_herramienta;
    }

    public void setId_herramienta(int id_herramienta) {
        this.id_herramienta = id_herramienta;
    }

    public LocalDate getFechap() {
        return fechap;
    }

    public void setFechap(LocalDate fechap) {
        this.fechap = fechap;
    }

    public LocalDate getFechad() {
        return fechad;
    }

    public void setFechad(LocalDate fechad) {
        this.fechad = fechad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Movimiento{" + "id_movimiento=" + id_movimiento + ", id_empleado=" + id_empleado + ", id_herramienta=" + id_herramienta + ", fechap=" + fechap + ", fechad=" + fechad + ", estado=" + estado + '}';
    }

}
