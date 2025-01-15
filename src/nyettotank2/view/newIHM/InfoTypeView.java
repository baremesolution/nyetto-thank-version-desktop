package nyettotank2.view.newIHM;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import nyettotank2.model.InfoType;
import nyettotank2.utilitaires.ConfigureImageButton;

public class InfoTypeView extends javax.swing.JPanel {

    private InfoType infoType = new InfoType();
    private static InputStream logoImage;
    private static InputStream signatureImage;
    private static HashMap infoGenerale = new HashMap();
    Color DefaultColor, ClickedColor;

    public InfoTypeView() {
        initComponents();
        mainPanel.setLayout(new FlowLayout());
        mainPanel.removeAll();
        mainPanel.add(allPanel);

        DefaultColor = new Color(255, 255, 255);
        ClickedColor = new Color(242, 242, 242);
    }

    public static HashMap getInfoTypeData() {
        return infoGenerale;
    }

    public static InputStream getLogoInputStream() {
        return logoImage;
    }

    public static InputStream getSignatureInputStream() {
        return signatureImage;
    }

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

            chefOperation.setText(valeurs.getString("chefOperation"));
            lieuOperation.setText(valeurs.getString("lieuOperation"));
            detenteur.setText(valeurs.getString("detenteur"));
            adresseClient.setText(valeurs.getString("adresseClient"));
            agree.setText(valeurs.getString("agree"));
            btnAddLogo.setText(valeurs.getString("btnAddLogo"));
            btnAddsignature.setText(valeurs.getString("btnAddsignature"));
            btnActualiserInfoType.setText(valeurs.getString("btnActualiserInfoType"));
            validerButtonInfosType.setText(valeurs.getString("validerButtonInfosType"));

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        allPanel = new javax.swing.JPanel();
        datePanel = new javax.swing.JPanel();
        debutTravaux = new javax.swing.JLabel();
        debutTravauxValue = new datechooser.beans.DateChooserCombo();
        finTravaux = new javax.swing.JLabel();
        finTravauxValue = new datechooser.beans.DateChooserCombo();
        infoTypeData = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        chefOperation = new javax.swing.JLabel();
        chefOperationValue = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lieuOperation = new javax.swing.JLabel();
        lieuOperationValue = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        agreeValue = new javax.swing.JTextField();
        agree = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        adresseClient = new javax.swing.JLabel();
        adresseClientValue = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        detenteurValue = new javax.swing.JTextField();
        detenteur = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnActualiserInfoType = new javax.swing.JButton();
        validerButtonInfosType = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        btnAddLogo = new javax.swing.JButton();
        logoLabel = new javax.swing.JLabel();
        btnAddsignature = new javax.swing.JButton();
        signatureLabel = new javax.swing.JLabel();

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        allPanel.setBackground(new java.awt.Color(199, 136, 86));

        datePanel.setBackground(new java.awt.Color(204, 204, 204));
        datePanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));

        debutTravaux.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        debutTravaux.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        debutTravaux.setText("Début des travaux");

        debutTravauxValue.setCalendarBackground(new java.awt.Color(255, 255, 255));
        debutTravauxValue.setFieldFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));

        finTravaux.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        finTravaux.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        finTravaux.setText("Fin des travaux");

        finTravauxValue.setCalendarBackground(new java.awt.Color(255, 255, 255));
        finTravauxValue.setFieldFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));

        javax.swing.GroupLayout datePanelLayout = new javax.swing.GroupLayout(datePanel);
        datePanel.setLayout(datePanelLayout);
        datePanelLayout.setHorizontalGroup(
            datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(debutTravauxValue, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                    .addComponent(finTravauxValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(finTravaux, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(debutTravaux, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        datePanelLayout.setVerticalGroup(
            datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datePanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(debutTravaux)
                .addGap(18, 18, 18)
                .addComponent(debutTravauxValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(finTravaux)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(finTravauxValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        infoTypeData.setBackground(new java.awt.Color(255, 255, 255));
        infoTypeData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        chefOperation.setBackground(new java.awt.Color(0, 0, 0));
        chefOperation.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        chefOperation.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chefOperation.setText("Chef des opérations");

        chefOperationValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chefOperationValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chefOperationValueMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(chefOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chefOperationValue, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chefOperationValue, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chefOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        lieuOperation.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lieuOperation.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lieuOperation.setText("Lieu des opérations");

        lieuOperationValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lieuOperationValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lieuOperationValueMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lieuOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lieuOperationValue, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lieuOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lieuOperationValue, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });

        agreeValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        agreeValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agreeValueMouseClicked(evt);
            }
        });

        agree.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        agree.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        agree.setText("Agrée");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(agree, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(agreeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agreeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agree))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel4MousePressed(evt);
            }
        });

        adresseClient.setBackground(new java.awt.Color(255, 255, 255));
        adresseClient.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        adresseClient.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        adresseClient.setText("Adresse du demandeur");

        adresseClientValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        adresseClientValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adresseClientValueMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(adresseClient, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(adresseClientValue, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adresseClient)
                    .addComponent(adresseClientValue, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel5MousePressed(evt);
            }
        });

        detenteurValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        detenteurValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                detenteurValueMouseClicked(evt);
            }
        });

        detenteur.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        detenteur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        detenteur.setText("Détenteur");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(detenteur, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(detenteurValue, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(detenteurValue, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detenteur))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnActualiserInfoType.setBackground(new java.awt.Color(255, 153, 0));
        btnActualiserInfoType.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnActualiserInfoType.setForeground(new java.awt.Color(255, 255, 255));
        btnActualiserInfoType.setText("Actualiser");
        btnActualiserInfoType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualiserInfoTypeActionPerformed(evt);
            }
        });

        validerButtonInfosType.setBackground(new java.awt.Color(0, 153, 51));
        validerButtonInfosType.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        validerButtonInfosType.setForeground(new java.awt.Color(255, 255, 255));
        validerButtonInfosType.setText("Enregistrer");
        validerButtonInfosType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validerButtonInfosTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnActualiserInfoType, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(validerButtonInfosType, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualiserInfoType, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validerButtonInfosType, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout infoTypeDataLayout = new javax.swing.GroupLayout(infoTypeData);
        infoTypeData.setLayout(infoTypeDataLayout);
        infoTypeDataLayout.setHorizontalGroup(
            infoTypeDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        infoTypeDataLayout.setVerticalGroup(
            infoTypeDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoTypeDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnAddLogo.setBackground(new java.awt.Color(153, 153, 153));
        btnAddLogo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddLogo.setForeground(new java.awt.Color(255, 255, 255));
        btnAddLogo.setText("Ajouter un logo");
        btnAddLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddLogoMouseClicked(evt);
            }
        });
        btnAddLogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLogoActionPerformed(evt);
            }
        });

        logoLabel.setBackground(new java.awt.Color(255, 255, 255));
        logoLabel.setForeground(new java.awt.Color(255, 255, 255));
        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51)));
        logoLabel.setSize(200, 200);
        logoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoLabelMouseClicked(evt);
            }
        });

        btnAddsignature.setBackground(new java.awt.Color(153, 153, 153));
        btnAddsignature.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddsignature.setForeground(new java.awt.Color(255, 255, 255));
        btnAddsignature.setText("Ajouter une signature");
        btnAddsignature.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddsignatureMouseClicked(evt);
            }
        });
        btnAddsignature.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddsignatureActionPerformed(evt);
            }
        });

        signatureLabel.setBackground(new java.awt.Color(87, 75, 144));
        signatureLabel.setForeground(new java.awt.Color(255, 255, 255));
        signatureLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        signatureLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        signatureLabel.setSize(200, 200);
        signatureLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signatureLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(signatureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnAddsignature, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                            .addComponent(btnAddLogo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(logoLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnAddLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAddsignature, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(signatureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout allPanelLayout = new javax.swing.GroupLayout(allPanel);
        allPanel.setLayout(allPanelLayout);
        allPanelLayout.setHorizontalGroup(
            allPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoTypeData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        allPanelLayout.setVerticalGroup(
            allPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(allPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(infoTypeData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(datePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(allPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(allPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnAddLogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddLogoMouseClicked

    }//GEN-LAST:event_btnAddLogoMouseClicked

    private void btnAddLogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLogoActionPerformed
     ConfigureImageButton configureImageButton = new ConfigureImageButton();
    InputStream previousLogoImage = logoImage; // Save the previous image stream

    try {
        File logoFile = configureImageButton.showImage(btnAddLogo, logoLabel, 150, 150, logoLabel.getIcon() != null);
        if (logoFile.getAbsolutePath() != null) {
            System.out.println("Logo sélectionné: " + logoFile.getAbsolutePath());
            logoImage = logoFile.toURI().toURL().openStream();
            if (previousLogoImage != null) {
                previousLogoImage.close();
            }
        } else {
            System.out.println("Aucun logo sélectionné.");
        }
    } catch (IOException ex) {
        Logger.getLogger(InfoType.class.getName()).log(Level.SEVERE, "Erreur lors du chargement de l'image du logo", ex);
        showImageErrorDialog(ex.getMessage() + " - " + ex.getCause());
    }
    }//GEN-LAST:event_btnAddLogoActionPerformed

    private void logoLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoLabelMouseClicked
        //imageAction(evt, logoLabel);
    }//GEN-LAST:event_logoLabelMouseClicked

    private void btnAddsignatureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddsignatureMouseClicked

    }//GEN-LAST:event_btnAddsignatureMouseClicked

    private void btnAddsignatureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddsignatureActionPerformed
        ConfigureImageButton configureImageButton = new ConfigureImageButton();
        InputStream previousSignatureImage = signatureImage; // Sauvegarde de l'image précédente

        try {
            File signatureFile = configureImageButton.showImage(btnAddsignature, signatureLabel, 150, 150, signatureLabel.getIcon() != null);

            if (signatureFile != null) {
                if (previousSignatureImage != null) {
                    previousSignatureImage.close();
                }
                signatureImage = signatureFile.toURI().toURL().openStream();
            }
        } catch (IOException ex) {
            Logger.getLogger(InfoType.class.getName()).log(Level.SEVERE, "Erreur lors du chargement de l'image de la signature", ex);
            showImageErrorDialog("Erreur lors du chargement de l'image de la signature: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnAddsignatureActionPerformed

    private void signatureLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signatureLabelMouseClicked
        //imageAction(evt, signatureLabel);
    }//GEN-LAST:event_signatureLabelMouseClicked

    private void btnActualiserInfoTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualiserInfoTypeActionPerformed
        chefOperationValue.setText("");
        lieuOperationValue.setText("");
        detenteurValue.setText("");
        adresseClientValue.setText("");
        agreeValue.setText("");
        signatureLabel.setText("");
        logoLabel.setText("");
    }//GEN-LAST:event_btnActualiserInfoTypeActionPerformed

    private void validerButtonInfosTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validerButtonInfosTypeActionPerformed
        try {
            saveInfoType();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(InfoType.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_validerButtonInfosTypeActionPerformed

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {
        jPanel1.setBackground(ClickedColor);
        jPanel2.setBackground(DefaultColor);
        jPanel3.setBackground(DefaultColor);
        jPanel4.setBackground(DefaultColor);
        jPanel5.setBackground(DefaultColor);
    }

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {
        jPanel1.setBackground(DefaultColor);
        jPanel2.setBackground(ClickedColor);
        jPanel3.setBackground(DefaultColor);
        jPanel4.setBackground(DefaultColor);
        jPanel5.setBackground(DefaultColor);
    }

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {
        jPanel1.setBackground(DefaultColor);
        jPanel2.setBackground(DefaultColor);
        jPanel3.setBackground(ClickedColor);
        jPanel4.setBackground(DefaultColor);
        jPanel5.setBackground(DefaultColor);
    }

    private void jPanel4MousePressed(java.awt.event.MouseEvent evt) {
        jPanel1.setBackground(DefaultColor);
        jPanel2.setBackground(DefaultColor);
        jPanel3.setBackground(DefaultColor);
        jPanel4.setBackground(ClickedColor);
        jPanel5.setBackground(DefaultColor);
    }

    private void jPanel5MousePressed(java.awt.event.MouseEvent evt) {
        jPanel1.setBackground(DefaultColor);
        jPanel2.setBackground(DefaultColor);
        jPanel3.setBackground(DefaultColor);
        jPanel4.setBackground(DefaultColor);
        jPanel5.setBackground(ClickedColor);
    }

    private void jPanel6MousePressed(java.awt.event.MouseEvent evt) {

    }

    private void chefOperationValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chefOperationValueMouseClicked
        jPanel1.setBackground(ClickedColor);
        jPanel2.setBackground(DefaultColor);
        jPanel3.setBackground(DefaultColor);
        jPanel4.setBackground(DefaultColor);
        jPanel5.setBackground(DefaultColor);
    }//GEN-LAST:event_chefOperationValueMouseClicked

    private void lieuOperationValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lieuOperationValueMouseClicked
        jPanel1.setBackground(DefaultColor);
        jPanel2.setBackground(ClickedColor);
        jPanel3.setBackground(DefaultColor);
        jPanel4.setBackground(DefaultColor);
        jPanel5.setBackground(DefaultColor);
    }//GEN-LAST:event_lieuOperationValueMouseClicked

    private void agreeValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agreeValueMouseClicked
        jPanel1.setBackground(DefaultColor);
        jPanel2.setBackground(DefaultColor);
        jPanel3.setBackground(ClickedColor);
        jPanel4.setBackground(DefaultColor);
        jPanel5.setBackground(DefaultColor);
    }//GEN-LAST:event_agreeValueMouseClicked

    private void adresseClientValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adresseClientValueMouseClicked
        jPanel1.setBackground(DefaultColor);
        jPanel2.setBackground(DefaultColor);
        jPanel3.setBackground(DefaultColor);
        jPanel4.setBackground(ClickedColor);
        jPanel5.setBackground(DefaultColor);
    }//GEN-LAST:event_adresseClientValueMouseClicked

    private void detenteurValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detenteurValueMouseClicked
        jPanel1.setBackground(DefaultColor);
        jPanel2.setBackground(DefaultColor);
        jPanel3.setBackground(DefaultColor);
        jPanel4.setBackground(DefaultColor);
        jPanel5.setBackground(ClickedColor);
    }//GEN-LAST:event_detenteurValueMouseClicked

    public void saveInfoType() throws IOException {
        String chefOperation = chefOperationValue.getText().toString();
        String lieuOperation = lieuOperationValue.getText().toString();
        String detenteur = detenteurValue.getText().toString();
        String adresseClient = adresseClientValue.getText().toString();
        String agree = agreeValue.getText().toString();
        String debutTravaux = debutTravauxValue.getText().toString();
        String finTravaux = finTravauxValue.getText().toString();

        String signature = signatureLabel.getIcon() != null ? signatureLabel.getIcon().toString() : "";
        String logo = logoLabel.getIcon() != null ? logoLabel.getIcon().toString() : "";
        ConfigureImageButton configureImageButton = new ConfigureImageButton();

        // Vérifier si le logo existe déjà
//        File logoFile = null;
//        if (logoLabel.getIcon() != null) {
//            logoFile = configureImageButton.showImage(btnAddLogo, logoLabel, 150, 150, true);
//            if (logoFile != null) {
//                configureImageButton.saveImage(logoFile, logo);
//            }
//        }
//
//        File signatureFile = null;
//        if (signatureLabel.getIcon() != null) {
//            signatureFile = configureImageButton.showImage(btnAddsignature, signatureLabel, 150, 150, true);
//            if (signatureFile != null) {
//                configureImageButton.saveImage(signatureFile, signature);
//            }
//        }
        File logoFile = null;
        if (logoLabel.getIcon() != null) {
            //logoFile = configureImageButton.showImage(btnAddLogo, logoLabel, 150, 150, true);
        }

        File signatureFile = null;
        if (signatureLabel.getIcon() != null) {
            //signatureFile = configureImageButton.showImage(btnAddsignature, signatureLabel, 150, 150, true);
        }

        boolean isNotEmpty = !chefOperation.isEmpty() || !lieuOperation.isEmpty()
                || !detenteur.isEmpty() || !adresseClient.isEmpty() || !agree.isEmpty()
                || !signature.isEmpty() || !logo.isEmpty()
                || (logoFile != null) || (signatureFile != null);

        if (isNotEmpty) {
            if (logoFile != null) {
                configureImageButton.saveImage(logoFile, logo);
            }
            if (signatureFile != null) {
                configureImageButton.saveImage(signatureFile, signature);
            }
            infoType.setChefOperation(chefOperation);
            infoType.setLieuOperation(lieuOperation);
            infoType.setDetenteur(detenteur);
            infoType.setAdresseClient(adresseClient);
            infoType.setAgree(agree);
            infoType.setPhotoSignature(signature);
            infoType.setPhotoLogo(logo);
            infoType.setDateDebutTravaux(debutTravaux);
            infoType.setDateFinTravaux(finTravaux);
            if (chefOperationValue.getText().length() > 2) {
                infoGenerale.put("chef des operations", chefOperation);
            }
            if (lieuOperationValue.getText().length() > 2) {
                infoGenerale.put("lieu des operations", lieuOperation);
            }
            if (detenteurValue.getText().length() > 2) {
                infoGenerale.put("detenteur", detenteur);
            }
            if (adresseClientValue.getText().length() > 2) {
                infoGenerale.put("adresse demandeur", adresseClient);
            }
            if (agreeValue.getText().length() > 2) {
                infoGenerale.put("agree", agree);
            }
            infoGenerale.put("debut des travaux", debutTravaux);
            infoGenerale.put("fin des travaux", finTravaux);
            JOptionPane.showMessageDialog(null, "Vos données ont bien été pris en compte!.", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Aucune information n'a été renseignée.", "Succès", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    


    private void showImageErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adresseClient;
    private javax.swing.JTextField adresseClientValue;
    private javax.swing.JLabel agree;
    private javax.swing.JTextField agreeValue;
    private javax.swing.JPanel allPanel;
    private javax.swing.JButton btnActualiserInfoType;
    private javax.swing.JButton btnAddLogo;
    private javax.swing.JButton btnAddsignature;
    private javax.swing.JLabel chefOperation;
    private javax.swing.JTextField chefOperationValue;
    private javax.swing.JPanel datePanel;
    private javax.swing.JLabel debutTravaux;
    private datechooser.beans.DateChooserCombo debutTravauxValue;
    private javax.swing.JLabel detenteur;
    private javax.swing.JTextField detenteurValue;
    private javax.swing.JLabel finTravaux;
    private datechooser.beans.DateChooserCombo finTravauxValue;
    private javax.swing.JPanel infoTypeData;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lieuOperation;
    private javax.swing.JTextField lieuOperationValue;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel signatureLabel;
    private javax.swing.JButton validerButtonInfosType;
    // End of variables declaration//GEN-END:variables

}
