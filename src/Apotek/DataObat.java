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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author user
 */
public class DataObat extends javax.swing.JFrame {
    Statement stmt, stmt1, stmt2;
    ResultSet rsGudang, rsObat, rsCari;
    String x;
    String[] title = {"Nama Obat", "Golongan", "Satuan", 
        "Persediaan Gudang", "Tanggal Kadaluarsa Gudang", "Persediaan Apotek", "Tanggal Kadaluarsa Apotek"};
    ArrayList<setObat> list = new ArrayList<setObat>();
    String sql = "SELECT * FROM DataGudang LEFT OUTER JOIN DataObat ON DataGudang.namaObatG=DataObat.namaObat "
            + "AND DataGudang.golObatG=DataObat.golObat AND DataGudang.satG=DataObat.sat "
            + "UNION "
            + "SELECT * FROM DataGudang RIGHT OUTER JOIN DataObat ON DataGudang.namaObatG=DataObat.namaObat "
            + "AND DataGudang.golObatG=DataObat.golObat AND DataGudang.satG=DataObat.sat "
            + "WHERE DataGudang.namaObatG IS NULL ORDER BY namaObatG";
    /**
     * Creates new form MainMenu
     */
    public DataObat() {
        this.setWaktu();
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        try {
            setConnection koneksi = new setConnection();
            stmt2 = koneksi.connection.createStatement();
            rsObat = stmt2.executeQuery(sql);
            while(rsObat.next() == true) {
                x = rsObat.getString("namaObatG");
                if(!(x == null)) {
                    list.add(new setObat(rsObat.getString("namaObatG"), 
                        rsObat.getString("golObatG"), 
                        rsObat.getString("satG"), 
                        rsObat.getInt("jumlahSediaG"), 
                        rsObat.getDate("exdateG"), 
                        rsObat.getInt("jumlahSedia"), 
                        rsObat.getDate("exdate")));
                } else if(x == null) {
                    list.add(new setObat(rsObat.getString("namaObat"), 
                        rsObat.getString("golObatG"), 
                        rsObat.getString("satG"), 
                        rsObat.getInt("jumlahSediaG"), 
                        rsObat.getDate("exdateG"), 
                        rsObat.getInt("jumlahSedia"), 
                        rsObat.getDate("exdate")));
                }
            }
            SearchSugges();
        } catch (SQLException ex) {
            System.out.println("Kesalahan: " + ex);;
        }
//        txtWelcome.setText(MainMenu.txtWelcome.getText());
        updateTable();
        
    }
    
    private void updateTable() {
        Object[][] data = new Object[this.list.size()][7];
        int x = 0;
        for(setObat o: this.list) {
            data[x][0] = o.getNamaObat();
            data[x][1] = o.getGolObat();
            data[x][2] = o.getSat();
            data[x][3] = o.getSisaGudang();
            data[x][4] = o.getExGudang();
            data[x][5] = o.getSisaApotek();
            data[x][6] = o.getExApotek();
            ++x;
        }
        tblEx.setModel(new DefaultTableModel(data, title));
    }
    
    private void removeTable() {
        DefaultTableModel model = (DefaultTableModel)tblEx.getModel();
        while (model.getRowCount() > 0){
            for (int i = 0; i < model.getRowCount(); ++i){
                model.removeRow(i);
            }
        }
    }
    private void SearchSugges() throws SQLException{
        setConnection koneksi = new setConnection();
        stmt = koneksi.connection.createStatement();
        ArrayList<String> li = new ArrayList<String>();
        rsCari = stmt.executeQuery("SELECT * FROM DataGudang ORDER BY namaObatG");
        li.add("");
        while(rsCari.next()==true){
            li.add(rsCari.getString("namaObatG"));
        }
        String [] cariObat = new String[li.size()];
        cariObat = li.toArray(cariObat);
        
        DefaultComboBoxModel<String> cO = new DefaultComboBoxModel<String>(cariObat);
        cbCariNama.setModel(cO);
        AutoCompleteDecorator.decorate(this.cbCariNama);
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
        clPanelTransparan1 = new PanelTransparan.ClPanelTransparan();
        txtWelcome = new javax.swing.JLabel();
        txtTanggal = new javax.swing.JLabel();
        btnKeluar = new javax.swing.JLabel();
        clPanelTransparan3 = new PanelTransparan.ClPanelTransparan();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEx = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbCariGol = new javax.swing.JComboBox<String>();
        btnCari = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbCariSort = new javax.swing.JComboBox<String>();
        cbCariNama = new javax.swing.JComboBox<String>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistem Informasi Apotek Taman");

        jPanel1.setPreferredSize(new java.awt.Dimension(1370, 690));
        jPanel1.setLayout(null);

        clPanelTransparan1.setBackground(new java.awt.Color(255, 255, 255));
        clPanelTransparan1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        clPanelTransparan1.setMinimumSize(new java.awt.Dimension(1370, 40));
        clPanelTransparan1.setPreferredSize(new java.awt.Dimension(1370, 680));

        txtWelcome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtWelcome.setText("jLabel1");

        txtTanggal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTanggal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnKeluar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Home.png"))); // NOI18N
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 524, Short.MAX_VALUE)
                .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(492, 492, 492)
                .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
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

        clPanelTransparan3.setBackground(new java.awt.Color(255, 255, 255));
        clPanelTransparan3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblEx.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nama Obat", "Golongan Obat", "Satuan", "Persediaan Gudang", "Tanggal Kadaluarsa Gudang", "Persediaan Apotek", "Tanggal Kadaluarsa Apotek"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEx.setEnabled(false);
        jScrollPane1.setViewportView(tblEx);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Data Obat");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Nama : ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Golongan :");

        cbCariGol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Tampilkan Semua--", "NARKOTIKA", "PSIKOTROPIKA", "(1a) ANALG,ANTIP,AN.INFL NON NARKOTIK", "(1b) ANALGETIK NARKOTIK", "(1c) ANTIPIRAI ", "(2) ANASTESI LOKAL", "(3) AN.EPILEPSI, AN.KONV, AN.ASIETAS, SEDATIV, HIPNOTIK, AN.PSIKOTIK", "(4) ANTI PARKINSON ", "(5) ANTI DEPRESI", "(6) ANTI MIGREN", "(7) ANTI ANGINA-ANTI ARITMIA", "(8) ANTI HIPERTENSI-DIURETIKA", "(9) GLUKOSIDA JANTUNG", "(10) OBAT PD SHOK-ANTI ASMA KORTIKOS", "(11) ANTI TUSIF", "(12) EKSPEKTORAN", "(13) ANTI INFLUENZA", "(14) ANTASIDA", "(15) OBAT DIARE-KESEIMBANGAN CAIRAN", "(16) LAKSAN", "(17) ANTI SPASMODIK", "(18) ANTI HISTAMIN", "(19) LARUTAN NUTRISI", "(20) TIROID ANTAGONIS", "(21) ANTI DIABETIK ORAL", "(22) ANTI DIABETIK PARENTERAL", "(23) VITAMIN DAN MINERAL", "(24) ANTI BAKTERI SISTEMIK, ANTISEPTIK", "(25) ANTI VIRUS", "(26) ANTI FUNGSI", "(27) ANTI TUBERKULOSIS", "(28) ANTI SEPTIK, DESINFEKTAN", "(29) ANTELMENTIK", "(30) ANTI AMUBIASIS", "(31) OBAT YG MEMPENGARUHI DARAH, ANTI ANEMIA", "(32) HEMOSTATIK", "(33) PRODUK DAN SUBTITUEN PLASMA", "(34) SERUM", "(35) AKSITOSIK", "(36) RELAKSAN UTERUS", "(37) ANTI INFLAMASI SALEP", "(38) PERANGSANG JARINGAN GRANULASI", "(39) ANTI BAKTERI", "(40) ANTI FUNGSI SALEP", "(41) ANTI SCABIES", "(42) ANTI SEPTIK", "(43) LAIN-LAIN OBAT KULIT", "(44) ANTI SISTEMIK MATA", "(45) ANASTESI LOKAL MATA ", "(46) ANTI INFEKSI MATA", "(47) LAIN-LAIN OBAT MATA", "(48) ANTI INFEKSI THT", "(49) LAIN-LAIN INFEKSI THT ", "(50) ANTI FILARIASIS", "(51) ANTI HEMOROID", "(52) ANTI EMETIK", "(53) ANTI HIPERKOLESTEROLEMIA", "(54) NOOTROPIK", "(55) IMMUNDILATOR", "(56) OBAT GIGI", "(57) OBAT TOPIKAL MULUT ", "(58) ALAT KESEHATAN HABIS PAKAI", "(59) REAGENSIA & LAIN-LAIN" }));

        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cari.png"))); // NOI18N
        btnCari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCariMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Urutkan Berdasar :");

        cbCariSort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nama Obat", "Golongan Obat", "Satuan", "Jumlah Gudang", "Tanggal Kadaluarsa Gudang", "Jumlah Apotek", "Tanggal Kadaluarsa Apotek" }));
        cbCariSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCariSortActionPerformed(evt);
            }
        });

        cbCariNama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout clPanelTransparan3Layout = new javax.swing.GroupLayout(clPanelTransparan3);
        clPanelTransparan3.setLayout(clPanelTransparan3Layout);
        clPanelTransparan3Layout.setHorizontalGroup(
            clPanelTransparan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clPanelTransparan3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clPanelTransparan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1306, Short.MAX_VALUE)
                    .addGroup(clPanelTransparan3Layout.createSequentialGroup()
                        .addGroup(clPanelTransparan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(clPanelTransparan3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbCariNama, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(cbCariGol, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbCariSort, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCari))
                            .addGroup(clPanelTransparan3Layout.createSequentialGroup()
                                .addGap(604, 604, 604)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        clPanelTransparan3Layout.setVerticalGroup(
            clPanelTransparan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, clPanelTransparan3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(clPanelTransparan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(clPanelTransparan3Layout.createSequentialGroup()
                        .addGroup(clPanelTransparan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(cbCariSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(cbCariGol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbCariNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))
                    .addComponent(btnCari))
                .addContainerGap())
        );

        jPanel1.add(clPanelTransparan3);
        clPanelTransparan3.setBounds(20, 60, 1330, 600);

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
    }// </editor-fold>//GEN-END:initComponents

    private void btnKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKeluarMouseClicked
        // TODO add your handling code here:
        new MainMenu(setApoteker.tmpID, setApoteker.tmpPass).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKeluarMouseClicked

    private void cbCariSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCariSortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCariSortActionPerformed

    private void btnCariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCariMouseClicked
        // TODO add your handling code here:
        removeTable();
        String urut = null;
        
        switch(cbCariSort.getSelectedIndex()) {
            case 0: urut = "namaObatG"; break;
            case 1: urut = "golObatG"; break;
            case 2: urut = "satG"; break;
            case 3: urut = "jumlahSediaG"; break;
            case 4: urut = "exdateG"; break;
            case 5: urut = "jumlahSedia"; break;
            case 6: urut = "exdate"; break;
        }
        
        try {
            setConnection koneksi = new setConnection();
            stmt1 = koneksi.connection.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        if(cbCariNama.getSelectedItem().toString().equals("") || cbCariNama.getSelectedItem().toString()==null) {
            if(cbCariGol.getSelectedItem().equals("--Tampilkan Semua--")) {
                try {
                    rsObat = stmt1.executeQuery("SELECT * FROM DataGudang LEFT OUTER JOIN DataObat "
                            + "ON DataGudang.namaObatG=DataObat.namaObat "
                            + "AND DataGudang.golObatG=DataObat.golObat AND DataGudang.satG=DataObat.sat "
                            + "UNION "
                            + "SELECT * FROM DataGudang RIGHT OUTER JOIN DataObat ON DataGudang.namaObatG=DataObat.namaObat "
                            + "AND DataGudang.golObatG=DataObat.golObat AND DataGudang.satG=DataObat.sat "
                            + "WHERE DataGudang.namaObatG IS NULL ORDER BY " + urut + "");
                    while(rsObat.next() == true) {
                        list.add(new setObat(rsObat.getString("namaObatG"), 
                                rsObat.getString("golObatG"), 
                                rsObat.getString("satG"), 
                                rsObat.getInt("jumlahSediaG"), 
                                rsObat.getDate("exdateG"), 
                                rsObat.getInt("jumlahSedia"), 
                                rsObat.getDate("exdate")));    
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            } else {
                try {
                    rsGudang = stmt1.executeQuery("SELECT * FROM DataGudang LEFT OUTER JOIN DataObat "
                            + "ON DataGudang.namaObatG=DataObat.namaObat "
                            + "AND DataGudang.golObatG=DataObat.golObat AND DataGudang.satG=DataObat.sat "
                            + "WHERE golObatG='" + cbCariGol.getSelectedItem().toString() 
                            + "' UNION SELECT * FROM DataGudang RIGHT OUTER JOIN DataObat "
                            + "ON DataGudang.namaObatG=DataObat.namaObat "
                            + "AND DataGudang.golObatG=DataObat.golObat AND DataGudang.satG=DataObat.sat "
                            + "WHERE DataGudang.namaObatG IS NULL AND golObatG='" + cbCariGol.getSelectedItem().toString() 
                            + "' ORDER BY " + urut + "");
                    while(rsGudang.next() == true) {
                        list.add(new setObat(rsObat.getString("namaObatG"), 
                                rsObat.getString("golObatG"), 
                                rsObat.getString("satG"), 
                                rsObat.getInt("jumlahSediaG"), 
                                rsObat.getDate("exdateG"), 
                                rsObat.getInt("jumlahSedia"), 
                                rsObat.getDate("exdate")));    
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        } else {
            if(cbCariGol.getSelectedItem().equals("--Tampilkan Semua--")) {
                try {
                    rsGudang = stmt1.executeQuery("SELECT * FROM DataGudang LEFT OUTER JOIN DataObat "
                            + "ON DataGudang.namaObatG=DataObat.namaObat "
                            + "AND DataGudang.golObatG=DataObat.golObat AND DataGudang.satG=DataObat.sat "
                            + "WHERE namaObatG='" + cbCariNama.getSelectedItem().toString()
                            + "' UNION "
                            + "SELECT * FROM DataGudang RIGHT OUTER JOIN DataObat ON DataGudang.namaObatG=DataObat.namaObat "
                            + "AND DataGudang.golObatG=DataObat.golObat AND DataGudang.satG=DataObat.sat "
                            + "WHERE DataGudang.namaObatG IS NULL AND namaObatG='" + cbCariNama.getSelectedItem().toString()
                            + "' ORDER BY " + urut + "");
                    while(rsGudang.next() == true) {
                        list.add(new setObat(rsObat.getString("namaObatG"), 
                                rsObat.getString("golObatG"), 
                                rsObat.getString("satG"), 
                                rsObat.getInt("jumlahSediaG"), 
                                rsObat.getDate("exdateG"), 
                                rsObat.getInt("jumlahSedia"), 
                                rsObat.getDate("exdate")));    
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            } else {
                try {
                    rsGudang = stmt1.executeQuery("SELECT * FROM DataGudang LEFT OUTER JOIN DataObat "
                            + "ON DataGudang.namaObatG=DataObat.namaObat "
                            + "AND DataGudang.golObatG=DataObat.golObat AND DataGudang.satG=DataObat.sat "
                            + "WHERE namaObatG='" + cbCariNama.getSelectedItem().toString()
                            + "' AND golObatG='" + cbCariGol.getSelectedItem().toString()
                            + "' UNION "
                            + "SELECT * FROM DataGudang RIGHT OUTER JOIN DataObat ON DataGudang.namaObatG=DataObat.namaObat "
                            + "AND DataGudang.golObatG=DataObat.golObat AND DataGudang.satG=DataObat.sat "
                            + "WHERE DataGudang.namaObatG IS NULL AND namaObatG='" + cbCariNama.getSelectedItem().toString()
                            + "' AND golObatG='" + cbCariGol.getSelectedItem().toString()
                            + "' ORDER BY " + urut + "");
                    while(rsGudang.next() == true) {
                        list.add(new setObat(rsObat.getString("namaObatG"), 
                                rsObat.getString("golObatG"), 
                                rsObat.getString("satG"), 
                                rsObat.getInt("jumlahSediaG"), 
                                rsObat.getDate("exdateG"), 
                                rsObat.getInt("jumlahSedia"), 
                                rsObat.getDate("exdate")));     
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }
        removeTable();
        updateTable();
    }//GEN-LAST:event_btnCariMouseClicked

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
            java.util.logging.Logger.getLogger(DataObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataObat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataObat().setVisible(true);
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
    private javax.swing.JLabel btnCari;
    private javax.swing.JLabel btnKeluar;
    private javax.swing.JComboBox<String> cbCariGol;
    private javax.swing.JComboBox<String> cbCariNama;
    private javax.swing.JComboBox<String> cbCariSort;
    private PanelTransparan.ClPanelTransparan clPanelTransparan1;
    private PanelTransparan.ClPanelTransparan clPanelTransparan3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEx;
    private javax.swing.JLabel txtTanggal;
    private javax.swing.JLabel txtWelcome;
    // End of variables declaration//GEN-END:variables
}