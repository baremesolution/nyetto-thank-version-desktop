package nyettotank2.view.newIHM;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import nyettotank2.metier.Line;
import nyettotank2.utilitaires.FormValidator;

public class DonneesVolumetriques extends javax.swing.JPanel {

    private static HashMap valueVolumetryData = new HashMap();
    private static HashMap<String, String> valueVolumetryInfo = new HashMap();
    private DefaultTableModel tableModel;
    private Line line;
    private static List<Line> data = new ArrayList<Line>();

    public DonneesVolumetriques() {
        initComponents();
        invalidFields.setText("");
        mainPanel.setLayout(new FlowLayout());
        mainPanel.removeAll();
        mainPanel.add(allPanel);

        FormValidator.setupRealTimeValidation(txtHauteur, invalidFields);
        FormValidator.setupRealTimeValidation(txtVolume, invalidFields);
        FormValidator.setupRealTimeValidation(fieldVolumeResiduel, invalidFields);
        FormValidator.setupRealTimeValidation(fieldDiametre, invalidFields);

        btnInsertDataIntoTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String volumeData = txtVolume.getText();
                String hauteurData = txtHauteur.getText();
                if (!volumeData.isEmpty() && !hauteurData.isEmpty()) {
                    float volume, hauteur = 0;
                    try {
                        volume = Float.parseFloat(volumeData);
                        hauteur = Float.parseFloat(hauteurData);
                    } catch (NumberFormatException n) {
                        return;
                    }

                    addDataInTable(volume, hauteur);
                    invalidFields.setText("");
                } else {
                    invalidFields.setText("Champs obligatoires");
                }
                txtVolume.setText("");
                txtHauteur.setText("");
            }

        });
        tableModel = (DefaultTableModel) tableau.getModel();
        tableModel.setRowCount(0); //supprime les lignes vides

        btnDeleteLine.setEnabled(false);
        btnDeleteLine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ligneSelectionne = tableau.getSelectedRow();
                if (ligneSelectionne != -1) {
                    data.remove(ligneSelectionne);
                    tableModel.removeRow(ligneSelectionne);

                }
            }
        });

        tableau.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    btnDeleteLine.setEnabled(tableau.getSelectedRow() != -1);
                }
            }
        });
    }

    public static HashMap getvalueVolumetryInfoGenerale() {
        return valueVolumetryInfo; //InfoGeneral
    }

    public static HashMap getvaluVolumetryData() {
        return valueVolumetryData;
    }

    public static List<Line> getData() {
        return data;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        allPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        labelVolume = new javax.swing.JLabel();
        txtVolume = new javax.swing.JTextField();
        labelHauteur = new javax.swing.JLabel();
        txtHauteur = new javax.swing.JTextField();
        btnInsertDataIntoTable = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableau = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        labelUniteVolume = new javax.swing.JLabel();
        comboUnitVolume = new javax.swing.JComboBox<>();
        labelFormsCapacity = new javax.swing.JLabel();
        comboFormeCapacity = new javax.swing.JComboBox<>();
        labelOriention = new javax.swing.JLabel();
        comboOrientation = new javax.swing.JComboBox<>();
        labelUniteHeight = new javax.swing.JLabel();
        comboUnitHeight = new javax.swing.JComboBox<>();
        labelVolumeResiduel = new javax.swing.JLabel();
        fieldVolumeResiduel = new javax.swing.JTextField();
        labelDiametre = new javax.swing.JLabel();
        fieldDiametre = new javax.swing.JTextField();
        enroll = new javax.swing.JButton();
        btnDeleteLine = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelModeOperatoire = new javax.swing.JLabel();
        comboMethodeValue = new javax.swing.JComboBox<>();
        invalidFields = new javax.swing.JLabel();

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        allPanel.setBackground(new java.awt.Color(204, 204, 204));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelVolume.setText("Volume ");

        txtVolume.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelHauteur.setText("Hauteur");

        txtHauteur.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelHauteur, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtVolume, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHauteur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHauteur, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHauteur, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btnInsertDataIntoTable.setBackground(new java.awt.Color(255, 255, 254));
        btnInsertDataIntoTable.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        btnInsertDataIntoTable.setText("Inserer dans le tableau");
        btnInsertDataIntoTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnInsertDataIntoTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertDataIntoTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnInsertDataIntoTable, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnInsertDataIntoTable, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        tableau.setBackground(new java.awt.Color(255, 255, 254));
        tableau.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Volume (unite de volume)", "Hauteur (Unite de hauteur)"
            }
        ));
        tableau.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tableau);

        jPanel2.setBackground(new java.awt.Color(199, 136, 86));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelUniteVolume.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        labelUniteVolume.setText("Unite de volume");

        comboUnitVolume.setBackground(new java.awt.Color(255, 255, 254));
        comboUnitVolume.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "litre", "m3", "baril", "gallon", "USA gallon", "IMPERIAL" }));

        labelFormsCapacity.setBackground(new java.awt.Color(255, 255, 255));
        labelFormsCapacity.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        labelFormsCapacity.setText("Forme capacité");

        comboFormeCapacity.setBackground(new java.awt.Color(255, 255, 254));
        comboFormeCapacity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cylindrique", "Spherique", "Elliptique", "Cubique" }));

        labelOriention.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        labelOriention.setText("Orientation");

        comboOrientation.setBackground(new java.awt.Color(255, 255, 254));
        comboOrientation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Horizontale", "Verticale", "Oblique" }));

        labelUniteHeight.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        labelUniteHeight.setText("Unite de hauteur");

        comboUnitHeight.setBackground(new java.awt.Color(255, 255, 254));
        comboUnitHeight.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "cm", "dm", "mm", "pied", "pouce", "m" }));

        labelVolumeResiduel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        labelVolumeResiduel.setText("Volume residuel");

        fieldVolumeResiduel.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelDiametre.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        labelDiametre.setText("Diametre");

        fieldDiametre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fieldDiametre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldDiametreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelUniteHeight, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelOriention, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelVolumeResiduel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelFormsCapacity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelUniteVolume, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                            .addComponent(comboFormeCapacity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboUnitVolume, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboOrientation, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(labelDiametre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboUnitHeight, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fieldVolumeResiduel, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldDiametre, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labelFormsCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboFormeCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelUniteVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboUnitVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelVolumeResiduel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldVolumeResiduel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelOriention, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboOrientation, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelUniteHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboUnitHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDiametre, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldDiametre, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        enroll.setBackground(new java.awt.Color(0, 102, 0));
        enroll.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        enroll.setForeground(new java.awt.Color(255, 255, 255));
        enroll.setText("Enregistrer");
        enroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrollActionPerformed(evt);
            }
        });

        btnDeleteLine.setBackground(new java.awt.Color(204, 102, 0));
        btnDeleteLine.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        btnDeleteLine.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteLine.setText("Supprimer une ligne");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelModeOperatoire.setText("Mode opératoire");

        comboMethodeValue.setBackground(new java.awt.Color(255, 255, 254));
        comboMethodeValue.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        comboMethodeValue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ISO 4269-1", "ISO 4269-2" }));
        comboMethodeValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMethodeValueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelModeOperatoire, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comboMethodeValue, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelModeOperatoire)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboMethodeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        invalidFields.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        invalidFields.setForeground(new java.awt.Color(204, 0, 51));
        invalidFields.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        invalidFields.setText("Champs invalides");

        javax.swing.GroupLayout allPanelLayout = new javax.swing.GroupLayout(allPanel);
        allPanel.setLayout(allPanelLayout);
        allPanelLayout.setHorizontalGroup(
            allPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(allPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(allPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(allPanelLayout.createSequentialGroup()
                        .addComponent(btnDeleteLine, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119)
                        .addComponent(invalidFields, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(allPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                        .addGap(9, 9, 9)))
                .addGroup(allPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enroll, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 15, Short.MAX_VALUE))
        );
        allPanelLayout.setVerticalGroup(
            allPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(allPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(allPanelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(allPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enroll, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteLine, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(invalidFields))
                .addContainerGap(44, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertDataIntoTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertDataIntoTableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInsertDataIntoTableActionPerformed

    private void fieldDiametreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldDiametreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldDiametreActionPerformed

    private void addDataInTable(double volumeData, float hauteurData) {
        Vector<Object> vector = new Vector<>();
        vector.add(volumeData);
        vector.add(hauteurData);
        line = new Line(volumeData, hauteurData);
        tableModel.addRow(vector);
        data.add(line);
    }

    private void enrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enrollActionPerformed
        saveDonneeVolumetrique();
    }//GEN-LAST:event_enrollActionPerformed

    private void comboMethodeValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMethodeValueActionPerformed

    }//GEN-LAST:event_comboMethodeValueActionPerformed

    private void saveDonneeVolumetrique() {
        valueVolumetryData.clear();

        valueVolumetryData.put("mode operatoire", comboMethodeValue.getSelectedItem().toString());

        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldDiametre.getText()).matches()) {
            valueVolumetryData.put("diametre", fieldDiametre.getText());
        } else {
            return;
        }

        if (fieldVolumeResiduel.getText().length() > 1) {
            valueVolumetryData.put("volume residuel", fieldVolumeResiduel.getText());
        } else {
            valueVolumetryData.put("volume residuel", "0");
            //return;
        }

        valueVolumetryInfo.clear();
        valueVolumetryInfo.put("unite de volume", comboUnitVolume.getSelectedItem().toString());
        valueVolumetryInfo.put("unite des hauteurs", comboUnitHeight.getSelectedItem().toString());
        valueVolumetryInfo.put("orientation", comboOrientation.getSelectedItem().toString());
        valueVolumetryInfo.put("forme capacite", comboFormeCapacity.getSelectedItem().toString());

        JOptionPane.showMessageDialog(null, "Vos données ont bien été pris en compte!.", "Succès", JOptionPane.INFORMATION_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel allPanel;
    private javax.swing.JButton btnDeleteLine;
    private javax.swing.JButton btnInsertDataIntoTable;
    private javax.swing.JComboBox<String> comboFormeCapacity;
    private javax.swing.JComboBox<String> comboMethodeValue;
    private javax.swing.JComboBox<String> comboOrientation;
    private javax.swing.JComboBox<String> comboUnitHeight;
    private javax.swing.JComboBox<String> comboUnitVolume;
    private javax.swing.JButton enroll;
    private javax.swing.JTextField fieldDiametre;
    private javax.swing.JTextField fieldVolumeResiduel;
    private javax.swing.JLabel invalidFields;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDiametre;
    private javax.swing.JLabel labelFormsCapacity;
    private javax.swing.JLabel labelHauteur;
    private javax.swing.JLabel labelModeOperatoire;
    private javax.swing.JLabel labelOriention;
    private javax.swing.JLabel labelUniteHeight;
    private javax.swing.JLabel labelUniteVolume;
    private javax.swing.JLabel labelVolume;
    private javax.swing.JLabel labelVolumeResiduel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTable tableau;
    private javax.swing.JTextField txtHauteur;
    private javax.swing.JTextField txtVolume;
    // End of variables declaration//GEN-END:variables

    public void traductionLabel() {
        Locale[] locale = {Locale.FRENCH, Locale.ENGLISH, Locale.GERMANY};
        ResourceBundle val_fr = ResourceBundle.getBundle("Bombe_fr", locale[0]);
        ResourceBundle val_en = ResourceBundle.getBundle("Bombe_en", locale[1]);
        ResourceBundle val_de = ResourceBundle.getBundle("Bombe_de", locale[2]);
        String chooseLanguage = MainView.getChoosenLanguage();
        ResourceBundle valeurs = null;

        if (chooseLanguage.equalsIgnoreCase("FRANCAIS") || chooseLanguage.equalsIgnoreCase("FRENCH") || chooseLanguage.equalsIgnoreCase("FRANZÖSISCH")) {
            valeurs = val_fr;
        } else if (chooseLanguage.equalsIgnoreCase("ANGLAIS") || chooseLanguage.equalsIgnoreCase("ENGLISH") || chooseLanguage.equalsIgnoreCase("ENGLISCH")) {
            valeurs = val_en;
        } else if (chooseLanguage.equalsIgnoreCase("ALLEMAND") || chooseLanguage.equalsIgnoreCase("GERMAN") || chooseLanguage.equalsIgnoreCase("DEUTSCH")) {
            valeurs = val_de;
        }

        labelUniteHeight.setText(valeurs.getString("unite_volume"));
        labelUniteHeight.setText(valeurs.getString("unite_longueur"));
        labelOriention.setText(valeurs.getString("orientation"));
        labelFormsCapacity.setText(valeurs.getString("forme_capacite"));
        labelDiametre.setText(valeurs.getString("diametre"));
        labelVolumeResiduel.setText(valeurs.getString("volume_residuel"));
        labelHauteur.setText(valeurs.getString("hauteur"));
        labelVolume.setText(valeurs.getString("volume"));
        labelModeOperatoire.setText(valeurs.getString("modeOperatoire"));

        comboUnitVolume.removeAllItems();
        for (int i = 1; i <= 6; i++) {
            String key = "uniteV" + i;
            String fondTraduit = valeurs.getString(key);
            comboUnitVolume.addItem(fondTraduit);
        }
        comboUnitHeight.removeAllItems();
        for (int i = 1; i <= 7; i++) {
            String key = "uniteVol" + i;
            String fondTraduit = valeurs.getString(key);
            comboUnitHeight.addItem(fondTraduit);
        }
        comboFormeCapacity.removeAllItems();
        for (int i = 1; i <= 5; i++) {
            String key = "formeCap" + i;
            String fondTraduit = valeurs.getString(key);
            comboFormeCapacity.addItem(fondTraduit);
        }

        comboOrientation.removeAllItems();
        for (int i = 1; i <= 3; i++) {
            String key = "orientationV" + i;
            String fondTraduit = valeurs.getString(key);
            comboOrientation.addItem(fondTraduit);
        }

    }

}
