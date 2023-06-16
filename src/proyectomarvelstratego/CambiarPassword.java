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

import javax.swing.*;
public class CambiarPassword extends javax.swing.JFrame {
    /**
     * Creates new form CambiarPassword
     */    
    private Usuario usuario;
    SistemaUsuarios sistemaUsuarios;
    MiPerfil ventanaPerfil;
    
    public CambiarPassword(SistemaUsuarios sistemaUsuarios, MiPerfil ventanaPrincipal) {
        initComponents();
        this.sistemaUsuarios = sistemaUsuarios;
        this.ventanaPerfil = ventanaPrincipal;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
  
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtContraseñaActual = new javax.swing.JTextField();
        txtContraseñaNueva = new javax.swing.JTextField();
        btnCambiarContra = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Contraseña Actual:");

        jLabel2.setText("Contraseña Nueva:");

        btnCambiarContra.setText("ACTUALIZAR PASSWORD");
        btnCambiarContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarContraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContraseñaActual)
                            .addComponent(txtContraseñaNueva))
                        .addGap(56, 56, 56))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCambiarContra)
                        .addGap(203, 203, 203))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtContraseñaActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtContraseñaNueva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(btnCambiarContra, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCambiarContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarContraActionPerformed
        // TODO add your handling code here:
        if (usuario != null) {
            String contrasenaActual = usuario.getContrasena();
            String contrasenaIngresada = txtContraseñaActual.getText();

            if (contrasenaIngresada.equals(contrasenaActual)) {
                String nuevaContrasena = txtContraseñaNueva.getText();
                
                if (nuevaContrasena.length() != 5) {
                    JOptionPane.showMessageDialog(this, "ERROR. La contrasena debe ser de 5 caracteres");
                    return;
                }
                
                usuario.setContrasena(nuevaContrasena); // Actualiza contraseña en el objeto Usuario
                sistemaUsuarios.actualizarUsuario(usuario);
                ventanaPerfil.setSistemaUsuarios(sistemaUsuarios);
                JOptionPane.showMessageDialog(this, "Contraseña cambiada exitosamente a: " + nuevaContrasena);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "La contraseña actual es incorrecta.");
                return;
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo cambiar la contraseña.");
            dispose();
        }
    }//GEN-LAST:event_btnCambiarContraActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiarContra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtContraseñaActual;
    private javax.swing.JTextField txtContraseñaNueva;
    // End of variables declaration//GEN-END:variables
}
