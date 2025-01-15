package nyettotank2.metier;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import nyettotank2.courbe.ZoneGeometrie;
import nyettotank2.courbe.ZoneVolume;

public class DataResumeDialog extends JDialog {

    private BaremeArtisan bareme = new BaremeArtisan();

    public DataResumeDialog(JFrame parent, String title, boolean modal) {
        super(parent, title, modal);
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
//this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

    }

    public void viewDataResumePropertiesDialog(HashMap info, HashMap data) {

        Object[] setInfo = info.entrySet().toArray();
        Object[] setData = null;
        String[] a = null;
        Matcher m;

        System.out.println("affichage sur propiete  " + info);
        System.out.println("affichage sur data  " + data);
        String[] titre = {"NOM", "VALEUR", "UNITE"};

        Object[][] dat = new Object[info.size() + data.size()][3];
        if (data.size() != 0) {
            setData = data.entrySet().toArray();
        }

        for (int i = 0; i < setInfo.length; i++) {
            a = setInfo[i].toString().split("=");
            if (a.length == 2) {
                m = Pattern.compile("[0-9]+\\.[\\d]+|\\d+").matcher(a[1]);
                dat[i][0] = a[0];
                dat[i][1] = a[1];
                if (a[0].startsWith("volume")) {
                    dat[i][2] = info.get("unite de volume").toString();
                } else if (a[0].startsWith("hauteur")) {
                    dat[i][2] = info.get("unite des hauteurs").toString();

                } //else if (m.matches() && !a[0].contains("nombre")) {
//                dat[i][2] = data.get("unite des hauteurs").toString();
//            }
            }
        }

        if (data.size() != 0) {
            for (int i = 0; i < setData.length; i++) {
                a = setData[i].toString().split("=");
                if (a.length == 2) {
                    dat[i + info.size()][0] = a[0];
                    dat[i + info.size()][1] = a[1];
                    if (!a[0].contains("angle")) {
                        dat[i + info.size()][2] = info.get("unite des hauteurs").toString();

                    }
                    if (a[0].contains("volume")) {
                        dat[i + info.size()][2] = info.get("unite de volume").toString();

                    }
                }
            }
        }

        JTable mytable = new JTable(dat, titre);
        
        mytable.setRowHeight(30);
        this.getContentPane().removeAll();
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(mytable), BorderLayout.CENTER);
        this.setVisible(true);

    }

    public void ViewTableHeightToVolumeGeometrie(HashMap info, HashMap data, JTextField resultLittre, JTextField resultBaril, JTextField m3Result) {

        JTable mytable2 = null;
        if (data.containsKey("longueur") || data.containsKey("diametre") || data.containsKey("rayon sphere") || data.containsKey("grand axe") || data.containsKey("petit axe")
                || data.containsKey("fleche cote gauche") || data.containsKey("fleche cote droit")) {

            Object[][] dat = null;
            String formeCapacite = info.get("forme de la capacite").toString().toUpperCase();
            float diametre = 0;

             if (synonymeFormeCapaciteMultiLangueForCylindre(info.get("forme de la capacite").toString().toLowerCase())) {
                diametre = Float.parseFloat(data.get("diametre").toString());

            } else if (synonymeFormeCapaciteMultiLangueForSphere(info.get("forme de la capacite").toString().toLowerCase())) {
                float rayonSphere = Float.parseFloat(data.get("rayon sphere").toString());
                diametre = 2 * rayonSphere;

            } else if (synonymeFormeCapaciteMultiLangueForCube(info.get("forme de la capacite").toString().toLowerCase())) {
                diametre = Float.parseFloat(data.get("hauteur").toString());
            } else if (synonymeFormeCapaciteMultiLangueForEllpsoide(info.get("forme de la capacite").toString().toLowerCase())) {
                diametre = Float.parseFloat(data.get("hauteur").toString());

            }
            
              if (synonymeForOrientationVerticale(info.get("orientation").toString() ) && synonymeFormeCapaciteMultiLangueForCylindre(formeCapacite)) {
                diametre = Float.parseFloat(data.get("longueur").toString());
                if (data.containsKey("fleche cote gauche")) {

                    diametre += Float.parseFloat(data.get("fleche cote gauche").toString());

                }
                if (data.containsKey("fleche cote droit")) {

                    diametre += Float.parseFloat(data.get("fleche cote droit").toString());

                }

            }
              
            if( synonymeForOrientationOblique( info.get("orientation").toString() ) && synonymeFormeCapaciteMultiLangueForCylindre(formeCapacite) ){
            
                float angleInclinaison = Float.parseFloat(data.get("angle inclinaison").toString());
                float sinusAngle = (float) Math.sin(Math.PI*angleInclinaison/180);
                float cosinusAngle = (float) Math.cos(Math.PI*angleInclinaison/180);
                diametre = Float.parseFloat(data.get("longueur").toString())*sinusAngle + diametre*cosinusAngle;

            
            }

            if (!info.get("unite des hauteurs").toString().toLowerCase().equals("cm")) {

                String[] titre = {"HAUTEUR(cm)", "HAUTEUR(" + info.get("unite des hauteurs").toString() + ")",
                    "VOLUME(" + info.get("unite de volume").toString() + ")", "REMPLISSAGE"};
                String unit = info.get("unite des hauteurs").toString();
                diametre = bareme.convertToCentimeter(unit, diametre);
                int diamInteger = (int) Math.floor(diametre);
                dat = new Object[diamInteger + 3][4];

                dat[diamInteger + 2][1] = diametre;

                String unit_vol = info.get("unite de volume").toString();

                double volumeRecipient = bareme.fonctionCalculVolume(data, info, diametre); 
                volumeRecipient = volumeRecipient / 1000;
                resultLittre.setText(String.valueOf(volumeRecipient));
                m3Result.setText(String.valueOf(bareme.convertToVolumeDesired("m3", volumeRecipient)));
                resultBaril.setText(String.valueOf(bareme.convertToVolumeDesired("baril", volumeRecipient)));

                volumeRecipient = bareme.convertToVolumeDesired(unit_vol, volumeRecipient);

                dat[diamInteger + 2][0] = diametre;
                dat[diamInteger + 2][2] = volumeRecipient;
                dat[diamInteger + 2][3] = 100;

                for (int i = 1; i <= diamInteger; i++) {

                    double volCalcule = bareme.fonctionCalculVolume(data, info, i);
                    volCalcule = volCalcule / 1000;
                    volCalcule = bareme.convertToVolumeDesired(unit_vol, volCalcule);
                    float percent = (float) (volCalcule / volumeRecipient);
                    percent *= 100;

                    float heightConvert = bareme.convertToUnitDesire(unit, i);

                    dat[i - 1][0] = i;
                    dat[i - 1][1] = twoDecimale(heightConvert + "");
                    dat[i - 1][2] = twoDecimale(volCalcule + "");
                    dat[i - 1][3] = twoDecimale(percent + "");

                }
                mytable2 = new JTable(dat, titre);
            } else {

                String[] titre = {"HAUTEUR(cm)", "VOLUME(" + info.get("unite de volume").toString() + ")",
                    "REMPLISSAGE"};
                // int diam = parseInt(data.get("diametre").toString());

                String unit_vol = info.get("unite de volume").toString();

                int diamInteger = (int) Math.floor(diametre);
                double volumeRecipient = bareme.fonctionCalculVolume(data, info, diametre);
                volumeRecipient = volumeRecipient / 1000;

                resultLittre.setText(String.valueOf(volumeRecipient));
                m3Result.setText(String.valueOf(bareme.convertToVolumeDesired("m3", volumeRecipient)));
                resultBaril.setText(String.valueOf(bareme.convertToVolumeDesired("baril", volumeRecipient)));

                volumeRecipient = bareme.convertToVolumeDesired(unit_vol, volumeRecipient);

                dat = new Object[diamInteger + 3][3];

                dat[diamInteger + 2][0] = diametre;
                dat[diamInteger + 2][1] = volumeRecipient;
                dat[diamInteger + 2][2] = 100;

                for (int i = 1; i <= diamInteger; i++) {

                    double volCalcule = bareme.fonctionCalculVolume(data, info, i);
                    volCalcule = volCalcule / 1000;
                    volCalcule = bareme.convertToVolumeDesired(unit_vol, volCalcule);
                    double percent = volCalcule / volumeRecipient;
                    percent *= 100;

                    dat[i - 1][0] = i;
                    dat[i - 1][1] = twoDecimale(volCalcule + "");
                    dat[i - 1][2] = twoDecimale(percent + "");

                }
                mytable2 = new JTable(dat, titre);

            }
            this.getContentPane().removeAll();
                    mytable2.setRowHeight(30);
            this.getContentPane().add(new JScrollPane(mytable2), BorderLayout.CENTER);
            this.setVisible(true);
        } else {
            JOptionPane optionPane = new JOptionPane();
            optionPane.showMessageDialog(null,
                    "veiller renseigner les differentes valeurs tels que : \n la longueur, le diametre, les differentes fleches des parties donnee geometrique"
                            .toUpperCase(),
                    "Message d'erreur".toUpperCase(), JOptionPane.ERROR_MESSAGE);
        }

    }

    public String twoDecimale(String value) {
        String ch = "" + value;
        int pos = ch.indexOf(".");
        if (pos >= 0) {
            if ((ch.length() - pos) > 6) {
                return ch.substring(0, pos + 6);
            } else {
                return ch;
            }
        }
        return ch;
    }

    public void ViewTableHeightToVolumeVolumetrie(HashMap info, HashMap data, List<Line> myLine) {

        List<Float> abcisse = new ArrayList();
        List<Double> ordonne = new ArrayList();
        JTable mytable = null;

        for (int i = 0; i < myLine.size(); i++) {

            abcisse.add(i, myLine.get(i).getHauteur());
            ordonne.add(i, myLine.get(i).getVolume());
        }

        ordonne.add(0, 0d);
        abcisse.add(0, (float) 0.00);
        Sline fonction = new Sline(abcisse, ordonne);
        String unit = info.get("unite des hauteurs").toString();
        String unit_vol = info.get("unite de volume").toString();

        Object[][] dat = null;
        if (!info.get("unite des hauteurs").toString().toLowerCase().equals("cm")) {

            String[] titre = {"HAUTEUR(cm)", "HAUTEUR(" + info.get("unite des hauteurs").toString() + ")",
                "VOLUME(" + info.get("unite de volume").toString() + ")", "REMPLISSAGE"};

            float lastHeiht = bareme.convertToCentimeter(unit, abcisse.get(abcisse.size() - 1));
            int lineNumber = (int) Math.floor(lastHeiht);
            dat = new Object[lineNumber + 2 + abcisse.size()][4];

            double lastVolume = ordonne.get(abcisse.size() - 1);
            int increment = 0;

            while (increment < abcisse.size() - 1) {

                float heightTest = bareme.convertToCentimeter(unit, abcisse.get(increment)) + 1; // en cm
                float nextAbcisse = bareme.convertToCentimeter(unit, abcisse.get(increment + 1)); // en cm
                int heightTestInt = (int) Math.floor(heightTest);
                double percent = 0d;

                while (heightTest <= nextAbcisse) {

                    heightTestInt = (int) Math.floor(heightTest);
                    float currentHeightUnit = bareme.convertToUnitDesire(unit, heightTestInt);
                    double currentVolumeUnit = fonction.interpolate(currentHeightUnit);

                    dat[heightTestInt][0] = heightTestInt;
                    dat[heightTestInt][1] = twoDecimale(currentHeightUnit + "");
                    dat[heightTestInt][2] = twoDecimale(currentVolumeUnit + "");
                    percent = 100 * currentVolumeUnit / lastVolume;
                    dat[heightTestInt][3] = twoDecimale(percent + "");

                    heightTest += 1;
                }

                dat[heightTestInt + 1][0] = nextAbcisse;
                dat[heightTestInt + 1][1] = twoDecimale(abcisse.get(increment + 1) + "");
                dat[heightTestInt + 1][2] = twoDecimale(ordonne.get(increment + 1) + "");
                percent = 100 * ordonne.get(increment + 1) / lastVolume;
                dat[heightTestInt + 1][3] = twoDecimale(percent + "");

                increment++;

            }

            // diametre = bareme.convertToCentimeter(unit, diametre);
            mytable = new JTable(dat, titre);
        } else {

            String[] titre = {"HAUTEUR(cm)", "VOLUME(" + info.get("unite de volume").toString() + ")", "REMPLISSAGE"};

            float lastHeiht = abcisse.get(abcisse.size() - 1);
            int lineNumber = (int) Math.floor(lastHeiht);
            dat = new Object[lineNumber + 2 + abcisse.size()][3];

            double lastVolume = ordonne.get(abcisse.size() - 1);
            int increment = 0;

            while (increment < abcisse.size() - 1) {

                float heightTest = abcisse.get(increment) + 1; // en cm
                float nextAbcisse = abcisse.get(increment + 1); // en cm
                int heightInteger = (int) Math.floor(heightTest);
                float percent = 0f;

                while (heightTest <= nextAbcisse) {

                    heightInteger = (int) Math.floor(heightTest);
                    double currentVolumeUnit = fonction.interpolate(heightInteger);

                    dat[heightInteger][0] = heightInteger;
                    dat[heightInteger][1] = twoDecimale(currentVolumeUnit + "");
                    percent = (float) (100 * currentVolumeUnit / lastVolume);
                    dat[heightInteger][2] = twoDecimale(percent + "");

                    heightTest += 1;
                }

                dat[heightInteger][0] = nextAbcisse;
                dat[heightInteger][1] = ordonne.get(increment + 1);
                percent = (float) (100 * ordonne.get(increment + 1) / lastVolume);
                dat[heightInteger][2] = twoDecimale(percent + "");

                increment++;

            }
            mytable = new JTable(dat, titre);

        }

        this.getContentPane().removeAll();
                mytable.setRowHeight(30);
        this.getContentPane().add(new JScrollPane(mytable), BorderLayout.CENTER);
        this.setVisible(true);

    }

    public DataResumeDialog AfficherCourbeGeometrique(HashMap info, HashMap data) {
        ZoneGeometrie zone = new ZoneGeometrie(info, data);

        this.getContentPane().removeAll();
        this.getContentPane().add(zone, BorderLayout.CENTER);
        return this;
//        this.setVisible(true);

    }

    public DataResumeDialog AfficherCourbeVolumetrique(List<Float> x, List<Double> y, String h, String v) {
        ZoneVolume zone = new ZoneVolume(x, y, h, v);
        this.getContentPane().removeAll();
        this.getContentPane().add(zone, BorderLayout.CENTER);
        return this;
//        this.setVisible(true);
    }
    
    
    public boolean synonymeFormeCapaciteMultiLangueForCylindre(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Cylindrique") || formeCapacite.equalsIgnoreCase("Cylindrical") || formeCapacite.equalsIgnoreCase("Zylindrisch")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean synonymeFormeCapaciteMultiLangueForCube(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Kubisch") || formeCapacite.equalsIgnoreCase("Parallelepiped") || formeCapacite.equalsIgnoreCase("Parallélépipède")) {
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

    public boolean synonymeForNatureFlechePlatOfCylinder(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Plat") || formeCapacite.equalsIgnoreCase("Flat") || formeCapacite.equalsIgnoreCase("Flach")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean synonymeForNatureFlecheConiqueOfCylinder(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Conique") || formeCapacite.equalsIgnoreCase("Conical") || formeCapacite.equalsIgnoreCase("Konisch")) {
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

    public boolean synonymeForOrientationVerticale(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Vertikal") || formeCapacite.equalsIgnoreCase("Vertical")) {
            return true;
        } else {
            return false;
        }

    }


}
