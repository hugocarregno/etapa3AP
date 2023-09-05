/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Grupo2
 */
public class Inscripcion {
    private int idInscripcion;
    private int nota;
    private int idAlumno;
    private int idMateria;

    public Inscripcion() {
        this.idInscripcion=-1;
    }

    public Inscripcion(int idInscripcion, int nota, int idAlumno, int idMateria) {
        this.idInscripcion = idInscripcion;
        this.nota = nota;
        this.idAlumno = idAlumno;
        this.idMateria = idMateria;
    }

    public Inscripcion(int nota, int idAlumno, int idMateria) {
        this.idInscripcion=-1;
        this.nota = nota;
        this.idAlumno = idAlumno;
        this.idMateria = idMateria;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    @Override
    public String toString() {
        return "Inscripcion{" + "idInscripcion=" + idInscripcion + ", nota=" + nota + ", idAlumno=" + idAlumno + ", idMateria=" + idMateria + '}';
    }
    
    
    
    
    
        
    
    
}
