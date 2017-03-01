/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apotek;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class MainMenu extends javax.swing.JFrame {
    Statement stmt1, stmt2;
    ResultSet rsLogin, rsObat;
    String nama, id;
    String[] title = {"Tanggal Kadaluarsa", "Nama Obat", "Golongan", "Satuan", "Sisa Persediaan Gudang", "Sisa Persediaan Apotek"};
    ArrayList<setObat> list = new ArrayList<setObat>();
    /**
     * Creates new form MainMenu
     */
    public MainMenu(String id, String pass) {
        String sql = "SELECT * FROM DataGudang LEFT OUTER JOIN DataObat ON DataGudang.namaObatG=DataObat.namaObat "
                + "AND DataGudang.golObatG=DataObat.golObat AND DataGudang.satG=DataObat.sat "
                + "WHERE datediff(exdateG, current_date()) BETWEEN 0 AND 30 "
                + "UNION SELECT * FROM DataGudang RIGHT OUTER JOIN DataObat ON DataGudang.namaObatG=DataObat.namaObat "
                + "AND DataGudang.golObatG=DataObat.golObat AND DataGudang.satG=DataObat.sat "
                + "WHERE DataGudang.namaObatG IS NULL "
                + "AND datediff(exdateG, current_date()) BETWEEN 0 AND 30 ORDER BY exdateG";
        
        this.setWaktu();
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        try {
            setConnection koneksi = new setConnection();
            stmt1 = koneksi.connection.createStatement();
            rsLogin = stmt1.executeQuery("SELECT * FROM LoginApoteker WHERE id='"+ id +"'");
            while(rsLogin.next() == true) {
                this.id = id;
                nama = rsLogin.getString("nama");
            }
            
            stmt2 = koneksi.connection.createStatement();
            rsObat = stmt2.executeQuery(sql);
            while(rsObat.next() == true) {
                list.add(new setObat(rsObat.getDate("exdateG"), 
                        rsObat.getString("namaObatG"), 
                        rsObat.getString("golObatG"), 
                        rsObat.getString("satG"),
                        rsObat.getInt("jumlahSediaG"),
                        rsObat.getInt("jumlahSedia")));
            }
            
        } catch (SQLException ex) {
            System.out.println("Kesalahan: " + ex);;
        }
        txtWelcome.setText("Selamat datang, " + nama);
        updateTable();
    }
    
    private void updateTable() {
        Object[][] data = new Object[this.list.size()][6];
        int x = 0;
        for(setObat o: this.list) {
            data[x][0] = o.getExGudang();
            data[x][1] = o.getNamaObat();
            data[x][2] = o.getGolObat();
            data[x][3] = o.getSat();
            data[x][4] = o.getSisaGudang();
            data[x][5] = o.getSisaApotek();
            ++x;
        }
        tblEx.setModel(new DefaultTableModel(data, title));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new Apotek.SetImage("/image/wall.png");
        clPanelTransparan2 = new PanelTransparan.ClPanelTransparan();
        clPanelTransparan1 = new PanelTransparan.ClPanelTransparan();
        txtWelcome = new javax.swing.JLabel();
        txtTanggal = new javax.swing.JLabel();
        btnKeluar = new javax.swing.JLabel();
        btnResep = new javax.swing.JLabel();
        btnObat = new javax.swing.JLabel();
        btnGudang = new javax.swing.JLabel();
        btnLaporan = new javax.swing.JLabel();
        btnBantuan = new javax.swing.JLabel();
        clPanelTransparan3 = new PanelTransparan.ClPanelTransparan();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEx = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistem Informasi Apotek Taman");
        setPreferredSize(new java.awt.Dimension(1600, 680));

        jPanel1.setPreferredSize(new java.awt.Dimension(1370, 690));
        jPanel1.setLayout(null);

        clPanelTransparan2.setLayout(null);
        jPanel1.add(clPanelTransparan2);
        clPanelTransparan2.setBounds(360, 610, 640, 76);

        clPanelTransparan1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        clPanelTransparan1.setMinimumSize(new java.awt.Dimension(1370, 40));
        clPanelTransparan1.setPreferredSize(new java.awt.Dimension(1370, 680));

        txtWelcome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtWelcome.setText("jLabel1");

        txtTanggal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTanggal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnKeluar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout1.png"))); // NOI18N
        btnKeluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKeluarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout clPanelTransparan1Layout = new javax.swing.GroupLayout(clPanelTransparan1);
        clPanelTransparan1.setLayout(clPanelTransparan1Layout);
        clPanelTransparan1Layout.setHorizontalGroup(
            clPanelTransparan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clPanelTransparan1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(txtWelcome)
                .addGap(445, 445, 445)
                .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 571, Short.MAX_VALUE)
                .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        clPanelTransparan1Layout.setVerticalGroup(
            clPanelTransparan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, clPanelTransparan1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(clPanelTransparan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTanggal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtWelcome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(btnKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(clPanelTransparan1);
        clPanelTransparan1.setBounds(0, 0, 1370, 40);

        btnResep.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnResep.setForeground(new java.awt.Color(255, 255, 255));
        btnResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Resep.png"))); // NOI18N
        btnResep.setText("Resep");
        btnResep.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnResep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnResep.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnResep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResepMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnResepMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnResepMouseExited(evt);
            }
        });
        jPanel1.add(btnResep);
        btnResep.setBounds(400, 580, 70, 85);
        btnResep.getAccessibleContext().setAccessibleDescription("Resep");

        btnObat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnObat.setForeground(new java.awt.Color(255, 255, 255));
        btnObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/DataObat.png"))); // NOI18N
        btnObat.setText("Data Obat");
        btnObat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnObat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnObat.setMinimumSize(new java.awt.Dimension(48, 90));
        btnObat.setPreferredSize(new java.awt.Dimension(48, 90));
        btnObat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnObatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnObatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnObatMouseExited(evt);
            }
        });
        jPanel1.add(btnObat);
        btnObat.setBounds(520, 580, 70, 85);

        btnGudang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGudang.setForeground(new java.awt.Color(255, 255, 255));
        btnGudang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Gudang.png"))); // NOI18N
        btnGudang.setText("Gudang");
        btnGudang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGudang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGudang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGudang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGudangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGudangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGudangMouseExited(evt);
            }
        });
        jPanel1.add(btnGudang);
        btnGudang.setBounds(660, 580, 70, 85);

        btnLaporan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLaporan.setForeground(new java.awt.Color(255, 255, 255));
        btnLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Laporan.png"))); // NOI18N
        btnLaporan.setText("Laporan");
        btnLaporan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLaporan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLaporan.setMinimumSize(new java.awt.Dimension(48, 90));
        btnLaporan.setPreferredSize(new java.awt.Dimension(48, 90));
        btnLaporan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLaporanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLaporanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLaporanMouseExited(evt);
            }
        });
        jPanel1.add(btnLaporan);
        btnLaporan.setBounds(790, 580, 70, 85);

        btnBantuan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBantuan.setForeground(new java.awt.Color(255, 255, 255));
        btnBantuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Bantuan.png"))); // NOI18N
        btnBantuan.setText("Bantuan");
        btnBantuan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBantuan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBantuan.setMinimumSize(new java.awt.Dimension(48, 90));
        btnBantuan.setPreferredSize(new java.awt.Dimension(48, 90));
        btnBantuan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBantuan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBantuanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBantuanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBantuanMouseExited(evt);
            }
        });
        jPanel1.add(btnBantuan);
        btnBantuan.setBounds(910, 580, 70, 85);

        clPanelTransparan3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblEx.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tanggal Kadaluarsa", "Nama Obat", "Golongan Obat", "Satuan", "Sisa Persediaan Gudang", "Sisa Persediaan Apotek"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEx.setEnabled(false);
        jScrollPane1.setViewportView(tblEx);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Expired Reminder");

        javax.swing.GroupLayout clPanelTransparan3Layout = new javax.swing.GroupLayout(clPanelTransparan3);
        clPanelTransparan3.setLayout(clPanelTransparan3Layout);
        clPanelTransparan3Layout.setHorizontalGroup(
            clPanelTransparan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clPanelTransparan3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(clPanelTransparan3Layout.createSequentialGroup()
                .addGap(414, 414, 414)
                .addComponent(jLabel1)
                .addContainerGap(421, Short.MAX_VALUE))
        );
        clPanelTransparan3Layout.setVerticalGroup(
            clPanelTransparan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, clPanelTransparan3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(clPanelTransparan3);
        clPanelTransparan3.setBounds(180, 120, 1000, 390);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResepMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResepMouseEntered
        // TODO add your handling code here:
        btnResep.setIcon(new ImageIcon(MainMenu.class.getResource("/image/Resep2.png")));
    }//GEN-LAST:event_btnResepMouseEntered

    private void btnResepMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResepMouseExited
        // TODO add your handling code here:
        btnResep.setIcon(new ImageIcon(MainMenu.class.getResource("/image/Resep.png")));
    }//GEN-LAST:event_btnResepMouseExited

    private void btnResepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResepMouseClicked
        // TODO add your handling code here:
        new Resep().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnResepMouseClicked

    private void btnObatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnObatMouseEntered
        // TODO add your handling code here:
        btnObat.setIcon(new ImageIcon(MainMenu.class.getResource("/image/DataObat2.png")));
    }//GEN-LAST:event_btnObatMouseEntered

    private void btnObatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnObatMouseExited
        // TODO add your handling code here:
        btnObat.setIcon(new ImageIcon(MainMenu.class.getResource("/image/DataObat.png")));
    }//GEN-LAST:event_btnObatMouseExited

    private void btnObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnObatMouseClicked
        // TODO add your handling code here:
        new DataObat().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnObatMouseClicked

    private void btnGudangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGudangMouseEntered
        // TODO add your handling code here:
        btnGudang.setIcon(new ImageIcon(MainMenu.class.getResource("/image/Gudang2.png")));
    }//GEN-LAST:event_btnGudangMouseEntered

    private void btnGudangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGudangMouseExited
        // TODO add your handling code here:
        btnGudang.setIcon(new ImageIcon(MainMenu.class.getResource("/image/Gudang.png")));
    }//GEN-LAST:event_btnGudangMouseExited

    private void btnGudangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGudangMouseClicked
        // TODO add your handling code here:
        new Gudang().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnGudangMouseClicked

    private void btnLaporanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLaporanMouseEntered
        // TODO add your handling code here:
        btnLaporan.setIcon(new ImageIcon(MainMenu.class.getResource("/image/Laporan2.png")));
    }//GEN-LAST:event_btnLaporanMouseEntered

    private void btnLaporanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLaporanMouseExited
        // TODO add your handling code here:
        btnLaporan.setIcon(new ImageIcon(MainMenu.class.getResource("/image/Laporan.png")));
    }//GEN-LAST:event_btnLaporanMouseExited

    private void btnLaporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLaporanMouseClicked
        // TODO add your handling code here:
        new Laporan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnLaporanMouseClicked

    private void btnBantuanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBantuanMouseEntered
        // TODO add your handling code here:
        btnBantuan.setIcon(new ImageIcon(MainMenu.class.getResource("/image/Bantuan2.png")));
    }//GEN-LAST:event_btnBantuanMouseEntered

    private void btnBantuanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBantuanMouseExited
        // TODO add your handling code here:
        btnBantuan.setIcon(new ImageIcon(MainMenu.class.getResource("/image/Bantuan.png")));
    }//GEN-LAST:event_btnBantuanMouseExited

    private void btnBantuanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBantuanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBantuanMouseClicked

    private void btnKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKeluarMouseClicked
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKeluarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }
    
    public final void setWaktu() {
        ActionListener taskPerformer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nol_jam = "", nol_menit = "", nol_detik = "";

            java.util.Date dateTime = new java.util.Date();
            int nilai_jam = dateTime.getHours();
            int nilai_menit = dateTime.getMinutes();
            int nilai_detik = dateTime.getSeconds();

            if(nilai_jam <= 9) nol_jam="0";
            if(nilai_menit <= 9) nol_menit="0";
            if(nilai_detik <= 9) nol_detik="0";

            String waktu = nol_jam + Integer.toString(nilai_jam);
            String menit = nol_menit + Integer.toString(nilai_menit);
            String detik = nol_detik + Integer.toString(nilai_detik);

            txtTanggal.setText(String.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))) + " " + 
                    waktu + ":" + menit + ":" + detik);
        }
        };
        new Timer(1000, taskPerformer).start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBantuan;
    private javax.swing.JLabel btnGudang;
    private javax.swing.JLabel btnKeluar;
    private javax.swing.JLabel btnLaporan;
    private javax.swing.JLabel btnObat;
    private javax.swing.JLabel btnResep;
    private PanelTransparan.ClPanelTransparan clPanelTransparan1;
    private PanelTransparan.ClPanelTransparan clPanelTransparan2;
    private PanelTransparan.ClPanelTransparan clPanelTransparan3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEx;
    private javax.swing.JLabel txtTanggal;
    public static javax.swing.JLabel txtWelcome;
    // End of variables declaration//GEN-END:variables
}
