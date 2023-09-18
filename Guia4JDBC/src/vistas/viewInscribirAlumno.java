/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import accesoADatos.AlumnoData;
import entidades.Alumno;
import java.time.ZoneOffset;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Hugo
 */
public class viewInscribirAlumno extends javax.swing.JInternalFrame {

    private AlumnoData ad;

    /**
     * Creates new form viewInscribirAlumno
     *
     * @param ad
     */
    public viewInscribirAlumno(AlumnoData ad) {
        initComponents();
        this.ad = ad;
        this.jrbActivo.setSelected(true);
    }

    public void guardarAlumno() {
        int dni = Integer.parseInt(txfDni.getText());
        String apellido = txfApellido.getText();
        String nombre = txfNombre.getText();
        boolean estado = jrbActivo.isSelected();
        java.sql.Date fdn = new java.sql.Date(jdcFechaDeNacimiento.getDate().getTime());
        Alumno alumno = new Alumno(dni, nombre, apellido, fdn.toLocalDate(), estado);
        ad.guardarAlumno(alumno);
    }

    public void buscarAlumno() {
        try{
            Alumno alumno = ad.buscarAlumnoPorDni(Integer.parseInt(txfDni.getText()));
        
            txfNombre.setText(alumno.getNombre());
            txfApellido.setText(alumno.getApellido());
            jdcFechaDeNacimiento.setDate(Date.from(alumno.getFechaNacimiento().atStartOfDay().toInstant(ZoneOffset.UTC)));
        
        }catch(NullPointerException e){
            System.out.println("No existe ese alumno");
        }
        

    }

    public void eliminarAlumno() {
        int seleccion = JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro que desea borrar al alumno?", "Eliminar Alumno",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

        if (seleccion == 0) {
            int id = ad.buscarAlumnoPorDni(Integer.parseInt(txfDni.getText())).getIdAlumno();
            ad.eliminarAlumno(id);
        }
    }

    public void limpiar() {
        txfNombre.setText("");
        txfApellido.setText("");
        txfDni.setText("");
        //Date date = new Date();
        jdcFechaDeNacimiento.setDate(null);
    }

    public void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblApellido = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblFechaDeNacimiento = new javax.swing.JLabel();
        lblDocumento = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        txfNombre = new javax.swing.JTextField();
        txfApellido = new javax.swing.JTextField();
        txfDni = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jdcFechaDeNacimiento = new com.toedter.calendar.JDateChooser();
        btnBuscar = new javax.swing.JButton();
        lblAlumno = new javax.swing.JLabel();
        Separator = new javax.swing.JSeparator();
        jrbActivo = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(0, 102, 0));
        setTitle("Alumnos");

        lblApellido.setText("Apellido:");

        lblNombre.setText("Nombre:");

        lblFechaDeNacimiento.setText("Fecha de Nacimiento:");

        lblDocumento.setText("Documento:");

        lblEstado.setText("Estado:");

        txfApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfApellidoActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/search_find_lupa.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lblAlumno.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblAlumno.setForeground(new java.awt.Color(0, 0, 0));
        lblAlumno.setText("Alumno");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblApellido, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblDocumento)
                                        .addGap(167, 167, 167))
                                    .addComponent(lblNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEstado, javax.swing.GroupLayout.Alignment.LEADING))
                                .addComponent(btnBuscar)
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblFechaDeNacimiento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txfDni, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jrbActivo)
                                            .addComponent(txfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jdcFechaDeNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Separator, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNuevo)
                                .addGap(33, 33, 33)
                                .addComponent(btnEliminar)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardar)
                                .addGap(18, 18, 18)
                                .addComponent(btnSalir)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(lblAlumno))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAlumno)
                        .addGap(4, 4, 4)
                        .addComponent(Separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txfDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDocumento)
                            .addComponent(btnBuscar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblApellido))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(txfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEstado)
                            .addComponent(jrbActivo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdcFechaDeNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblFechaDeNacimiento)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnNuevo)
                    .addComponent(btnEliminar)
                    .addComponent(btnGuardar))
                .addGap(18, 18, 18))
        );

        jdcFechaDeNacimiento.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txfApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfApellidoActionPerformed

    }//GEN-LAST:event_txfApellidoActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarAlumno();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarAlumno();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarAlumno();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator Separator;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private com.toedter.calendar.JDateChooser jdcFechaDeNacimiento;
    private javax.swing.JRadioButton jrbActivo;
    private javax.swing.JLabel lblAlumno;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaDeNacimiento;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextField txfApellido;
    private javax.swing.JTextField txfDni;
    private javax.swing.JTextField txfNombre;
    // End of variables declaration//GEN-END:variables
}
