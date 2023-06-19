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

        jPanel1 = new javax.swing.JPanel();
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
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));

        jPanel1.setBackground(new java.awt.Color(25, 25, 55));

        btnCambiarPassword.setBackground(new java.awt.Color(0, 255, 255));
        btnCambiarPassword.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnCambiarPassword.setForeground(new java.awt.Color(0, 102, 102));
        btnCambiarPassword.setText("CAMBIAR PASSWORD");
        btnCambiarPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarPasswordActionPerformed(evt);
            }
        });

        btnEliminarCuenta.setBackground(new java.awt.Color(0, 255, 255));
        btnEliminarCuenta.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        btnEliminarCuenta.setForeground(new java.awt.Color(0, 102, 102));
        btnEliminarCuenta.setText("ELIMINAR MI CUENTA");
        btnEliminarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCuentaActionPerformed(evt);
            }
        });

        labelUsuario.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        labelUsuario.setForeground(new java.awt.Color(255, 255, 255));
        labelUsuario.setText("jLabel1");

        backBtn.setBackground(new java.awt.Color(255, 102, 102));
        backBtn.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        backBtn.setForeground(new java.awt.Color(255, 255, 255));
        backBtn.setText("REGRESAR");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Nirmala UI", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario:");

        jScrollPane1.setBackground(new java.awt.Color(25, 25, 25));

        matchTable.setBackground(new java.awt.Color(153, 153, 153));
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
        matchTable.setToolTipText("");
        matchTable.setName(""); // NOI18N
        matchTable.setShowGrid(true);
        matchTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(matchTable);

        jLabel2.setFont(new java.awt.Font("Nirmala UI", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Partidas con Heroes:");

        heroesLabel.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        heroesLabel.setForeground(new java.awt.Color(255, 255, 255));
        heroesLabel.setText("jLabel3");

        jLabel3.setFont(new java.awt.Font("Nirmala UI", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Partidas con Villanos:");

        villanosLabel.setFont(new java.awt.Font("Nirmala UI", 0, 18)); // NOI18N
        villanosLabel.setForeground(new java.awt.Color(255, 255, 255));
        villanosLabel.setText("jLabel4");

        jLabel4.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Mi Perfil");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(558, 558, 558))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(heroesLabel))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(labelUsuario))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(villanosLabel))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnCambiarPassword)
                                .addGap(85, 85, 85)
                                .addComponent(backBtn)
                                .addGap(86, 86, 86)
                                .addComponent(btnEliminarCuenta)))))
                .addContainerGap(345, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelUsuario))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(heroesLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(villanosLabel))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarCuenta)
                    .addComponent(backBtn)
                    .addComponent(btnCambiarPassword))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JTable matchTable;
    private javax.swing.JLabel villanosLabel;
    // End of variables declaration//GEN-END:variables
}
