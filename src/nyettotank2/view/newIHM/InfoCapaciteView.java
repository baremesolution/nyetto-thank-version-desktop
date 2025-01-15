package nyettotank2.view.newIHM;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import nyettotank2.model.InfoCapacite;
import nyettotank2.utilitaires.FormValidator;

public class InfoCapaciteView extends javax.swing.JPanel {

    private InfoCapacite infoCapacite = new InfoCapacite();
    private static HashMap infoGenerale = new HashMap();
    Color DefaultColor, ClickedColor;

    public InfoCapaciteView() {
        initComponents();   
        invalidFields.setBackground(Color.white);
        invalidFields.setForeground(Color.white);     
        FormValidator.setupRealTimeValidation(nombreDeCompartimentsValue, invalidFields);
        FormValidator.setupRealTimeValidation(volumeNominalValue, invalidFields);
        FormValidator.setupRealTimeValidation(hauteurTemoinValue, invalidFields);
        FormValidator.setupRealTimeValidation(anneeFabricationValue, invalidFields);

        mainPanel.setLayout(new FlowLayout());
        mainPanel.removeAll();
        mainPanel.add(allPanel);

        DefaultColor = new Color(255, 255, 255);
        ClickedColor = new Color(242, 242, 242);
        

    }

    public static HashMap getInfoCapaciteData() {
        return infoGenerale;
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
            numeroSerie.setText(valeurs.getString("numeroSerie"));
            nomCapacite.setText(valeurs.getString("nomCapacite"));
            produitStocke.setText(valeurs.getString("produitStocke"));
            volumeNominal.setText(valeurs.getString("volumeNominal"));
            nombreDeCompartiments.setText(valeurs.getString("nombreDeCompartiments"));
            fondCapacite.setText(valeurs.getString("fondCapacite"));
            typeCapacite.setText(valeurs.getString("typeCapacite"));
            etancheite.setText(valeurs.getString("etancheite"));
            btnActualiserCapacite.setText(valeurs.getString("btnActualiserCapacite"));
            suivantButtonCapacite.setText(valeurs.getString("suivantButtonCapacite"));
            invalidFields.setText(valeurs.getString("invalidFields"));
            hauteurTemoin.setText(valeurs.getString("hauteurTemoin"));
            fabricant.setText(valeurs.getString("fabricant"));
            anneeFabrication.setText(valeurs.getString("anneeFabrication"));

            fondCapaciteValue.removeAllItems();
            for (int i = 1; i <= 4; i++) {
                String fondKey = "fond" + i;
                String fondTraduit = valeurs.getString(fondKey);
                fondCapaciteValue.addItem(fondTraduit);
            }
            typeCapaciteValue.removeAllItems();
            for (int i = 1; i <= 7; i++) {
                String typeKey = "type" + i;
                String typeTraduit = valeurs.getString(typeKey);
                typeCapaciteValue.addItem(typeTraduit);
            }

            etancheiteValue.removeAllItems();
            for (int i = 1; i <= 2; i++) {
                String etancheiteKey = "etancheite" + i;
                String etancheiteTraduit = valeurs.getString(etancheiteKey);
                etancheiteValue.addItem(etancheiteTraduit);
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        allPanel = new javax.swing.JPanel();
        datePanel = new javax.swing.JPanel();
        fondCapacite = new javax.swing.JLabel();
        fondCapaciteValue = new javax.swing.JComboBox<>();
        typeCapacite = new javax.swing.JLabel();
        typeCapaciteValue = new javax.swing.JComboBox<>();
        etancheite = new javax.swing.JLabel();
        etancheiteValue = new javax.swing.JComboBox<>();
        capacityData = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        nomCapacite = new javax.swing.JLabel();
        nomCapaciteValue = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        produitStocke = new javax.swing.JLabel();
        produitStockeValue = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        volumeNominal = new javax.swing.JLabel();
        volumeNominalValue = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        hauteurTemoin = new javax.swing.JLabel();
        hauteurTemoinValue = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        nombreDeCompartiments = new javax.swing.JLabel();
        nombreDeCompartimentsValue = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        fabricant = new javax.swing.JLabel();
        fabricantValue = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        anneeFabrication = new javax.swing.JLabel();
        anneeFabricationValue = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        numeroSerie = new javax.swing.JLabel();
        numeroSerieValue1 = new javax.swing.JTextField();
        btnActualiserCapacite = new javax.swing.JButton();
        suivantButtonCapacite = new javax.swing.JButton();
        invalidFields = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        allPanel.setBackground(new java.awt.Color(199, 136, 86));

        datePanel.setBackground(new java.awt.Color(204, 204, 204));
        datePanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));

        fondCapacite.setBackground(new java.awt.Color(0, 0, 0));
        fondCapacite.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        fondCapacite.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fondCapacite.setText("Fond de la capacité ");

        fondCapaciteValue.setBackground(new java.awt.Color(255, 255, 254));
        fondCapaciteValue.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        fondCapaciteValue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Conique", "Convexe", "Plat", "Concave" }));
        fondCapaciteValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fondCapaciteValueMouseClicked(evt);
            }
        });
        fondCapaciteValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fondCapaciteValueActionPerformed(evt);
            }
        });

        typeCapacite.setBackground(new java.awt.Color(255, 255, 255));
        typeCapacite.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        typeCapacite.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        typeCapacite.setText("Type de la capacite");

        typeCapaciteValue.setBackground(new java.awt.Color(255, 255, 254));
        typeCapaciteValue.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        typeCapaciteValue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bac de stockage", "Cubi", "Cigare", "Citerne", "Cuve", "Sphère", "Sylos" }));
        typeCapaciteValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                typeCapaciteValueMouseClicked(evt);
            }
        });
        typeCapaciteValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeCapaciteValueActionPerformed(evt);
            }
        });

        etancheite.setBackground(new java.awt.Color(255, 255, 255));
        etancheite.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etancheite.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etancheite.setText("Etanchéité");

        etancheiteValue.setBackground(new java.awt.Color(255, 255, 254));
        etancheiteValue.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        etancheiteValue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bonne", "Mauvaise" }));
        etancheiteValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                etancheiteValueMouseClicked(evt);
            }
        });
        etancheiteValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etancheiteValueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout datePanelLayout = new javax.swing.GroupLayout(datePanel);
        datePanel.setLayout(datePanelLayout);
        datePanelLayout.setHorizontalGroup(
            datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fondCapaciteValue, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(typeCapacite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fondCapacite, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(typeCapaciteValue, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etancheiteValue, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etancheite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        datePanelLayout.setVerticalGroup(
            datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fondCapacite, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fondCapaciteValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(typeCapacite, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(typeCapaciteValue, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(etancheite, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etancheiteValue, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        capacityData.setBackground(new java.awt.Color(255, 255, 255));
        capacityData.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        nomCapacite.setBackground(new java.awt.Color(0, 0, 0));
        nomCapacite.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        nomCapacite.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nomCapacite.setText("Nom de la capacité");

        nomCapaciteValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nomCapaciteValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nomCapaciteValueMouseClicked(evt);
            }
        });
        nomCapaciteValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomCapaciteValueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nomCapacite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(nomCapaciteValue, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomCapaciteValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomCapacite))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(637, 42));

        produitStocke.setBackground(new java.awt.Color(0, 0, 0));
        produitStocke.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        produitStocke.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        produitStocke.setText("Produit Stocké");

        produitStockeValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        produitStockeValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                produitStockeValueMouseClicked(evt);
            }
        });
        produitStockeValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produitStockeValueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(produitStocke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(produitStockeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(produitStockeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(produitStocke))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(591, 42));

        volumeNominal.setBackground(new java.awt.Color(0, 0, 0));
        volumeNominal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        volumeNominal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        volumeNominal.setText("Volume nominal");

        volumeNominalValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        volumeNominalValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                volumeNominalValueMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(volumeNominal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(volumeNominalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(volumeNominalValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volumeNominal))
                .addGap(8, 8, 8))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(607, 42));

        hauteurTemoin.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        hauteurTemoin.setText("Hauteur  Total Temoin");

        hauteurTemoinValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        hauteurTemoinValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hauteurTemoinValueMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hauteurTemoin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(hauteurTemoinValue, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hauteurTemoinValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hauteurTemoin))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(623, 42));

        nombreDeCompartiments.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        nombreDeCompartiments.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nombreDeCompartiments.setText("Nombre de compartiments");

        nombreDeCompartimentsValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombreDeCompartimentsValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nombreDeCompartimentsValueMouseClicked(evt);
            }
        });
        nombreDeCompartimentsValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreDeCompartimentsValueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombreDeCompartiments, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(nombreDeCompartimentsValue, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreDeCompartimentsValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreDeCompartiments))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(680, 42));

        fabricant.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        fabricant.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        fabricant.setText("Fabricant");

        fabricantValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fabricantValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fabricantValueMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fabricantValueMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fabricant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(fabricantValue, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fabricant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fabricantValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        anneeFabrication.setBackground(new java.awt.Color(204, 204, 204));
        anneeFabrication.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        anneeFabrication.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        anneeFabrication.setText("Année de fabrication");

        anneeFabricationValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        anneeFabricationValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                anneeFabricationValueMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(anneeFabrication, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(anneeFabricationValue, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(anneeFabrication)
                    .addComponent(anneeFabricationValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        numeroSerie.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        numeroSerie.setText("Numéro de serie");

        numeroSerieValue1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        numeroSerieValue1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                numeroSerieValue1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numeroSerie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(numeroSerieValue1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeroSerieValue1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroSerie))
                .addContainerGap())
        );

        btnActualiserCapacite.setBackground(new java.awt.Color(255, 153, 0));
        btnActualiserCapacite.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        btnActualiserCapacite.setForeground(new java.awt.Color(255, 255, 255));
        btnActualiserCapacite.setText("Actualiser");
        btnActualiserCapacite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualiserCapaciteActionPerformed(evt);
            }
        });

        suivantButtonCapacite.setBackground(new java.awt.Color(0, 153, 51));
        suivantButtonCapacite.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        suivantButtonCapacite.setForeground(new java.awt.Color(255, 255, 255));
        suivantButtonCapacite.setText("Enregistrer");
        suivantButtonCapacite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suivantButtonCapaciteActionPerformed(evt);
            }
        });

        invalidFields.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        invalidFields.setForeground(new java.awt.Color(204, 0, 51));
        invalidFields.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        invalidFields.setText("Champs invalides");
        invalidFields.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                invalidFieldsComponentHidden(evt);
            }
        });

        javax.swing.GroupLayout capacityDataLayout = new javax.swing.GroupLayout(capacityData);
        capacityData.setLayout(capacityDataLayout);
        capacityDataLayout.setHorizontalGroup(
            capacityDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(capacityDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnActualiserCapacite, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(suivantButtonCapacite, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(invalidFields, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        capacityDataLayout.setVerticalGroup(
            capacityDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(capacityDataLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(invalidFields)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(capacityDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualiserCapacite, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suivantButtonCapacite, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout allPanelLayout = new javax.swing.GroupLayout(allPanel);
        allPanel.setLayout(allPanelLayout);
        allPanelLayout.setHorizontalGroup(
            allPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(capacityData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        allPanelLayout.setVerticalGroup(
            allPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(allPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(capacityData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(allPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nombreDeCompartimentsValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreDeCompartimentsValueActionPerformed

    }//GEN-LAST:event_nombreDeCompartimentsValueActionPerformed

    private void etancheiteValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etancheiteValueActionPerformed

    }//GEN-LAST:event_etancheiteValueActionPerformed

    private void fondCapaciteValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fondCapaciteValueActionPerformed

    }//GEN-LAST:event_fondCapaciteValueActionPerformed

    private void btnActualiserCapaciteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualiserCapaciteActionPerformed
        nomCapaciteValue.setText("");
        fabricantValue.setText("");
        produitStockeValue.setText("");
        volumeNominalValue.setText("");
        hauteurTemoin.setText("");
        nombreDeCompartimentsValue.setText("");
        fondCapaciteValue.setSelectedIndex(0);
        anneeFabricationValue.setText("");
        typeCapaciteValue.setSelectedIndex(0);
        etancheiteValue.setSelectedIndex(0);
    }//GEN-LAST:event_btnActualiserCapaciteActionPerformed

    private void suivantButtonCapaciteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suivantButtonCapaciteActionPerformed
        try {
            saveCapacite();
        } catch (IOException ex) {
            Logger.getLogger(InfoCapacite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_suivantButtonCapaciteActionPerformed

    private void typeCapaciteValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeCapaciteValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeCapaciteValueActionPerformed

    private void numeroSerieValue1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_numeroSerieValue1MouseClicked
        jPanel1.setBackground(DefaultColor);
        jPanel2.setBackground(DefaultColor);
        jPanel3.setBackground(DefaultColor);
        jPanel4.setBackground(DefaultColor);
        jPanel5.setBackground(DefaultColor);
        jPanel6.setBackground(DefaultColor);
        jPanel7.setBackground(DefaultColor);
        jPanel8.setBackground(DefaultColor);
        jPanel8.setBackground(ClickedColor);
    }//GEN-LAST:event_numeroSerieValue1MouseClicked

    private void produitStockeValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produitStockeValueMouseClicked
        jPanel1.setBackground(DefaultColor);
        jPanel2.setBackground(ClickedColor);
        jPanel3.setBackground(DefaultColor);
        jPanel4.setBackground(DefaultColor);
        jPanel5.setBackground(DefaultColor);
        jPanel6.setBackground(DefaultColor);
        jPanel7.setBackground(DefaultColor);
        jPanel8.setBackground(DefaultColor);
        jPanel8.setBackground(DefaultColor);
    }//GEN-LAST:event_produitStockeValueMouseClicked

    private void volumeNominalValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumeNominalValueMouseClicked
        jPanel1.setBackground(DefaultColor);
        jPanel2.setBackground(DefaultColor);
        jPanel3.setBackground(ClickedColor);
        jPanel4.setBackground(DefaultColor);
        jPanel5.setBackground(DefaultColor);
        jPanel6.setBackground(DefaultColor);
        jPanel7.setBackground(DefaultColor);
        jPanel8.setBackground(DefaultColor);
        jPanel8.setBackground(DefaultColor);
    }//GEN-LAST:event_volumeNominalValueMouseClicked

    private void hauteurTemoinValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hauteurTemoinValueMouseClicked
        jPanel1.setBackground(DefaultColor);
        jPanel2.setBackground(DefaultColor);
        jPanel3.setBackground(DefaultColor);
        jPanel4.setBackground(ClickedColor);
        jPanel5.setBackground(DefaultColor);
        jPanel6.setBackground(DefaultColor);
        jPanel7.setBackground(DefaultColor);
        jPanel8.setBackground(DefaultColor);
        jPanel8.setBackground(DefaultColor);
    }//GEN-LAST:event_hauteurTemoinValueMouseClicked

    private void nombreDeCompartimentsValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nombreDeCompartimentsValueMouseClicked
        jPanel1.setBackground(DefaultColor);
        jPanel2.setBackground(DefaultColor);
        jPanel3.setBackground(DefaultColor);
        jPanel4.setBackground(DefaultColor);
        jPanel5.setBackground(ClickedColor);
        jPanel6.setBackground(DefaultColor);
        jPanel7.setBackground(DefaultColor);
        jPanel8.setBackground(DefaultColor);
        jPanel8.setBackground(DefaultColor);
    }//GEN-LAST:event_nombreDeCompartimentsValueMouseClicked

    private void fondCapaciteValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fondCapaciteValueMouseClicked
        jPanel1.setBackground(DefaultColor);
        jPanel2.setBackground(DefaultColor);
        jPanel3.setBackground(DefaultColor);
        jPanel4.setBackground(DefaultColor);
        jPanel5.setBackground(DefaultColor);
        jPanel6.setBackground(ClickedColor);
        jPanel7.setBackground(DefaultColor);
        jPanel8.setBackground(DefaultColor);
        jPanel8.setBackground(DefaultColor);
    }//GEN-LAST:event_fondCapaciteValueMouseClicked

    private void typeCapaciteValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_typeCapaciteValueMouseClicked
        jPanel1.setBackground(DefaultColor);
        jPanel2.setBackground(DefaultColor);
        jPanel3.setBackground(DefaultColor);
        jPanel4.setBackground(DefaultColor);
        jPanel5.setBackground(DefaultColor);
        jPanel6.setBackground(DefaultColor);
        jPanel7.setBackground(ClickedColor);
        jPanel8.setBackground(DefaultColor);
        jPanel8.setBackground(DefaultColor);
    }//GEN-LAST:event_typeCapaciteValueMouseClicked

    private void etancheiteValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_etancheiteValueMouseClicked
        jPanel1.setBackground(DefaultColor);
        jPanel2.setBackground(DefaultColor);
        jPanel3.setBackground(DefaultColor);
        jPanel4.setBackground(DefaultColor);
        jPanel5.setBackground(DefaultColor);
        jPanel6.setBackground(DefaultColor);
        jPanel7.setBackground(DefaultColor);
        jPanel8.setBackground(DefaultColor);
        jPanel8.setBackground(DefaultColor);
    }//GEN-LAST:event_etancheiteValueMouseClicked

    private void nomCapaciteValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nomCapaciteValueMouseClicked
        jPanel1.setBackground(ClickedColor);
        jPanel2.setBackground(DefaultColor);
        jPanel3.setBackground(DefaultColor);
        jPanel4.setBackground(DefaultColor);
        jPanel5.setBackground(DefaultColor);
        jPanel6.setBackground(DefaultColor);
        jPanel7.setBackground(DefaultColor);
        jPanel8.setBackground(DefaultColor);
        jPanel8.setBackground(DefaultColor);
    }//GEN-LAST:event_nomCapaciteValueMouseClicked

    private void fabricantValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fabricantValueMouseClicked
        jPanel1.setBackground(DefaultColor);
        jPanel2.setBackground(DefaultColor);
        jPanel3.setBackground(DefaultColor);
        jPanel4.setBackground(DefaultColor);
        jPanel5.setBackground(DefaultColor);
        jPanel6.setBackground(ClickedColor);
        jPanel7.setBackground(DefaultColor);
    }//GEN-LAST:event_fabricantValueMouseClicked

    private void fabricantValueMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fabricantValueMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_fabricantValueMouseExited

    private void anneeFabricationValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anneeFabricationValueMouseClicked
        jPanel1.setBackground(DefaultColor);
        jPanel2.setBackground(DefaultColor);
        jPanel3.setBackground(DefaultColor);
        jPanel4.setBackground(DefaultColor);
        jPanel5.setBackground(DefaultColor);
        jPanel6.setBackground(DefaultColor);
        jPanel7.setBackground(ClickedColor);
    }//GEN-LAST:event_anneeFabricationValueMouseClicked

    private void nomCapaciteValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomCapaciteValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomCapaciteValueActionPerformed

    private void produitStockeValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produitStockeValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_produitStockeValueActionPerformed

    private void invalidFieldsComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_invalidFieldsComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_invalidFieldsComponentHidden

    void saveCapacite() throws IOException {
        Date date = new Date();
        String nomCapacite = nomCapaciteValue.getText().toString();
        String numeroSerie = numeroSerieValue1.getText().toString();
        String produitStocke = produitStockeValue.getText().toString();
        String volumeNominal = volumeNominalValue.getText().toString();
        String hauteurTemoin = hauteurTemoinValue.getText();
        String nombreCompartiments = nombreDeCompartimentsValue.getText().toString();
        String fondCapacite = fondCapaciteValue.getSelectedItem().toString();
        String typeCapacite = typeCapaciteValue.getSelectedItem().toString();
        String etancheite = etancheiteValue.getSelectedItem().toString();
        String anneeFabrication = anneeFabricationValue.getText().toString();
        String fabricant = fabricantValue.getText().toString();

        int compartiments = 0;
        if (!nombreCompartiments.isEmpty()) {
            try {
                compartiments = Integer.parseInt(nombreCompartiments);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Une erreur s'est produite. Veuillez vérifier vos champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        int annee_fabrication = 0;
        if (!anneeFabrication.isEmpty()) {
            try {
                annee_fabrication = Integer.parseInt(anneeFabrication);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Une erreur s'est produite. Veuillez vérifier le champs annee  de fabrication", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        float volume = 0;
        if (!volumeNominal.isEmpty()) {
            try {
                volume = Float.parseFloat(volumeNominal);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Une erreur s'est produite. Veuillez vérifier vos champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        float hauteur_temoin = 0;
        if (!hauteurTemoin.isEmpty()) {
            try {
                hauteur_temoin = Float.parseFloat(hauteurTemoin);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Une erreur s'est produite. Veuillez vérifier le champs hauteur temoin", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        infoCapacite.setNomCapacite(nomCapacite);
        infoCapacite.setNumeroSerieCapacite(numeroSerie);
        infoCapacite.setProduitStockeCapacite(produitStocke);
        infoCapacite.setVolumeNominalCapacite(volume);
        infoCapacite.setHauteurTemoin(hauteur_temoin);
        infoCapacite.setNombreCompartiment(compartiments);
        infoCapacite.setFondCapacite(fondCapacite);
        infoCapacite.setFabricant(fabricant);
        infoCapacite.setAnneeFabrication(annee_fabrication);
        infoCapacite.setTypeCapacite(typeCapacite);
        infoCapacite.setEtancheite(etancheite);

        infoGenerale.put("fond capacite", fondCapacite);

        infoGenerale.put("type de la capacite", typeCapacite);
        infoGenerale.put("etancheite", etancheite);
        infoGenerale.put("hauteur temoin", hauteur_temoin);

        if (nomCapaciteValue.getText().length() > 2) {
            infoGenerale.put("nom de la capacite", nomCapacite);
        }
        if (numeroSerieValue1.getText().length() > 2) {
            infoGenerale.put("numero serie", numeroSerie);
        }
        if (produitStockeValue.getText().length() > 2) {
            infoGenerale.put("produit stocke", produitStocke);
        }
        if (volumeNominalValue.getText().length() > 2) {
            infoGenerale.put("volume nominal", volumeNominal);
        }
        if (nombreDeCompartimentsValue.getText().length() > 2) {
            infoGenerale.put("nombre compartiments", nombreCompartiments);
        }
        if (fabricantValue.getText().length() > 2) {
            infoGenerale.put("fabricant", fabricant);
        }
        if (anneeFabricationValue.getText().length() > 2) {
            infoGenerale.put("annee fabrication", anneeFabrication);
        }
        JOptionPane.showMessageDialog(null, "Vos données ont bien été pris en compte!.", "Succès", JOptionPane.INFORMATION_MESSAGE);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel allPanel;
    private javax.swing.JLabel anneeFabrication;
    private javax.swing.JTextField anneeFabricationValue;
    private javax.swing.JButton btnActualiserCapacite;
    private javax.swing.JPanel capacityData;
    private javax.swing.JPanel datePanel;
    private javax.swing.JLabel etancheite;
    private javax.swing.JComboBox<String> etancheiteValue;
    private javax.swing.JLabel fabricant;
    private javax.swing.JTextField fabricantValue;
    private javax.swing.JLabel fondCapacite;
    private javax.swing.JComboBox<String> fondCapaciteValue;
    private javax.swing.JLabel hauteurTemoin;
    private javax.swing.JTextField hauteurTemoinValue;
    private javax.swing.JLabel invalidFields;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel nomCapacite;
    private javax.swing.JTextField nomCapaciteValue;
    private javax.swing.JLabel nombreDeCompartiments;
    private javax.swing.JTextField nombreDeCompartimentsValue;
    private javax.swing.JLabel numeroSerie;
    private javax.swing.JTextField numeroSerieValue1;
    private javax.swing.JLabel produitStocke;
    private javax.swing.JTextField produitStockeValue;
    private javax.swing.JButton suivantButtonCapacite;
    private javax.swing.JLabel typeCapacite;
    private javax.swing.JComboBox<String> typeCapaciteValue;
    private javax.swing.JLabel volumeNominal;
    private javax.swing.JTextField volumeNominalValue;
    // End of variables declaration//GEN-END:variables

}
