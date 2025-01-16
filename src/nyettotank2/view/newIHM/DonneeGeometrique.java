package nyettotank2.view.newIHM;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import nyettotank2.dessin3D.horizontal.CylindreHorizontale;
import nyettotank2.dessin3D.incline.CylindreHorizontaleIncline;
import nyettotank2.dessin3D.vertical.CylinderVerticale;
import nyettotank2.utilitaires.FormValidator;
import nyettotank.dessin3D.*;
import nyettotank2.metier.BaremeArtisan;
//import nyettotank.dessin3D;


 



public class DonneeGeometrique extends javax.swing.JPanel {

    private static HashMap valueGeometryData = new HashMap();
    private static HashMap<String, String> valueGeometryInformation = new HashMap();
    private static HashMap infoGenerale = new HashMap();
    private ButtonGroup btnGrpTrigonometrie = new ButtonGroup();
    private int[] tabIndexComoBoxActve = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static String sensRotationOblique = "";

    public DonneeGeometrique() {
        initComponents();
        fieldCoteCube.setPreferredSize(new Dimension(145, 25));
        fieldRayonSphere.setPreferredSize(new Dimension(145, 25));
        labelHauteur.setPreferredSize(new Dimension(145, 25));
        fieldGrandAxe.setPreferredSize(new Dimension(145, 25));
        fieldPetitAxe.setPreferredSize(new Dimension(145, 25));

        invalidFields.setText("");

        FormValidator.setupRealTimeValidation(fieldCoteCube, invalidFields);
        FormValidator.setupRealTimeValidation(fieldRayonSphere, invalidFields);
        FormValidator.setupRealTimeValidation(fieldLongueur, invalidFields);
        FormValidator.setupRealTimeValidation(fieldDiametre, invalidFields);
        // FormValidator.setupRealTimeValidation(fieldVolumeResiduel, invalidFields);
        FormValidator.setupRealTimeValidation(fieldFlecheDroit, invalidFields);
        FormValidator.setupRealTimeValidation(fieldFlecheGauche, invalidFields);
        FormValidator.setupRealTimeValidation(fieldInclineAngle, invalidFields);
        FormValidator.setupRealTimeValidation(fieldPositionx, invalidFields);
        FormValidator.setupRealTimeValidation(fieldPositiony, invalidFields);
        FormValidator.setupRealTimeValidation(fieldPositionz, invalidFields);
        FormValidator.setupRealTimeValidation(fieldHauteur, invalidFields);
        FormValidator.setupRealTimeValidation(fieldGrandAxe, invalidFields);
        FormValidator.setupRealTimeValidation(fieldPetitAxe, invalidFields);

        panelOrientation.setVisible(false);
        panelFormeElliptique.setVisible(false);
        panelRayonSphere.setVisible(false);
        panelCoteCube.setVisible(false);

        formsfigurePanel.setLayout(new FlowLayout());

        comboOrientationValue.addItemListener(new ItemActionOrientation());
        comboNatureFDValue.addItemListener(new MaskFondForCalculIntegral());
        comboNatureFGValue.addItemListener(new MaskFondForCalculIntegral());
        comboNatureFGValueMoindreCarree.addItemListener(new MaskFondForMoindreCarree());
        comboNatureFDValueMoindreCarree.addItemListener(new MaskFondForMoindreCarree());
        comboFormeCapacite.addItemListener(new ShowPanelByFormeCapacity());

        btnGrpTrigonometrie.add(jRadioBtnTrigonometrique);
        btnGrpTrigonometrie.add(jRadioBtnNonTrigonometrique);

        jRadioBtnTrigonometrique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sensRotationOblique = "Trigo";
            }
        });
        jRadioBtnNonTrigonometrique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sensRotationOblique = "Non Trigo";
            }
        });

        jRadioBtnTrigonometrique.setSelected(true);
    }

    public static String getSensRotationOblique() {
        return sensRotationOblique;
    }

    public static HashMap getValueGeometryData() {
        return valueGeometryData; //infoDonne
    }

    public static HashMap getValueGeometryInfoGenerale() {
        return infoGenerale; //infoDonne
    }

    class ItemActionOrientation implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {

                int selectedIndex = comboOrientationValue.getSelectedIndex();
                 
                String languageActivate = MainView.getChoosenLanguage();

                if (selectedIndex == 2 || selectedIndex == 0) {
                    if (selectedIndex == 0) {
                        panelOrientation.setVisible(false);
                        sensRotationOblique = "";
                    } else if (selectedIndex == 1) {
                        panelOrientation.setVisible(false);
                        sensRotationOblique = "";
                    } else if (selectedIndex == 2) {
                        if (comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("cylindrique") || comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Cylindrical") || comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Zylindrisch")) {
                                                     panelOrientation.setVisible(true);
                                  }
                        sensRotationOblique = "Trigo";
                    }

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
                    
                     labelNatureFlecheDroiteMoindreCarree.setVisible(true);
                        comboNatureFDValueMoindreCarree.setVisible(true);
                        labelNatureFlecheGaucheMoindreCarree.setVisible(true);
                        comboNatureFGValueMoindreCarree.setVisible(true);

                        labelFlecheGauche.setVisible(false);
                        fieldFlecheGauche.setVisible(false);
                        labelFlecheDroite.setVisible(false);
                        fieldFlecheDroit.setVisible(false);

                        labelNatureFlecheDroite.setVisible(false);
                        comboNatureFDValue.setVisible(false);
                        labelNatureFlecheGauche.setVisible(false);
                        comboNatureFGValue.setVisible(false);
                        

                    labelNatureFlecheGauche.setText(valeurs.getString("nature_fleche_g"));
                    labelNatureFlecheDroite.setText(valeurs.getString("nature_fleche_d"));
                    labelFlecheGauche.setText(valeurs.getString("fleche_g"));
                    labelFlecheDroite.setText(valeurs.getString("fleche_d"));
                    labelNatureFlecheDroiteMoindreCarree.setText(valeurs.getString("nature_fleche_d"));
                    labelNatureFlecheGaucheMoindreCarree.setText(valeurs.getString("nature_fleche_g"));
                } 
                else {
                    panelOrientation.setVisible(false);
                    switch (languageActivate) {

                        case "FRANCAIS":
                            labelNatureFlecheDroite.setText("Nature Fleche du Bas");
                            labelNatureFlecheDroiteMoindreCarree.setText("Nature Fleche Bas");
                            labelFlecheDroite.setText("Fleche du Bas");

                            labelFlecheGauche.setText("Fleche du Haut");
                            labelNatureFlecheGaucheMoindreCarree.setText("Nature Fleche Haut");
                            labelNatureFlecheGauche.setText("Nature Fleche du Haut");
                            break;

                        case "ANGLAIS":
                            labelNatureFlecheDroite.setText("Nature Bottom Arrow ");
                            labelNatureFlecheDroiteMoindreCarree.setText("Nature Bottom Arrow");
                            labelFlecheDroite.setText("Bottom Arrow");

                            labelFlecheGauche.setText("Top Arrow");
                            labelNatureFlecheGaucheMoindreCarree.setText("Nature Top Arrow");
                            labelNatureFlecheGauche.setText("Nature Top Arrow");
                            break;

                        case "ALLEMAND":
                            labelNatureFlecheDroite.setText("Natur Linker Renter ");
                            labelNatureFlecheDroiteMoindreCarree.setText("Natur Linker Renter");
                            labelFlecheDroite.setText("Linker Renter ");

                            labelFlecheGauche.setText("Linker Hoch ");
                            labelNatureFlecheGaucheMoindreCarree.setText("Natur Linker Hoch");
                            labelNatureFlecheGauche.setText("Natur Linker Hoch ");
                            break;
                    }    
                              labelNatureFlecheDroite.setVisible(true);
                        comboNatureFDValue.setVisible(true);
                        labelNatureFlecheGauche.setVisible(true);
                        comboNatureFGValue.setVisible(true);

                        labelNatureFlecheDroiteMoindreCarree.setVisible(false);
                        comboNatureFDValueMoindreCarree.setVisible(false);
                        labelNatureFlecheGaucheMoindreCarree.setVisible(false);
                        comboNatureFGValueMoindreCarree.setVisible(false);
                       // fieldFlecheDroit.setText("");
                       // fieldFlecheGauche.setText("");
                        fieldFlecheGauche.setVisible(true);
                        fieldFlecheDroit.setVisible(true);
                        labelFlecheGauche.setVisible(true);
                        labelFlecheDroite.setVisible(true);

                        
                }
            }

        }
    }

    class MaskFondForCalculIntegral implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (comboNatureFGValue.getSelectedItem().toString().equalsIgnoreCase("SPHERIQUE") || comboNatureFGValue.getSelectedItem().toString().equalsIgnoreCase("Spherical") || comboNatureFGValue.getSelectedItem().toString().equalsIgnoreCase("Sphärisch") || comboNatureFGValue.getSelectedItem().toString().equalsIgnoreCase("PLAT") || comboNatureFGValue.getSelectedItem().toString().equalsIgnoreCase("Flat") || comboNatureFGValue.getSelectedItem().toString().equalsIgnoreCase("Flach")) {
                    labelFlecheGauche.setVisible(false);
                    fieldFlecheGauche.setVisible(false);
                } else {
                    labelFlecheGauche.setVisible(true);
                    fieldFlecheGauche.setVisible(true);
                }

                if (comboNatureFDValue.getSelectedItem().toString().equalsIgnoreCase("SPHERIQUE") || comboNatureFDValue.getSelectedItem().toString().equalsIgnoreCase("Spherical") || comboNatureFDValue.getSelectedItem().toString().equalsIgnoreCase("Sphärisch") || comboNatureFDValue.getSelectedItem().toString().equalsIgnoreCase("PLAT") || comboNatureFDValue.getSelectedItem().toString().equalsIgnoreCase("Flat") || comboNatureFDValue.getSelectedItem().toString().equalsIgnoreCase("Flach")) {
                    labelFlecheDroite.setVisible(false);
                    fieldFlecheDroit.setVisible(false);
                } else {
                    labelFlecheDroite.setVisible(true);
                    fieldFlecheDroit.setVisible(true);
                }

            }
        }
    }

    class MaskFondForMoindreCarree implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (comboNatureFGValueMoindreCarree.getItemCount() == 0) {
                return;
            }
            if (comboNatureFDValueMoindreCarree.getItemCount() == 0) {
                return;
            }
            if (comboNatureFGValueMoindreCarree.getSelectedItem().toString().equalsIgnoreCase("Conique") || comboNatureFGValueMoindreCarree.getSelectedItem().toString().equalsIgnoreCase("Conical") || comboNatureFGValueMoindreCarree.getSelectedItem().toString().equalsIgnoreCase("Konisch")
                    || comboNatureFGValueMoindreCarree.getSelectedItem().toString().equalsIgnoreCase("Forme Non Conventionnelle") || comboNatureFGValueMoindreCarree.getSelectedItem().toString().equalsIgnoreCase("No Conventional Forms") ) {
                labelFlecheGauche.setVisible(true);
                fieldFlecheGauche.setVisible(true);
            } else {
                labelFlecheGauche.setVisible(false);
                fieldFlecheGauche.setVisible(false);
            }

            if (comboNatureFDValueMoindreCarree.getSelectedItem().toString().equalsIgnoreCase("Conique") || comboNatureFDValueMoindreCarree.getSelectedItem().toString().equalsIgnoreCase("Conical") || comboNatureFDValueMoindreCarree.getSelectedItem().toString().equalsIgnoreCase("Konisch")
                    || comboNatureFDValueMoindreCarree.getSelectedItem().toString().equalsIgnoreCase("Forme Non Conventionnelle") || comboNatureFDValueMoindreCarree.getSelectedItem().toString().equalsIgnoreCase("No Conventional Forms") ) {
                labelFlecheDroite.setVisible(true);
                fieldFlecheDroit.setVisible(true);
            } else {
                labelFlecheDroite.setVisible(false);
                fieldFlecheDroit.setVisible(false);
            }

        }
        //}
    }

    class ShowPanelByFormeCapacity implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("cylindrique") || comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Cylindrical") || comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Zylindrisch")) {
                    panelData.setVisible(true);
                    panelFormeElliptique.setVisible(false);
                    panelRayonSphere.setVisible(false);
                    panelCoteCube.setVisible(false);
                    labelDiametre.setVisible(true);
                    fieldDiametre.setVisible(true);
//                    labelVolumeResiduel.setVisible(true);
//                    fieldVolumeResiduel.setVisible(true);
                    labelLength.setVisible(true);
                    fieldLongueur.setVisible(true);

                } 
                else if (comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("spherique") || comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Spherical") || comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Sphärisch")) {
                    panelData.setVisible(false);
                    panelFormeElliptique.setVisible(false);
                    panelRayonSphere.setVisible(true);
                    panelCoteCube.setVisible(false);

                } else if (comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("ELLIPSOIDE") || comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Elliptical") || comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Elliptisch")) {
                    panelFormeElliptique.setVisible(true);
                    panelData.setVisible(false);
                    panelRayonSphere.setVisible(false);
                    panelCoteCube.setVisible(false);

                } else if (comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Parallélépipède") || comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Parallelepiped") || comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Kubisch")) {
                   
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
                  
                    labelHauteur.setText(valeurs.getString("hauteur"));
                    labelPetitAxe.setText(valeurs.getString("large"));
                    labelGrandAxe.setText(valeurs.getString("longueur"));
                    
                    panelFormeElliptique.setVisible(true);
                    panelData.setVisible(false);
                    panelRayonSphere.setVisible(false);
                    panelCoteCube.setVisible(false);

                } 
                else {
                    panelFormeElliptique.setVisible(false);
                    panelData.setVisible(true);
                }
            }
        }

    }

      class ShowPanelByFormeCapacityV2 implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            String methodeDeCalcul = MainView.getMethodCalculGeometrie();

            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("cylindrique") || comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Cylindrical") || comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Zylindrisch")) {
                    panelData.setVisible(true);
                    panelFormeElliptique.setVisible(false);
                    panelRayonSphere.setVisible(false);
                    panelCoteCube.setVisible(false);

                    if (methodeDeCalcul.equalsIgnoreCase("Calcul intégral")) {
                        labelNatureFlecheDroite.setVisible(true);
                        comboNatureFDValue.setVisible(true);
                        labelNatureFlecheGauche.setVisible(true);
                        comboNatureFGValue.setVisible(true);

                        labelNatureFlecheDroiteMoindreCarree.setVisible(false);
                        comboNatureFDValueMoindreCarree.setVisible(false);
                        labelNatureFlecheGaucheMoindreCarree.setVisible(false);
                        comboNatureFGValueMoindreCarree.setVisible(false);

                    } else if (methodeDeCalcul.equalsIgnoreCase("Moindres carrées")) {
                        labelNatureFlecheDroiteMoindreCarree.setVisible(true);
                        comboNatureFDValueMoindreCarree.setVisible(true);
                        labelNatureFlecheGaucheMoindreCarree.setVisible(true);
                        comboNatureFGValueMoindreCarree.setVisible(true);

                        labelFlecheGauche.setVisible(false);
                        fieldFlecheGauche.setVisible(false);
                        labelFlecheDroite.setVisible(false);
                        fieldFlecheDroit.setVisible(false);

                        labelNatureFlecheDroite.setVisible(false);
                        comboNatureFDValue.setVisible(false);
                        labelNatureFlecheGauche.setVisible(false);
                        comboNatureFGValue.setVisible(false);
                    }

                    labelDiametre.setVisible(true);
                    fieldDiametre.setVisible(true);
//                    labelVolumeResiduel.setVisible(true);
//                    fieldVolumeResiduel.setVisible(true);
                    labelLength.setVisible(true);
                    fieldLongueur.setVisible(true);

                } 
                else if (comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("spherique") || comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Spherical") || comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Sphärisch")) {
                    panelData.setVisible(false);
                    panelFormeElliptique.setVisible(false);
                    panelRayonSphere.setVisible(true);
                    panelCoteCube.setVisible(false);

                } else if (comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("ELLIPSOIDE") || comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Elliptical") || comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Elliptisch")) {
                    panelFormeElliptique.setVisible(true);
                    panelData.setVisible(false);
                    panelRayonSphere.setVisible(false);
                    panelCoteCube.setVisible(false);

                } else if (comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("cubique") || comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Cubic") || comboFormeCapacite.getSelectedItem().toString().equalsIgnoreCase("Kubisch")) {
                   
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
                  
                    labelHauteur.setText(valeurs.getString("hauteur"));
                    labelPetitAxe.setText(valeurs.getString("large"));
                    labelGrandAxe.setText(valeurs.getString("longueur"));
                    
                    panelFormeElliptique.setVisible(true);
                    panelData.setVisible(false);
                    panelRayonSphere.setVisible(false);
                    panelCoteCube.setVisible(false);

                } else {
                    panelFormeElliptique.setVisible(false);
                    panelData.setVisible(true);
                }
            }
        }

    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        panelUnite = new javax.swing.JPanel();
        labelUniteHeight = new javax.swing.JLabel();
        labelUniteVolume = new javax.swing.JLabel();
        labelFormsCapacity = new javax.swing.JLabel();
        comboUniteLong = new javax.swing.JComboBox<>();
        comboUniteVolume = new javax.swing.JComboBox<>();
        comboFormeCapacite = new javax.swing.JComboBox<>();
        labelOriention = new javax.swing.JLabel();
        comboOrientationValue = new javax.swing.JComboBox<>();
        panelData = new javax.swing.JPanel();
        labelLength = new javax.swing.JLabel();
        fieldLongueur = new javax.swing.JTextField();
        labelDiametre = new javax.swing.JLabel();
        fieldDiametre = new javax.swing.JTextField();
        labelNatureFlecheGauche = new javax.swing.JLabel();
        comboNatureFGValue = new javax.swing.JComboBox<>();
        labelFlecheGauche = new javax.swing.JLabel();
        fieldFlecheGauche = new javax.swing.JTextField();
        labelFlecheDroite = new javax.swing.JLabel();
        fieldFlecheDroit = new javax.swing.JTextField();
        labelNatureFlecheDroite = new javax.swing.JLabel();
        comboNatureFDValue = new javax.swing.JComboBox<>();
        labelNatureFlecheGaucheMoindreCarree = new javax.swing.JLabel();
        comboNatureFGValueMoindreCarree = new javax.swing.JComboBox<>();
        labelNatureFlecheDroiteMoindreCarree = new javax.swing.JLabel();
        comboNatureFDValueMoindreCarree = new javax.swing.JComboBox<>();
        formsfigurePanel = new javax.swing.JPanel();
        panelButton = new javax.swing.JPanel();
        draw = new javax.swing.JButton();
        enroll = new javax.swing.JButton();
        panelFormeElliptique = new javax.swing.JPanel();
        labelHauteur = new javax.swing.JLabel();
        fieldHauteur = new javax.swing.JTextField();
        labelGrandAxe = new javax.swing.JLabel();
        fieldGrandAxe = new javax.swing.JTextField();
        labelPetitAxe = new javax.swing.JLabel();
        fieldPetitAxe = new javax.swing.JTextField();
        panelMethode = new javax.swing.JPanel();
        labelModeOperatoire = new javax.swing.JLabel();
        comboMethodeValue = new javax.swing.JComboBox<>();
        panelOrientation = new javax.swing.JPanel();
        labelInclineAngle = new javax.swing.JLabel();
        fieldInclineAngle = new javax.swing.JTextField();
        labelPositionX = new javax.swing.JLabel();
        fieldPositionx = new javax.swing.JTextField();
        labelPositionZ = new javax.swing.JLabel();
        labelPositionY = new javax.swing.JLabel();
        fieldPositiony = new javax.swing.JTextField();
        fieldPositionz = new javax.swing.JTextField();
        jRadioBtnTrigonometrique = new javax.swing.JRadioButton();
        jRadioBtnNonTrigonometrique = new javax.swing.JRadioButton();
        panelCoteCube = new javax.swing.JPanel();
        labelCote = new javax.swing.JLabel();
        fieldCoteCube = new javax.swing.JTextField();
        panelRayonSphere = new javax.swing.JPanel();
        labelRayon = new javax.swing.JLabel();
        fieldRayonSphere = new javax.swing.JTextField();
        invalidFields = new javax.swing.JLabel();

        mainPanel.setBackground(new java.awt.Color(199, 136, 86));

        panelUnite.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelUniteHeight.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelUniteHeight.setText("Unite de longueur");

        labelUniteVolume.setText("Unite de volume");

        labelFormsCapacity.setText("Forme de la capacite");

        comboUniteLong.setBackground(new java.awt.Color(255, 255, 254));
        comboUniteLong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "cm", "dm", "mm", "pied", "pouce", "m" }));
        comboUniteLong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboUniteLongActionPerformed(evt);
            }
        });

        comboUniteVolume.setBackground(new java.awt.Color(255, 255, 254));
        comboUniteVolume.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "litre", "m3", "baril", "gallon", "USA gallon", "IMPERIAL" }));
        comboUniteVolume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboUniteVolumeActionPerformed(evt);
            }
        });

        comboFormeCapacite.setBackground(new java.awt.Color(255, 255, 254));
        comboFormeCapacite.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cylindrique", "Spherique", "Ellipsoide", "Parallélépipède" }));
        comboFormeCapacite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFormeCapaciteActionPerformed(evt);
            }
        });

        labelOriention.setText("Orientation");

        comboOrientationValue.setBackground(new java.awt.Color(255, 255, 254));
        comboOrientationValue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Horizontale", "Verticale", "Oblique" }));
        comboOrientationValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboOrientationValueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelUniteLayout = new javax.swing.GroupLayout(panelUnite);
        panelUnite.setLayout(panelUniteLayout);
        panelUniteLayout.setHorizontalGroup(
            panelUniteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUniteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUniteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelUniteVolume, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelUniteHeight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelFormsCapacity, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(comboUniteLong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboUniteVolume, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboFormeCapacite, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelOriention, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboOrientationValue, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelUniteLayout.setVerticalGroup(
            panelUniteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUniteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelUniteHeight)
                .addGap(3, 3, 3)
                .addComponent(comboUniteLong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelUniteVolume)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboUniteVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelFormsCapacity)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboFormeCapacite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelOriention)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboOrientationValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelLength.setText("Longueur");

        fieldLongueur.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelDiametre.setText("Diametre");

        fieldDiametre.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelNatureFlecheGauche.setText("Nature fleche gauche");

        comboNatureFGValue.setBackground(new java.awt.Color(255, 255, 254));
        comboNatureFGValue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Spherique", "Elliptique", "Plat", "Conique" }));
        comboNatureFGValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboNatureFGValueActionPerformed(evt);
            }
        });

        labelFlecheGauche.setText("Flèche gauche");

        fieldFlecheGauche.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fieldFlecheGauche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFlecheGaucheActionPerformed(evt);
            }
        });

        labelFlecheDroite.setText("Flèche droite");

        fieldFlecheDroit.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelNatureFlecheDroite.setText("Nature fleche droite");

        comboNatureFDValue.setBackground(new java.awt.Color(255, 255, 254));
        comboNatureFDValue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Spherique", "Elliptique", "Plat", "Conique" }));
        comboNatureFDValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboNatureFDValueActionPerformed(evt);
            }
        });

        labelNatureFlecheGaucheMoindreCarree.setText("Nature fleche gauche");

        comboNatureFGValueMoindreCarree.setBackground(new java.awt.Color(255, 255, 254));
        comboNatureFGValueMoindreCarree.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bomber", "Torispherique", "Elliptique", "Hemispherique", "Plat" }));
        comboNatureFGValueMoindreCarree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboNatureFGValueMoindreCarreeActionPerformed(evt);
            }
        });

        labelNatureFlecheDroiteMoindreCarree.setText("Nature fleche droite");

        comboNatureFDValueMoindreCarree.setBackground(new java.awt.Color(255, 255, 254));
        comboNatureFDValueMoindreCarree.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bomber", "Torispherique", "Elliptique", "Hemispherique", "Plat" }));
        comboNatureFDValueMoindreCarree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboNatureFDValueMoindreCarreeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDataLayout = new javax.swing.GroupLayout(panelData);
        panelData.setLayout(panelDataLayout);
        panelDataLayout.setHorizontalGroup(
            panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataLayout.createSequentialGroup()
                        .addComponent(fieldDiametre)
                        .addContainerGap(1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataLayout.createSequentialGroup()
                        .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelDiametre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelLength, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fieldLongueur))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataLayout.createSequentialGroup()
                        .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelNatureFlecheGauche, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboNatureFGValue, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelNatureFlecheGaucheMoindreCarree, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboNatureFGValueMoindreCarree, javax.swing.GroupLayout.Alignment.LEADING, 0, 218, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataLayout.createSequentialGroup()
                        .addGroup(panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelNatureFlecheDroite, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fieldFlecheDroit, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelFlecheGauche, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fieldFlecheGauche, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboNatureFDValue, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelNatureFlecheDroiteMoindreCarree, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboNatureFDValueMoindreCarree, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelFlecheDroite, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        panelDataLayout.setVerticalGroup(
            panelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLength)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldLongueur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDiametre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldDiametre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelNatureFlecheGauche)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboNatureFGValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelNatureFlecheGaucheMoindreCarree)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboNatureFGValueMoindreCarree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelFlecheGauche)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fieldFlecheGauche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelNatureFlecheDroite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboNatureFDValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelNatureFlecheDroiteMoindreCarree)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboNatureFDValueMoindreCarree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelFlecheDroite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldFlecheDroit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        formsfigurePanel.setBackground(new java.awt.Color(255, 255, 255));
        formsfigurePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout formsfigurePanelLayout = new javax.swing.GroupLayout(formsfigurePanel);
        formsfigurePanel.setLayout(formsfigurePanelLayout);
        formsfigurePanelLayout.setHorizontalGroup(
            formsfigurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 628, Short.MAX_VALUE)
        );
        formsfigurePanelLayout.setVerticalGroup(
            formsfigurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelButton.setBackground(new java.awt.Color(51, 51, 51));

        draw.setBackground(new java.awt.Color(204, 102, 0));
        draw.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        draw.setForeground(new java.awt.Color(255, 255, 255));
        draw.setText("Visualiser forme");
        draw.setActionCommand("");
        draw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawActionPerformed(evt);
            }
        });

        enroll.setBackground(new java.awt.Color(0, 153, 102));
        enroll.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        enroll.setForeground(new java.awt.Color(255, 255, 255));
        enroll.setText("Enregister");
        enroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrollActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonLayout = new javax.swing.GroupLayout(panelButton);
        panelButton.setLayout(panelButtonLayout);
        panelButtonLayout.setHorizontalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(draw, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                .addComponent(enroll, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        panelButtonLayout.setVerticalGroup(
            panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(draw, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enroll, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panelFormeElliptique.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelHauteur.setText("Mesure de la hauteur");

        fieldHauteur.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fieldHauteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldHauteurActionPerformed(evt);
            }
        });

        labelGrandAxe.setText("Mesure du grand axe");

        fieldGrandAxe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fieldGrandAxe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldGrandAxeActionPerformed(evt);
            }
        });

        labelPetitAxe.setText("Mesure du petit axe");

        fieldPetitAxe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fieldPetitAxe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldPetitAxeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFormeElliptiqueLayout = new javax.swing.GroupLayout(panelFormeElliptique);
        panelFormeElliptique.setLayout(panelFormeElliptiqueLayout);
        panelFormeElliptiqueLayout.setHorizontalGroup(
            panelFormeElliptiqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormeElliptiqueLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormeElliptiqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormeElliptiqueLayout.createSequentialGroup()
                        .addComponent(labelHauteur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormeElliptiqueLayout.createSequentialGroup()
                        .addGroup(panelFormeElliptiqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fieldPetitAxe, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPetitAxe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fieldHauteur, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldGrandAxe, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelGrandAxe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        panelFormeElliptiqueLayout.setVerticalGroup(
            panelFormeElliptiqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormeElliptiqueLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelHauteur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldHauteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelGrandAxe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldGrandAxe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPetitAxe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldPetitAxe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelMethode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelModeOperatoire.setText("Mode opératoire");

        comboMethodeValue.setBackground(new java.awt.Color(255, 255, 254));
        comboMethodeValue.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        comboMethodeValue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ISO 7507-1", "ISO 7507-2", "ISO 7507-3", "ISO 7507-4", "ISO 7507-5", "ISO 7507-6", "ISO 12917-1", "ISO 12917-2" }));
        comboMethodeValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMethodeValueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMethodeLayout = new javax.swing.GroupLayout(panelMethode);
        panelMethode.setLayout(panelMethodeLayout);
        panelMethodeLayout.setHorizontalGroup(
            panelMethodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMethodeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMethodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comboMethodeValue, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelModeOperatoire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelMethodeLayout.setVerticalGroup(
            panelMethodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMethodeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelModeOperatoire)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboMethodeValue, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelOrientation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelInclineAngle.setText("Angle d'inclinaison");

        fieldInclineAngle.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelPositionX.setText("Position X");

        fieldPositionx.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fieldPositionx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldPositionxActionPerformed(evt);
            }
        });

        labelPositionZ.setText("Position Z");

        labelPositionY.setText("Position Y");

        fieldPositiony.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fieldPositiony.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldPositionyActionPerformed(evt);
            }
        });

        fieldPositionz.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fieldPositionz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldPositionzActionPerformed(evt);
            }
        });

        jRadioBtnTrigonometrique.setText("Trigonométrique");

        jRadioBtnNonTrigonometrique.setText("Non Trigonométrique");

        javax.swing.GroupLayout panelOrientationLayout = new javax.swing.GroupLayout(panelOrientation);
        panelOrientation.setLayout(panelOrientationLayout);
        panelOrientationLayout.setHorizontalGroup(
            panelOrientationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOrientationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOrientationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPositionY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldPositionx, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelPositionZ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelPositionX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldPositiony)
                    .addComponent(fieldPositionz)
                    .addComponent(fieldInclineAngle)
                    .addComponent(labelInclineAngle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(jRadioBtnTrigonometrique, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioBtnNonTrigonometrique, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelOrientationLayout.setVerticalGroup(
            panelOrientationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOrientationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInclineAngle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldInclineAngle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPositionX)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldPositionx, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPositionY)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldPositiony, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPositionZ)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldPositionz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioBtnTrigonometrique)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioBtnNonTrigonometrique)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        panelCoteCube.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelCote.setText("Cote du Cube");

        fieldCoteCube.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout panelCoteCubeLayout = new javax.swing.GroupLayout(panelCoteCube);
        panelCoteCube.setLayout(panelCoteCubeLayout);
        panelCoteCubeLayout.setHorizontalGroup(
            panelCoteCubeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCoteCubeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCoteCubeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldCoteCube))
                .addContainerGap())
        );
        panelCoteCubeLayout.setVerticalGroup(
            panelCoteCubeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCoteCubeLayout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(labelCote)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldCoteCube, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelRayonSphere.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        labelRayon.setText("Rayon de la sphere");

        fieldRayonSphere.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout panelRayonSphereLayout = new javax.swing.GroupLayout(panelRayonSphere);
        panelRayonSphere.setLayout(panelRayonSphereLayout);
        panelRayonSphereLayout.setHorizontalGroup(
            panelRayonSphereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRayonSphereLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRayonSphereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelRayon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldRayonSphere))
                .addContainerGap())
        );
        panelRayonSphereLayout.setVerticalGroup(
            panelRayonSphereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRayonSphereLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelRayon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldRayonSphere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        invalidFields.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        invalidFields.setForeground(new java.awt.Color(153, 0, 0));
        invalidFields.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        invalidFields.setText("invalidFields");
        invalidFields.setOpaque(true);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelUnite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelMethode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelOrientation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCoteCube, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRayonSphere, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelFormeElliptique, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(invalidFields, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formsfigurePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(formsfigurePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(panelData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelFormeElliptique, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(invalidFields))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(panelMethode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelUnite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelOrientation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelCoteCube, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelRayonSphere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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

    private void drawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawActionPerformed
        if (synonymeFormeCapaciteMultiLangueForCylindre(comboFormeCapacite.getSelectedItem().toString().toLowerCase())) {

            //if (!MainView.getMethodCalculGeometrie().equalsIgnoreCase("Moindres carrées")) {
            if( synonymeForOrientationVerticale( comboOrientationValue.getSelectedItem().toString() ) ) {
                String nature_fond_g = comboNatureFGValue.getSelectedItem().toString();
                String nature_fond_d = comboNatureFDValue.getSelectedItem().toString();
                dessinerForme(nature_fond_g, nature_fond_d);

            } else {
                String nature_fond_g = comboNatureFGValueMoindreCarree.getSelectedItem().toString();
                String nature_fond_d = comboNatureFDValueMoindreCarree.getSelectedItem().toString();

                if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldDiametre.getText()).matches() && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldLongueur.getText()).matches()) {

                    float diametre = Float.parseFloat(fieldDiametre.getText());

                    if (comboNatureFDValueMoindreCarree.getSelectedItem().toString().equalsIgnoreCase("BOMBER")) {
                        fieldFlecheDroit.setText(String.valueOf(diametre / 7));
                    } else if (synonymeFormeCapaciteMultiLangueForEllpsoide(comboNatureFDValueMoindreCarree.getSelectedItem().toString())) {
                        fieldFlecheDroit.setText(String.valueOf(diametre / 4));
                    } else if (synonymeFormeCapaciteMultiLangueForToriSpherique(comboNatureFDValueMoindreCarree.getSelectedItem().toString())) {
                        fieldFlecheDroit.setText(String.valueOf(diametre / 5));
                    } else if (synonymeFormeCapaciteMultiLangueForHemispherique(comboNatureFDValueMoindreCarree.getSelectedItem().toString())) {
                        fieldFlecheDroit.setText(String.valueOf(diametre / 2));
                    } else if (synonymeForNatureFlechePlatOfCylinder(comboNatureFDValueMoindreCarree.getSelectedItem().toString())) {
                        fieldFlecheDroit.setText("0");
                    } 

                    if (comboNatureFGValueMoindreCarree.getSelectedItem().toString().equalsIgnoreCase("BOMBER")) {
                        fieldFlecheGauche.setText(String.valueOf(diametre / 7));
                    } else if (synonymeFormeCapaciteMultiLangueForEllpsoide(comboNatureFGValueMoindreCarree.getSelectedItem().toString())) {
                        fieldFlecheGauche.setText(String.valueOf(diametre / 4));
                    } else if (synonymeFormeCapaciteMultiLangueForToriSpherique(comboNatureFGValueMoindreCarree.getSelectedItem().toString())) {
                        fieldFlecheGauche.setText(String.valueOf(diametre / 5));
                    } else if (synonymeFormeCapaciteMultiLangueForHemispherique(comboNatureFGValueMoindreCarree.getSelectedItem().toString())) {
                        fieldFlecheGauche.setText(String.valueOf(diametre / 2));
                    } else if (synonymeForNatureFlechePlatOfCylinder(comboNatureFGValueMoindreCarree.getSelectedItem().toString())) {
                        fieldFlecheGauche.setText("0");
                    } 

                    dessinerFormeRayonCarre(nature_fond_g, nature_fond_d);

                } else {
                    JOptionPane.showMessageDialog(null, "la valeur du diametre ou de la longueur que vous avez entrer ne correspond pas à celle d'un nombre .  ", "Erreur", JOptionPane.ERROR_MESSAGE);

                }

            }
        } 
        else {
           
        formsfigurePanel.removeAll();
        if (synonymeFormeCapaciteMultiLangueForSphere(comboFormeCapacite.getSelectedItem().toString().toLowerCase())) {

            if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldRayonSphere.getText()).matches() ) {

                Sphere sphere = new Sphere(  Float.parseFloat( fieldRayonSphere.getText() ) );
             sphere.setPreferredSize(new Dimension(600, 500));
                        formsfigurePanel.add(sphere);
            }
            
        } else if (synonymeFormeCapaciteMultiLangueForCube(comboFormeCapacite.getSelectedItem().toString().toLowerCase())) {

             if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldGrandAxe.getText()).matches() &&
                    Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldPetitAxe.getText()).matches() &&
                    Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldHauteur.getText()).matches()) {

                 Parallelipipede parallelipipede = new Parallelipipede( Float.parseFloat( fieldPetitAxe.getText() ) ,
                         Float.parseFloat( fieldGrandAxe.getText() ), Float.parseFloat( fieldHauteur.getText() ));
            
                  parallelipipede.setPreferredSize(new Dimension(600, 500));
                        formsfigurePanel.add(parallelipipede);
            }
            
        } else if (synonymeFormeCapaciteMultiLangueForEllpsoide(comboFormeCapacite.getSelectedItem().toString().toLowerCase())) {

            if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldGrandAxe.getText()).matches() &&
                    Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldPetitAxe.getText()).matches() &&
                    Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldHauteur.getText()).matches()) {

                Ellipsoide ellipsoide = new Ellipsoide( Float.parseFloat( fieldGrandAxe.getText() ),
                   Float.parseFloat( fieldPetitAxe.getText() ), Float.parseFloat( fieldHauteur.getText() ) );
                
                 ellipsoide.setPreferredSize(new Dimension(600, 500));
                        formsfigurePanel.add(ellipsoide);
            }
            
        }
        formsfigurePanel.revalidate();
        formsfigurePanel.repaint();
        }
    }//GEN-LAST:event_drawActionPerformed

    public void dessinerFormeRayonCarre(String nature_fond_g, String nature_fond_d) {

        if (synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_d)) {
            nature_fond_d = "spherique";
        }

        if (synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_g)) {
            nature_fond_g = "spherique";
        }

        if (nature_fond_d.equalsIgnoreCase("bomber")
                || synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_d)
                || synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d) 
                || synonymeFormeCapaciteMultiLangueForFormeNonConventionnelle(nature_fond_d) 
                || synonymeFormeCapaciteMultiLangueForFormeNonConventionnelle(nature_fond_d) ) {
            nature_fond_d = "elliptique";
        }

        if (nature_fond_g.equalsIgnoreCase("bomber")
                || synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_g)
                || synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)
                || synonymeFormeCapaciteMultiLangueForFormeNonConventionnelle(nature_fond_g)
                || synonymeFormeCapaciteMultiLangueForFormeNonConventionnelle(nature_fond_g) ) { 
            nature_fond_g = "elliptique";
        }
        dessinerForme(nature_fond_g, nature_fond_d);

    }

    public void remplirFlecheDroitOuGauche() {

        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldDiametre.getText()).matches() && 
                Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldLongueur.getText()).matches() ) {

            float diametre = Float.parseFloat(fieldDiametre.getText());

            if (comboNatureFDValueMoindreCarree.getSelectedItem().toString().equalsIgnoreCase("BOMBER")) {
                valueGeometryData.put("fleche cote droit", diametre / 7);
            } else if (synonymeFormeCapaciteMultiLangueForEllpsoide(comboNatureFDValueMoindreCarree.getSelectedItem().toString())) {
                valueGeometryData.put("fleche cote droit", diametre / 4);
            } else if (synonymeFormeCapaciteMultiLangueForToriSpherique(comboNatureFDValueMoindreCarree.getSelectedItem().toString())) {
                valueGeometryData.put("fleche cote droit", diametre / 5);
            } else if (synonymeFormeCapaciteMultiLangueForHemispherique(comboNatureFDValueMoindreCarree.getSelectedItem().toString())) {
                valueGeometryData.put("fleche cote droit", diametre / 2);
            }

            if (comboNatureFGValueMoindreCarree.getSelectedItem().toString().equalsIgnoreCase("BOMBER")) {
                valueGeometryData.put("fleche cote gauche", diametre / 7);
            } else if (synonymeFormeCapaciteMultiLangueForEllpsoide(comboNatureFGValueMoindreCarree.getSelectedItem().toString())) {
                valueGeometryData.put("fleche cote gauche", diametre / 4);
            } else if (synonymeFormeCapaciteMultiLangueForToriSpherique(comboNatureFGValueMoindreCarree.getSelectedItem().toString())) {
                valueGeometryData.put("fleche cote gauche", diametre / 5);
            } else if (synonymeFormeCapaciteMultiLangueForHemispherique(comboNatureFGValueMoindreCarree.getSelectedItem().toString())) {
                valueGeometryData.put("fleche cote gauche", diametre / 2);
            }

        } else {
            JOptionPane.showMessageDialog(null, "la valeur du diametre ou du raon que vous avez entrer ne correspond pas a un nombre .  ", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fieldPositionyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldPositionyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldPositionyActionPerformed

    private void fieldPositionzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldPositionzActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldPositionzActionPerformed

    private void fieldPositionxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldPositionxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldPositionxActionPerformed

    private void fieldFlecheGaucheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFlecheGaucheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldFlecheGaucheActionPerformed

    private void enrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enrollActionPerformed
                
          String typeMethodeGeometiy = MainView.getMethodCalculGeometrie();

          if( ( !synonymeForOrientationVerticale( comboOrientationValue.getSelectedItem().toString() ) ) && synonymeFormeCapaciteMultiLangueForCylindre(comboFormeCapacite.getSelectedItem().toString()) ){
            saveDonneeGeometriqueByRayonCarreMethod();
        } else {
            saveDonneeGeometrique();
        }
          
          saveComboIndexActive();
        
    }//GEN-LAST:event_enrollActionPerformed

    private void fieldHauteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldHauteurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldHauteurActionPerformed

    private void fieldGrandAxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldGrandAxeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldGrandAxeActionPerformed

    private void fieldPetitAxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldPetitAxeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldPetitAxeActionPerformed

    private void comboMethodeValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMethodeValueActionPerformed

    }//GEN-LAST:event_comboMethodeValueActionPerformed

    private void comboNatureFDValueMoindreCarreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboNatureFDValueMoindreCarreeActionPerformed

    }//GEN-LAST:event_comboNatureFDValueMoindreCarreeActionPerformed

    private void comboUniteLongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUniteLongActionPerformed

    }//GEN-LAST:event_comboUniteLongActionPerformed

    private void comboUniteVolumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboUniteVolumeActionPerformed

    }//GEN-LAST:event_comboUniteVolumeActionPerformed

    private void comboFormeCapaciteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFormeCapaciteActionPerformed

    }//GEN-LAST:event_comboFormeCapaciteActionPerformed

    private void comboOrientationValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboOrientationValueActionPerformed

    }//GEN-LAST:event_comboOrientationValueActionPerformed

    private void comboNatureFGValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboNatureFGValueActionPerformed

    }//GEN-LAST:event_comboNatureFGValueActionPerformed

    private void comboNatureFGValueMoindreCarreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboNatureFGValueMoindreCarreeActionPerformed

    }//GEN-LAST:event_comboNatureFGValueMoindreCarreeActionPerformed

    private void comboNatureFDValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboNatureFDValueActionPerformed

    }//GEN-LAST:event_comboNatureFDValueActionPerformed

    private void saveDonneeGeometrique() {
        boolean coteCube = false, rayonSphere = false, longueur = false, diametre = false, volumeResiduel = false, flecheGauche = false,
                angleInclinaison = false, positionX = false, positionY = false, positionZ = false, hauteur = false, grandAxe = false,
                petitAxe = false, flecheDroite = false, volumetrie = false, largeur = false;
        infoGenerale.clear();
        valueGeometryData.clear();
        infoGenerale.put("unite de volume", comboUniteVolume.getSelectedItem().toString());
        infoGenerale.put("unite des hauteurs", comboUniteLong.getSelectedItem().toString());
        infoGenerale.put("orientation", comboOrientationValue.getSelectedItem().toString().toUpperCase());
        infoGenerale.put("forme de la capacite", comboFormeCapacite.getSelectedItem().toString().toUpperCase());
        valueGeometryData.put("mode operatoire", comboMethodeValue.getSelectedItem().toString());

        switch (comboFormeCapacite.getSelectedItem().toString()) {

            case "Parallélépipède":
            case "Parallelepiped":
            case "Kubisch":
                
                 if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldGrandAxe.getText()).matches()) {
                    longueur = true;
                    valueGeometryData.put("longueur", fieldGrandAxe.getText());
                }

                if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldPetitAxe.getText()).matches()) {
                    largeur = true;
                    valueGeometryData.put("largeur", fieldPetitAxe.getText());
                }

                if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldHauteur.getText()).matches()) {
                    hauteur = true;
                    valueGeometryData.put("hauteur", fieldHauteur.getText());
                }
                
                break;

            case "Spherique":
            case "Spherical":
            case "Sphärisch":
                if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldRayonSphere.getText()).matches()) {
                    rayonSphere = true;
                    valueGeometryData.put("rayon sphere", fieldRayonSphere.getText());
                }
                break;

            case "Ellipsoide":
            case "Elliptical":
            case "Elliptisch":
                if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldGrandAxe.getText()).matches()) {
                    grandAxe = true;
                    valueGeometryData.put("grand axe", fieldGrandAxe.getText());
                }

                if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldPetitAxe.getText()).matches()) {
                    petitAxe = true;
                    valueGeometryData.put("petit axe", fieldPetitAxe.getText());
                }

                if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldHauteur.getText()).matches()) {
                    hauteur = true;
                    valueGeometryData.put("hauteur", fieldHauteur.getText());
                }
                break;

            case "Cylindrique":
            case "Cylindrical":
            case "Zylindrisch":

                
                if ((!synonymeFormeCapaciteMultiLangueForSphere(comboNatureFGValue.getSelectedItem().toString()) && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches())) {
                    flecheGauche = true;
                    valueGeometryData.put("fleche cote gauche", fieldFlecheGauche.getText());
                }

                  if (synonymeForNatureFlechePlatOfCylinder(comboNatureFDValue.getSelectedItem().toString())) {
                    valueGeometryData.put("fleche cote droit", "0");
                }

                if (synonymeForNatureFlechePlatOfCylinder(comboNatureFGValue.getSelectedItem().toString())) {
                    valueGeometryData.put("fleche cote gauche", "0");
                }
                
                if ((!synonymeFormeCapaciteMultiLangueForSphere(comboNatureFDValue.getSelectedItem().toString()) && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches())) {
                    flecheDroite = true;
                    valueGeometryData.put("fleche cote droit", fieldFlecheDroit.getText());
                }

                if (synonymeFormeCapaciteMultiLangueForSphere(comboNatureFGValue.getSelectedItem().toString())) {
                    flecheGauche = true;
                    valueGeometryData.put("fleche cote gauche", Float.parseFloat(fieldDiametre.getText()) / 2);
                }

                if (synonymeFormeCapaciteMultiLangueForSphere(comboNatureFDValue.getSelectedItem().toString())) {
                    flecheDroite = true;
                    valueGeometryData.put("fleche cote droit", Float.parseFloat(fieldDiametre.getText()) / 2);
                }

                if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldLongueur.getText()).matches()) {
                    longueur = true;
                    valueGeometryData.put("longueur", fieldLongueur.getText());
                }

                if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldDiametre.getText()).matches()) {
                    diametre = true;
                    valueGeometryData.put("diametre", fieldDiametre.getText());
                }

                infoGenerale.put("nature fond gauche", comboNatureFGValue.getSelectedItem().toString().toUpperCase());
                infoGenerale.put("nature fond droite", comboNatureFDValue.getSelectedItem().toString().toUpperCase());

                break;
        }

        if (synonymeForOrientationOblique(comboOrientationValue.getSelectedItem().toString().toLowerCase())) {
            if ( !fieldInclineAngle.getText().isEmpty() && !fieldInclineAngle.getText().isBlank() && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldInclineAngle.getText()).matches()) {
                angleInclinaison = true;
                valueGeometryData.put("angle inclinaison", fieldInclineAngle.getText());
            }

            if ( !fieldPositionx.getText().isBlank() && !fieldPositionx.getText().isEmpty() && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldPositionx.getText()).matches()) {
                positionX = true;
                valueGeometryData.put("position x", fieldPositionx.getText());
            }

            if ( !fieldPositiony.getText().isBlank() && !fieldPositiony.getText().isEmpty() && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldPositiony.getText()).matches()) {
                positionY = true;
                valueGeometryData.put("position y", fieldPositiony.getText());
            }

            if ( !fieldPositionz.getText().isEmpty() && !fieldPositionz.getText().isBlank() && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldPositionz.getText()).matches()) {
                positionZ = true;
                valueGeometryData.put("position z", fieldPositionz.getText());
            }

            //if (btnGrpTrigonometrie.getSelection().isSelected()) {
            //volumetrie = true;                
            valueGeometryData.put("valeur trigonometrique", btnGrpTrigonometrie.getSelection().toString());
            
            if( !angleInclinaison && !positionZ && !positionY && !positionX )
                JOptionPane.showMessageDialog(null, "Une erreur s'est produite. Veuillez vérifier les valeurs du formulaire de vos champs qui correspondent à l'orientation oblique de la capacite. \n Parmi lesquels : l'angle d'inclinaison et les différentes positions en X, Y et Z", "Erreur", JOptionPane.ERROR_MESSAGE);
            if( !angleInclinaison )
                JOptionPane.showMessageDialog(null, "Une erreur s'est produite. La valeur de l'angle d'inclinaison n'est pas correcte. ", "Erreur", JOptionPane.ERROR_MESSAGE);

            //}
        }

        switch (comboFormeCapacite.getSelectedItem().toString()) {
            case "Cubique":
            case "Cubic":
            case "Kubisch":
                if (!longueur || !largeur || !hauteur) {
                    JOptionPane.showMessageDialog(null, "Une erreur s'est produite. Veuillez vérifier vos champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Vos données ont bien été pris en compte!.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }

                break;

            case "Spherique":
            case "Spherical":
            case "Sphärisch":
                if (!rayonSphere) {
                    JOptionPane.showMessageDialog(null, "Une erreur s'est produite. Veuillez vérifier vos champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Vos données ont bien été pris en compte!.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
                break;

            case "Ellipsoide":
            case "Elliptical":
            case "Elliptisch":
                if (!hauteur || !grandAxe || !petitAxe) {
                    JOptionPane.showMessageDialog(null, "Une erreur s'est produite. Veuillez vérifier vos champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Vos données ont bien été pris en compte!.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
                break;

            case "Cylindrique":
            case "Cylindrical":
            case "Zylindrisch":
                if (!longueur && !diametre) {
                    JOptionPane.showMessageDialog(null, "Une erreur s'est produite. Veuillez vérifier vos champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Vos données ont bien été pris en compte!.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
        }
    }

    public void saveComboIndexActive(){
    
         tabIndexComoBoxActve[0] = comboMethodeValue.getSelectedIndex();
        tabIndexComoBoxActve[1] = comboUniteLong.getSelectedIndex();
        tabIndexComoBoxActve[2] = comboUniteVolume.getSelectedIndex();
        tabIndexComoBoxActve[3] = comboFormeCapacite.getSelectedIndex();
        tabIndexComoBoxActve[4] = comboOrientationValue.getSelectedIndex();
        tabIndexComoBoxActve[5] = comboNatureFGValue.getSelectedIndex();
        tabIndexComoBoxActve[6] = comboNatureFDValue.getSelectedIndex();
        tabIndexComoBoxActve[7] = comboNatureFGValueMoindreCarree.getSelectedIndex();
        tabIndexComoBoxActve[8] = comboNatureFDValueMoindreCarree.getSelectedIndex();
    
    }
    
    private void saveDonneeGeometriqueByRayonCarreMethod() {

        infoGenerale.clear();
        valueGeometryData.clear();
        infoGenerale.put("unite de volume", comboUniteVolume.getSelectedItem().toString());
        infoGenerale.put("unite des hauteurs", comboUniteLong.getSelectedItem().toString());
        infoGenerale.put("orientation", comboOrientationValue.getSelectedItem().toString());
        infoGenerale.put("forme de la capacite", comboFormeCapacite.getSelectedItem().toString());
        valueGeometryData.put("mode operatoire", comboMethodeValue.getSelectedItem().toString());

        if( synonymeForNatureFlecheConiqueOfCylinder( comboNatureFGValueMoindreCarree.getSelectedItem().toString() ) ){
        
            if ( Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches()) {
            valueGeometryData.put("fleche cote gauche", fieldFlecheGauche.getText());
            } else
                        JOptionPane.showMessageDialog(null, "la valeur de la fleche gauche que vous avez entrer ne correspond pas a un nombre .  ", "Erreur", JOptionPane.ERROR_MESSAGE);        
        }
        
        
        if( synonymeForNatureFlecheConiqueOfCylinder( comboNatureFDValueMoindreCarree.getSelectedItem().toString() ) ){
        
            if ( Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {
            valueGeometryData.put("fleche cote droit", fieldFlecheDroit.getText());
            } else
                        JOptionPane.showMessageDialog(null, "la valeur de la fleche droite que vous avez entrer ne correspond pas a un nombre .  ", "Erreur", JOptionPane.ERROR_MESSAGE);

        
        }
        
        if( synonymeFormeCapaciteMultiLangueForFormeNonConventionnelle( comboNatureFDValueMoindreCarree.getSelectedItem().toString() ) ){
        
            if( Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches() )
                    valueGeometryData.put("fleche cote droit", fieldFlecheDroit.getText());
            else
                                        JOptionPane.showMessageDialog(null, "la valeur de la fleche droite que vous avez entrer ne correspond pas a un nombre .  ", "Erreur", JOptionPane.ERROR_MESSAGE);

        }
        
        if( synonymeFormeCapaciteMultiLangueForFormeNonConventionnelle( comboNatureFGValueMoindreCarree.getSelectedItem().toString() ) ){
        
            if ( Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches()) {
            valueGeometryData.put("fleche cote gauche", fieldFlecheGauche.getText());
            } else
                        JOptionPane.showMessageDialog(null, "la valeur de la fleche gauche que vous avez entrer ne correspond pas a un nombre .  ", "Erreur", JOptionPane.ERROR_MESSAGE);

        
        }
        
        if (synonymeForNatureFlechePlatOfCylinder(comboNatureFGValueMoindreCarree.getSelectedItem().toString())) {
            valueGeometryData.put("fleche cote droit", "0");
        }

        if (synonymeForNatureFlechePlatOfCylinder(comboNatureFDValueMoindreCarree.getSelectedItem().toString())) {
            valueGeometryData.put("fleche cote gauche", "0");
        }

        remplirFlecheDroitOuGauche();

        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldLongueur.getText()).matches()) {

            valueGeometryData.put("longueur", fieldLongueur.getText());
        } else
                                    JOptionPane.showMessageDialog(null, "la valeur de la longueur que vous avez entrer ne correspond pas a un nombre .  ", "Erreur", JOptionPane.ERROR_MESSAGE);


        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldDiametre.getText()).matches()) {
            valueGeometryData.put("diametre", fieldDiametre.getText());
        } else
                                    JOptionPane.showMessageDialog(null, "la valeur du diametre que vous avez entrer ne correspond pas a un nombre .  ", "Erreur", JOptionPane.ERROR_MESSAGE);


        if (synonymeForOrientationOblique(comboOrientationValue.getSelectedItem().toString())) {

            if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldInclineAngle.getText()).matches()) {
                valueGeometryData.put("angle inclinaison", fieldInclineAngle.getText());
            }

            if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldPositionx.getText()).matches()) {
                valueGeometryData.put("position x", fieldPositionx.getText());
            }

            if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldPositiony.getText()).matches()) {
                valueGeometryData.put("position y", fieldPositiony.getText());
            }

            if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldPositionz.getText()).matches()) {
                valueGeometryData.put("position z", fieldPositionz.getText());
            }

        }

        infoGenerale.put("nature fond gauche", comboNatureFDValueMoindreCarree.getSelectedItem().toString().toUpperCase());
        infoGenerale.put("nature fond droite", comboNatureFGValueMoindreCarree.getSelectedItem().toString().toUpperCase());

        JOptionPane.showMessageDialog(null, "Vos données ont bien été pris en compte!.", "Succès", JOptionPane.INFORMATION_MESSAGE);

    }

    public void dessinerForme(String fondLeft, String fondRight) {

        float flecheg = 0;
        float fleched = 0;

        BaremeArtisan baremeArtisan = new BaremeArtisan();

        float longueur = Float.parseFloat(fieldLongueur.getText());
        float diametre = Float.parseFloat(fieldDiametre.getText());
        diametre = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), diametre);
        longueur = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), longueur);

        String formeRecipentSelectionne = comboOrientationValue.getSelectedItem().toString().toUpperCase();

        formsfigurePanel.removeAll();

        if (!synonymeForOrientationVerticale(formeRecipentSelectionne) && !synonymeForOrientationOblique(formeRecipentSelectionne)) {

            if (fondLeft.equals(fondRight)) {

                if (synonymeElliptiqueMultiLangue(fondRight)) {

                    if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches() && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                        fleched = Float.parseFloat(fieldFlecheDroit.getText());
                        flecheg = Float.parseFloat(fieldFlecheGauche.getText());

                        fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);
                        flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                        CylindreHorizontale cylindreHorizontale = new CylindreHorizontale(diametre, longueur, flecheg, fleched);
//                    CylindreElliptique ellipticCylindric = new CylindreElliptique(diametre, longueur, diametre / 4, diametre / 4);
                        cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                        formsfigurePanel.add(cylindreHorizontale);
                    } else {
                        JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                    }

                } 
                else if (synonymeFormeCapaciteMultiLangueForSphere(fondRight)) {
                    CylindreHorizontale cylindreHorizontale = new CylindreHorizontale(diametre, longueur, diametre / 2, diametre / 2);
                    cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                    formsfigurePanel.add(cylindreHorizontale);

                } else if (synonymeForNatureFlecheConiqueOfCylinder(fondRight)) {

                    if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches() && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                        flecheg = Float.parseFloat(fieldFlecheGauche.getText());
                        fleched = Float.parseFloat(fieldFlecheDroit.getText());

                        fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);
                        flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                        CylindreHorizontale cylindreHorizontale = new CylindreHorizontale(diametre, longueur, flecheg, fleched, "conique", "conique");
                        cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                        formsfigurePanel.add(cylindreHorizontale);
                    } else {
                        JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                    }

                } else if (synonymeForNatureFlechePlatOfCylinder(fondRight)) {
                    CylindreHorizontale cylindreHorizontale = new CylindreHorizontale(diametre, longueur, 0, 0);
                    cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                    formsfigurePanel.add(cylindreHorizontale);
                }

            } else {

                if (synonymeForNatureFlechePlatOfCylinder(fondLeft)) {

                    if (synonymeFormeCapaciteMultiLangueForSphere(fondRight)) {

                        CylindreHorizontale cylindreHorizontale = new CylindreHorizontale(diametre, longueur, 0, diametre / 2);
                        cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                        formsfigurePanel.add(cylindreHorizontale);
                    } else if (synonymeElliptiqueMultiLangue(fondRight)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                            fleched = Float.parseFloat(fieldFlecheDroit.getText());
                            fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);

                            CylindreHorizontale cylindreHorizontale = new CylindreHorizontale(diametre, longueur, 0, fleched);
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else if (synonymeForNatureFlecheConiqueOfCylinder(fondRight)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                            fleched = Float.parseFloat(fieldFlecheDroit.getText());
                            fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);

                            CylindreHorizontale cylindreHorizontale = new CylindreHorizontale(diametre, longueur, 0, fleched, "", "conique");
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }

                } else if (synonymeForNatureFlechePlatOfCylinder(fondRight)) {

                    if (synonymeFormeCapaciteMultiLangueForSphere(fondLeft)) {

                        CylindreHorizontale cylindreHorizontale = new CylindreHorizontale(diametre, longueur, diametre / 2, 0);
                        cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                        formsfigurePanel.add(cylindreHorizontale);
                    } else if (synonymeElliptiqueMultiLangue(fondLeft)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches()) {

                            flecheg = Float.parseFloat(fieldFlecheGauche.getText());
                            flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                            CylindreHorizontale cylindreHorizontale = new CylindreHorizontale(diametre, longueur, flecheg, 0);
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else if (synonymeForNatureFlecheConiqueOfCylinder(fondLeft)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches()) {

                            flecheg = Float.parseFloat(fieldFlecheGauche.getText());
                            flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                            CylindreHorizontale cylindreHorizontale = new CylindreHorizontale(diametre, longueur, flecheg, 0, "conique", "");
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }

                } else if (synonymeElliptiqueMultiLangue(fondRight)) {

                    if (synonymeFormeCapaciteMultiLangueForSphere(fondLeft)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                            fleched = Float.parseFloat(fieldFlecheDroit.getText());
                            fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);

                            CylindreHorizontale cylindreHorizontale = new CylindreHorizontale(diametre, longueur, diametre / 2, fleched);
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else if (synonymeForNatureFlecheConiqueOfCylinder(fondLeft)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches() && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                            fleched = Float.parseFloat(fieldFlecheDroit.getText());
                            flecheg = Float.parseFloat(fieldFlecheGauche.getText());

                            fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);
                            flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                            CylindreHorizontale cylindreHorizontale = new CylindreHorizontale(diametre, longueur, flecheg, fleched, "conique", "");
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }

                } else if (synonymeElliptiqueMultiLangue(fondLeft)) {

                    if (synonymeFormeCapaciteMultiLangueForSphere(fondRight)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches()) {

                            flecheg = Float.parseFloat(fieldFlecheGauche.getText());

                            flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                            CylindreHorizontale cylindreHorizontale = new CylindreHorizontale(diametre, longueur, flecheg, diametre / 2);
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else if (synonymeForNatureFlecheConiqueOfCylinder(fondRight)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches() && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                            fleched = Float.parseFloat(fieldFlecheDroit.getText());
                            flecheg = Float.parseFloat(fieldFlecheGauche.getText());

                            fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);
                            flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                            CylindreHorizontale cylindreHorizontale = new CylindreHorizontale(diametre, longueur, flecheg, fleched, "", "conique");
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }
                } else if (synonymeFormeCapaciteMultiLangueForSphere(fondRight) && synonymeForNatureFlecheConiqueOfCylinder(fondLeft)) {

                    if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches()) {

                        flecheg = Float.parseFloat(fieldFlecheGauche.getText());

                        flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                        CylindreHorizontale cylindreHorizontale = new CylindreHorizontale(diametre, longueur, flecheg, diametre / 2, "conique", "");
                        cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                        formsfigurePanel.add(cylindreHorizontale);
                    } else {
                        JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                    }

                } else if (synonymeFormeCapaciteMultiLangueForSphere(fondLeft) && synonymeForNatureFlecheConiqueOfCylinder(fondRight)) {

                    if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                        fleched = Float.parseFloat(fieldFlecheDroit.getText());

                        fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);

                        CylindreHorizontale cylindreHorizontale = new CylindreHorizontale(diametre, longueur, diametre / 2, fleched, "", "conique");
                        cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                        formsfigurePanel.add(cylindreHorizontale);
                    } else {
                        JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                    }

                }

            }

        } 
        else if (synonymeForOrientationVerticale(formeRecipentSelectionne)) {

            if (fondLeft.equals(fondRight)) {

                if (synonymeElliptiqueMultiLangue(fondRight)) {

                    if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches() && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                        fleched = Float.parseFloat(fieldFlecheDroit.getText());
                        flecheg = Float.parseFloat(fieldFlecheGauche.getText());
                        fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);
                        flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                        CylinderVerticale cylindreHorizontale = new CylinderVerticale(diametre, longueur, flecheg, fleched);
//                    CylindreElliptique ellipticCylindric = new CylindreElliptique(diametre, longueur, diametre / 4, diametre / 4);
                        cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                        formsfigurePanel.add(cylindreHorizontale);
                    } else {
                        JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                    }

                } else if (synonymeFormeCapaciteMultiLangueForSphere(fondRight)) {
                    CylinderVerticale cylindreHorizontale = new CylinderVerticale(diametre, longueur, diametre / 2, diametre / 2);
                    cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                    formsfigurePanel.add(cylindreHorizontale);

                } else if (synonymeForNatureFlecheConiqueOfCylinder(fondRight)) {

                    if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches() && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                        flecheg = Float.parseFloat(fieldFlecheGauche.getText());
                        fleched = Float.parseFloat(fieldFlecheDroit.getText());

                        fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);
                        flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                        CylinderVerticale cylindreHorizontale = new CylinderVerticale(diametre, longueur, flecheg, fleched, "conique", "conique");
                        cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                        formsfigurePanel.add(cylindreHorizontale);
                    } else {
                        JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                    }

                } else if (synonymeForNatureFlechePlatOfCylinder(fondRight)) {
                    CylinderVerticale cylindreHorizontale = new CylinderVerticale(diametre, longueur, 0, 0);
                    cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                    formsfigurePanel.add(cylindreHorizontale);
                }

            } else {

                if (synonymeForNatureFlechePlatOfCylinder(fondLeft)) {

                    if (synonymeFormeCapaciteMultiLangueForSphere(fondRight)) {

                        CylinderVerticale cylindreHorizontale = new CylinderVerticale(diametre, longueur, 0, diametre / 2);
                        cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                        formsfigurePanel.add(cylindreHorizontale);
                    } 
                    else if (synonymeElliptiqueMultiLangue(fondRight)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                            fleched = Float.parseFloat(fieldFlecheDroit.getText());
                            fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);

                            CylinderVerticale cylindreHorizontale = new CylinderVerticale(diametre, longueur, 0, fleched );
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } 
                    else if (synonymeForNatureFlecheConiqueOfCylinder(fondRight)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                            fleched = Float.parseFloat(fieldFlecheDroit.getText());
                            fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);

                            CylinderVerticale cylindreHorizontale = new CylinderVerticale(diametre, longueur, 0, fleched, "", "conique");
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }

                } else if (synonymeForNatureFlechePlatOfCylinder(fondRight)) {

                    if (synonymeFormeCapaciteMultiLangueForSphere(fondLeft)) {

                        CylinderVerticale cylindreHorizontale = new CylinderVerticale(diametre, longueur,diametre / 2, 0);
                        cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                        formsfigurePanel.add(cylindreHorizontale);
                    } else if (synonymeElliptiqueMultiLangue(fondLeft)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches()) {

                            flecheg = Float.parseFloat(fieldFlecheGauche.getText());
                            flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                            CylinderVerticale cylindreHorizontale = new CylinderVerticale(diametre, longueur, flecheg, 0);
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else if (synonymeForNatureFlecheConiqueOfCylinder(fondLeft)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches()) {

                            flecheg = Float.parseFloat(fieldFlecheGauche.getText());
                            flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                            CylinderVerticale cylindreHorizontale = new CylinderVerticale(diametre, longueur, flecheg, 0, "conique", "");
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }

                } else if (synonymeElliptiqueMultiLangue(fondRight)) {

                    if (synonymeFormeCapaciteMultiLangueForSphere(fondLeft)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                            fleched = Float.parseFloat(fieldFlecheDroit.getText());
                            fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);

                            CylinderVerticale cylindreHorizontale = new CylinderVerticale(diametre, longueur, diametre / 2, fleched);
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else if (synonymeForNatureFlecheConiqueOfCylinder(fondLeft)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches() && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                            fleched = Float.parseFloat(fieldFlecheDroit.getText());
                            flecheg = Float.parseFloat(fieldFlecheGauche.getText());

                            fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);
                            flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                            CylinderVerticale cylindreHorizontale = new CylinderVerticale(diametre, longueur, flecheg, fleched, "conique", "");
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }

                } else if (synonymeElliptiqueMultiLangue(fondLeft)) {

                    if (synonymeFormeCapaciteMultiLangueForSphere(fondRight)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches()) {

                            flecheg = Float.parseFloat(fieldFlecheGauche.getText());
                            flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                            CylinderVerticale cylindreHorizontale = new CylinderVerticale(diametre, longueur, flecheg, diametre / 2);
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else if (synonymeForNatureFlecheConiqueOfCylinder(fondRight)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches() && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                            fleched = Float.parseFloat(fieldFlecheDroit.getText());
                            flecheg = Float.parseFloat(fieldFlecheGauche.getText());

                            fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);
                            flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                            CylinderVerticale cylindreHorizontale = new CylinderVerticale(diametre, longueur, flecheg, fleched, "", "conique");
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }
                } else if (synonymeFormeCapaciteMultiLangueForSphere(fondRight) && synonymeForNatureFlecheConiqueOfCylinder(fondLeft)) {

                    if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches()) {

                        flecheg = Float.parseFloat(fieldFlecheGauche.getText());

                        flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                        CylinderVerticale cylindreHorizontale = new CylinderVerticale(diametre, longueur, flecheg, diametre / 2, "conique", "");
                        cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                        formsfigurePanel.add(cylindreHorizontale);
                    } else {
                        JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                    }

                } else if (synonymeFormeCapaciteMultiLangueForSphere(fondLeft) && synonymeForNatureFlecheConiqueOfCylinder(fondRight)) {

                    if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                        fleched = Float.parseFloat(fieldFlecheDroit.getText());
                        fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);

                        CylinderVerticale cylindreHorizontale = new CylinderVerticale(diametre, longueur, diametre / 2, fleched, "", "conique");
                        cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                        formsfigurePanel.add(cylindreHorizontale);
                    } else {
                        JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                    }

                }

            }

        } else if (synonymeForOrientationOblique(formeRecipentSelectionne)) {

            if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldInclineAngle.getText()).matches()) {

                float angle = Float.parseFloat(fieldInclineAngle.getText());

                if (fondLeft.equals(fondRight)) {

                    if (synonymeElliptiqueMultiLangue(fondRight)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches() && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                            fleched = Float.parseFloat(fieldFlecheDroit.getText());
                            flecheg = Float.parseFloat(fieldFlecheGauche.getText());

                            fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);
                            flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                            CylindreHorizontaleIncline cylindreHorizontale = new CylindreHorizontaleIncline(angle, diametre, longueur, flecheg, fleched);
//                    CylindreElliptique ellipticCylindric = new CylindreElliptique(diametre, longueur, diametre / 4, diametre / 4);
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else if (synonymeFormeCapaciteMultiLangueForSphere(fondRight)) {
                        CylindreHorizontaleIncline cylindreHorizontale = new CylindreHorizontaleIncline(angle, diametre, longueur, diametre / 2, diametre / 2);
                        cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                        formsfigurePanel.add(cylindreHorizontale);

                    } else if (synonymeForNatureFlecheConiqueOfCylinder(fondRight)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches() && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                            flecheg = Float.parseFloat(fieldFlecheGauche.getText());
                            fleched = Float.parseFloat(fieldFlecheDroit.getText());

                            fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);
                            flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                            CylindreHorizontaleIncline cylindreHorizontale = new CylindreHorizontaleIncline(angle, diametre, longueur, flecheg, fleched, "conique", "conique");
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else if (synonymeForNatureFlechePlatOfCylinder(fondRight)) {
                        CylindreHorizontaleIncline cylindreHorizontale = new CylindreHorizontaleIncline(angle, diametre, longueur, 0, 0);
                        cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                        formsfigurePanel.add(cylindreHorizontale);
                    }

                } else {

                    if (synonymeForNatureFlechePlatOfCylinder(fondLeft)) {

                        if (synonymeFormeCapaciteMultiLangueForSphere(fondRight)) {

                            CylindreHorizontaleIncline cylindreHorizontale = new CylindreHorizontaleIncline(angle, diametre, longueur, 0, diametre / 2);
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else if (synonymeElliptiqueMultiLangue(fondRight)) {

                            if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                                fleched = Float.parseFloat(fieldFlecheDroit.getText());
                                fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);

                                CylindreHorizontaleIncline cylindreHorizontale = new CylindreHorizontaleIncline(angle, diametre, longueur, 0, fleched);
                                cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                                formsfigurePanel.add(cylindreHorizontale);
                            } else {
                                JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                            }

                        } else if (synonymeForNatureFlecheConiqueOfCylinder(fondRight)) {

                            if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                                fleched = Float.parseFloat(fieldFlecheDroit.getText());
                                fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);

                                CylindreHorizontaleIncline cylindreHorizontale = new CylindreHorizontaleIncline(angle, diametre, longueur, 0, fleched, "", "conique");
                                cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                                formsfigurePanel.add(cylindreHorizontale);
                            } else {
                                JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                            }

                        }

                    } else if (synonymeForNatureFlechePlatOfCylinder(fondRight)) {

                        if (synonymeFormeCapaciteMultiLangueForSphere(fondLeft)) {

                            CylindreHorizontaleIncline cylindreHorizontale = new CylindreHorizontaleIncline(angle, diametre, longueur, diametre / 2, 0);
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else if (synonymeElliptiqueMultiLangue(fondLeft)) {

                            if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches()) {

                                flecheg = Float.parseFloat(fieldFlecheGauche.getText());
                                flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                                CylindreHorizontaleIncline cylindreHorizontale = new CylindreHorizontaleIncline(angle, diametre, longueur, flecheg, 0);
                                cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                                formsfigurePanel.add(cylindreHorizontale);
                            } else {
                                JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                            }

                        } else if (synonymeForNatureFlecheConiqueOfCylinder(fondLeft)) {

                            if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches()) {

                                flecheg = Float.parseFloat(fieldFlecheGauche.getText());
                                flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                                CylindreHorizontaleIncline cylindreHorizontale = new CylindreHorizontaleIncline(angle, diametre, longueur, flecheg, 0, "conique", "");
                                cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                                formsfigurePanel.add(cylindreHorizontale);
                            } else {
                                JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                            }

                        }

                    } else if (synonymeElliptiqueMultiLangue(fondRight)) {

                        if (synonymeFormeCapaciteMultiLangueForSphere(fondLeft)) {

                            if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                                fleched = Float.parseFloat(fieldFlecheDroit.getText());
                                fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);

                                CylindreHorizontaleIncline cylindreHorizontale = new CylindreHorizontaleIncline(angle, diametre, longueur, diametre / 2, fleched);
                                cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                                formsfigurePanel.add(cylindreHorizontale);
                            } else {
                                JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                            }

                        } else if (synonymeForNatureFlecheConiqueOfCylinder(fondLeft)) {

                            if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches() && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                                fleched = Float.parseFloat(fieldFlecheDroit.getText());
                                flecheg = Float.parseFloat(fieldFlecheGauche.getText());

                                fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);
                                flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                                CylindreHorizontaleIncline cylindreHorizontale = new CylindreHorizontaleIncline(angle, diametre, longueur, flecheg, fleched, "conique", "");
                                cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                                formsfigurePanel.add(cylindreHorizontale);
                            } else {
                                JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                            }

                        }

                    } else if (synonymeElliptiqueMultiLangue(fondLeft)) {

                        if (synonymeFormeCapaciteMultiLangueForSphere(fondRight)) {

                            if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches()) {

                                flecheg = Float.parseFloat(fieldFlecheGauche.getText());

                                flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                                CylindreHorizontaleIncline cylindreHorizontale = new CylindreHorizontaleIncline(angle, diametre, longueur, flecheg, diametre / 2);
                                cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                                formsfigurePanel.add(cylindreHorizontale);
                            } else {
                                JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                            }

                        } else if (synonymeForNatureFlecheConiqueOfCylinder(fondRight)) {

                            if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches() && Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                                fleched = Float.parseFloat(fieldFlecheDroit.getText());
                                flecheg = Float.parseFloat(fieldFlecheGauche.getText());

                                fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);
                                flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                                CylindreHorizontaleIncline cylindreHorizontale = new CylindreHorizontaleIncline(angle, diametre, longueur, flecheg, fleched, "", "conique");
                                cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                                formsfigurePanel.add(cylindreHorizontale);
                            } else {
                                JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                            }

                        }
                    } else if (synonymeFormeCapaciteMultiLangueForSphere(fondRight) && synonymeForNatureFlecheConiqueOfCylinder(fondLeft)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheGauche.getText()).matches()) {

                            flecheg = Float.parseFloat(fieldFlecheGauche.getText());

                            flecheg = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), flecheg);

                            CylindreHorizontaleIncline cylindreHorizontale = new CylindreHorizontaleIncline(angle, diametre, longueur, flecheg, diametre / 2, "conique", "");
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else if (synonymeFormeCapaciteMultiLangueForSphere(fondLeft) && synonymeForNatureFlecheConiqueOfCylinder(fondRight)) {

                        if (Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(fieldFlecheDroit.getText()).matches()) {

                            fleched = Float.parseFloat(fieldFlecheDroit.getText());

                            fleched = baremeArtisan.convertToCentimeter(comboUniteLong.getSelectedItem().toString(), fleched);

                            CylindreHorizontaleIncline cylindreHorizontale = new CylindreHorizontaleIncline(angle, diametre, longueur, diametre / 2, fleched, "", "conique");
                            cylindreHorizontale.setPreferredSize(new Dimension(600, 500));
                            formsfigurePanel.add(cylindreHorizontale);
                        } else {
                            JOptionPane.showMessageDialog(null, "les valeurs de la fleche sont incorrectes.".toUpperCase(), "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }

                }

            } else {

            }

        }
        formsfigurePanel.revalidate();
        // formsfigurePanel.updateUI();
        formsfigurePanel.repaint();

    }

    public boolean synonymeForNatureFlecheConiqueOfCylinder(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Conique") || formeCapacite.equalsIgnoreCase("Conical") || formeCapacite.equalsIgnoreCase("Konisch")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean synonymeFormeCapaciteMultiLangueForCylindre(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Cylindrique") || formeCapacite.equalsIgnoreCase("Cylindrical") || formeCapacite.equalsIgnoreCase("Zylindrisch")) {
            return true;
        } else {
            return false;
        }

    }
    
    public boolean synonymeFormeCapaciteMultiLangueForFormeNonConventionnelle(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Forme Non Conventionnelle") || formeCapacite.equalsIgnoreCase("No Conventional Forms")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean synonymeFormeCapaciteMultiLangueForCube(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Kubisch") || formeCapacite.equalsIgnoreCase("Parallélépipède") || formeCapacite.equalsIgnoreCase("Parallelepiped")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean synonymeFormeCapaciteMultiLangueForSphere(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Sphärisch") || formeCapacite.equalsIgnoreCase("Spherical") || formeCapacite.equalsIgnoreCase("Spherique")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean synonymeFormeCapaciteMultiLangueForEllpsoide(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Ellipsoide") || formeCapacite.equalsIgnoreCase("Elliptical") || formeCapacite.equalsIgnoreCase("Elliptisch") || formeCapacite.equalsIgnoreCase("Elliptique")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean synonymeElliptiqueMultiLangue(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Ellipsoide") || formeCapacite.equalsIgnoreCase("Elliptical") || formeCapacite.equalsIgnoreCase("Elliptisch") || formeCapacite.equalsIgnoreCase("Elliptique")
                || formeCapacite.equalsIgnoreCase("torispherique") || formeCapacite.equalsIgnoreCase("hemispherique") || formeCapacite.equalsIgnoreCase("bomber")
                || formeCapacite.equalsIgnoreCase("hemiSpherical") || formeCapacite.equalsIgnoreCase("halbkugelformig") || formeCapacite.equalsIgnoreCase("torispheric") || formeCapacite.equalsIgnoreCase("torispharisch")
                || formeCapacite.equalsIgnoreCase("Forme Non Conventionnelle") || formeCapacite.equalsIgnoreCase("No Conventional Forms")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean synonymeForOrientationVerticale(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Vertikal") || formeCapacite.equalsIgnoreCase("Vertical")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean synonymeFormeCapaciteMultiLangueForHemispherique(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("hemispherique") || formeCapacite.equalsIgnoreCase("hemiSpherical") || formeCapacite.equalsIgnoreCase("halbkugelformig")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean synonymeFormeCapaciteMultiLangueForToriSpherique(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("torispherique") || formeCapacite.equalsIgnoreCase("torispheric") || formeCapacite.equalsIgnoreCase("torispharisch")) {
            return true;
        } else {
            return false;
        }

    }

    private boolean synonymeForOrientationOblique(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Oblique") || formeCapacite.equalsIgnoreCase("Schräg")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean synonymeForNatureFlechePlatOfCylinder(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Plat") || formeCapacite.equalsIgnoreCase("Flat") || formeCapacite.equalsIgnoreCase("Flach")) {
            return true;
        } else {
            return false;
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboFormeCapacite;
    private javax.swing.JComboBox<String> comboMethodeValue;
    private javax.swing.JComboBox<String> comboNatureFDValue;
    private javax.swing.JComboBox<String> comboNatureFDValueMoindreCarree;
    private javax.swing.JComboBox<String> comboNatureFGValue;
    private javax.swing.JComboBox<String> comboNatureFGValueMoindreCarree;
    private javax.swing.JComboBox<String> comboOrientationValue;
    private javax.swing.JComboBox<String> comboUniteLong;
    private javax.swing.JComboBox<String> comboUniteVolume;
    private javax.swing.JButton draw;
    private javax.swing.JButton enroll;
    private javax.swing.JTextField fieldCoteCube;
    private javax.swing.JTextField fieldDiametre;
    private javax.swing.JTextField fieldFlecheDroit;
    private javax.swing.JTextField fieldFlecheGauche;
    private javax.swing.JTextField fieldGrandAxe;
    private javax.swing.JTextField fieldHauteur;
    private javax.swing.JTextField fieldInclineAngle;
    private javax.swing.JTextField fieldLongueur;
    private javax.swing.JTextField fieldPetitAxe;
    private javax.swing.JTextField fieldPositionx;
    private javax.swing.JTextField fieldPositiony;
    private javax.swing.JTextField fieldPositionz;
    private javax.swing.JTextField fieldRayonSphere;
    private javax.swing.JPanel formsfigurePanel;
    private javax.swing.JLabel invalidFields;
    private javax.swing.JRadioButton jRadioBtnNonTrigonometrique;
    private javax.swing.JRadioButton jRadioBtnTrigonometrique;
    private javax.swing.JLabel labelCote;
    private javax.swing.JLabel labelDiametre;
    private javax.swing.JLabel labelFlecheDroite;
    private javax.swing.JLabel labelFlecheGauche;
    private javax.swing.JLabel labelFormsCapacity;
    private javax.swing.JLabel labelGrandAxe;
    private javax.swing.JLabel labelHauteur;
    private javax.swing.JLabel labelInclineAngle;
    private javax.swing.JLabel labelLength;
    private javax.swing.JLabel labelModeOperatoire;
    private javax.swing.JLabel labelNatureFlecheDroite;
    private javax.swing.JLabel labelNatureFlecheDroiteMoindreCarree;
    private javax.swing.JLabel labelNatureFlecheGauche;
    private javax.swing.JLabel labelNatureFlecheGaucheMoindreCarree;
    private javax.swing.JLabel labelOriention;
    private javax.swing.JLabel labelPetitAxe;
    private javax.swing.JLabel labelPositionX;
    private javax.swing.JLabel labelPositionY;
    private javax.swing.JLabel labelPositionZ;
    private javax.swing.JLabel labelRayon;
    private javax.swing.JLabel labelUniteHeight;
    private javax.swing.JLabel labelUniteVolume;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel panelButton;
    private javax.swing.JPanel panelCoteCube;
    private javax.swing.JPanel panelData;
    private javax.swing.JPanel panelFormeElliptique;
    private javax.swing.JPanel panelMethode;
    private javax.swing.JPanel panelOrientation;
    private javax.swing.JPanel panelRayonSphere;
    private javax.swing.JPanel panelUnite;
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

        labelUniteVolume.setText(valeurs.getString("unite_volume"));
        
        labelUniteHeight.setText(valeurs.getString("unite_longueur"));
        labelOriention.setText(valeurs.getString("orientation"));
        labelFormsCapacity.setText(valeurs.getString("forme_capacite"));
        //labelFondCapacity.setText(valeurs.getString("fond_capacite"));
        labelInclineAngle.setText(valeurs.getString("angle_incline"));
        labelPositionX.setText(valeurs.getString("positionx"));
        labelPositionY.setText(valeurs.getString("positiony"));

        labelPositionZ.setText(valeurs.getString("positionz"));
        labelLength.setText(valeurs.getString("longueur"));
        labelDiametre.setText(valeurs.getString("diametre"));
        labelNatureFlecheGauche.setText(valeurs.getString("nature_fleche_g"));
        labelNatureFlecheDroite.setText(valeurs.getString("nature_fleche_d"));
        labelFlecheGauche.setText(valeurs.getString("fleche_g"));
        labelFlecheDroite.setText(valeurs.getString("fleche_d"));
        labelModeOperatoire.setText(valeurs.getString("modeOperatoire"));
        labelNatureFlecheGaucheMoindreCarree.setText(valeurs.getString("nature_fleche_g_carre"));
        labelNatureFlecheDroiteMoindreCarree.setText(valeurs.getString("nature_fleche_d_carre"));
        
        
        if (tabIndexComoBoxActve[4] == 1) {

            labelHauteur.setText(valeurs.getString("hauteur"));
            labelPetitAxe.setText(valeurs.getString("petit_axe"));
            labelGrandAxe.setText(valeurs.getString("longueur"));

        } else {
                
            labelHauteur.setText(valeurs.getString("hauteur"));
            labelPetitAxe.setText(valeurs.getString("petit_axe"));
            labelGrandAxe.setText(valeurs.getString("grand_axe"));
        
        }
        

        labelRayon.setText(valeurs.getString("rayon"));
        labelCote.setText(valeurs.getString("cote_cube"));
        //labelVolumeResiduel.setText(valeurs.getString("volume_residuel"));

        draw.setText(valeurs.getString("show_shape"));
        enroll.setText(valeurs.getString("enroll"));
        comboUniteVolume.removeAllItems();
        for (int i = 1; i <= 6; i++) {
            String key = "uniteV" + i;
            String fondTraduit = valeurs.getString(key);
            comboUniteVolume.addItem(fondTraduit);
        }
        comboUniteLong.removeAllItems();
        for (int i = 1; i <= 7; i++) {
            String key = "uniteVol" + i;
            String fondTraduit = valeurs.getString(key);
            comboUniteLong.addItem(fondTraduit);
        }
        comboFormeCapacite.removeAllItems();
        for (int i = 1; i <= 5; i++) {
            String key = "formeCap" + i;
            String fondTraduit = valeurs.getString(key);
            comboFormeCapacite.addItem(fondTraduit);
        }
        comboFormeCapacite.removeAllItems();
        for (int i = 1; i <= 4; i++) {
            String key = "formeCap" + i;
            String fondTraduit = valeurs.getString(key);
            comboFormeCapacite.addItem(fondTraduit);
        }
        comboOrientationValue.removeAllItems();
        for (int i = 1; i <= 3; i++) {
            String key = "orientationV" + i;
            String fondTraduit = valeurs.getString(key);
            comboOrientationValue.addItem(fondTraduit);
        }
        comboNatureFDValue.removeAllItems();
        for (int i = 1; i <= 4; i++) {
            String key = "fleche" + i;
            String fondTraduit = valeurs.getString(key);
            comboNatureFDValue.addItem(fondTraduit);
        }
        comboNatureFGValue.removeAllItems();
        for (int i = 1; i <= 4; i++) {
            String key = "fleche" + i;
            String fondTraduit = valeurs.getString(key);
            comboNatureFGValue.addItem(fondTraduit);
        }

        comboNatureFDValueMoindreCarree.removeAllItems();
        for (int i = 1; i <= 6; i++) {
            String key = "fleche_carre" + i;
            String fondTraduit = valeurs.getString(key);
            comboNatureFDValueMoindreCarree.addItem(fondTraduit);
        }

        comboNatureFGValueMoindreCarree.removeAllItems();
        for (int i = 1; i <= 6; i++) {
            String key = "fleche_carre" + i;
            String fondTraduit = valeurs.getString(key);
            comboNatureFGValueMoindreCarree.addItem(fondTraduit);
        }
        updateComboIndex();
    }

    public void updateComboIndex() {
        String methodCalculGeometrie = MainView.getMethodCalculGeometrie();

        comboMethodeValue.setSelectedIndex(tabIndexComoBoxActve[0]);
        comboUniteLong.setSelectedIndex(tabIndexComoBoxActve[1]);
        comboUniteVolume.setSelectedIndex(tabIndexComoBoxActve[2]);
        comboFormeCapacite.setSelectedIndex(tabIndexComoBoxActve[3]);
        
        if (tabIndexComoBoxActve[4] != 2) {
            panelOrientation.setVisible(false);
        }
        comboOrientationValue.setSelectedIndex(tabIndexComoBoxActve[4]);

        comboNatureFGValue.setSelectedIndex(tabIndexComoBoxActve[5]);
        comboNatureFDValue.setSelectedIndex(tabIndexComoBoxActve[6]);
        comboNatureFGValueMoindreCarree.setSelectedIndex(tabIndexComoBoxActve[7]);
        comboNatureFDValueMoindreCarree.setSelectedIndex(tabIndexComoBoxActve[8]);

        switch (tabIndexComoBoxActve[3]) {
            case 3:
                panelData.setVisible(false);
                panelFormeElliptique.setVisible(true);
                panelRayonSphere.setVisible(false);
                //panelCoteCube.setVisible(true);
                break;

            case 1:
                panelData.setVisible(false);
                panelFormeElliptique.setVisible(true);
                panelRayonSphere.setVisible(false);
                panelCoteCube.setVisible(false);
                break;

            case 2:
                panelData.setVisible(false);
                panelFormeElliptique.setVisible(false);
                panelRayonSphere.setVisible(true);
                panelCoteCube.setVisible(false);
                break;
        }

        if( tabIndexComoBoxActve[4] == 1 && tabIndexComoBoxActve[3] == 0 ){
            labelFlecheGauche.setVisible(true);
            labelFlecheDroite.setVisible(true);

             if (tabIndexComoBoxActve[5] == 0 || tabIndexComoBoxActve[5] == 3) {

                fieldFlecheGauche.setVisible(true);
             } else {

                fieldFlecheGauche.setVisible(false);
             }

             if (tabIndexComoBoxActve[6] == 0 || tabIndexComoBoxActve[6] == 3) {

                fieldFlecheDroit.setVisible(true);
             } else {

                fieldFlecheDroit.setVisible(false);
             }
        
        }
       
        if (  !synonymeForOrientationVerticale( comboOrientationValue.getSelectedItem().toString() ) ) {
            labelFlecheDroite.setVisible(false);
            fieldFlecheDroit.setVisible(false);
            labelFlecheGauche.setVisible(false);
            fieldFlecheGauche.setVisible(false);
            
              if (tabIndexComoBoxActve[7] >= 5 ) {

                fieldFlecheGauche.setVisible(true);
             } else {

                fieldFlecheGauche.setVisible(false);
             }

             if (tabIndexComoBoxActve[8] >= 5) {

                fieldFlecheDroit.setVisible(true);
             } else {

                fieldFlecheDroit.setVisible(false);
             }
            
        }

    }

}
