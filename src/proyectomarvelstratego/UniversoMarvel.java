/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectomarvelstratego;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge Hernandez
 */
public class UniversoMarvel extends javax.swing.JFrame {

    /**
     * Creates new form UniversoMarvel
     */
    
    SistemaUsuarios sistemaUsuarios;
    Stats stats;
    public UniversoMarvel(SistemaUsuarios sistemaUsuarios, Stats stats) {
        this.sistemaUsuarios = sistemaUsuarios;
        this.stats = stats;
        initComponents();
        cargarRankings();
        
        activeLabel.setText("Usuarios Activos: " + sistemaUsuarios.usuariosActivos.toArray().length);
        historicLabel.setText("Usuarios Historicos: " + sistemaUsuarios.usuariosHistoricos);
        matchesLabel.setText("Partidas Jugadas: " + Stats.getPartidasJugadas());
        heroesLabel.setText("Victorias Heroes: " + Stats.getVictoriasHeroes());
        villainLabel.setText("Victorias Villanos: " + Stats.getVictoriasVillanos());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        activeLabel = new javax.swing.JLabel();
        historicLabel = new javax.swing.JLabel();
        heroesLabel = new javax.swing.JLabel();
        matchesLabel = new javax.swing.JLabel();
        villainLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        rankingTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Universo Marvel");
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(25, 25, 55));

        jLabel1.setFont(new java.awt.Font("Nirmala UI", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("RANKING");

        activeLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        activeLabel.setForeground(new java.awt.Color(255, 255, 255));
        activeLabel.setText("Usuarios Activos");

        historicLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        historicLabel.setForeground(new java.awt.Color(255, 255, 255));
        historicLabel.setText("Usuarios Historicos");

        heroesLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        heroesLabel.setForeground(new java.awt.Color(255, 255, 255));
        heroesLabel.setText("Victorias Heroes");

        matchesLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        matchesLabel.setForeground(new java.awt.Color(255, 255, 255));
        matchesLabel.setText("Partidas Jugadas");

        villainLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        villainLabel.setForeground(new java.awt.Color(255, 255, 255));
        villainLabel.setText("Victorias Villanos");

        rankingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Posicion", "Usuario", "Puntos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        rankingTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(rankingTable);

        jLabel2.setFont(new java.awt.Font("Raleway Light", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("UNIVERSO MARVEL");

        jLabel3.setFont(new java.awt.Font("Nirmala UI", 1, 22)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("BATALLAS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(historicLabel)
                            .addComponent(matchesLabel)
                            .addComponent(heroesLabel)
                            .addComponent(villainLabel)
                            .addComponent(activeLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(382, 382, 382))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(382, 382, 382))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel2)
                .addGap(74, 74, 74)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(activeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(historicLabel)
                        .addGap(7, 7, 7)
                        .addComponent(matchesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(heroesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(villainLabel)))
                .addContainerGap(21, Short.MAX_VALUE))
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

    void printUsuarios(Usuario[] usuarios) {
        for (Usuario user:usuarios) {
            System.out.println(user.getUsuario() + ": " + user.getPuntos());
        }
    }
    
    void cargarRankings() {
        Usuario[] usuarios = sistemaUsuarios.getUsuariosActivos();
        printUsuarios(usuarios);
        usuarios = ordernarUsuario(usuarios);
        System.out.println("===ORDENADOS===");
        printUsuarios(usuarios);
        
        DefaultTableModel model = (DefaultTableModel) rankingTable.getModel();
        for (int i = 0; i<usuarios.length;i++) {
            Usuario user = usuarios[i];
            model.addRow(new Object[]{i+1, user.getUsuario(), user.getPuntos()});
        }
        
    }
    
    /**
     * Ordena los usuarios de mayor a menor en base a sus puntos
     * @return 
     */
    Usuario[] ordernarUsuario(Usuario[] usuarios) {
        Usuario usuariosOrdenados[] = Arrays.copyOf(usuarios, usuarios.length);
        // Usar bubble sort para ordenar los usuarios en base a su puntuacion
        
        // EL ARRAY ESTA ORDENADO PORQUE SOLO TIENE UN USUARIO
        if (usuarios.length == 1) return usuarios;
        
        for (int i =0;i<usuariosOrdenados.length-1;i++){
        /*
            if (i== usuarios.length -1) {
                if (usuarios[i].getPuntos() < usuarios[i-1].getPuntos()) {
                    Usuario usuarioMayor = usuarios[i-1];
                    Usuario usuarioMenor = usuarios[i];
                    usuarios[i] = usuarioMayor;
                    usuarios[i-1] = usuarioMenor;
                    continue;
                }
            }
         */
            
        
            Usuario L = usuarios[i];
            Usuario R = usuarios[i+1];
            if (L.getPuntos()<R.getPuntos()) {
                usuarios[i + 1] = L;
                usuarios[i] = R;
            }
        }
        
        return usuarios;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel activeLabel;
    private javax.swing.JLabel heroesLabel;
    private javax.swing.JLabel historicLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel matchesLabel;
    private javax.swing.JTable rankingTable;
    private javax.swing.JLabel villainLabel;
    // End of variables declaration//GEN-END:variables
}
