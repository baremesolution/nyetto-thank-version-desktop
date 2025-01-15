package nyettotank2.view.newIHM;

import java.awt.FlowLayout;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import nyettotank2.metier.BaremeArtisan;
import nyettotank2.metier.DataResumeDialog;
import nyettotank2.metier.Line;
import nyettotank2.metier.Volmetrie;
import nyettotank2.metier.WordFile;
import nyettotank2.metier.ZModel;

public class Traitement_1 extends javax.swing.JPanel {

    private JOptionPane option = new JOptionPane();
    private ZModel zModel = null;
    private JPanel jPanel7 = new JPanel();
    private JPanel panelFigure = new JPanel();
    private String typeDonnee = MainView.getTypeDonne();

    private DataResumeDialog dataResumeDialog = new DataResumeDialog(null, "recapitulatif des données saisies".toUpperCase(), true);
    private BaremeArtisan bareme = new BaremeArtisan();

    public Traitement_1() {
        initComponents();
        traductionLabel();
        btnDocumentWord.setVisible(false);
        mainPanel.setLayout(new FlowLayout());
        mainPanel.removeAll();
        mainPanel.add(allPanel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        allPanel = new javax.swing.JPanel();
        donneesTraitement = new javax.swing.JPanel();
        tableNom = new javax.swing.JLabel();
        tableNomValue = new javax.swing.JTextField();
        typeValeurVolume = new javax.swing.JLabel();
        typeVolumeValue = new javax.swing.JComboBox<>();
        nombreDivisions = new javax.swing.JLabel();
        nombreDivisionsValue = new javax.swing.JComboBox<>();
        certificatNomValue = new javax.swing.JTextField();
        certificatNom = new javax.swing.JLabel();
        listBoutons = new javax.swing.JPanel();
        propertiesButton = new javax.swing.JButton();
        height_volumeButton = new javax.swing.JButton();
        btnDocumentWord = new javax.swing.JButton();
        btnDocumentExcel = new javax.swing.JButton();
        btnDocumentPDF = new javax.swing.JButton();
        btnGenererCourbe = new javax.swing.JButton();
        resultats = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        resultatEnLitres = new javax.swing.JLabel();
        resultatEnLitresValue = new javax.swing.JTextField();
        resultatEnBarilValue = new javax.swing.JTextField();
        resultatEnBaril = new javax.swing.JLabel();
        resultatEnM3Value = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        resultatEnM3 = new javax.swing.JLabel();

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        allPanel.setBackground(new java.awt.Color(199, 136, 86));

        donneesTraitement.setBackground(new java.awt.Color(255, 255, 255));
        donneesTraitement.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableNom.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        tableNom.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tableNom.setText("Nom du fichier Table");

        tableNomValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tableNomValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableNomValueActionPerformed(evt);
            }
        });

        typeValeurVolume.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        typeValeurVolume.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        typeValeurVolume.setText("Type de valeur du Volume");

        typeVolumeValue.setBackground(new java.awt.Color(255, 255, 254));
        typeVolumeValue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VALEUR ENTIERE", "VALEUR FLOTTANTE" }));

        nombreDivisions.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        nombreDivisions.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nombreDivisions.setText("Nombre de Divisions");

        nombreDivisionsValue.setBackground(new java.awt.Color(255, 255, 254));
        nombreDivisionsValue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "45", "50", "60" }));

        certificatNomValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        certificatNomValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                certificatNomValueActionPerformed(evt);
            }
        });

        certificatNom.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        certificatNom.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        certificatNom.setText("Nom du fichier Certificat");

        javax.swing.GroupLayout donneesTraitementLayout = new javax.swing.GroupLayout(donneesTraitement);
        donneesTraitement.setLayout(donneesTraitementLayout);
        donneesTraitementLayout.setHorizontalGroup(
            donneesTraitementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(donneesTraitementLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(donneesTraitementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(donneesTraitementLayout.createSequentialGroup()
                        .addGroup(donneesTraitementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tableNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(typeValeurVolume, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(certificatNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(donneesTraitementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tableNomValue, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(typeVolumeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(certificatNomValue, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(donneesTraitementLayout.createSequentialGroup()
                        .addComponent(nombreDivisions, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nombreDivisionsValue, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addContainerGap())
        );
        donneesTraitementLayout.setVerticalGroup(
            donneesTraitementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, donneesTraitementLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(donneesTraitementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(certificatNom, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(certificatNomValue, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(donneesTraitementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tableNom, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tableNomValue, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(donneesTraitementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeValeurVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeVolumeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(donneesTraitementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreDivisionsValue, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreDivisions, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        listBoutons.setBackground(new java.awt.Color(51, 51, 51));

        propertiesButton.setBackground(new java.awt.Color(255, 255, 254));
        propertiesButton.setText("Properties");
        propertiesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                propertiesButtonMouseClicked(evt);
            }
        });
        propertiesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                propertiesButtonActionPerformed(evt);
            }
        });

        height_volumeButton.setBackground(new java.awt.Color(255, 255, 254));
        height_volumeButton.setText("Height to Volume");
        height_volumeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                height_volumeButtonMouseClicked(evt);
            }
        });
        height_volumeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                height_volumeButtonActionPerformed(evt);
            }
        });

        btnDocumentWord.setBackground(new java.awt.Color(255, 255, 254));
        btnDocumentWord.setText("Document WORD");
        btnDocumentWord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDocumentWordMouseClicked(evt);
            }
        });
        btnDocumentWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocumentWordActionPerformed(evt);
            }
        });

        btnDocumentExcel.setBackground(new java.awt.Color(255, 255, 254));
        btnDocumentExcel.setText("Document EXCEL");
        btnDocumentExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDocumentExcelMouseClicked(evt);
            }
        });
        btnDocumentExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocumentExcelActionPerformed(evt);
            }
        });

        btnDocumentPDF.setBackground(new java.awt.Color(255, 255, 254));
        btnDocumentPDF.setText("Document PDF");
        btnDocumentPDF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDocumentPDFMouseClicked(evt);
            }
        });
        btnDocumentPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocumentPDFActionPerformed(evt);
            }
        });

        btnGenererCourbe.setBackground(new java.awt.Color(255, 255, 254));
        btnGenererCourbe.setText("Générer courbe");
        btnGenererCourbe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGenererCourbeMouseClicked(evt);
            }
        });
        btnGenererCourbe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenererCourbeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout listBoutonsLayout = new javax.swing.GroupLayout(listBoutons);
        listBoutons.setLayout(listBoutonsLayout);
        listBoutonsLayout.setHorizontalGroup(
            listBoutonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listBoutonsLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(listBoutonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDocumentWord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDocumentPDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDocumentExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGenererCourbe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(height_volumeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(propertiesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        listBoutonsLayout.setVerticalGroup(
            listBoutonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listBoutonsLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(propertiesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(height_volumeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnGenererCourbe, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnDocumentExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnDocumentPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnDocumentWord, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        resultats.setBackground(new java.awt.Color(255, 255, 255));
        resultats.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RESULTATS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        resultatEnLitres.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        resultatEnLitres.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resultatEnLitres.setText("Resultat en litre");

        resultatEnLitresValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        resultatEnLitresValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultatEnLitresValueActionPerformed(evt);
            }
        });

        resultatEnBarilValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        resultatEnBaril.setBackground(new java.awt.Color(0, 102, 0));
        resultatEnBaril.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        resultatEnBaril.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resultatEnBaril.setText("Resultat en baril");

        resultatEnM3Value.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        resultatEnM3Value.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultatEnM3ValueActionPerformed(evt);
            }
        });

        resultatEnM3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        resultatEnM3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resultatEnM3.setText("Resultat en m3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(resultatEnLitresValue)
                    .addComponent(resultatEnLitres, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(resultatEnM3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resultatEnBaril, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resultatEnM3Value, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(resultatEnBarilValue, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resultatEnLitres, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resultatEnBaril)
                    .addComponent(resultatEnM3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(resultatEnLitresValue)
                    .addComponent(resultatEnBarilValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resultatEnM3Value))
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout resultatsLayout = new javax.swing.GroupLayout(resultats);
        resultats.setLayout(resultatsLayout);
        resultatsLayout.setHorizontalGroup(
            resultatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultatsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        resultatsLayout.setVerticalGroup(
            resultatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultatsLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout allPanelLayout = new javax.swing.GroupLayout(allPanel);
        allPanel.setLayout(allPanelLayout);
        allPanelLayout.setHorizontalGroup(
            allPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, allPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(allPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(resultats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(donneesTraitement, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(listBoutons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        allPanelLayout.setVerticalGroup(
            allPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(allPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(listBoutons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(allPanelLayout.createSequentialGroup()
                        .addComponent(donneesTraitement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(resultats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(allPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(allPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void propertiesButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_propertiesButtonMouseClicked

    }//GEN-LAST:event_propertiesButtonMouseClicked

    private void propertiesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_propertiesButtonActionPerformed

        String cle = MainView.getTypeDonne();

        if (cle.equalsIgnoreCase("Volumetrique")) {
            HashMap allVolumetrieProperties = getUnionInfoGeneraleTakeFromPanelVolumetryAndDonneeGeneraleVolumetry();
            HashMap<String, String> dataFieldVolumetry = getVolumetryDataField();
            dataResumeDialog.viewDataResumePropertiesDialog(allVolumetrieProperties, dataFieldVolumetry);

        } else if (cle.equalsIgnoreCase("Geometrique")) {
            HashMap allGeometrieProperties = getUnionInfoGeneralTakeFromPanelGeometryAndDonneesGeneraleGeometry();
            HashMap<String, String> dataFieldGeometry = getGeometryDataField();
            dataResumeDialog.viewDataResumePropertiesDialog(allGeometrieProperties, dataFieldGeometry);
        }

    }//GEN-LAST:event_propertiesButtonActionPerformed

    private void height_volumeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_height_volumeButtonMouseClicked

    }//GEN-LAST:event_height_volumeButtonMouseClicked

    private void height_volumeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_height_volumeButtonActionPerformed

        String cle = MainView.getTypeDonne();

        if (cle.equalsIgnoreCase("Volumetrique")) {

            HashMap<String, String> height_volume = getUnionInfoGeneraleTakeFromPanelVolumetryAndDonneeGeneraleVolumetry();
            HashMap<String, String> dataFieldVolumetry = getVolumetryDataField();
            List<Line> line = DonneesVolumetriques.getData();
            double lastLineVolume = line.get(line.size() - 1).getVolume();

            height_volume.put("nombre divisions", nombreDivisionsValue.getSelectedItem().toString());
            lastLineVolume = bareme.convertVolumeUnitCourentTocm(height_volume.get("unite de volume").toString(), lastLineVolume);

            resultatEnBarilValue.setText(String.valueOf(bareme.convertToVolumeDesired("baril", lastLineVolume)));
            resultatEnM3Value.setText(String.valueOf(bareme.convertToVolumeDesired("m3", lastLineVolume)));
            resultatEnLitresValue.setText(String.valueOf(lastLineVolume));

            dataResumeDialog.ViewTableHeightToVolumeVolumetrie(height_volume, dataFieldVolumetry, line);

        } else if (cle.equalsIgnoreCase("geometrique")) {

            HashMap allGeneralProperties = getUnionInfoGeneralTakeFromPanelGeometryAndDonneesGeneraleGeometry();
            HashMap dataFieldGeometry = getGeometryDataField();

            if (dataFieldGeometry.containsKey("longueur") || dataFieldGeometry.containsKey("diametre") || dataFieldGeometry.containsKey("rayon sphere") || dataFieldGeometry.containsKey("grand axe") || dataFieldGeometry.containsKey("petit axe")
                    || dataFieldGeometry.containsKey("fleche cote gauche") || dataFieldGeometry.containsKey("fleche cote droit")) {

                dataResumeDialog.ViewTableHeightToVolumeGeometrie(allGeneralProperties, dataFieldGeometry, resultatEnLitresValue, resultatEnBarilValue, resultatEnM3Value);
            } else {
                option.showMessageDialog(null, "veuillez renseiger vos chams en fonction de la forme de capacité precedemment choisie",
                        "Message d'erreur".toUpperCase(), JOptionPane.ERROR_MESSAGE);
            }

        }

    }//GEN-LAST:event_height_volumeButtonActionPerformed

    private void btnDocumentWordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDocumentWordMouseClicked

    }//GEN-LAST:event_btnDocumentWordMouseClicked

    private void btnDocumentWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocumentWordActionPerformed
        WordFile wordFile = new WordFile();

        String cle = MainView.getTypeDonne();

        if (cle.equalsIgnoreCase("Volumetrique")) {

            HashMap word = getUnionInfoGeneraleTakeFromPanelVolumetryAndDonneeGeneraleVolumetry();
            HashMap<String, String> dataFieldVolumetry = getVolumetryDataField();

            if (certificatNomValue.getText().length() > 2) {
                word.put("certificat", certificatNomValue.getText());
                try {
                    wordFile.createSimpleTable(word, dataFieldVolumetry);
                } catch (Exception ex) {
                    Logger.getLogger(Traitement.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                option.showMessageDialog(null, "veiller renseigner le nom du certificat avec au moins 3 caracteres".toUpperCase(),
                        "Message d'erreur".toUpperCase(), JOptionPane.ERROR_MESSAGE);
            }
        } else if (cle.equalsIgnoreCase("geometrique")) {

            HashMap word = getUnionInfoGeneralTakeFromPanelGeometryAndDonneesGeneraleGeometry();
            HashMap dataFieldGeometry = getGeometryDataField();

            if (certificatNomValue.getText().length() > 2) {
                word.put("certificat", certificatNomValue.getText());

                try {
                    wordFile.createSimpleTable(word, dataFieldGeometry);
                } catch (Exception ex) {
                    Logger.getLogger(Traitement.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                option.showMessageDialog(null, "veiller renseigner le nom du certificat avec au moins 3 caracteres".toUpperCase(),
                        "Message d'erreur".toUpperCase(), JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnDocumentWordActionPerformed

    private void btnDocumentExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDocumentExcelMouseClicked

    }//GEN-LAST:event_btnDocumentExcelMouseClicked

    private void btnDocumentExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocumentExcelActionPerformed
        String cle = MainView.getTypeDonne();

        if (cle.equalsIgnoreCase("Volumetrique")) {
            HashMap excel = getUnionInfoGeneraleTakeFromPanelVolumetryAndDonneeGeneraleVolumetry();
            HashMap<String, String> dataFieldVolumetry = getVolumetryDataField();
            Volmetrie vol = new Volmetrie();
            List<Line> myLine = DonneesVolumetriques.getData();

            if (tableNomValue.getText().length() > 2) {
                excel.put("table", tableNomValue.getText());
                excel.put("nombre divisions", nombreDivisionsValue.getSelectedItem().toString());
                String typeValue = typeVolumeValue.getSelectedItem().toString().equals("VALEUR ENTIERE") ? "entier" : "decimale";

                excel.put("type value", typeValue);

                if (excel.containsKey("nombre divisions")) {
                    vol.genererTableVolumetrie(excel, dataFieldVolumetry, myLine);
                } else {
                    option.showMessageDialog(null, "veiller renseigner la longueur, le diametre dans l'onglet donnée géométrique".toUpperCase(),
                            "Message d'erreur".toUpperCase(), JOptionPane.ERROR_MESSAGE);
                }
            } else {
                option.showMessageDialog(null, "veiller renseigner le nom de la table avec au moins 3 caracteres".toUpperCase(),
                        "Message d'erreur".toUpperCase(), JOptionPane.ERROR_MESSAGE);
            }

        } else if (cle.equalsIgnoreCase("geometrique")) {
            HashMap excel = getUnionInfoGeneralTakeFromPanelGeometryAndDonneesGeneraleGeometry();
            HashMap dataFieldGeometry = getGeometryDataField();

            if (tableNomValue.getText().length() > 2) {
                String type_val = typeVolumeValue.getSelectedItem().toString().equals("VALEUR ENTIERE") ? "entier" : "decimale";

                excel.put("table", tableNomValue.getText());
                excel.put("nombre divisions", nombreDivisionsValue.getSelectedItem().toString());
                excel.put("type value", type_val);

                if (excel.containsKey("longueur") || excel.containsKey("diametre") || excel.containsKey("nombre divisions") || dataFieldGeometry.containsKey("fleche cote gauche") || dataFieldGeometry.containsKey("fleche cote droit")) {
                    bareme.genererTableForGeometrie(excel, dataFieldGeometry);
                } else {
                    option.showMessageDialog(null, "veiller renseigner la longueur, le diametre dans l'onglet donnée géométrique".toUpperCase(),
                            "Message d'erreur".toUpperCase(), JOptionPane.ERROR_MESSAGE);
                }
            } else {
                option.showMessageDialog(null, "veiller renseigner le nom de la table avec au moins 3 caracteres".toUpperCase(),
                        "Message d'erreur".toUpperCase(), JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnDocumentExcelActionPerformed

    private void btnDocumentPDFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDocumentPDFMouseClicked

    }//GEN-LAST:event_btnDocumentPDFMouseClicked

    private void btnDocumentPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocumentPDFActionPerformed
        String cle = MainView.getTypeDonne();
        InputStream signatureStream = InfoTypeView.getSignatureInputStream();
         InputStream logoStream = InfoTypeView.getLogoInputStream();
        List<Float> abcisse = new ArrayList();
        List<Double> ordonne = new ArrayList();
        
        if (cle.equalsIgnoreCase("Volumetrique")) {
            HashMap pdf = getUnionInfoGeneraleTakeFromPanelVolumetryAndDonneeGeneraleVolumetry();
            HashMap<String, String> dataFieldVolumetry = getVolumetryDataField();

            List<Line> myLine = DonneesVolumetriques.getData();
            if (myLine.size() > 1) {
                for (int i = 0; i < myLine.size(); i++) {

                    abcisse.add(i, myLine.get(i).getHauteur());
                    ordonne.add(i, myLine.get(i).getVolume());
                }
            }

            abcisse.add(0, (float) 0.0);
            ordonne.add(0, 0d);

            if (certificatNomValue.getText().length() > 2) {
                pdf.put("certificat", certificatNomValue.getText());
                try {
                    bareme.certificat_with_head(pdf, dataFieldVolumetry, logoStream, signatureStream, "volumetrie", abcisse, ordonne);
                } catch (Exception ex) {
                    Logger.getLogger(Traitement.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                option.showMessageDialog(null, "veiller renseigner le nom du certificat avec au moins 3 caracteres".toUpperCase(),
                        "Message d'erreur".toUpperCase(), JOptionPane.ERROR_MESSAGE);
            }

        } else if (cle.equalsIgnoreCase("Geometrique")) {
            HashMap pdf = getUnionInfoGeneralTakeFromPanelGeometryAndDonneesGeneraleGeometry();
            HashMap dataFieldGeometry = getGeometryDataField();
            //InputStream logoImage = DonneesGeneralesGeometrique.getSignatureInputStream();
            if (certificatNomValue.getText().length() > 2) {
                pdf.put("certificat", certificatNomValue.getText());
                try {
                    bareme.certificat_with_head(pdf, dataFieldGeometry,logoStream, signatureStream, "geometrie", null, null);
                } catch (Exception ex) {
                    Logger.getLogger(Traitement.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                option.showMessageDialog(null, "veuillez renseigner le nom du certificat avec au moins 3 caractères".toUpperCase(),
                        "Message d'erreur".toUpperCase(), JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_btnDocumentPDFActionPerformed

    private void btnGenererCourbeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenererCourbeMouseClicked

    }//GEN-LAST:event_btnGenererCourbeMouseClicked

    private void btnGenererCourbeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenererCourbeActionPerformed
        List<Float> abcisse = new ArrayList();
        List<Double> ordonne = new ArrayList();
        String cle = MainView.getTypeDonne();
        if (cle.equalsIgnoreCase("Volumetrique")) {
            List<Line> myLine = DonneesVolumetriques.getData();
            for (int i = 0; i < myLine.size(); i++) {

                abcisse.add(i, myLine.get(i).getHauteur());
                ordonne.add(i, myLine.get(i).getVolume());
            }
            abcisse.add(0, (float) 0.0);
            ordonne.add(0, 0d);

            HashMap<String, String> allGeneralProperties = getUnionInfoGeneraleTakeFromPanelVolumetryAndDonneeGeneraleVolumetry();
            DataResumeDialog courbe = dataResumeDialog.AfficherCourbeVolumetrique(abcisse, ordonne, allGeneralProperties.get("unite des hauteurs").toString(), allGeneralProperties.get("unite de volume").toString());

            courbe.setVisible(true);

        } else if (cle.equalsIgnoreCase("geometrique")) {
            HashMap allGeneralProperties = getUnionInfoGeneralTakeFromPanelGeometryAndDonneesGeneraleGeometry();
            HashMap dataFieldGeometry = getGeometryDataField();

            DataResumeDialog courbe = dataResumeDialog.AfficherCourbeGeometrique(allGeneralProperties, dataFieldGeometry);
            courbe.setVisible(true);
        }


    }//GEN-LAST:event_btnGenererCourbeActionPerformed

    private void tableNomValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableNomValueActionPerformed

    }//GEN-LAST:event_tableNomValueActionPerformed

    private void certificatNomValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_certificatNomValueActionPerformed

    }//GEN-LAST:event_certificatNomValueActionPerformed

    private void resultatEnLitresValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultatEnLitresValueActionPerformed

    }//GEN-LAST:event_resultatEnLitresValueActionPerformed

    private void resultatEnM3ValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultatEnM3ValueActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_resultatEnM3ValueActionPerformed

    public HashMap getVolumetryDataField() {
        return DonneesVolumetriques.getvaluVolumetryData();
    }

    public HashMap getGeometryDataField() {
        return DonneeGeometrique.getValueGeometryData();
    }

    public HashMap getUnionInfoGeneraleTakeFromPanelVolumetryAndDonneeGeneraleVolumetry() {
        HashMap<String, String> infoGeneraleFromDonneeVolumetrque = DonneesVolumetriques.getvalueVolumetryInfoGenerale();
        HashMap<String, String> infoGeneraleFromInfoType = InfoTypeView.getInfoTypeData();
        HashMap<String, String> infoGeneraleFromInfoCapacite = InfoCapaciteView.getInfoCapaciteData();
        String typeDonne = MainView.getTypeDonne();
        String modeOperatoire = MainView.getChooseISO();
        HashMap<String, String> unionInfoGenerale = new HashMap();

        if (!modeOperatoire.isEmpty()) {
            unionInfoGenerale.put("mode operatoire", modeOperatoire);
        }

        if (!infoGeneraleFromInfoType.isEmpty()) {
            for (Map.Entry<String, String> entry : infoGeneraleFromInfoType.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                unionInfoGenerale.put(key, value + "");
            }
        }

        if (!infoGeneraleFromInfoCapacite.isEmpty()) {
            for (Map.Entry<String, String> entry : infoGeneraleFromInfoCapacite.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                unionInfoGenerale.put(key, value + "");
            }
        }

        if (!infoGeneraleFromDonneeVolumetrque.isEmpty()) {
            for (Map.Entry<String, String> entry : infoGeneraleFromDonneeVolumetrque.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                unionInfoGenerale.put(key, value + "");
            }
        }
        if (!typeDonne.isEmpty()) {
            unionInfoGenerale.put("type de donnée", typeDonne);
        }

        return unionInfoGenerale;
    }

    public HashMap getUnionInfoGeneralTakeFromPanelGeometryAndDonneesGeneraleGeometry() {
        HashMap<String, String> infoGeneralFromDonneesGeneralesGeometrique = DonneeGeometrique.getValueGeometryInfoGenerale();
        HashMap<String, String> infoGeneraleFromInfoType = InfoTypeView.getInfoTypeData();
        HashMap<String, String> infoGeneraleFromInfoCapacite = InfoCapaciteView.getInfoCapaciteData();
        String modeOperatoire = MainView.getChooseISO();
        String typeDonne = MainView.getTypeDonne();
        String methodCalcul = MainView.getMethodCalculGeometrie();
        HashMap<String, String> unionInfoGenerale = new HashMap();

        if (!modeOperatoire.isEmpty()) {
            unionInfoGenerale.put("mode operatoire", modeOperatoire);
        }

        if (!methodCalcul.isEmpty()) {
            unionInfoGenerale.put("methode de calcul", methodCalcul);
        }

        if (!infoGeneralFromDonneesGeneralesGeometrique.isEmpty()) {
            for (Map.Entry<String, String> entry : infoGeneralFromDonneesGeneralesGeometrique.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                unionInfoGenerale.put(key, value + "");
            }
        }
        if (!infoGeneraleFromInfoType.isEmpty()) {
            for (Map.Entry<String, String> entry : infoGeneraleFromInfoType.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                unionInfoGenerale.put(key, value + "");
            }
        }

        if (!infoGeneraleFromInfoCapacite.isEmpty()) {
            for (Map.Entry<String, String> entry : infoGeneraleFromInfoCapacite.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                unionInfoGenerale.put(key, value + "");
            }
        }
        if (!typeDonne.isEmpty()) {
            unionInfoGenerale.put("type de donnée", typeDonne);
        }

        return unionInfoGenerale;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel allPanel;
    private javax.swing.JButton btnDocumentExcel;
    private javax.swing.JButton btnDocumentPDF;
    private javax.swing.JButton btnDocumentWord;
    private javax.swing.JButton btnGenererCourbe;
    private javax.swing.JLabel certificatNom;
    private javax.swing.JTextField certificatNomValue;
    private javax.swing.JPanel donneesTraitement;
    private javax.swing.JButton height_volumeButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel listBoutons;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel nombreDivisions;
    private javax.swing.JComboBox<String> nombreDivisionsValue;
    private javax.swing.JButton propertiesButton;
    private javax.swing.JLabel resultatEnBaril;
    private javax.swing.JTextField resultatEnBarilValue;
    private javax.swing.JLabel resultatEnLitres;
    private javax.swing.JTextField resultatEnLitresValue;
    private javax.swing.JLabel resultatEnM3;
    private javax.swing.JTextField resultatEnM3Value;
    private javax.swing.JPanel resultats;
    private javax.swing.JLabel tableNom;
    private javax.swing.JTextField tableNomValue;
    private javax.swing.JLabel typeValeurVolume;
    private javax.swing.JComboBox<String> typeVolumeValue;
    // End of variables declaration//GEN-END:variables

    public void traductionLabel() {

        Locale[] locale = {Locale.FRENCH, Locale.ENGLISH, Locale.GERMANY};
        ResourceBundle val_fr = ResourceBundle.getBundle("Bombe_fr", locale[0]);
        ResourceBundle val_en = ResourceBundle.getBundle("Bombe_en", locale[1]);
        ResourceBundle val_de = ResourceBundle.getBundle("Bombe_de", locale[2]);
        String chooseLanguage = MainView.getChoosenLanguage();

        ResourceBundle valeurs = null;
        if (chooseLanguage.equalsIgnoreCase("FRANCAIS") || chooseLanguage.equalsIgnoreCase("FRENCH")) {
            valeurs = val_fr;
        } else if (chooseLanguage.equalsIgnoreCase("ANGLAIS") || chooseLanguage.equalsIgnoreCase("ENGLISH")) {
            valeurs = val_en;
        } else if (chooseLanguage.equalsIgnoreCase("ALLEMAND") || chooseLanguage.equalsIgnoreCase("GERMAN")) {
            valeurs = val_de;
        }
        if (valeurs != null) {
            certificatNom.setText(valeurs.getString("certificatNom"));
            typeValeurVolume.setText(valeurs.getString("typeValeurVolume"));
            tableNom.setText(valeurs.getString("tableNom"));
            nombreDivisions.setText(valeurs.getString("nombreDivisions"));
            resultatEnLitres.setText(valeurs.getString("resultatEnLitres"));
            resultatEnBaril.setText(valeurs.getString("resultatEnBaril"));
            resultatEnM3.setText(valeurs.getString("resultatEnM3"));
            resultatEnLitres.setText(valeurs.getString("resultatEnLitres"));
            propertiesButton.setText(valeurs.getString("propertiesButton"));
            height_volumeButton.setText(valeurs.getString("height_volumeButton"));
            btnGenererCourbe.setText(valeurs.getString("btnGenererCourbe"));
            btnDocumentExcel.setText(valeurs.getString("btnDocumentExcel"));
            btnDocumentPDF.setText(valeurs.getString("btnDocumentPDF"));
            btnDocumentWord.setText(valeurs.getString("btnDocumentWord"));

            typeVolumeValue.removeAllItems();
            for (int i = 1; i <= 2; i++) {
                String typeV = "typeValVolume" + i;
                String typeVTraduit = valeurs.getString(typeV);
                typeVolumeValue.addItem(typeVTraduit);
            }
        }
    }

}
