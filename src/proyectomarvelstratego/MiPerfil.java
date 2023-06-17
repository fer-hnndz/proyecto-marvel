/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectomarvelstratego;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabby
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class MiPerfil extends javax.swing.JFrame {

    /**
     * Creates new form MiPerfil
     */
    private SistemaUsuarios sistemaUsuarios;
    private Usuario usuario;
    MenuInicio ventanaPrincipal;
    
    public MiPerfil(SistemaUsuarios sistemaUsuarios, MenuInicio ventanaPrincipal) {
        initComponents();
        this.sistemaUsuarios = sistemaUsuarios;
        this.ventanaPrincipal = ventanaPrincipal;
        cargarPartidas();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void setSistemaUsuarios(SistemaUsuarios sistemaUsuarios) {
        this.sistemaUsuarios = sistemaUsuarios;
    }
        
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        mostrarInformacionUsuario();
    }
    
    public void cargarPartidas() {
        ArrayList<Partida> partidas = sistemaUsuarios.usuarioIniciado.getPartidas();
        DefaultTableModel model = (DefaultTableModel) matchTable.getModel();

        for (int i = 0;i<partidas.toArray().length;i++){
            Partida partida = partidas.get(i);
            String resultado;
           
            if (partida.puntosGanados == 1.5) resultado = "EMPATE";
            else resultado = (partida.victoria) ?"VICTORIA":"DERROTA";

            
            model.addRow(new Object[]{partida.contrincante.getUsuario(), partida.bandoUsado, resultado, partida.puntosGanados});
        }
        
    }
    
    
    public void mostrarInformacionUsuario() {
        if (usuario != null) {
            String nombreUsuario = usuario.getUsuario();
            labelUsuario.setText(nombreUsuario);
            heroesLabel.setText("" + usuario.partidasBuenos);
            villanosLabel.setText("" + usuario.partidasMalos);
        }
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCambiarPassword = new javax.swing.JButton();
        btnEliminarCuenta = new javax.swing.JButton();
        labelUsuario = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        matchTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        heroesLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        villanosLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));

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

        labelUsuario.setText("jLabel1");

        backBtn.setText("REGRESAR");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Usuario:");

        matchTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Contrincante", "Bando Jugado", "Resultado", "Puntos Ganados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        matchTable.setShowGrid(true);
        matchTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(matchTable);

        jLabel2.setText("Partidas con Heroes:");

        heroesLabel.setText("jLabel3");

        jLabel3.setText("Partidas con Villanos:");

        villanosLabel.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(heroesLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(labelUsuario))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(villanosLabel))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCambiarPassword)
                                .addGap(85, 85, 85)
                                .addComponent(backBtn)
                                .addGap(86, 86, 86)
                                .addComponent(btnEliminarCuenta)))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelUsuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(heroesLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(villanosLabel))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarCuenta)
                    .addComponent(backBtn)
                    .addComponent(btnCambiarPassword))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCambiarPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarPasswordActionPerformed
        // TODO add your handling code here:
        CambiarPassword changePassword = new CambiarPassword(sistemaUsuarios, this);
        changePassword.setUsuario(usuario);
        changePassword.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCambiarPasswordActionPerformed

    private void btnEliminarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCuentaActionPerformed
        // TODO add your handling code here:
        EliminarCuenta delete = new EliminarCuenta(sistemaUsuarios, ventanaPrincipal);
        delete.setUsuario(usuario);
        delete.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEliminarCuentaActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    public void mostrarMiPerfil(String nombre) {
        labelUsuario.setText(nombre);
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton btnCambiarPassword;
    private javax.swing.JButton btnEliminarCuenta;
    private javax.swing.JLabel heroesLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JTable matchTable;
    private javax.swing.JLabel villanosLabel;
    // End of variables declaration//GEN-END:variables
}
