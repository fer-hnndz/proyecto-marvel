/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectomarvelstratego;

import javax.swing.JOptionPane;

/**
 *
 * @author Gabby
 */
public class MiPerfil extends javax.swing.JFrame {

    /**
     * Creates new form MiPerfil
     */
    private SistemaUsuarios sistemaUsuarios;
    private Usuario usuario;

    public MiPerfil() {
        initComponents();
        sistemaUsuarios = SistemaUsuarios.getInstancia();
    }
        
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        mostrarInformacionUsuario();
    }
    
    public void mostrarInformacionUsuario() {
        if (usuario != null) {
            String nombreUsuario = usuario.getUsuario();
            String contrasena = usuario.getContrasena();
            jLabelNombreUsuario.setText(nombreUsuario);
        }
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCambiarPassword = new javax.swing.JButton();
        btnEliminarCuenta = new javax.swing.JButton();
        jLabelNombreUsuario = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCambiarPassword.setText("CAMBIAR PASSWORD");
        btnCambiarPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarPasswordActionPerformed(evt);
            }
        });

        btnEliminarCuenta.setText("ELIMINAR MI CUENTA");
        btnEliminarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCuentaActionPerformed(evt);
            }
        });

        jLabelNombreUsuario.setText("jLabel1");

        jButton1.setText("REGRESAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Usuario:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEliminarCuenta)
                            .addComponent(btnCambiarPassword)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelNombreUsuario)
                        .addGap(58, 58, 58)))
                .addContainerGap(161, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombreUsuario)
                    .addComponent(jLabel1))
                .addGap(60, 60, 60)
                .addComponent(btnCambiarPassword)
                .addGap(30, 30, 30)
                .addComponent(btnEliminarCuenta)
                .addGap(30, 30, 30)
                .addComponent(jButton1)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCambiarPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarPasswordActionPerformed
        // TODO add your handling code here:
        CambiarPassword changePassword = new CambiarPassword(sistemaUsuarios);
        changePassword.setUsuario(usuario);
        changePassword.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCambiarPasswordActionPerformed

    private void btnEliminarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCuentaActionPerformed
        // TODO add your handling code here:
        EliminarCuenta delete = new EliminarCuenta();
        delete.setUsuario(usuario);
        delete.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEliminarCuentaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        MenuInicio menu = new MenuInicio(sistemaUsuarios);
        menu.setUsuario(usuario);
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void mostrarMiPerfil(String nombre) {
        jLabelNombreUsuario.setText(nombre);
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiarPassword;
    private javax.swing.JButton btnEliminarCuenta;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
