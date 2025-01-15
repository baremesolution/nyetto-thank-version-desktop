package nyettotank2.metier;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import nyettotank2.view.newIHM.DonneeGeometrique;

import static com.lowagie.text.Image.MIDDLE;
import com.lowagie.text.Paragraph;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import nyettotank2.courbe.ZoneGeometrie;
import nyettotank2.courbe.ZoneVolume;
import nyettotank2.view.newIHM.MainView;

//
//import com.lowagie.text.Element;
//import java.awt.Color;
//import java.awt.Component;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import jxl.write.WritableSheet;
//import jxl.write.WritableWorkbook;
//import jxl.write.WriteException;
//import nyettotank2.view.newIHM.MainView;
//import org.apache.poi.ss.usermodel.Font;
//import org.apache.poi.xwpf.usermodel.Document;
public class BaremeArtisan {

    private JOptionPane location = new JOptionPane();
    private JOptionPane jop3 = new JOptionPane();
    private JOptionPane nom_fichier = new JOptionPane();

    public String verifie(HashMap s, String key) {
        if (s.containsKey(key)) {
            return (String) s.get(key);
        } else {
            return "";
        }
    }

    public BaremeArtisan() {
    }

    // on quitte d4une unite de longueur voulue pour le cm
    public float convertToCentimeter(String unite, float value) {

        switch (unite) {

            case "m":
                value = 100 * value;
                break;

            case "dm":
                value = 10 * value;
                break;

            case "pouce":
            case "zoll":
            case "inch":
                value = (float) (2.54 * value);
                break;

            case "pied":
            case "foot":
            case "fuß":
                value = (float) (30.48 * value);
                break;

            case "mm":
                value = value / 10;
                break;

        }
        return value;
    }

    // on d4une unite du litre/dm3 vers une unite de volume desiree
    public double convertToVolumeDesired(String unite, double volumeRecipient) {
        // unite = unite.toLowerCase();
        switch (unite) {
            case "Gallon USA":
            case "US-Gallone":
            case "US Gallon":
                volumeRecipient = (float) (volumeRecipient / 3.78541);
                break;

            case "Gallon Imperial":
            case "Imperial Gallone":
            case "Imperial Gallon":
                volumeRecipient = (float) (volumeRecipient / 4.54609);
                break;

            case "baril":
            case "barrel":
                volumeRecipient = (float) (volumeRecipient / 159);
                break;

            case "m3":
                volumeRecipient = (float) (volumeRecipient / 1000);
                break;

            case "cm3":
                volumeRecipient = (float) (volumeRecipient * 1000);
                break;

        }
        return volumeRecipient;
    }

    // cette est utile a la fonction fonctionCalculVolume pour le calcul du volume
    // d'un recipient de forme cylindrique
    // cette fonction genere un certificat sans entete contenant republic du
    // cameroun et
    // faisant mention des divisions et departemet ministeriel
    // cette fonction genere un certificat sans entete contenant republic du
    // cameroun et
    // fqisqnt mention des divisions et departemet ministeriel
    public float convertToUnitDesire(String unite, float value) {

        switch (unite) {

            case "m":
                value = value / 100;
                break;

            case "pouce":
            case "zoll":
            case "inch":
                value = (float) (value / 2.54);
                break;

            case "pied":
            case "foot":
            case "fuß":
                value = (float) (value / 30.48);
                break;

            case "dm":
                value = (float) (value / 10);
                break;

            case "mm":
                value = value * 10;
                break;

        }
        return value;

    }

    public double fonctionCalculVolume(HashMap data, HashMap info, float val) {

        String unit = info.get("unite des hauteurs").toString();
        float diametre = 0;
        float fleche_g = 0;
        float fleche_d = 0;
        float longueur = 0;
        float hauteur = 0;
        float rayonSphere = 0;
        float grandAxe = 0;
        float petitAxe = 0;
        float largeur = 0;

        if (data.containsKey("diametre")) {
            diametre = Float.parseFloat(data.get("diametre").toString());
            diametre = convertToCentimeter(unit, diametre);
        }

        if (data.containsKey("fleche cote gauche")) {
            fleche_g = Float.parseFloat(data.get("fleche cote gauche").toString());
            fleche_g = convertToCentimeter(unit, fleche_g);

        }
        if (data.containsKey("fleche cote droit")) {
            fleche_d = Float.parseFloat(data.get("fleche cote droit").toString());
            fleche_d = convertToCentimeter(unit, fleche_d);
        }
        if (data.containsKey("longueur")) {
            longueur = Float.parseFloat(data.get("longueur").toString());
            longueur = convertToCentimeter(unit, longueur);
        }

        if (data.containsKey("largeur")) {
            largeur = Float.parseFloat(data.get("largeur").toString());
            largeur = convertToCentimeter(unit, largeur);
        }

        if (data.containsKey("hauteur")) {
            hauteur = Float.parseFloat(data.get("hauteur").toString());
            hauteur = convertToCentimeter(unit, hauteur);
        }

        if (data.containsKey("rayon sphere")) {
            rayonSphere = Float.parseFloat(data.get("rayon sphere").toString());
            rayonSphere = convertToCentimeter(unit, rayonSphere);
        }

        if (data.containsKey("grand axe")) {
            grandAxe = Float.parseFloat(data.get("grand axe").toString());
            grandAxe = convertToCentimeter(unit, grandAxe);
        }

        if (data.containsKey("petit axe")) {
            petitAxe = Float.parseFloat(data.get("petit axe").toString());
            petitAxe = convertToCentimeter(unit, petitAxe);
        }

        double valeur = 0, inter = 0;

        if (synonymeFormeCapaciteMultiLangueForCylindre(info.get("forme de la capacite").toString().toLowerCase())) {

            if (synonymeForOrientationVerticale(info.get("orientation").toString())) {
                valeur = volumeFormeCylindre(unit, info.get("orientation").toString(),
                        info.get("nature fond gauche").toString().toUpperCase(),
                        info.get("nature fond droite").toString().toUpperCase(), data, val);
            } else {
                valeur = volumeFormeCylindreByRayonCarre(unit, info.get("orientation").toString(),
                        info.get("nature fond gauche").toString().toUpperCase(),
                        info.get("nature fond droite").toString().toUpperCase(), data, val);
            }

        } else if (synonymeFormeCapaciteMultiLangueForSphere(
                info.get("forme de la capacite").toString().toLowerCase())) {

            if (val <= rayonSphere) {
                valeur = 2 * Math.PI * Math.pow(val, 2) * (2 * rayonSphere - val) / 3;

            } else if (rayonSphere < val && val <= 2 * rayonSphere) {
                valeur = 2 * Math.PI * (2 * rayonSphere * rayonSphere * rayonSphere
                        - (2 * rayonSphere - val) * (2 * rayonSphere - val) * val) / 3;
            }
        } else if (synonymeFormeCapaciteMultiLangueForCube(info.get("forme de la capacite").toString().toLowerCase())) {
            valeur = longueur * largeur * val;
        } else if (synonymeFormeCapaciteMultiLangueForEllpsoide(
                info.get("forme de la capacite").toString().toLowerCase())) {
            if (hauteur / 2 < val && val <= hauteur) {
                double p = (val * (hauteur - val)) / (hauteur * hauteur);
                double bee2 = p * petitAxe * grandAxe * (hauteur - val), bee1 = grandAxe * petitAxe * hauteur / 2;
                valeur = Math.PI * (bee1 - 2 * bee2) / 3;
            } else if (val <= (hauteur / 2)) {
                valeur = 2 * Math.PI * petitAxe * grandAxe * val * val
                        * (hauteur - val) / (3 * Math.pow(hauteur, 2));
            }
        } else if (synonymeForNatureFlecheConiqueOfCylinder(
                info.get("forme de la capacite").toString().toLowerCase())) {
            inter = diametre * valeur / (2 * longueur);
            valeur = (double) (Math.PI * inter * inter * val / 3);
        }
        return valeur;

    }

    public double volumeFormeCylindreByRayonCarre(String unit, String orientation, String nature_fond_g,
            String nature_fond_d,
            HashMap data, float t) {

        float fleche_g = 0;
        float fleche_d = 0;
        if (data.containsKey("fleche cote gauche")) {
            fleche_g = Float.parseFloat(data.get("fleche cote gauche").toString());
            fleche_g = convertToCentimeter(unit, fleche_g);
        }
        if (data.containsKey("fleche cote droit")) {
            fleche_d = Float.parseFloat(data.get("fleche cote droit").toString());
            fleche_d = convertToCentimeter(unit, fleche_d);
        }

        float longueur = Float.parseFloat(data.get("longueur").toString());
        float diametre = Float.parseFloat(data.get("diametre").toString());
        float r_courbure = (float) 0.00;

        diametre = convertToCentimeter(unit, diametre);
        longueur = convertToCentimeter(unit, longueur);

        double vol_fleche = (double) 0.00;
        double vol_fleche1 = (double) 0.00;
        double vol_centre = (double) 0.00;

        double vol_fleche2 = (double) 0.00;

        if (synonymeFormeCapaciteMultiLangueForFormeNonConventionnelle(nature_fond_d) || synonymeFormeCapaciteMultiLangueForFormeNonConventionnelle(nature_fond_g)) {

            if (synonymeFormeCapaciteMultiLangueForFormeNonConventionnelle(nature_fond_d) && synonymeFormeCapaciteMultiLangueForFormeNonConventionnelle(nature_fond_g)) {

                nature_fond_d = "Elliptique";
                nature_fond_g = "Elliptique";
                vol_fleche = volumeFormeCylindre(unit, orientation,
                        nature_fond_g.toUpperCase(),
                        nature_fond_d.toUpperCase(), data, t);

            } else {

                double volume1 = 0d;
                double volume2 = 0d;
                double volume3 = 0d;
                String natureGauche = "", natureDroite = "";

                if (synonymeForOrientationOblique(orientation)) {

                    if (synonymeFormeCapaciteMultiLangueForFormeNonConventionnelle(nature_fond_d)) {
                        natureDroite = "Elliptique";
                        if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g)) {
                            vol_fleche = volumeFormeCylindre(unit, orientation,
                                    "PLAT",
                                    natureDroite, data, t);
                        } else {

                            volume1 = volumeFormeCylindre(unit, orientation,
                                    "PLAT",
                                    natureDroite, data, t);

                            volume2 = volumeFormeCylindre(unit, orientation,
                                    "PLAT",
                                    "PLAT", data, t);

                            volume3 = volumeFormeCylindreByRayonCarre(unit, orientation, nature_fond_g,
                                    "PLAT",
                                    data, t);
                            vol_fleche = volume1 + volume3 - volume2;

                        }

                    } else if (synonymeFormeCapaciteMultiLangueForFormeNonConventionnelle(nature_fond_g)) {
                        natureGauche = "Elliptique";
                        if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d)) {
                            vol_fleche = volumeFormeCylindre(unit, orientation,
                                    natureGauche, "PLAT", data, t);
                        } else {

                            volume1 = volumeFormeCylindre(unit, orientation, natureGauche,
                                    "PLAT",
                                    data, t);

                            volume2 = volumeFormeCylindre(unit, orientation,
                                    "PLAT",
                                    "PLAT", data, t);

                            volume3 = volumeFormeCylindreByRayonCarre(unit, orientation,
                                    "PLAT", nature_fond_d,
                                    data, t);

                        }

                    }

                } else {

                    if (synonymeFormeCapaciteMultiLangueForFormeNonConventionnelle(nature_fond_d)) {
                        natureDroite = "Elliptique";

                        if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g)) {
                            vol_fleche = volumeFormeCylindre(unit, orientation,
                                    "PLAT",
                                    natureDroite, data, t);
                        } else {

                            volume1 = volumeFormeCylindre(unit, orientation,
                                    "PLAT",
                                    natureDroite, data, t);

                            volume2 = volumeFormeCylindre(unit, orientation,
                                    "PLAT",
                                    "PLAT", data, t);

                            volume3 = volumeFormeCylindreByRayonCarre(unit, orientation,
                                    "PLAT",
                                    nature_fond_g, data, t);
                            vol_fleche = volume1 + volume3 - volume2;

                        }

                    } else if (synonymeFormeCapaciteMultiLangueForFormeNonConventionnelle(nature_fond_g)) {

                        natureGauche = "Elliptique";

                        if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d)) {
                            vol_fleche = volumeFormeCylindre(unit, orientation,
                                    "PLAT",
                                    natureGauche, data, t);
                        } else {

                            volume1 = volumeFormeCylindre(unit, orientation, natureGauche,
                                    "PLAT",
                                    data, t);

                            volume2 = volumeFormeCylindre(unit, orientation,
                                    "PLAT",
                                    "PLAT", data, t);

                            volume3 = volumeFormeCylindreByRayonCarre(unit, orientation, nature_fond_d,
                                    "PLAT", data, t);

                            vol_fleche = volume1 + volume3 - volume2;

                        }

                    }

                }

            }

        } else {

            if (!synonymeForOrientationVerticale(orientation.toLowerCase())
                    && !synonymeForOrientationOblique(orientation.toLowerCase())) {

                if (nature_fond_g.equals(nature_fond_d) && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {

                    vol_fleche = 0.2618 * t * t * (1.5 * diametre - t);
                    vol_fleche *= 2;
                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche += vol_centre;
                } else if (nature_fond_g.equals(nature_fond_d) && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)) {

                    vol_fleche = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                            - Math.pow(diametre / 2 - t, 3) - 3 * t * (diametre / 2 - t))
                            / (3 * diametre));
                    vol_fleche += (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                            - Math.pow(diametre / 2 - t, 3) - 3 * t * (diametre / 2 - t))
                            / (3 * diametre));
                    vol_fleche += longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4);

                } else if (nature_fond_g.equals(nature_fond_d)
                        && synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_g)) {

                    vol_fleche = 0.5236 * t * t * (1.5 * diametre - t);
                    vol_fleche *= 2;

                    vol_fleche1 = longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4);

                    vol_fleche += vol_fleche1;

                } else if (nature_fond_g.equals(nature_fond_d)
                        && synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_g)) {

                    vol_fleche = 0.19 * t * t * (1.5 * diametre - t);
                    vol_fleche *= 2;
                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche += vol_centre;
                } else if (nature_fond_g.equals(nature_fond_d) && nature_fond_g.equals("BOMBER")) {

                    vol_fleche = 0.116 * t * t * (1.5 * diametre - t);
                    vol_fleche *= 2;
                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche += vol_centre;
                } else if (nature_fond_g.equals(nature_fond_d) && synonymeForNatureFlechePlatOfCylinder(nature_fond_g)) {

                    vol_fleche = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                } else if (synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_g)
                        && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)
                        || (synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_d)
                        && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g))) {

                    vol_fleche = 0.2618 * t * t * (1.5 * diametre - t); // ellipTIQUE
                    vol_fleche += 0.5236 * t * t * (1.5 * diametre - t);

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre;

                } else if ((synonymeForNatureFlechePlatOfCylinder(nature_fond_g)
                        && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d))
                        || (synonymeForNatureFlechePlatOfCylinder(nature_fond_d)
                        && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g))) {

                    vol_fleche = 0.2618 * t * t * (1.5 * diametre - t); // ellipTIQUE

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre;

                } else if (synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_g)
                        && synonymeForNatureFlechePlatOfCylinder(nature_fond_d)
                        || synonymeForNatureFlechePlatOfCylinder(nature_fond_g)
                        && synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_d)) {

                    vol_fleche = 0.5236 * t * t * (1.5 * diametre - t);

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre;

                } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g)
                        && synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_d)) {

                    vol_fleche = 0.19 * t * t * (1.5 * diametre - t);// torispherique

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre;

                } else if (synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_g)
                        && synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_d)
                        || synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_d)
                        && synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_g)) {

                    vol_fleche = 0.5236 * t * t * (1.5 * diametre - t);// hemisperique
                    vol_fleche += 0.19 * t * t * (1.5 * diametre - t);// torispherique

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre;

                } else if (synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)
                        && synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_d)
                        || synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)
                        && synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_g)) {

                    vol_fleche = 0.2618 * t * t * (1.5 * diametre - t);
                    vol_fleche += 0.19 * t * t * (1.5 * diametre - t);// torispherique

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre;

                } else if (nature_fond_g.equals("BOMBER") && synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_d)
                        || nature_fond_d.equals("BOMBER")
                        && synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_g)) {

                    vol_fleche = 0.116 * t * t * (1.5 * diametre - t);
                    vol_fleche += 0.19 * t * t * (1.5 * diametre - t);// torispherique

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre;

                } else if (synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_g)
                        && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_d)) {

                    vol_fleche = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                            - Math.pow(diametre / 2 - t, 3) - 3 * t * (diametre / 2 - t))
                            / (3 * diametre));

                    vol_fleche += 0.19 * t * t * (1.5 * diametre - t);// torispherique

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre;

                } else if (synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_d)
                        && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)) {

                    vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                            - Math.pow(diametre / 2 - t, 3) - 3 * t * (diametre / 2 - t))
                            / (3 * diametre));

                    vol_fleche += 0.19 * t * t * (1.5 * diametre - t);// torispherique

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre;

                } else if (nature_fond_d.equals("BOMBER") && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)) {

                    vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                            - Math.pow(diametre / 2 - t, 3) - 3 * t * (diametre / 2 - t))
                            / (3 * diametre));

                    vol_fleche += 0.116 * t * t * (1.5 * diametre - t);// BOMBER

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre;

                } else if (nature_fond_g.equals("BOMBER") && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_d)) {

                    vol_fleche = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                            - Math.pow(diametre / 2 - t, 3) - 3 * t * (diametre / 2 - t))
                            / (3 * diametre));

                    vol_fleche += 0.116 * t * t * (1.5 * diametre - t);// BOMBER

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre;

                } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g)
                        && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_d)) {

                    vol_fleche = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                            - Math.pow(diametre / 2 - t, 3) - 3 * t * (diametre / 2 - t))
                            / (3 * diametre));

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre;

                } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d)
                        && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)) {

                    vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                            - Math.pow(diametre / 2 - t, 3) - 3 * t * (diametre / 2 - t))
                            / (3 * diametre));

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre;

                } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d)
                        && synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_g)) {

                    vol_fleche = 0.19 * t * t * (1.5 * diametre - t);// torispherique

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre;

                } else if ((synonymeForNatureFlechePlatOfCylinder(nature_fond_d) && nature_fond_g.equals("BOMBER"))
                        || (synonymeForNatureFlechePlatOfCylinder(nature_fond_g) && nature_fond_d.equals("BOMBER"))) {

                    vol_fleche = 0.116 * t * t * (1.5 * diametre - t);// torispherique

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre;

                } else if (synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)
                        && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {

                    vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                            - Math.pow(diametre / 2 - t, 3) - 3 * t * (diametre / 2 - t))
                            / (3 * diametre));

                    vol_fleche1 = 0.2618 * t * t * (1.5 * diametre - t); // ellipTIQUE

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre + vol_fleche1;

                } else if (synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)
                        && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_d)) {

                    vol_fleche = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                            - Math.pow(diametre / 2 - t, 3) - 3 * t * (diametre / 2 - t))
                            / (3 * diametre));

                    vol_fleche1 = 0.2618 * t * t * (1.5 * diametre - t); // ellipTIQUE

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre + vol_fleche1;

                } else if (synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_g)
                        && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_d)) {

                    vol_fleche = 0.5236 * t * t * (1.5 * diametre - t);// hemisperique

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                            - Math.pow(diametre / 2 - t, 3) - 3 * t * (diametre / 2 - t))
                            / (3 * diametre));

                    vol_fleche = vol_fleche + vol_centre + vol_fleche1;

                } else if (synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)
                        && synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_d)) {

                    vol_fleche = 0.5236 * t * t * (1.5 * diametre - t);// hemisperique

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche1 = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                            - Math.pow(diametre / 2 - t, 3) - 3 * t * (diametre / 2 - t))
                            / (3 * diametre));

                    vol_fleche = vol_fleche + vol_centre + vol_fleche1;

                } else if (synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_g) && nature_fond_d.equals("BOMBER")
                        || synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_d)
                        && nature_fond_g.equals("BOMBER")) {

                    vol_fleche = 0.5236 * t * t * (1.5 * diametre - t);// hemisperique
                    vol_fleche += 0.116 * t * t * (1.5 * diametre - t);

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre;

                } else if (synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g) && nature_fond_d.equals("BOMBER")
                        || synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d) && nature_fond_g.equals("BOMBER")) {

                    vol_fleche = 0.2618 * t * t * (1.5 * diametre - t);
                    vol_fleche += 0.116 * t * t * (1.5 * diametre - t);

                    vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - diametre / 2)
                            + diametre * diametre * Math.acos(1 - 2 * t / diametre) / 4));

                    vol_fleche = vol_fleche + vol_centre;

                }

            } else if (synonymeForOrientationOblique(orientation.toLowerCase())) {

                float abcisse = Float.parseFloat(data.get("position x").toString());
                float angle = Float.parseFloat(data.get("angle inclinaison").toString());
                float cosinusAngle = (float) Math.cos(Math.PI * angle / 180);
                String sensOrientation = DonneeGeometrique.getSensRotationOblique();
                float rayon = diametre / 2;
                if (sensOrientation.equalsIgnoreCase("trigo")) {

                    float hauteurSectionSuperieure = ((diametre / cosinusAngle) - t) / cosinusAngle;
                    hauteurSectionSuperieure = (float) (diametre - hauteurSectionSuperieure
                            - abcisse * Math.tan(Math.PI * angle / 180));
                    float variableIntermediaireK = 1 - (hauteurSectionSuperieure / rayon);
                    float variableIntermediaireC = (float) (variableIntermediaireK
                            - (longueur * Math.tan(Math.PI * angle / 180) / rayon));
                    // float hauteurLimite = (float) (diametre - (longueur - abcisse) *
                    // Math.sin(Math.PI*angle/180));

                    double intermediaireCalculVolume = variableIntermediaireK * Math.acos(variableIntermediaireK)
                            - variableIntermediaireC * Math.acos(variableIntermediaireC);
                    intermediaireCalculVolume += (variableIntermediaireC * variableIntermediaireC + 2)
                            * Math.sqrt(1 - variableIntermediaireC * variableIntermediaireC) / 3;
                    intermediaireCalculVolume = intermediaireCalculVolume
                            - (variableIntermediaireK * variableIntermediaireK + 2)
                            * Math.sqrt(1 - variableIntermediaireK * variableIntermediaireK) / 3;

                    vol_centre = Math.pow(rayon, 3) / Math.tan(Math.PI * angle / 180);
                    vol_centre *= intermediaireCalculVolume;
                    float tg = (float) (hauteurSectionSuperieure + longueur * Math.tan(Math.PI * angle / 180));

                    float condition = (float) ((abcisse + diametre * Math.tan(Math.PI * angle / 180))
                            * Math.sin(Math.PI * angle / 180));

                    if (t > condition) {

                        if (nature_fond_g.equals(nature_fond_d) && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {

                            if (tg <= diametre) {
                                vol_fleche = 0.2618 * tg * tg * (1.5 * diametre - tg);
                                vol_fleche1 = 0.2618 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = Math.PI * 2 * Math.pow(rayon, 2) * fleche_g / 3;
                                vol_fleche1 = 0.2618 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (nature_fond_g.equals(nature_fond_d) && nature_fond_g.equalsIgnoreCase("bomber")) {
                            if (tg <= diametre) {

                                vol_fleche = 0.116 * tg * tg * (1.5 * diametre - tg);
                                vol_fleche1 = 0.116 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.116 * diametre * diametre * (0.5 * diametre);

                                vol_fleche1 = 0.116 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (nature_fond_g.equals(nature_fond_d) && synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_g)) {
                            if (tg <= diametre) {
                                vol_fleche = 0.5236 * tg * tg * (1.5 * diametre - tg);
                                vol_fleche1 = 0.5236 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = Math.PI * Math.pow(diametre, 3) / 12;

                                vol_fleche1 = 0.5236 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (nature_fond_g.equals(nature_fond_d) && synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_g)) {
                            if (tg <= diametre) {
                                vol_fleche = 0.19 * tg * tg * (1.5 * diametre - tg);
                                vol_fleche1 = 0.19 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.19 * diametre * diametre * (0.5 * diametre);

                                vol_fleche1 = 0.19 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (nature_fond_g.equals(nature_fond_d) && synonymeForNatureFlechePlatOfCylinder(nature_fond_g)) {

                            vol_fleche = vol_centre;

                        } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d) && synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_g)) {
                            if (tg <= diametre) {
                                vol_fleche = 0.5236 * tg * tg * (1.5 * diametre - tg);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = Math.PI * Math.pow(diametre, 3) / 12;

                            }

                            vol_fleche = vol_centre + vol_fleche;

                        } else if ((synonymeForNatureFlechePlatOfCylinder(nature_fond_g) && synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_d))) {
                            vol_fleche = 0.5236 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            vol_fleche = vol_centre + vol_fleche;

                        } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g) && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {
                            vol_fleche = 0.2618 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            vol_fleche = vol_centre + vol_fleche;

                        } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d) && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {
                            if (tg <= diametre) {
                                vol_fleche = 0.2618 * tg * tg * (1.5 * diametre - tg);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.2618 * diametre * diametre * (0.5 * diametre);

                            }
                            vol_fleche = vol_centre + vol_fleche;

                        } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d) && synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_g)) {

                            if (tg <= diametre) {
                                vol_fleche = 0.19 * tg * tg * (1.5 * diametre - tg);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.19 * diametre * diametre * (0.5 * diametre);

                            }

                            vol_fleche = vol_centre + vol_fleche;

                        } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d) && nature_fond_g.equalsIgnoreCase("bomber")) {

                            if (tg <= diametre) {
                                vol_fleche = 0.116 * tg * tg * (1.5 * diametre - tg);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.116 * diametre * diametre * (0.5 * diametre);

                            }

                            vol_fleche = vol_centre + vol_fleche;

                        } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g) && synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_d)) {
                            vol_fleche = 0.19 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            vol_fleche = vol_centre + vol_fleche;

                        } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g) && nature_fond_d.equalsIgnoreCase("bomber")) {
                            vol_fleche = 0.116 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            vol_fleche = vol_centre + vol_fleche;

                        } else if (synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_g) && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {
                            if (tg <= diametre) {
                                vol_fleche = 0.5236 * tg * tg * (1.5 * diametre - tg);
                                vol_fleche1 = 0.2618 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = Math.PI * Math.pow(diametre, 3) / 12;

                                vol_fleche1 = 0.2618 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_d) && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {
                            if (tg <= diametre) {
                                vol_fleche = 0.2618 * tg * tg * (1.5 * diametre - tg);
                                vol_fleche1 = 0.5236 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.2618 * diametre * diametre * (0.5 * diametre);
                                vol_fleche1 = 0.5236 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_g) && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {

                            if (tg <= diametre) {
                                vol_fleche = 0.19 * tg * tg * (1.5 * diametre - tg);
                                vol_fleche1 = 0.2618 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.19 * diametre * diametre * (0.5 * diametre);
                                vol_fleche1 = 0.2618 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_d) && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {

                            if (tg <= diametre) {
                                vol_fleche = 0.2618 * tg * tg * (1.5 * diametre - tg);
                                vol_fleche1 = 0.19 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.2618 * diametre * diametre * (0.5 * diametre);
                                vol_fleche1 = 0.19 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d) && nature_fond_g.equalsIgnoreCase("bomber")) {

                            if (tg <= diametre) {
                                vol_fleche = 0.116 * tg * tg * (1.5 * diametre - tg);
                                vol_fleche1 = 0.2618 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.116 * diametre * diametre * (0.5 * diametre);
                                vol_fleche1 = 0.2618 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche;

                        } else if (synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g) && nature_fond_d.equalsIgnoreCase("bomber")) {

                            if (tg <= diametre) {
                                vol_fleche = 0.2618 * tg * tg * (1.5 * diametre - tg);
                                vol_fleche1 = 0.116 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.2618 * diametre * diametre * (0.5 * diametre);
                                vol_fleche1 = 0.116 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche;

                        } else if (synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_d) && synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_g)) {
                            if (tg <= diametre) {
                                vol_fleche = 0.5236 * tg * tg * (1.5 * diametre - tg);
                                vol_fleche1 = 0.19 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = Math.PI * Math.pow(diametre, 3) / 12;

                                vol_fleche1 = 0.19 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_g) && synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_d)) {

                            if (tg <= diametre) {
                                vol_fleche = 0.19 * tg * tg * (1.5 * diametre - tg);
                                vol_fleche1 = 0.5236 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.19 * diametre * diametre * (0.5 * diametre);
                                vol_fleche1 = 0.5236 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_d) && nature_fond_g.equalsIgnoreCase("bomber")) {

                            if (tg <= diametre) {
                                vol_fleche = 0.116 * tg * tg * (1.5 * diametre - tg);
                                vol_fleche1 = 0.5236 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.116 * diametre * diametre * (0.5 * diametre);
                                vol_fleche1 = 0.5236 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche;

                        } else if (synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_g) && nature_fond_d.equalsIgnoreCase("bomber")) {

                            if (tg <= diametre) {
                                vol_fleche = 0.5236 * tg * tg * (1.5 * diametre - tg);
                                vol_fleche1 = 0.116 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.5236 * diametre * diametre * (0.5 * diametre);
                                vol_fleche1 = 0.116 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche;

                        } else if (synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_d) && nature_fond_g.equalsIgnoreCase("bomber")) {

                            if (tg <= diametre) {
                                vol_fleche = 0.116 * tg * tg * (1.5 * diametre - tg);
                                vol_fleche1 = 0.19 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.116 * diametre * diametre * (0.5 * diametre);
                                vol_fleche1 = 0.19 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche;

                        } else if (synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_g) && nature_fond_d.equalsIgnoreCase("bomber")) {

                            if (tg <= diametre) {
                                vol_fleche = 0.19 * tg * tg * (1.5 * diametre - tg);
                                vol_fleche1 = 0.116 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.19 * diametre * diametre * (0.5 * diametre);
                                vol_fleche1 = 0.116 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche;

                        }

                    } else {
                        return 0;
                    }

                } else {
                    float hauteurSectionSuperieure = ((diametre / cosinusAngle) - t) / cosinusAngle;
                    hauteurSectionSuperieure = (float) (diametre - hauteurSectionSuperieure - abcisse * Math.tan(Math.PI * angle / 180));
                    float variableIntermediaireK = 1 - (hauteurSectionSuperieure / rayon);
                    float variableIntermediaireC = (float) (variableIntermediaireK - (longueur * Math.tan(Math.PI * angle / 180) / rayon));
// float hauteurLimite = (float) (diametre - (longueur - abcisse) * Math.sin(Math.PI*angle/180));

                    double intermediaireCalculVolume = variableIntermediaireK * Math.acos(variableIntermediaireK) - variableIntermediaireC * Math.acos(variableIntermediaireC);
                    intermediaireCalculVolume += (variableIntermediaireC * variableIntermediaireC + 2) * Math.sqrt(1 - variableIntermediaireC * variableIntermediaireC) / 3;
                    intermediaireCalculVolume = intermediaireCalculVolume - (variableIntermediaireK * variableIntermediaireK + 2) * Math.sqrt(1 - variableIntermediaireK * variableIntermediaireK) / 3;

                    vol_centre = Math.pow(rayon, 3) / Math.tan(Math.PI * angle / 180);
                    vol_centre *= intermediaireCalculVolume;
                    float td = (float) (hauteurSectionSuperieure + longueur * Math.tan(Math.PI * angle / 180));

                    float condition1 = (float) (t + (longueur - abcisse) * Math.sin(Math.PI * angle / 180));
                    float condition2 = (float) (t - abcisse * Math.sin(Math.PI * angle / 180));
                    float condition = (float) ((abcisse + diametre * Math.tan(Math.PI * angle / 180)) * Math.sin(Math.PI * angle / 180));

                    if (t > condition) {

                        if (nature_fond_g.equals(nature_fond_d) && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {

                            if (td <= diametre) { //
                                vol_fleche = 0.2618 * td * td * (1.5 * diametre - td);
                                vol_fleche1 = 0.2618 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.2618 * diametre * diametre * (0.5 * diametre);

                                vol_fleche = 0.2618 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (nature_fond_g.equals(nature_fond_d) && nature_fond_g.equalsIgnoreCase("bomber")) {
                            if (td <= diametre) {

                                vol_fleche = 0.116 * td * td * (1.5 * diametre - td);
                                vol_fleche1 = 0.116 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.116 * diametre * diametre * (0.5 * diametre);

                                vol_fleche1 = 0.116 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (nature_fond_g.equals(nature_fond_d) && synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_d)) {
                            if (td <= diametre) {
                                vol_fleche = 0.5236 * td * td * (1.5 * diametre - td);
                                vol_fleche1 = 0.5236 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = Math.PI * Math.pow(diametre, 3) / 12;

                                vol_fleche1 = 0.5236 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (nature_fond_g.equals(nature_fond_d) && synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_d)) {
                            if (td <= diametre) {
                                vol_fleche = 0.19 * td * td * (1.5 * diametre - td);
                                vol_fleche1 = 0.19 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.19 * diametre * diametre * (0.5 * diametre);

                                vol_fleche1 = 0.19 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (nature_fond_g.equals(nature_fond_d) && synonymeForNatureFlechePlatOfCylinder(nature_fond_g)) {

                            vol_fleche = vol_centre;

                        } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d) && synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_g)) {

                            if (hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.5236 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);
                                vol_fleche = vol_centre + vol_fleche;
                            }

                        } else if ((synonymeForNatureFlechePlatOfCylinder(nature_fond_g) && synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_d))) {
                            if (td <= diametre) {
                                vol_fleche = 0.5236 * td * td * (1.5 * diametre - td);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = Math.PI * Math.pow(diametre, 3) / 12;

                            }

                            vol_fleche = vol_centre + vol_fleche;

                        } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g) && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {
                            if (td <= diametre) {
                                vol_fleche = 0.2618 * td * td * (1.5 * diametre - td);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.2618 * diametre * diametre * (0.5 * diametre);

                            }

                            vol_fleche = vol_centre + vol_fleche;

                        } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d) && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {
                            if (hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.2618 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);
                                vol_fleche = vol_centre + vol_fleche;
                            }

                        } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d) && synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_g)) {

                            if (hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.19 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);
                                vol_fleche = vol_centre + vol_fleche;
                            }

                        } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g) && synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_d)) {
                            if (td <= diametre) {
                                vol_fleche1 = 0.19 * td * td * (1.5 * diametre - td);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche1 = 0.19 * diametre * diametre * (0.5 * diametre);

                            }

                            vol_fleche = vol_centre + vol_fleche1;

                        } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d) && nature_fond_g.equalsIgnoreCase("bomber")) {

                            if (hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.116 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);
                                vol_fleche = vol_centre + vol_fleche;
                            }

                        } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g) && nature_fond_d.equalsIgnoreCase("bomber")) {
                            if (td <= diametre) {
                                vol_fleche1 = 0.116 * td * td * (1.5 * diametre - td);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche1 = 0.116 * diametre * diametre * (0.5 * diametre);

                            }

                            vol_fleche = vol_centre + vol_fleche1;

                        } else if (synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_g) && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {
                            if (td <= diametre) {
                                vol_fleche = 0.2618 * td * td * (1.5 * diametre - td);
                                vol_fleche1 = 0.5236 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.2618 * diametre * diametre * (0.5 * diametre);
                                vol_fleche1 = 0.5236 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_d) && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {
                            if (td <= diametre) {
                                vol_fleche = 0.5236 * td * td * (1.5 * diametre - td);
                                vol_fleche1 = 0.2618 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = Math.PI * Math.pow(diametre, 3) / 12;
                                vol_fleche1 = 0.2618 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_g) && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {

                            if (td <= diametre) {
                                vol_fleche = 0.2618 * td * td * (1.5 * diametre - td);
                                vol_fleche1 = 0.19 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.2618 * diametre * diametre * (0.5 * diametre);
                                vol_fleche1 = 0.19 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_d) && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {

                            if (td <= diametre) {
                                vol_fleche = 0.19 * td * td * (1.5 * diametre - td);
                                vol_fleche1 = 0.2618 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche1 = 0.2618 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                                vol_fleche = 0.19 * diametre * diametre * (0.5 * diametre);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (nature_fond_d.equalsIgnoreCase("bomber") && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {

                            if (td <= diametre) {
                                vol_fleche = 0.116 * td * td * (1.5 * diametre - td);
                                vol_fleche1 = 0.2618 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche1 = 0.2618 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                                vol_fleche = 0.116 * diametre * diametre * (0.5 * diametre);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (nature_fond_g.equalsIgnoreCase("bomber") && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {

                            if (td <= diametre) {
                                vol_fleche = 0.2618 * td * td * (1.5 * diametre - td);
                                vol_fleche1 = 0.116 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.2618 * diametre * diametre * (0.5 * diametre);
                                vol_fleche1 = 0.116 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_d) && synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_g)) {
                            if (td <= diametre) {
                                vol_fleche = 0.19 * td * td * (1.5 * diametre - td);

                                vol_fleche1 = 0.5236 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.19 * diametre * diametre * (0.5 * diametre);

                                vol_fleche1 = 0.5236 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_g) && synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_d)) {

                            if (td <= diametre) {
                                vol_fleche = 0.5236 * td * td * (1.5 * diametre - td);
                                vol_fleche1 = 0.19 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.5236 * diametre * diametre * (0.5 * diametre);

                                vol_fleche1 = 0.19 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (nature_fond_d.equalsIgnoreCase("bomber") && synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_g)) {

                            if (td <= diametre) {
                                vol_fleche = 0.116 * td * td * (1.5 * diametre - td);
                                vol_fleche1 = 0.5236 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche1 = 0.5236 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                                vol_fleche = 0.116 * diametre * diametre * (0.5 * diametre);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (nature_fond_g.equalsIgnoreCase("bomber") && synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_d)) {

                            if (td <= diametre) {
                                vol_fleche = 0.5236 * td * td * (1.5 * diametre - td);
                                vol_fleche1 = 0.116 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = Math.PI * Math.pow(diametre, 3) / 12;
                                vol_fleche1 = 0.116 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (nature_fond_d.equalsIgnoreCase("bomber") && synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_g)) {

                            if (td <= diametre) {
                                vol_fleche = 0.116 * td * td * (1.5 * diametre - td);
                                vol_fleche1 = 0.19 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche1 = 0.19 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                                vol_fleche = 0.116 * diametre * diametre * (0.5 * diametre);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        } else if (nature_fond_g.equalsIgnoreCase("bomber") && synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_d)) {

                            if (td <= diametre) {
                                vol_fleche = 0.19 * td * td * (1.5 * diametre - td);
                                vol_fleche1 = 0.116 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                                vol_fleche = 0.19 * diametre * diametre * (0.5 * diametre);
                                vol_fleche1 = 0.116 * hauteurSectionSuperieure * hauteurSectionSuperieure * (1.5 * diametre - hauteurSectionSuperieure);

                            }

                            vol_fleche = vol_centre + vol_fleche + vol_fleche1;

                        }

                    }

                }

            } else if (synonymeForOrientationVerticale(orientation.toLowerCase())) {

                if (synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_d)) {
                    nature_fond_d = "spherique";
                }

                if (synonymeFormeCapaciteMultiLangueForHemispherique(nature_fond_g)) {
                    nature_fond_g = "spherique";
                }

                if (nature_fond_d.equalsIgnoreCase("bomber")
                        || synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_d)
                        || synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {
                    nature_fond_d = "elliptique";
                }

                if (nature_fond_g.equalsIgnoreCase("bomber")
                        || synonymeFormeCapaciteMultiLangueForToriSpherique(nature_fond_g)
                        || synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {
                    nature_fond_g = "elliptique";
                }

                vol_fleche = volumeFormeCylindre(unit, orientation, nature_fond_g, nature_fond_d, data, t);

            }

        }

        return vol_fleche;
    } // retourne la valeur du volume du recipient rempli a une hauteur donne
    // la valeur de la hauteur est represente par largument val de la fonction
    // le volume est donne en cm3

    // cette fonction genere un certificat sans entete contenant republic du
    // cameroun et
    // fqisqnt mention des divisions et departemet ministeriel
    public void genererTableForGeometrie(HashMap info, HashMap data) {

        if (data.containsKey("longueur") || data.containsKey("diametre") || info.containsKey("nombre divisions")
                || data.containsKey("fleche cote gauche") || data.containsKey("fleche cote droit")
                || data.containsKey("grand axe")
                || data.containsKey("petit axe") || data.containsKey("rayon sphere")
                || data.containsKey("hauteur")) {

            String nom = (String) info.get("table");

            String formeCapacite = info.get("forme de la capacite").toString().toUpperCase();
            float diametre = 0;

            if (synonymeFormeCapaciteMultiLangueForCylindre(
                    info.get("forme de la capacite").toString().toLowerCase())) {
                diametre = Float.parseFloat(data.get("diametre").toString());

            } else if (synonymeFormeCapaciteMultiLangueForSphere(
                    info.get("forme de la capacite").toString().toLowerCase())) {
                float rayonSphere = Float.parseFloat(data.get("rayon sphere").toString());
                diametre = 2 * rayonSphere;

            } else if (synonymeFormeCapaciteMultiLangueForCube(
                    info.get("forme de la capacite").toString().toLowerCase())) {
                diametre = Float.parseFloat(data.get("hauteur").toString());
            } else if (synonymeFormeCapaciteMultiLangueForEllpsoide(
                    info.get("forme de la capacite").toString().toLowerCase())) {
                diametre = Float.parseFloat(data.get("hauteur").toString());

            }

            if (synonymeForOrientationVerticale(info.get("orientation").toString())
                    && synonymeFormeCapaciteMultiLangueForCylindre(formeCapacite)) {
                diametre = Float.parseFloat(data.get("longueur").toString());
                if (data.containsKey("fleche cote gauche")) {

                    diametre += Float.parseFloat(data.get("fleche cote gauche").toString());

                }
                if (data.containsKey("fleche cote droit")) {

                    diametre += Float.parseFloat(data.get("fleche cote droit").toString());

                }

            }

            if (synonymeForOrientationOblique(info.get("orientation").toString()) && synonymeFormeCapaciteMultiLangueForCylindre(formeCapacite)) {

                float angleInclinaison = Float.parseFloat(data.get("angle inclinaison").toString());
                float sinusAngle = (float) Math.sin(Math.PI * angleInclinaison / 180);
                float cosinusAngle = (float) Math.cos(Math.PI * angleInclinaison / 180);
                diametre = Float.parseFloat(data.get("longueur").toString()) * sinusAngle + diametre * cosinusAngle;

            }

            String unit = info.get("unite des hauteurs").toString().toLowerCase();
            diametre = convertToCentimeter(unit, diametre);
            int nb_divi = Integer.parseInt(info.get("nombre divisions").toString());
            int nb_group = (int) (diametre / nb_divi);
            int x = 5, y = 4;

            WritableWorkbook workbook;
            String documentDirectory = System.getProperty("user.home") + "/Documents/";
            String nyettofDirectory = documentDirectory + "NyettoftTank_files/";

            // Créer le répertoire si nécessaire
            File nyettofFile = new File(nyettofDirectory);
            if (!nyettofFile.exists()) {
                nyettofFile.mkdirs();
            }
            String filePath = nyettofDirectory + nom + ".xls";
            try {

                workbook = Workbook.createWorkbook(new File(filePath));
                WritableSheet sheet = workbook.createSheet("sheet1", 0);

                // sheet.mergeCells(7, 0, 17, 4);
                sheet.mergeCells(y, 6, nb_group, 6);
                sheet.mergeCells(y, 10, nb_group, 10);

                sheet.addCell(new Label(5 + nb_group / 2, 4, " 22/MINCOMMERCE/SG/DMQP/SDM/SIV "));
                sheet.addCell(
                        new Label(5 + nb_group / 2, 5, "Nom de la capacité : "
                                + verifie(info, "nom capacite")));

                sheet.mergeCells(5 + nb_group / 2, 4, 4 + nb_group, 4);
                sheet.mergeCells(5 + nb_group / 2, 5, 4 + nb_group, 5);

                sheet.addCell(new Label(4, 7, "VOLUME "));
                sheet.mergeCells(4, 7, 5, 7);
                sheet.addCell(new Label(6, 7, verifie(info, "volume nominal")));
                sheet.mergeCells(6, 7, 7, 7);
                sheet.addCell(new Label(8, 7, "Lieux Operations "));
                sheet.mergeCells(8, 7, 9, 7);
                sheet.addCell(new Label(10, 7, verifie(info, "lieu des operations")));
                sheet.mergeCells(10, 7, 11, 7);
                sheet.addCell(new Label(12, 7, "Realise par "));
                sheet.mergeCells(12, 7, 13, 7);
                sheet.addCell(new Label(14, 7, verifie(info, "fabricant")));
                sheet.mergeCells(14, 7, 15, 7);

                sheet.addCell(new Label(4, 8, "Diametre"));
                sheet.mergeCells(4, 8, 5, 8);
                sheet.addCell(new Label(6, 8, diametre + ""));
                sheet.mergeCells(6, 8, 7, 8);
                sheet.addCell(new Label(8, 8, "Date d'etablissement "));
                sheet.mergeCells(8, 8, 10, 8);
                sheet.addCell(new Label(11, 8, " "));
                sheet.addCell(new Label(12, 8, "Debut Travaux "));
                sheet.mergeCells(12, 8, 13, 8);
                sheet.addCell(new Label(14, 8, verifie(info, "debut des  travaux")));
                sheet.mergeCells(14, 8, 15, 8);

                sheet.addCell(new Label(4, 9, "Detenteur / Client"));
                sheet.mergeCells(4, 9, 5, 9);
                sheet.addCell(new Label(6, 9, verifie(info, "detenteur")));
                sheet.mergeCells(6, 9, 8, 9);
                sheet.addCell(new Label(9, 9, "Fin Travaux "));
                sheet.mergeCells(9, 9, 10, 9);
                sheet.addCell(new Label(11, 9, verifie(info, "fin des  travaux")));
                sheet.mergeCells(11, 9, 12, 9);

                for (int i = 0; i < (diametre / nb_divi); i++) {

                    sheet.addCell(new Label(3 * i + 4, 11,
                            "H( " + info.get("unite des hauteurs").toString().toLowerCase()
                            + " )"));
                    sheet.addCell(new Label(3 * i + 5, 11,
                            "V( " + info.get("unite de volume").toString().toLowerCase()
                            + ") "));
                    sheet.addCell(new Label(3 * i + 6, 11, " "));

                }

                String typeValueReturn = info.get("type value").toString();

                for (int j = 1; j <= nb_divi; j++) {
                    for (int i = 0; i < (diametre / nb_divi); i++) {

                        double vol_fleche = (float) 0.00;
                        int t = i * nb_divi + j;
                        // String unit = data.get("unite des hauteurs").toString();
                        String unit_vol = info.get("unite de volume").toString();

                        if (t <= diametre) {
                            vol_fleche = fonctionCalculVolume(data, info, t); // en cm3
                            vol_fleche = vol_fleche / 1000;// en litres
                            vol_fleche = convertToVolumeDesired(unit_vol, vol_fleche);

                            String ch = twoDecimale(vol_fleche, typeValueReturn);

                            float xx = convertToUnitDesire(unit, t);
                            String ch1 = twoDecimale(xx, typeValueReturn);

                            sheet.addCell(new Label(3 * i + 4, 11 + j, t + ""));
                            sheet.addCell(new Label(3 * i + 5, 11 + j, ch));
                            sheet.addCell(new Label(3 * i + 6, 11 + j, " "));

                        }
                    }
                }

                workbook.write();
                workbook.close();
            } catch (IOException ex) {
                Logger.getLogger(BaremeArtisan.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WriteException ex) {
                Logger.getLogger(BaremeArtisan.class.getName()).log(Level.SEVERE, null, ex);
            }

            location.showMessageDialog(null, "Votre FICHER SE TROUVE DANS \n  " + filePath,
                    "LOCALISATION DU FICHIER",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            jop3.showMessageDialog(null,
                    "veiller renseigner les differentes valeurs \n des parties donnee geometrique et/ou \n information generales"
                            .toUpperCase(),
                    "Message d'erreur".toUpperCase(), JOptionPane.ERROR_MESSAGE);
        }

    }

    public float volumeFormeCylindre(String unit, String orientation, String fond_capacite, HashMap data, float t) {
        // TODO Auto-generated method stub
        return 0;
    }

    public String twoDecimale(double vol_fleche, String typeValue) {
        if (typeValue.equals("decimale")) {

            String ch = "" + vol_fleche;
            int pos = ch.indexOf(".");
            if (pos >= 0) {
                if ((ch.length() - pos) > 4) {
                    return ch.substring(0, pos + 4);
                } else {
                    return ch;
                }
            }
            return ch;

        } else {

            return String.valueOf((int) Math.floor(vol_fleche));

        }

    }

    public double volumeFormeCylindre(String unit, String orientation, String nature_fond_g, String nature_fond_d,
            HashMap data, float t) {

        float fleche_g = 0;
        float fleche_d = 0;
        if (data.get("fleche cote gauche").toString().length() >= 1) {
            fleche_g = Float.parseFloat(data.get("fleche cote gauche").toString());
            fleche_g = convertToCentimeter(unit, fleche_g);
        }
        if (data.get("fleche cote droit").toString().length() >= 1) {
            fleche_d = Float.parseFloat(data.get("fleche cote droit").toString());
            fleche_d = convertToCentimeter(unit, fleche_d);
        }

        float longueur = Float.parseFloat(data.get("longueur").toString());
        float diametre = Float.parseFloat(data.get("diametre").toString());

        diametre = convertToCentimeter(unit, diametre);
        longueur = convertToCentimeter(unit, longueur);

        float rayon = (float) (diametre * 0.5);

        double vol_fleche = (double) 0.00;
        double vol_fleche1 = (double) 0.00;
        double vol_centre = (double) 0.00;
        double vol_total = (double) 0.00;

        double vol_fleche2 = (double) 0.00;
        if (!synonymeForOrientationOblique(orientation.toLowerCase())
                && !synonymeForOrientationVerticale(orientation.toLowerCase())) {

            if (nature_fond_g.equals(nature_fond_d) && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {

                if (rayon < t && t <= 2 * rayon) {
                    vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_d / 3
                            - (2 * rayon - t) * (2 * rayon - t) * t * fleche_d / (3 * rayon));

                    vol_fleche1 = Math.PI * (2 * Math.pow(rayon, 2) * fleche_g / 3
                            - (2 * rayon - t) * (2 * rayon - t) * t * fleche_g / (3 * rayon));

                } else if (t <= rayon) {
                    double p = (t * (diametre - t)) / (rayon * rayon);
                    vol_fleche = Math.PI * p * fleche_d * rayon * t / 3;
                    vol_fleche1 = Math.PI * p * fleche_g * rayon * t / 3;

                }
                vol_centre = longueur * (Math.sqrt((diametre - t) * t) * (t - rayon)
                        + diametre * diametre * Math.acos(1 - (t / rayon)) / 4);
                vol_total = vol_centre + vol_fleche + vol_fleche1;

            } else if (nature_fond_g.equals(nature_fond_d) && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)) {

                vol_total = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                        - Math.pow(rayon - t, 3) - 3 * t * (rayon - t))
                        / (3 * diametre));
                vol_total += (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                        - Math.pow(rayon - t, 3) - 3 * t * (rayon - t))
                        / (3 * diametre));
                vol_total += longueur * (Math.sqrt((diametre - t) * t) * (t - rayon)
                        + diametre * diametre * Math.acos(1 - (t / rayon)) / 4);

            } else if (nature_fond_g.equals(nature_fond_d)
                    && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_g)) {

                if (t <= rayon) {
                    vol_fleche = Math.PI * Math.pow(t, 2) * (2 * rayon - t) / 3;
                    vol_fleche = vol_fleche * 2; // parce que le fond gauche et droit sont
                    // identiqyue
                } else if (rayon < t && t <= 2 * rayon) {
                    vol_fleche = 2 * Math.PI * Math.pow(rayon, 3) - Math.PI * Math.pow(2 * rayon - t, 2) * t;
                    vol_fleche = vol_fleche * 2 / 3;
                }
                vol_centre = longueur * (Math.sqrt((diametre - t) * t) * (t - rayon)
                        + diametre * diametre * Math.acos(1 - (t / rayon)) / 4);

                vol_total = vol_centre + vol_fleche;

            } else if (nature_fond_g.equals(nature_fond_d) && synonymeForNatureFlechePlatOfCylinder(nature_fond_g)) {

                if (t <= rayon) {

                    vol_total = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - rayon)
                            + diametre * diametre * Math.acos(1 - (t / rayon)) / 4));

                } else {
                    vol_total = longueur
                            * (Math.PI * diametre * diametre / 4 + Math.sqrt((diametre - t) * t) * (t - rayon)
                            - diametre * diametre * Math.acos(-1 + (t / rayon)) / 4);
                }

            } else if ((synonymeForNatureFlechePlatOfCylinder(nature_fond_d)
                    && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_g)
                    || synonymeForNatureFlechePlatOfCylinder(nature_fond_g)
                    && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_d))) {
                if (t <= rayon) {
                    vol_fleche = Math.PI * Math.pow(t, 2) * (2 * rayon - t) / 3;
                } else if (rayon < t && t <= 2 * rayon) {
                    vol_fleche = 2 * Math.pow(rayon, 3) - Math.pow(2 * rayon - t, 2) * t;
                    vol_fleche *= Math.PI / 3;
                }

                vol_centre = longueur * (Math.sqrt((diametre - t) * t) * (t - rayon)
                        + diametre * diametre * Math.acos(1 - (t / rayon)) / 4);

                vol_total = vol_fleche + vol_centre;

            } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g)
                    && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {

                if (rayon < t && t <= 2 * rayon) {

                    vol_fleche1 = 2 * Math.pow(rayon, 2) * fleche_d
                            - (2 * rayon - t) * (2 * rayon - t)
                            * fleche_d * t / rayon;

                    vol_fleche1 *= Math.PI / 3;
                } else if (t <= rayon) {
                    double p = (t * (2 * rayon - t)) / (rayon * rayon);
                    vol_fleche1 = Math.PI * p * fleche_d * rayon * t / 3;
                }

                vol_centre = (double) (longueur * (Math.sqrt((diametre - t) * t) * (t - rayon)
                        + diametre * diametre * Math.acos(1 - (t / rayon)) / 4));

                vol_total = vol_centre + vol_fleche1;

            } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d)
                    && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {

                if (rayon < t && t <= 2 * rayon) {
                    vol_fleche1 = 2 * Math.pow(rayon, 2) * fleche_g
                            - (2 * rayon - t) * (2 * rayon - t)
                            * fleche_g * t / rayon;

                    vol_fleche1 *= Math.PI / 3;
                } else if (t <= rayon) {
                    double p = (t * (2 * rayon - t)) / (rayon * rayon);
                    vol_fleche1 = Math.PI * p * fleche_g * rayon * t / 3;
                }

                vol_centre = longueur * (Math.sqrt((diametre - t) * t) * (t - rayon)
                        + diametre * diametre * Math.acos(1 - (t / rayon)) / 4);

                vol_total = vol_centre + vol_fleche1;

            } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d)
                    && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)) {

                vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                        - Math.pow(diametre / 2 - t, 3) - 3 * t * (diametre / 2 - t))
                        / (3 * diametre));

                vol_centre = longueur * (Math.sqrt((diametre - t) * t) * (t - rayon)
                        + diametre * diametre * Math.acos(1 - (t / rayon)) / 4);

                vol_total = vol_fleche + vol_centre;

            } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g)
                    && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_d)) {

                vol_fleche = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                        - Math.pow(diametre / 2 - t, 3) - 3 * t * (diametre / 2 - t))
                        / (3 * diametre));

                vol_centre = longueur * (Math.sqrt((diametre - t) * t) * (t - rayon)
                        + diametre * diametre * Math.acos(1 - (t / rayon)) / 4);

                vol_total = vol_centre + vol_fleche;

            } else if (synonymeFormeCapaciteMultiLangueForSphere(nature_fond_g)
                    && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {

                if (rayon < t && t <= 2 * rayon) {
                    vol_fleche = 2 * Math.pow(rayon, 3)
                            - Math.pow(2 * rayon - t, 2) * t;

                    vol_fleche1 = 2 * Math.pow(rayon, 2) * fleche_d
                            - (2 * rayon - t) * (2 * rayon - t)
                            * fleche_d * t / rayon;
                    vol_fleche *= Math.PI / 3;
                    vol_fleche1 *= Math.PI / 3;
                } else if (t <= rayon) {
                    double p = (t * (2 * rayon - t)) / (rayon * rayon);
                    vol_fleche = Math.PI * Math.pow(t, 2) * (2 * rayon - t) / 3;
                    vol_fleche1 = Math.PI * p * fleche_d * rayon * t / 3;
                }

                vol_centre = longueur * (Math.sqrt((diametre - t) * t) * (t - rayon)
                        + diametre * diametre * Math.acos(1 - (t / rayon)) / 4);

                vol_total = vol_fleche + vol_centre + vol_fleche1;

            } else if (synonymeFormeCapaciteMultiLangueForSphere(nature_fond_d)
                    && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {

                if (rayon < t && t <= 2 * rayon) {
                    vol_fleche = 2 * Math.pow(rayon, 3)
                            - Math.pow(2 * rayon - t, 2) * t;

                    vol_fleche1 = 2 * Math.pow(rayon, 2) * fleche_g
                            - (2 * rayon - t) * (2 * rayon - t)
                            * fleche_g * t / rayon;

                    vol_fleche *= Math.PI / 3;
                    vol_fleche1 *= Math.PI / 3;
                } else if (t <= rayon) {
                    double p = (t * (2 * rayon - t)) / (rayon * rayon);
                    vol_fleche = Math.PI * Math.pow(t, 2) * (2 * rayon - t) / 3;
                    vol_fleche1 = Math.PI * p * fleche_g * rayon * t / 3;
                }

                vol_centre = longueur * (Math.sqrt((diametre - t) * t) * (t - rayon)
                        + diametre * diametre * Math.acos(1 - (t / rayon)) / 4);

                vol_total = vol_fleche + vol_centre + vol_fleche1;

            } else if (synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)
                    && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {

                vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                        - Math.pow(diametre / 2 - t, 3) - 3 * t * (diametre / 2 - t))
                        / (3 * diametre));

                if (rayon < t && t <= 2 * rayon) {
                    vol_fleche1 = 2 * Math.pow(rayon, 2) * fleche_d
                            - (2 * rayon - t) * (2 * rayon - t)
                            * fleche_d * t / rayon;

                    vol_fleche1 *= Math.PI / 3;
                }
                if (t <= rayon) {
                    double p = (t * (2 * rayon - t)) / (rayon * rayon);
                    vol_fleche1 = Math.PI * p * fleche_d * rayon * t / 3;
                }
                vol_centre = longueur * (Math.sqrt((diametre - t) * t) * (t - rayon)
                        + diametre * diametre * Math.acos(1 - (t / rayon)) / 4);

                vol_total = vol_fleche + vol_centre + vol_fleche1;

            } else if (synonymeForNatureFlecheConiqueOfCylinder(nature_fond_d)
                    && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {

                vol_fleche = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                        - Math.pow(diametre / 2 - t, 3) - 3 * t * (diametre / 2 - t))
                        / (3 * diametre));

                if (rayon < t && t <= 2 * rayon) {
                    vol_fleche1 = 2 * Math.pow(rayon, 2) * fleche_g
                            - (2 * rayon - t) * (2 * rayon - t)
                            * fleche_g * t / rayon;

                    vol_fleche1 *= Math.PI / 3;
                } else if (t <= rayon) {
                    double p = (t * (2 * rayon - t)) / (rayon * rayon);
                    vol_fleche1 = Math.PI * p * fleche_g * rayon * t / 3;
                }

                vol_centre = longueur * (Math.sqrt((diametre - t) * t) * (t - rayon)
                        + diametre * diametre * Math.acos(1 - (t / rayon)) / 4);

                vol_total = vol_fleche + vol_centre + vol_fleche1;

            } else if (synonymeForNatureFlecheConiqueOfCylinder(nature_fond_d)
                    && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_g)) {
                // sphere------------conique
                System.out.println("cote gauche sphere,  cote droit  conique");
                if (t <= rayon) {
                    vol_fleche = (Math.PI) * Math.pow(t, 2) * (2 * rayon - t) / 3;
                } else if (rayon < t && t <= 2 * rayon) {
                    vol_fleche = 2 * Math.pow(rayon, 3)
                            - Math.pow(2 * rayon - t, 2) * t;

                    vol_fleche *= Math.PI / 3;
                }

                vol_centre = longueur * (Math.sqrt((diametre - t) * t) * (t - rayon)
                        + diametre * diametre * Math.acos(1 - (t / rayon)) / 4);

                vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                        - Math.pow(diametre / 2 - t, 3) - 3 * t * (diametre / 2 - t))
                        / (3 * diametre));

                vol_total = vol_fleche + vol_centre + vol_fleche1;

            } else if (synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)
                    && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_d)) {

                if (t <= rayon) {
                    vol_fleche = (Math.PI) * Math.pow(t, 2) * (2 * rayon - t) / 3;
                } else if (rayon < t && t < 2 * rayon) {
                    vol_fleche = 2 * Math.pow(rayon, 3)
                            - Math.pow(2 * rayon - t, 2) * t;

                    vol_fleche *= Math.PI / 3;
                }

                vol_centre = longueur * (Math.sqrt((diametre - t) * t) * (t - rayon)
                        + diametre * diametre * Math.acos(1 - (t / rayon)) / 4);

                vol_fleche1 = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                        - Math.pow(diametre / 2 - t, 3) - 3 * t * (diametre / 2 - t))
                        / (3 * diametre));

                vol_total = vol_fleche + vol_centre + vol_fleche1;

            }

        } else if (synonymeForOrientationVerticale(orientation.toLowerCase())) {

            if (nature_fond_g.equals(nature_fond_d) && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {

                if (t <= fleche_d) {

                    vol_total = 2 * Math.PI * rayon * rayon * t * t
                            * (2 * fleche_d - t) / (3 * Math.pow(fleche_d, 2));
                } else if (fleche_d < t && t <= (longueur + fleche_d)) {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (t - fleche_d) / 4
                            + 2 * rayon * rayon * fleche_d / 3));
                } else if ((fleche_d + longueur) < t && t <= (longueur + fleche_d + fleche_g)) {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (longueur / 4)
                            + 2 * rayon * rayon * fleche_d / 3));

                    double be = 0;

                    double be1 = (fleche_g + fleche_d + longueur - t) * (fleche_g + fleche_d + longueur - t)
                            * (fleche_g + t - fleche_d - longueur) * rayon * rayon
                            / (fleche_g * fleche_g);

                    double be2 = Math.pow(rayon, 2) * fleche_g;

                    be = be2 - be1;

                    System.out.println(" ellipise  ellipse  " + t + "  -----------------------    " + be);
                    vol_total += be * Math.PI * 2 / 3;
                    System.out.println(" ellipise  ellipse  -----------------------    " + vol_total);

                }

            } else if (nature_fond_g.equals(nature_fond_d) && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)) {

                if (t <= fleche_d) {
                    vol_total = (double) (Math.PI * t * t * t * diametre * diametre
                            / (12 * fleche_d * fleche_d));
                } else if (fleche_d < t && t <= longueur + fleche_d) {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (t - fleche_d) / 4
                            + diametre * diametre * fleche_d / 12));
                } else if (fleche_d + longueur < t && t <= longueur + fleche_d + fleche_g) {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (longueur / 4)
                            + diametre * diametre * fleche_d / 12));
                    vol_total += Math.PI
                            * (diametre * diametre * fleche_g / 4 - Math
                                    .pow((longueur + fleche_d + fleche_g - t), 3)
                            * diametre * diametre
                            / (4 * fleche_g * fleche_g))
                            / 3;
                }

            } else if (nature_fond_g.equals(nature_fond_d)
                    && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_g)) {

                if (t <= rayon) {
                    vol_total = 2 * Math.PI * Math.pow(t, 2) * (2 * fleche_d - t) / 3;
                    System.out.println(" spharique " + t + " spherique  -----------------------    " + vol_total);
                } else if (rayon < t && t <= longueur + rayon) {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (t - fleche_d) / 4
                            + 2 * (rayon * rayon * rayon) / 3));
                    System.out.println(" spharique " + t + " spherique  -----------------------    " + vol_total);

                } else if ((rayon + longueur) < t && t <= (longueur + diametre)) {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (longueur) / 4
                            + 2 * (rayon * rayon * rayon) / 3));
                    double bee1 = 0, bee2 = 0;
                    bee1 = rayon * rayon * rayon;
                    bee2 = (2 * rayon + longueur - t) * (2 * rayon + longueur - t) * (t - longueur);
                    vol_total += 2 * Math.PI * (bee1 - bee2) / 3;
                    System.out.println(" spharique " + t + " spherique  -----------------------    " + vol_total);

                }

            } else if (nature_fond_g.equals(nature_fond_d) && synonymeForNatureFlechePlatOfCylinder(nature_fond_g)) {

                vol_total = (double) (Math.PI * diametre * diametre * t / 4);

            } else if (synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)
                    && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {

                if (t <= fleche_d) {
                    vol_total = 2 * Math.PI * rayon * rayon * t * t
                            * (2 * fleche_d - t) / (3 * Math.pow(fleche_d, 2));
                } else if (fleche_d < t && t <= longueur + fleche_d) {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (t - fleche_d) / 4
                            + 2 * rayon * rayon * fleche_d / 3));
                } else if (t > longueur + fleche_d && t <= longueur + fleche_d + fleche_g) {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (longueur / 4)
                            + 2 * rayon * rayon * fleche_d / 3));

                    vol_total += Math.PI
                            * (diametre * diametre * fleche_g / 4 - Math
                                    .pow((longueur + fleche_d + fleche_g - t), 3)
                            * diametre * diametre
                            / (4 * fleche_g * fleche_g))
                            / 3;

                }
            } else if (synonymeFormeCapaciteMultiLangueForSphere(nature_fond_g)
                    && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {

                if (t <= fleche_d) {

                    vol_total = 2 * Math.PI * rayon * rayon * t * t
                            * (2 * fleche_d - t)
                            / (3 * Math.pow(fleche_d, 2));
                } else if (fleche_d < t && t <= longueur + fleche_d) {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (t - fleche_d) / 4
                            + 2 * rayon * rayon * fleche_d / 3));
                } else if (t > longueur + fleche_d && t <= longueur + fleche_d + rayon) {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (longueur / 4)
                            + 2 * rayon * rayon * fleche_d / 3));

                    double bee1 = 0, bee2 = 0;
                    bee1 = rayon * rayon * rayon;
                    bee2 = (fleche_d + rayon + longueur - t) * (fleche_d + rayon + longueur - t)
                            * (rayon + t - fleche_d - longueur);
                    vol_total += 2 * Math.PI * (bee1 - bee2) / 3;

                }
            } else if (synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)
                    && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_d)) {

                if (t <= fleche_d) {
                    vol_total = (double) (Math.PI * t * t * t * diametre * diametre
                            / (12 * fleche_d * fleche_d));
                } else if (fleche_d < t && t <= longueur + fleche_d) {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (t - fleche_d) / 4
                            + diametre * diametre * fleche_d / 12));
                } else {

                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (longueur / 4)
                            + diametre * diametre * fleche_d / 12));

                    double be = 0;

                    double be1 = (fleche_g + fleche_d + longueur - t) * (fleche_g + fleche_d + longueur - t)
                            * (fleche_g + t - fleche_d - longueur) * rayon * rayon
                            / (fleche_g * fleche_g);

                    double be2 = Math.pow(rayon, 2) * fleche_g;

                    be = be2 - be1;

                    vol_total += be * Math.PI * 2 / 3;

                }
            } else if (synonymeFormeCapaciteMultiLangueForSphere(nature_fond_g)
                    && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_d)) {

                if (t <= fleche_d) {
                    vol_total = (double) (Math.PI * t * t * t * diametre * diametre
                            / (12 * fleche_d * fleche_d));
                } else if (fleche_d < t && t <= longueur + fleche_d) {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (t - fleche_d) / 4
                            + diametre * diametre * fleche_d / 12));
                } else {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (longueur) / 4
                            + 2 * (rayon * rayon * rayon) / 3));

                    double bee1 = 0, bee2 = 0;
                    bee1 = rayon * rayon * rayon;
                    bee2 = (fleche_d + rayon + longueur - t) * (fleche_d + rayon + longueur - t)
                            * (rayon + t - fleche_d - longueur);
                    vol_total += 2 * Math.PI * (bee1 - bee2) / 3;

                }
            } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g)
                    && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_d)) {

                if (t <= fleche_d) {
                    vol_total = (double) (Math.PI * t * t * t * diametre * diametre
                            / (12 * fleche_d * fleche_d));
                } else if (fleche_d < t && t <= longueur + fleche_d) {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (t - fleche_d) / 4
                            + diametre * diametre * fleche_d / 12));
                }
            } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d)
                    && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)) {

                if (t <= longueur) {
                    vol_total = (double) Math.PI
                            * (diametre * diametre * (t) / 4);
                } else if (longueur < t && t <= longueur + fleche_g) {

                    vol_total = (double) Math.PI
                            * (diametre * diametre * (longueur / 4));
                    fleche_d = 0;

                    vol_total += Math.PI
                            * (diametre * diametre * fleche_g / 4 - Math
                                    .pow((longueur + fleche_g - t), 3)
                            * diametre * diametre
                            / (4 * fleche_g * fleche_g))
                            / 3;

                }
            } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g)
                    && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_d)) {
                if (t <= rayon) {
                    vol_total = 2 * Math.PI * Math.pow(t, 2) * (2 * rayon - t) / 3;

                } else if (rayon < t && t <= longueur + rayon) {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (t - fleche_d) / 4
                            + 2 * (rayon * rayon * rayon) / 3));
                }
            } else if (synonymeFormeCapaciteMultiLangueForSphere(nature_fond_g)
                    && synonymeForNatureFlechePlatOfCylinder(nature_fond_d)) {

                if (t <= longueur) {
                    vol_total = (double) Math.PI * (diametre * diametre * (t) / 4);
                } else if (longueur < t && t <= longueur + rayon) {

                    vol_total = (double) Math.PI * (diametre * diametre * longueur / 4);

                    double bee1 = 0, bee2 = 0;
                    bee1 = rayon * rayon * rayon;
                    bee2 = (rayon + longueur - t) * (rayon + longueur - t) * (t + rayon - longueur);
                    vol_total += 2 * Math.PI * (bee1 - bee2) / 3;

                }
            } else if (synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)
                    && synonymeForNatureFlechePlatOfCylinder(nature_fond_d)) {

                if (t <= longueur) {
                    vol_total = (double) Math.PI * (diametre * diametre * (t) / 4);
                } else if (longueur < t && t <= longueur + fleche_g) {

                    vol_total = (double) Math.PI * (diametre * diametre * longueur / 4);

                    double be = 0;

                    double be1 = (fleche_g + longueur - t) * (fleche_g + longueur - t)
                            * (fleche_g + t - longueur) * rayon * rayon
                            / (fleche_g * fleche_g);

                    double be2 = Math.pow(rayon, 2) * fleche_g;

                    be = be2 - be1;

                    vol_total += be * Math.PI * 2 / 3;

                }
            } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g)
                    && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {
                if (t <= fleche_d) {

                    vol_total = 2 * Math.PI * rayon * rayon * t * t
                            * (2 * fleche_d - t) / (3 * Math.pow(fleche_d, 2));
                } else if (fleche_d < t && t <= longueur + fleche_d) {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (t - fleche_d) / 4
                            + 2 * rayon * rayon * fleche_d / 3));
                }
            } else if (synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)
                    && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_d)) {

                if (t <= rayon) {
                    vol_total = 2 * Math.PI * Math.pow(t, 2) * (2 * rayon - t) / 3;

                } else if (rayon < t && t <= longueur + rayon) {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (t - fleche_d) / 4
                            + 2 * (rayon * rayon * rayon) / 3));
                } else {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (longueur) / 4
                            + 2 * (rayon * rayon * rayon) / 3));

                    double be = 0;
                    double be1 = (fleche_g + rayon + longueur - t) * (fleche_g + rayon + longueur - t)
                            * (fleche_g + t - rayon - longueur) * rayon * rayon
                            / (fleche_g * fleche_g);

                    double be2 = Math.pow(rayon, 2) * fleche_g;

                    be = be2 - be1;
                    vol_total += be * Math.PI * 2 / 3;

                }
            } else if (synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)
                    && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_d)) {

                if (t <= rayon) {
                    vol_total = 2 * Math.PI * Math.pow(t, 2) * (2 * rayon - t) / 3;

                } else if (rayon < t && t <= longueur + rayon) {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (t - fleche_d) / 4
                            + 2 * (rayon * rayon * rayon) / 3));
                } else {
                    vol_total = (double) (Math.PI
                            * (diametre * diametre * (longueur) / 4
                            + 2 * (rayon * rayon * rayon) / 3));

                    vol_total += Math.PI
                            * (diametre * diametre * fleche_g / 4 - Math
                                    .pow((longueur + fleche_d + fleche_g - t), 3)
                            * diametre * diametre
                            / (4 * fleche_g * fleche_g))
                            / 3;

                }
            }

        } else {
            String sensOrientation = DonneeGeometrique.getSensRotationOblique();
            if (!sensOrientation.equalsIgnoreCase("trigo")) {
                float abcisse = Float.parseFloat(data.get("position x").toString());
                float angle = Float.parseFloat(data.get("angle inclinaison").toString());
                float cosinusAngle = (float) Math.cos(Math.PI * angle / 180);

                float hauteurSectionSuperieure = ((diametre / cosinusAngle) - t) / cosinusAngle;
                hauteurSectionSuperieure = (float) (diametre - hauteurSectionSuperieure
                        - abcisse * Math.tan(Math.PI * angle / 180));
                float variableIntermediaireK = 1 - (hauteurSectionSuperieure / rayon);
                float variableIntermediaireC = (float) (variableIntermediaireK
                        - (longueur * Math.tan(Math.PI * angle / 180) / rayon));
                // float hauteurLimite = (float) (diametre - (longueur - abcisse) *
                // Math.sin(Math.PI*angle/180));

                double intermediaireCalculVolume = variableIntermediaireK * Math.acos(variableIntermediaireK)
                        - variableIntermediaireC * Math.acos(variableIntermediaireC);
                intermediaireCalculVolume += (variableIntermediaireC * variableIntermediaireC + 2)
                        * Math.sqrt(1 - variableIntermediaireC * variableIntermediaireC) / 3;
                intermediaireCalculVolume = intermediaireCalculVolume
                        - (variableIntermediaireK * variableIntermediaireK + 2)
                        * Math.sqrt(1 - variableIntermediaireK * variableIntermediaireK) / 3;

                vol_centre = Math.pow(rayon, 3) / Math.tan(Math.PI * angle / 180);
                vol_centre *= intermediaireCalculVolume;
                float tg = (float) (hauteurSectionSuperieure + longueur * Math.tan(Math.PI * angle / 180));

                float condition = (float) ((abcisse + diametre * Math.tan(Math.PI * angle / 180))
                        * Math.sin(Math.PI * angle / 180));

                if (t > condition) {

                    if (nature_fond_g.equals(nature_fond_d)
                            && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {

                        if (tg <= rayon) {
                            double p = (tg * (diametre - tg)) / (rayon * rayon);
                            vol_fleche1 = Math.PI * p * fleche_g * rayon * tg / 3;

                            p = (hauteurSectionSuperieure * (diametre - hauteurSectionSuperieure)) / (rayon * rayon);
                            vol_fleche = Math.PI * p * fleche_d * rayon * hauteurSectionSuperieure / 3;

                        } else if (tg > rayon && hauteurSectionSuperieure <= rayon) { //
                            double p = (hauteurSectionSuperieure * (diametre - hauteurSectionSuperieure))
                                    / (rayon * rayon);
                            vol_fleche = Math.PI * p * fleche_d * rayon * hauteurSectionSuperieure / 3;

                            vol_fleche1 = Math.PI * (2 * Math.pow(rayon, 2) * fleche_g / 3
                                    - (2 * rayon - tg) * (2 * rayon - tg) * tg * fleche_g / (3 * rayon));

                        } else if (hauteurSectionSuperieure > rayon && tg <= diametre) {

                            vol_fleche1 = Math.PI * (2 * Math.pow(rayon, 2) * fleche_g / 3
                                    - (2 * rayon - tg) * (2 * rayon - tg) * tg * fleche_g / (3 * rayon));

                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_d / 3
                                    - (2 * rayon - hauteurSectionSuperieure) * (2 * rayon - hauteurSectionSuperieure)
                                    * hauteurSectionSuperieure * fleche_d / (3 * rayon));

                        } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche1 = Math.PI * 2 * Math.pow(rayon, 2) * fleche_g / 3;

                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_d / 3
                                    - (2 * rayon - hauteurSectionSuperieure) * (2 * rayon - hauteurSectionSuperieure)
                                    * hauteurSectionSuperieure * fleche_d / (3 * rayon));

                        }

                        vol_total = vol_centre + vol_fleche + vol_fleche1;

                    } else if (nature_fond_g.equals(nature_fond_d)
                            && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)) {
                        if (tg <= rayon) {

                            vol_fleche1 = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - tg, 3) - 3 * tg * (rayon - tg))
                                    / (3 * diametre));

                            vol_fleche = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                        } else if (tg > rayon && hauteurSectionSuperieure <= rayon) {

                            vol_fleche = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                            vol_fleche1 = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - tg, 3) - 3 * tg * (rayon - tg))
                                    / (3 * diametre));

                        } else if (hauteurSectionSuperieure > rayon && tg <= diametre) {

                            vol_fleche = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                            vol_fleche1 = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - tg, 3) - 3 * tg * (rayon - tg))
                                    / (3 * diametre));

                        } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                            vol_fleche1 = (double) (Math.PI * Math.pow(diametre, 2) * fleche_g / 12);

                        }

                        vol_total = vol_centre + vol_fleche + vol_fleche1;

                    } else if (nature_fond_g.equals(nature_fond_d)
                            && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_g)) {
                        if (tg <= rayon) {
                            vol_fleche = Math.PI * Math.pow(tg, 2) * (2 * rayon - tg) / 3;
                            vol_fleche1 = Math.PI * Math.pow(hauteurSectionSuperieure, 2)
                                    * (2 * rayon - hauteurSectionSuperieure) / 3;

                        } else if (tg > rayon && hauteurSectionSuperieure <= rayon) {

                            vol_fleche = Math.PI * Math.pow(hauteurSectionSuperieure, 2)
                                    * (2 * rayon - hauteurSectionSuperieure) / 3;
                            vol_fleche1 = (2 * Math.PI * Math.pow(rayon, 3)
                                    - Math.PI * Math.pow(2 * rayon - tg, 2) * tg) / 3;

                        } else if (hauteurSectionSuperieure > rayon && tg <= diametre) {

                            vol_fleche = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI
                                    * Math.pow(2 * rayon - hauteurSectionSuperieure, 2) * hauteurSectionSuperieure) / 3;
                            vol_fleche1 = (2 * Math.PI * Math.pow(rayon, 3)
                                    - Math.PI * Math.pow(2 * rayon - tg, 2) * tg) / 3;

                        } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche1 = Math.PI * 2 * Math.pow(rayon, 3) / 3;

                            vol_fleche = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI
                                    * Math.pow(2 * rayon - hauteurSectionSuperieure, 2) * hauteurSectionSuperieure) / 3;

                        }

                        vol_total = vol_centre + vol_fleche + vol_fleche1;

                    } else if (nature_fond_g.equals(nature_fond_d)
                            && synonymeForNatureFlechePlatOfCylinder(nature_fond_g)) {

                        vol_total = vol_centre;

                    } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d)
                            && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_g)) {
                        if (tg <= rayon) {
                            vol_fleche = Math.PI * Math.pow(tg, 2) * (2 * rayon - tg) / 3;

                        } else if (tg > rayon && hauteurSectionSuperieure <= rayon) {

                            vol_fleche = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI * Math.pow(2 * rayon - tg, 2) * tg)
                                    / 3;

                        } else if (hauteurSectionSuperieure > rayon && tg <= diametre) {

                            vol_fleche = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI * Math.pow(2 * rayon - tg, 2) * tg)
                                    / 3;

                        } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche = (2 * Math.PI * Math.pow(rayon, 3)) / 3;

                        }

                        vol_total = vol_centre + vol_fleche;

                    } else if ((synonymeForNatureFlechePlatOfCylinder(nature_fond_g)
                            && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_d))) {
                        if (tg <= rayon) {
                            vol_fleche = Math.PI * Math.pow(hauteurSectionSuperieure, 2)
                                    * (2 * rayon - hauteurSectionSuperieure) / 3;

                        } else if (tg > rayon && hauteurSectionSuperieure <= rayon) {

                            vol_fleche = Math.PI * Math.pow(hauteurSectionSuperieure, 2)
                                    * (2 * rayon - hauteurSectionSuperieure) / 3;

                        } else if (hauteurSectionSuperieure > rayon && tg <= diametre) {

                            vol_fleche = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI
                                    * Math.pow(2 * rayon - hauteurSectionSuperieure, 2) * hauteurSectionSuperieure) / 3;

                        } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI
                                    * Math.pow(2 * rayon - hauteurSectionSuperieure, 2) * hauteurSectionSuperieure) / 3;

                        }

                        vol_total = vol_centre + vol_fleche;

                    } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g)
                            && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {
                        if ((tg <= rayon) || (tg > rayon && hauteurSectionSuperieure <= rayon)) {
                            double p = (hauteurSectionSuperieure * (diametre - hauteurSectionSuperieure))
                                    / (rayon * rayon);
                            vol_fleche1 = Math.PI * p * fleche_d * rayon * hauteurSectionSuperieure / 3;

                        } else if (hauteurSectionSuperieure > rayon && tg <= diametre) {

                            vol_fleche1 = Math.PI * (2 * Math.pow(rayon, 2) * fleche_d / 3
                                    - (2 * rayon - hauteurSectionSuperieure) * (2 * rayon - hauteurSectionSuperieure)
                                    * hauteurSectionSuperieure * fleche_d / (3 * rayon));

                        } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche1 = Math.PI * (2 * Math.pow(rayon, 2) * fleche_d / 3
                                    - (2 * rayon - hauteurSectionSuperieure) * (2 * rayon - hauteurSectionSuperieure)
                                    * hauteurSectionSuperieure * fleche_d / (3 * rayon));

                        }

                        vol_total = vol_centre + vol_fleche1;

                    } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d)
                            && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {
                        if (tg <= rayon) {

                            double p = (tg * (diametre - tg)) / (rayon * rayon);
                            vol_fleche = Math.PI * p * fleche_g * rayon * tg / 3;

                        } else if ((tg > rayon && hauteurSectionSuperieure <= rayon)
                                || (hauteurSectionSuperieure > rayon && tg <= diametre)) {

                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_g / 3
                                    - (2 * rayon - tg) * (2 * rayon - tg) * tg * fleche_g / (3 * rayon));

                        } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_g / 3);

                        }
                        vol_total = vol_centre + vol_fleche;

                    } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d)
                            && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)) {

                        if (tg <= rayon) {

                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - tg, 3) - 3 * tg * (rayon - tg))
                                    / (3 * diametre));

                        } else if (tg > rayon && hauteurSectionSuperieure <= rayon) {

                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - tg, 3) - 3 * tg * (rayon - tg))
                                    / (3 * diametre));

                        } else if (hauteurSectionSuperieure > rayon && tg <= diametre) {

                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - tg, 3) - 3 * tg * (rayon - tg))
                                    / (3 * diametre));

                        } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche = (double) (Math.PI * Math.pow(diametre, 2) * fleche_d / 12);

                        }

                        vol_total = vol_centre + vol_fleche;

                    } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g)
                            && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_d)) {
                        if (tg <= rayon) {

                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                        } else if (tg > rayon && hauteurSectionSuperieure <= rayon) {

                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                        } else if (hauteurSectionSuperieure > rayon && tg <= diametre) {

                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                        } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                        }

                        vol_total = vol_centre + vol_fleche1;

                    } else if (synonymeFormeCapaciteMultiLangueForSphere(nature_fond_g)
                            && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {
                        if (tg <= rayon) {
                            vol_fleche1 = Math.PI * Math.pow(tg, 2) * (2 * rayon - tg) / 3;
                            double p = (hauteurSectionSuperieure * (diametre - hauteurSectionSuperieure))
                                    / (rayon * rayon);
                            vol_fleche = Math.PI * p * fleche_d * rayon * hauteurSectionSuperieure / 3;

                        } else if (tg > rayon && hauteurSectionSuperieure <= rayon) {

                            double p = (hauteurSectionSuperieure * (diametre - hauteurSectionSuperieure))
                                    / (rayon * rayon);
                            vol_fleche = Math.PI * p * fleche_d * rayon * hauteurSectionSuperieure / 3;

                            vol_fleche1 = (2 * Math.PI * Math.pow(rayon, 3)
                                    - Math.PI * Math.pow(2 * rayon - tg, 2) * tg) / 3;

                        } else if (hauteurSectionSuperieure > rayon && tg <= diametre) {
                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_d / 3
                                    - (2 * rayon - hauteurSectionSuperieure) * (2 * rayon - hauteurSectionSuperieure)
                                    * hauteurSectionSuperieure * fleche_d / (3 * rayon));
                            vol_fleche1 = (2 * Math.PI * Math.pow(rayon, 3)
                                    - Math.PI * Math.pow(2 * rayon - tg, 2) * tg) / 3;

                        } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche1 = Math.PI * 2 * Math.pow(rayon, 3) / 3;

                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_d / 3
                                    - (2 * rayon - hauteurSectionSuperieure) * (2 * rayon - hauteurSectionSuperieure)
                                    * hauteurSectionSuperieure * fleche_d / (3 * rayon));
                        }

                        vol_total = vol_centre + vol_fleche + vol_fleche1;

                    } else if (synonymeFormeCapaciteMultiLangueForSphere(nature_fond_d)
                            && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {
                        if (tg <= rayon) {
                            vol_fleche = Math.PI * Math.pow(hauteurSectionSuperieure, 2)
                                    * (2 * rayon - hauteurSectionSuperieure) / 3;
                            double p = (tg * (diametre - tg)) / (rayon * rayon);
                            vol_fleche1 = Math.PI * p * fleche_g * rayon * tg / 3;

                        } else if (tg > rayon && hauteurSectionSuperieure <= rayon) {
                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_g / 3
                                    - (2 * rayon - tg) * (2 * rayon - tg) * tg * fleche_g / (3 * rayon));

                            vol_fleche1 = Math.PI * Math.pow(hauteurSectionSuperieure, 2)
                                    * (2 * rayon - hauteurSectionSuperieure) / 3;

                        } else if (hauteurSectionSuperieure > rayon && tg <= diametre) {

                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_g / 3
                                    - (2 * rayon - tg) * (2 * rayon - tg) * tg * fleche_g / (3 * rayon));

                            vol_fleche1 = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI
                                    * Math.pow(2 * rayon - hauteurSectionSuperieure, 2) * hauteurSectionSuperieure) / 3;

                        } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche = Math.PI * 2 * Math.pow(rayon, 2) * fleche_g / 3;
                            vol_fleche1 = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI
                                    * Math.pow(2 * rayon - hauteurSectionSuperieure, 2) * hauteurSectionSuperieure) / 3;

                        }

                        vol_total = vol_centre + vol_fleche + vol_fleche1;

                    } else if (synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)
                            && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {

                        if (tg <= rayon) {
                            double p = (hauteurSectionSuperieure * (diametre - hauteurSectionSuperieure))
                                    / (rayon * rayon);
                            vol_fleche1 = Math.PI * p * fleche_d * rayon * hauteurSectionSuperieure / 3;

                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - tg, 3) - 3 * tg * (rayon - tg))
                                    / (3 * diametre));

                        } else if (tg > rayon && hauteurSectionSuperieure <= rayon) {

                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - tg, 3) - 3 * tg * (rayon - tg))
                                    / (3 * diametre));

                            double p = (hauteurSectionSuperieure * (diametre - hauteurSectionSuperieure))
                                    / (rayon * rayon);
                            vol_fleche1 = Math.PI * p * fleche_d * rayon * hauteurSectionSuperieure / 3;

                        } else if (hauteurSectionSuperieure > rayon && tg <= diametre) {

                            vol_fleche1 = Math.PI * (2 * Math.pow(rayon, 2) * fleche_d / 3
                                    - (2 * rayon - hauteurSectionSuperieure) * (2 * rayon - hauteurSectionSuperieure)
                                    * hauteurSectionSuperieure * fleche_d / (3 * rayon));

                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - tg, 3) - 3 * tg * (rayon - tg))
                                    / (3 * diametre));

                        } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche1 = (double) (Math.PI * Math.pow(diametre, 2) * fleche_g / 12);

                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_d / 3
                                    - (2 * rayon - hauteurSectionSuperieure) * (2 * rayon - hauteurSectionSuperieure)
                                    * hauteurSectionSuperieure * fleche_d / (3 * rayon));
                        }

                        vol_total = vol_centre + vol_fleche + vol_fleche1;

                    } else if (synonymeForNatureFlecheConiqueOfCylinder(nature_fond_d)
                            && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {

                        if (tg <= rayon) {
                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                            double p = (tg * (diametre - tg)) / (rayon * rayon);
                            vol_fleche = Math.PI * p * fleche_g * rayon * tg / 3;

                        } else if (tg > rayon && hauteurSectionSuperieure <= rayon) {
                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_g / 3
                                    - (2 * rayon - tg) * (2 * rayon - tg) * tg * fleche_g / (3 * rayon));

                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                        } else if (hauteurSectionSuperieure > rayon && tg <= diametre) {

                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_g / 3
                                    - (2 * rayon - tg) * (2 * rayon - tg) * tg * fleche_g / (3 * rayon));

                        } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche1 = (double) (Math.PI * Math.pow(rayon, 2) * fleche_g / 3);

                            vol_fleche = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));
                        }

                        vol_total = vol_centre + vol_fleche + vol_fleche1;

                    } else if (synonymeForNatureFlecheConiqueOfCylinder(nature_fond_d)
                            && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_g)) {
                        if (tg <= rayon) {
                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));
                            vol_fleche = Math.PI * Math.pow(tg, 2) * (2 * rayon - tg) / 3;

                        } else if (tg > rayon && hauteurSectionSuperieure <= rayon) {

                            vol_fleche = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI * Math.pow(2 * rayon - tg, 2) * tg)
                                    / 3;
                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                        } else if (hauteurSectionSuperieure > rayon && tg <= diametre) {

                            vol_fleche = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI * Math.pow(2 * rayon - tg, 2) * tg)
                                    / 3;
                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                        } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche1 = (double) (Math.PI * Math.pow(rayon, 3) / 3);

                            vol_fleche = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));
                        }

                        vol_total = vol_centre + vol_fleche + vol_fleche1;

                    } else if (synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)
                            && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_d)) {

                        if (tg <= rayon) {
                            vol_fleche = Math.PI * Math.pow(hauteurSectionSuperieure, 2)
                                    * (2 * rayon - hauteurSectionSuperieure) / 3;
                            vol_fleche1 = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - tg, 3) - 3 * tg * (rayon - tg))
                                    / (3 * diametre));

                        } else if (tg > rayon && hauteurSectionSuperieure <= rayon) {
                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - tg, 3) - 3 * tg * (rayon - tg))
                                    / (3 * diametre));
                            vol_fleche1 = Math.PI * Math.pow(hauteurSectionSuperieure, 2)
                                    * (2 * rayon - hauteurSectionSuperieure) / 3;

                        } else if (hauteurSectionSuperieure > rayon && tg <= diametre) {
                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - tg, 3) - 3 * tg * (rayon - tg))
                                    / (3 * diametre));
                            vol_fleche1 = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI
                                    * Math.pow(2 * rayon - hauteurSectionSuperieure, 2) * hauteurSectionSuperieure) / 3;

                        } else if (tg > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche1 = Math.PI * Math.pow(diametre, 2) * fleche_g / 12;

                            vol_fleche = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI
                                    * Math.pow(2 * rayon - hauteurSectionSuperieure, 2) * hauteurSectionSuperieure) / 3;

                        }

                        vol_total = vol_centre + vol_fleche + vol_fleche1;

                    }

                } else {
                    return 0;
                }

            } else {

                float abcisse = Float.parseFloat(data.get("position x").toString());
                float angle = Float.parseFloat(data.get("angle inclinaison").toString());
                float cosinusAngle = (float) Math.cos(Math.PI * angle / 180);

                float hauteurSectionSuperieure = ((diametre / cosinusAngle) - t) / cosinusAngle;
                hauteurSectionSuperieure = (float) (diametre - hauteurSectionSuperieure
                        - abcisse * Math.tan(Math.PI * angle / 180));
                float variableIntermediaireK = 1 - (hauteurSectionSuperieure / rayon);
                float variableIntermediaireC = (float) (variableIntermediaireK
                        - (longueur * Math.tan(Math.PI * angle / 180) / rayon));
                // float hauteurLimite = (float) (diametre - (longueur - abcisse) *
                // Math.sin(Math.PI*angle/180));

                double intermediaireCalculVolume = variableIntermediaireK * Math.acos(variableIntermediaireK)
                        - variableIntermediaireC * Math.acos(variableIntermediaireC);
                intermediaireCalculVolume += (variableIntermediaireC * variableIntermediaireC + 2)
                        * Math.sqrt(1 - variableIntermediaireC * variableIntermediaireC) / 3;
                intermediaireCalculVolume = intermediaireCalculVolume
                        - (variableIntermediaireK * variableIntermediaireK + 2)
                        * Math.sqrt(1 - variableIntermediaireK * variableIntermediaireK) / 3;

                vol_centre = Math.pow(rayon, 3) / Math.tan(Math.PI * angle / 180);
                vol_centre *= intermediaireCalculVolume;
                float td = (float) (hauteurSectionSuperieure + longueur * Math.tan(Math.PI * angle / 180));

                float condition1 = (float) (t + (longueur - abcisse) * Math.sin(Math.PI * angle / 180));
                float condition2 = (float) (t - abcisse * Math.sin(Math.PI * angle / 180));
                float condition = (float) ((abcisse + diametre * Math.tan(Math.PI * angle / 180))
                        * Math.sin(Math.PI * angle / 180));

                if (t > condition) {

                    if (nature_fond_g.equals(nature_fond_d)
                            && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {

                        if (td <= rayon) { //
                            double p = (td * (diametre - td)) / (rayon * rayon);
                            vol_fleche1 = Math.PI * p * fleche_d * rayon * td / 3;

                            p = (hauteurSectionSuperieure * (diametre - hauteurSectionSuperieure)) / (rayon * rayon);
                            vol_fleche = Math.PI * p * fleche_g * rayon * hauteurSectionSuperieure / 3;

                        } else if (td > rayon && hauteurSectionSuperieure <= rayon) { //
                            double p = (hauteurSectionSuperieure * (diametre - hauteurSectionSuperieure))
                                    / (rayon * rayon);
                            vol_fleche = Math.PI * p * fleche_g * rayon * hauteurSectionSuperieure / 3;

                            vol_fleche1 = Math.PI * (2 * Math.pow(rayon, 2) * fleche_d / 3
                                    - (2 * rayon - td) * (2 * rayon - td) * td * fleche_d / (3 * rayon));

                        } else if (hauteurSectionSuperieure > rayon && td <= diametre) {

                            vol_fleche1 = Math.PI * (2 * Math.pow(rayon, 2) * fleche_d / 3
                                    - (2 * rayon - td) * (2 * rayon - td) * td * fleche_d / (3 * rayon));

                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_g / 3
                                    - (2 * rayon - hauteurSectionSuperieure) * (2 * rayon - hauteurSectionSuperieure)
                                    * hauteurSectionSuperieure * fleche_g / (3 * rayon));

                        } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche1 = Math.PI * 2 * Math.pow(rayon, 2) * fleche_d / 3;

                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_g / 3
                                    - (2 * rayon - hauteurSectionSuperieure) * (2 * rayon - hauteurSectionSuperieure)
                                    * hauteurSectionSuperieure * fleche_g / (3 * rayon));

                        }

                        vol_total = vol_centre + vol_fleche + vol_fleche1;

                    } else if (nature_fond_g.equals(nature_fond_d)
                            && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)) {
                        if (td <= rayon) {

                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - td, 3) - 3 * td * (rayon - td))
                                    / (3 * diametre));

                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                        } else if (td > rayon && hauteurSectionSuperieure <= rayon) {

                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - td, 3) - 3 * td * (rayon - td))
                                    / (3 * diametre));

                        } else if (hauteurSectionSuperieure > rayon && td <= diametre) {

                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - td, 3) - 3 * td * (rayon - td))
                                    / (3 * diametre));

                        } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                            vol_fleche1 = (double) (Math.PI * Math.pow(diametre, 2) * fleche_d / 12);

                        }

                        vol_total = vol_centre + vol_fleche + vol_fleche1;

                    } else if (nature_fond_g.equals(nature_fond_d)
                            && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_g)) {
                        if (td <= rayon) {
                            vol_fleche = Math.PI * Math.pow(td, 2) * (2 * rayon - td) / 3;
                            vol_fleche1 = Math.PI * Math.pow(hauteurSectionSuperieure, 2)
                                    * (2 * rayon - hauteurSectionSuperieure) / 3;

                        } else if (td > rayon && hauteurSectionSuperieure <= rayon) {

                            vol_fleche = Math.PI * Math.pow(hauteurSectionSuperieure, 2)
                                    * (2 * rayon - hauteurSectionSuperieure) / 3;
                            vol_fleche1 = (2 * Math.PI * Math.pow(rayon, 3)
                                    - Math.PI * Math.pow(2 * rayon - td, 2) * td) / 3;

                        } else if (hauteurSectionSuperieure > rayon && td <= diametre) {

                            vol_fleche = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI
                                    * Math.pow(2 * rayon - hauteurSectionSuperieure, 2) * hauteurSectionSuperieure) / 3;
                            vol_fleche1 = (2 * Math.PI * Math.pow(rayon, 3)
                                    - Math.PI * Math.pow(2 * rayon - td, 2) * td) / 3;

                        } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche1 = Math.PI * 2 * Math.pow(rayon, 3) / 3;

                            vol_fleche = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI
                                    * Math.pow(2 * rayon - hauteurSectionSuperieure, 2) * hauteurSectionSuperieure) / 3;

                        }

                        vol_total = vol_centre + vol_fleche + vol_fleche1;

                    } else if (nature_fond_g.equals(nature_fond_d)
                            && synonymeForNatureFlechePlatOfCylinder(nature_fond_g)) {

                        vol_total = vol_centre;

                    } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d)
                            && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_g)) {
                        if (td <= rayon) {
                            vol_fleche = Math.PI * Math.pow(hauteurSectionSuperieure, 2)
                                    * (2 * rayon - hauteurSectionSuperieure) / 3;

                        } else if (td > rayon && hauteurSectionSuperieure <= rayon) {

                            vol_fleche = Math.PI * Math.pow(hauteurSectionSuperieure, 2)
                                    * (2 * rayon - hauteurSectionSuperieure) / 3;

                        } else if (hauteurSectionSuperieure > rayon && td <= diametre) {

                            vol_fleche = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI
                                    * Math.pow(2 * rayon - hauteurSectionSuperieure, 2) * hauteurSectionSuperieure) / 3;

                        } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI
                                    * Math.pow(2 * rayon - hauteurSectionSuperieure, 2) * hauteurSectionSuperieure) / 3;

                        }

                        vol_total = vol_centre + vol_fleche;

                    } else if ((synonymeForNatureFlechePlatOfCylinder(nature_fond_g)
                            && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_d))) {
                        if (td <= rayon) {
                            vol_fleche = Math.PI * Math.pow(td, 2) * (2 * rayon - td) / 3;

                        } else if (td > rayon && hauteurSectionSuperieure <= rayon) {

                            vol_fleche = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI * Math.pow(2 * rayon - td, 2) * td)
                                    / 3;

                        } else if (hauteurSectionSuperieure > rayon && td <= diametre) {

                            vol_fleche = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI * Math.pow(2 * rayon - td, 2) * td)
                                    / 3;

                        } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche = 2 * Math.PI * Math.pow(rayon, 3) / 3;

                        }

                        vol_total = vol_centre + vol_fleche;

                    } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g)
                            && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {
                        if (td <= rayon) {
                            double p = (td * (diametre - td)) / (rayon * rayon);
                            vol_fleche1 = Math.PI * p * fleche_d * rayon * td / 3;

                        } else if (td > rayon && hauteurSectionSuperieure <= rayon) {

                            vol_fleche1 = Math.PI * (2 * Math.pow(rayon, 2) * fleche_d / 3
                                    - (2 * rayon - td) * (2 * rayon - td) * td * fleche_d / (3 * rayon));

                        } else if (hauteurSectionSuperieure > rayon && td <= diametre) {

                            vol_fleche1 = Math.PI * (2 * Math.pow(rayon, 2) * fleche_d / 3
                                    - (2 * rayon - td) * (2 * rayon - td) * td * fleche_d / (3 * rayon));

                        } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche1 = Math.PI * 2 * Math.pow(rayon, 2) * fleche_d / 3;

                        }

                        vol_total = vol_centre + vol_fleche1;

                    } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d)
                            && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {
                        if (td <= rayon) {

                            double p = (hauteurSectionSuperieure * (diametre - hauteurSectionSuperieure))
                                    / (rayon * rayon);
                            vol_fleche = Math.PI * p * fleche_g * rayon * hauteurSectionSuperieure / 3;

                        } else if (td > rayon && hauteurSectionSuperieure <= rayon) {
                            double p = (hauteurSectionSuperieure * (diametre - hauteurSectionSuperieure))
                                    / (rayon * rayon);
                            vol_fleche = Math.PI * p * fleche_g * rayon * hauteurSectionSuperieure / 3;

                        } else if (hauteurSectionSuperieure > rayon && td <= diametre) {

                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_g / 3
                                    - (2 * rayon - hauteurSectionSuperieure) * (2 * rayon - hauteurSectionSuperieure)
                                    * hauteurSectionSuperieure * fleche_g / (3 * rayon));

                        } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_g / 3
                                    - (2 * rayon - hauteurSectionSuperieure) * (2 * rayon - hauteurSectionSuperieure)
                                    * hauteurSectionSuperieure * fleche_g / (3 * rayon));

                        }
                        vol_total = vol_centre + vol_fleche;

                    } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_d)
                            && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)) {

                        if (td <= rayon) {

                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                        } else if (td > rayon && hauteurSectionSuperieure <= rayon) {

                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                        } else if (hauteurSectionSuperieure > rayon && td <= diametre) {

                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                        } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                        }

                        vol_total = vol_centre + vol_fleche;

                    } else if (synonymeForNatureFlechePlatOfCylinder(nature_fond_g)
                            && synonymeForNatureFlecheConiqueOfCylinder(nature_fond_d)) {
                        if (td <= rayon) {

                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - td, 3) - 3 * td * (rayon - td))
                                    / (3 * diametre));

                        } else if (td > rayon && hauteurSectionSuperieure <= rayon) {

                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - td, 3) - 3 * td * (rayon - td))
                                    / (3 * diametre));

                        } else if (hauteurSectionSuperieure > rayon && td <= diametre) {

                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - td, 3) - 3 * td * (rayon - td))
                                    / (3 * diametre));

                        } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche1 = (double) (Math.PI * Math.pow(diametre, 2) * fleche_d / 12);

                        }

                        vol_total = vol_centre + vol_fleche1;

                    } else if (synonymeFormeCapaciteMultiLangueForSphere(nature_fond_g)
                            && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {
                        if (td <= rayon) {
                            vol_fleche1 = Math.PI * Math.pow(hauteurSectionSuperieure, 2)
                                    * (2 * rayon - hauteurSectionSuperieure) / 3;
                            double p = (td * (diametre - td)) / (rayon * rayon);
                            vol_fleche = Math.PI * p * fleche_d * rayon * td / 3;

                        } else if (td > rayon && hauteurSectionSuperieure <= rayon) {

                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_d / 3
                                    - (2 * rayon - td) * (2 * rayon - td) * td * fleche_d / (3 * rayon));

                            vol_fleche1 = Math.PI * Math.pow(hauteurSectionSuperieure, 2)
                                    * (2 * rayon - hauteurSectionSuperieure) / 3;

                        } else if (hauteurSectionSuperieure > rayon && td <= diametre) {
                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_d / 3
                                    - (2 * rayon - td) * (2 * rayon - td) * td * fleche_d / (3 * rayon));
                            vol_fleche1 = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI
                                    * Math.pow(2 * rayon - hauteurSectionSuperieure, 2) * hauteurSectionSuperieure) / 3;

                        } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche1 = Math.PI * 2 * Math.pow(rayon, 2) * fleche_d / 3;

                            vol_fleche = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI
                                    * Math.pow(2 * rayon - hauteurSectionSuperieure, 2) * hauteurSectionSuperieure) / 3;

                        }

                        vol_total = vol_centre + vol_fleche + vol_fleche1;

                    } else if (synonymeFormeCapaciteMultiLangueForSphere(nature_fond_d)
                            && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {
                        if (td <= rayon) {
                            vol_fleche = Math.PI * Math.pow(td, 2) * (2 * rayon - td) / 3;
                            double p = (hauteurSectionSuperieure * (diametre - hauteurSectionSuperieure))
                                    / (rayon * rayon);
                            vol_fleche1 = Math.PI * p * fleche_g * rayon * hauteurSectionSuperieure / 3;

                        } else if (td > rayon && hauteurSectionSuperieure <= rayon) {
                            double p = (hauteurSectionSuperieure * (diametre - hauteurSectionSuperieure))
                                    / (rayon * rayon);
                            vol_fleche = Math.PI * p * fleche_g * rayon * hauteurSectionSuperieure / 3;

                            vol_fleche1 = (2 * Math.PI * Math.pow(rayon, 3)
                                    - Math.PI * Math.pow(2 * rayon - td, 2) * td) / 3;

                        } else if (hauteurSectionSuperieure > rayon && td <= diametre) {

                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_g / 3
                                    - (2 * rayon - hauteurSectionSuperieure) * (2 * rayon - hauteurSectionSuperieure)
                                    * hauteurSectionSuperieure * fleche_g / (3 * rayon));
                            vol_fleche1 = (2 * Math.PI * Math.pow(rayon, 3)
                                    - Math.PI * Math.pow(2 * rayon - td, 2) * td) / 3;

                        } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche1 = Math.PI * 2 * Math.pow(rayon, 3) / 3;
                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_g / 3
                                    - (2 * rayon - hauteurSectionSuperieure) * (2 * rayon - hauteurSectionSuperieure)
                                    * hauteurSectionSuperieure * fleche_g / (3 * rayon));

                        }

                        vol_total = vol_centre + vol_fleche + vol_fleche1;

                    } else if (synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)
                            && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_d)) {

                        if (td <= rayon) {
                            double p = (td * (diametre - td)) / (rayon * rayon);
                            vol_fleche1 = Math.PI * p * fleche_d * rayon * td / 3;

                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                        } else if (td > rayon && hauteurSectionSuperieure <= rayon) {

                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                            vol_fleche1 = Math.PI * (2 * Math.pow(rayon, 2) * fleche_d / 3
                                    - (2 * rayon - td) * (2 * rayon - td) * td * fleche_d / (3 * rayon));

                        } else if (hauteurSectionSuperieure > rayon && td <= diametre) {

                            vol_fleche1 = Math.PI * (2 * Math.pow(rayon, 2) * fleche_d / 3
                                    - (2 * rayon - td) * (2 * rayon - td) * td * fleche_d / (3 * rayon));

                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));

                        } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche1 = Math.PI * 2 * Math.pow(rayon, 2) * fleche_d / 3;

                            vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                    - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                    / (3 * diametre));
                        }

                        vol_total = vol_centre + vol_fleche + vol_fleche1;

                    } else if (synonymeForNatureFlecheConiqueOfCylinder(nature_fond_d)
                            && synonymeFormeCapaciteMultiLangueForEllpsoide(nature_fond_g)) {

                        if (td <= rayon) {
                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - td, 3) - 3 * td * (rayon - td))
                                    / (3 * diametre));

                            double p = (hauteurSectionSuperieure * (diametre - hauteurSectionSuperieure))
                                    / (rayon * rayon);
                            vol_fleche = Math.PI * p * fleche_g * rayon * hauteurSectionSuperieure / 3;

                        } else if (td > rayon && hauteurSectionSuperieure <= rayon) {
                            double p = (hauteurSectionSuperieure * (diametre - hauteurSectionSuperieure))
                                    / (rayon * rayon);
                            vol_fleche = Math.PI * p * fleche_g * rayon * hauteurSectionSuperieure / 3;

                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - td, 3) - 3 * td * (rayon - td))
                                    / (3 * diametre));

                        } else if (hauteurSectionSuperieure > rayon && td <= diametre) {

                            vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                    - Math.pow(rayon - td, 3) - 3 * td * (rayon - td))
                                    / (3 * diametre));

                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_g / 3
                                    - (2 * rayon - hauteurSectionSuperieure) * (2 * rayon - hauteurSectionSuperieure)
                                    * hauteurSectionSuperieure * fleche_g / (3 * rayon));

                        } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                            vol_fleche1 = (double) (Math.PI * Math.pow(diametre, 2) * fleche_d / 12);

                            vol_fleche = Math.PI * (2 * Math.pow(rayon, 2) * fleche_g / 3
                                    - (2 * rayon - hauteurSectionSuperieure) * (2 * rayon - hauteurSectionSuperieure)
                                    * hauteurSectionSuperieure * fleche_g / (3 * rayon));

                        }

                        vol_total = vol_centre + vol_fleche + vol_fleche1;

                    }

                } else if (synonymeForNatureFlecheConiqueOfCylinder(nature_fond_d)
                        && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_g)) {
                    if (td <= rayon) {
                        vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                - Math.pow(rayon - td, 3) - 3 * td * (rayon - td))
                                / (3 * diametre));
                        vol_fleche = Math.PI * Math.pow(hauteurSectionSuperieure, 2)
                                * (2 * rayon - hauteurSectionSuperieure) / 3;

                    } else if (td > rayon && hauteurSectionSuperieure <= rayon) {

                        vol_fleche = Math.PI * Math.pow(hauteurSectionSuperieure, 2)
                                * (2 * rayon - hauteurSectionSuperieure) / 3;
                        vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                - Math.pow(rayon - td, 3) - 3 * td * (rayon - td))
                                / (3 * diametre));

                    } else if (hauteurSectionSuperieure > rayon && td <= diametre) {

                        vol_fleche = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI
                                * Math.pow(2 * rayon - hauteurSectionSuperieure, 2) * hauteurSectionSuperieure) / 3;
                        vol_fleche1 = (double) (Math.PI * fleche_d * 2 * (diametre * diametre * diametre / 8
                                - Math.pow(rayon - td, 3) - 3 * td * (rayon - td))
                                / (3 * diametre));

                    } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                        vol_fleche1 = (double) (Math.PI * Math.pow(diametre, 2) * fleche_d / 12);

                        vol_fleche = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI
                                * Math.pow(2 * rayon - hauteurSectionSuperieure, 2) * hauteurSectionSuperieure) / 3;

                    }

                    vol_total = vol_centre + vol_fleche + vol_fleche1;

                } else if (synonymeForNatureFlecheConiqueOfCylinder(nature_fond_g)
                        && synonymeFormeCapaciteMultiLangueForSphere(nature_fond_d)) {

                    if (td <= rayon) {
                        vol_fleche = Math.PI * Math.pow(td, 2) * (2 * rayon - td) / 3;
                        vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                / (3 * diametre));

                    } else if (td > rayon && hauteurSectionSuperieure <= rayon) {
                        vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                / (3 * diametre));
                        vol_fleche1 = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI * Math.pow(2 * rayon - td, 2) * td)
                                / 3;

                    } else if (hauteurSectionSuperieure > rayon && td <= diametre) {
                        vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                / (3 * diametre));
                        vol_fleche1 = (2 * Math.PI * Math.pow(rayon, 3) - Math.PI * Math.pow(2 * rayon - td, 2) * td)
                                / 3;

                    } else if (td > diametre && hauteurSectionSuperieure <= diametre) {

                        vol_fleche1 = Math.PI * 2 * Math.pow(rayon, 3) / 3;

                        vol_fleche = (double) (Math.PI * fleche_g * 2 * (diametre * diametre * diametre / 8
                                - Math.pow(rayon - hauteurSectionSuperieure, 3)
                                - 3 * hauteurSectionSuperieure * (rayon - hauteurSectionSuperieure))
                                / (3 * diametre));

                    }

                    vol_total = vol_centre + vol_fleche + vol_fleche1;

                }

            }

        }
        return vol_total;

    } // retourne la valeur du volume du recipient rempli a une hauteur donne

    public void certificat_with_head(HashMap info, HashMap data, InputStream logo, InputStream signature, String typeMethode,
            List<Float> abcisse, List<Double> ordonne) throws BadElementException, DocumentException {
        // import com.lowagie.text.Document;
        com.lowagie.text.Document document = new com.lowagie.text.Document(PageSize.A4);

        String nom = (String) info.get("certificat");

        String documentDirectory = System.getProperty("user.home") + "/Documents/";
        String nyettofDirectory = documentDirectory + "NyettoftTank_files/";

        File nyettofFile = new File(nyettofDirectory);
        if (!(nyettofFile.exists())) {
            nyettofFile.mkdirs();
        }

        String filePath = nyettofDirectory + nom + ".pdf";

        try {
            LocalDate date = LocalDate.now();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Table table = new Table(2, 2);
            table.setSpacing(-2);
            table.setAutoFillEmptyCells(true);

            String selectedCountry = MainView.getChoosePays();
            // PAGE 1
            Cell cell = null;
            Cell cell2 = null;
            if (selectedCountry.equalsIgnoreCase("Cameroun") || selectedCountry.equalsIgnoreCase("Cameroon")
                    || selectedCountry.equalsIgnoreCase("Kamerun")) {

                cell = new Cell(new Chunk("REPUBLIQUE DU CAMEROUN",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell2 = new Cell(new Chunk("REPUBLIC OF CAMEROON",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table.addCell(cell);
                table.addCell(cell2);

                cell = new Cell(new Chunk("Paix – Travail – Patrie",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, Color.BLACK)));
                cell2 = new Cell(new Chunk("Peace – Work - Fatherland",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, Color.BLACK)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table.addCell(cell);
                table.addCell(cell2);

                cell = new Cell("-------");
                cell2 = new Cell("-------");
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table.addCell(cell);
                table.addCell(cell2);

                cell = new Cell(new Chunk(" MINISTERE DU COMMERCE",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell2 = new Cell(new Chunk("MINISTRY OF TRADE",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table.addCell(cell);
                table.addCell(cell2);

                cell = new Cell("-------");
                cell2 = new Cell("-------");
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table.addCell(cell);
                table.addCell(cell2);

                cell = new Cell(new Chunk("DIRECTION DE LA METROLOGIE, \n"
                        + "DE LA QUALITE ET DES PRIX",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell2 = new Cell(new Chunk(" DEPARTMENT OF METROLOGY, \n"
                        + "QUALITY AND PRICES",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table.addCell(cell);
                table.addCell(cell2);

                cell = new Cell("-------");
                cell2 = new Cell("-------");
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table.addCell(cell);
                table.addCell(cell2);

                cell = new Cell(new Chunk("SOUS-DIRECTION DE LA METROLOGIE",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD, Color.BLACK)));
                cell2 = new Cell(new Chunk("SUB-DEPARTMENT OF METROLOGY",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD, Color.BLACK)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table.addCell(cell);
                table.addCell(cell2);

                cell = new Cell("-------");
                cell2 = new Cell("-------");
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table.addCell(cell);
                table.addCell(cell2);

                cell = new Cell(new Chunk("SERVICE DES INSTRUMENTS VOLUMETRIQUES",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell2 = new Cell(new Chunk("SERVICE OF VOLUMETRICS\n"
                        + "INSTRUMENTS",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table.addCell(cell);
                table.addCell(cell2);

                cell = new Cell("-------");
                cell2 = new Cell("-------");
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table.addCell(cell);
                table.addCell(cell2);

                table.disableBorderSide(Rectangle.UNDEFINED);
                document.add(table);

            } else {

                Paragraph pied = new Paragraph("\n\n\n\n\n");
                document.add(pied);

            }

            Paragraph corp = new Paragraph(
                    " \n\n\n\n\n\n\n        TABLE DE JAUGEAGE A L’ECHELLE CENTRIMETRIQUE DES VOLUMES D’EXPLOITATION"
                    + "  DES "
                    + verifie(info, "type de la capacite").toString().toUpperCase()
                    + " DE "
                    + verifie(info, "lieu des operations") + "  ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, Color.BLACK));

            corp.setAlignment(Element.ALIGN_CENTER);
            document.add(corp);

            Paragraph pied = new Paragraph("\n\n\n\n\n\n\n");
            document.add(pied);

            if (signature != null) {
                Path cible = Paths.get("xxx3.jpg");
                Files.copy(signature, cible, StandardCopyOption.REPLACE_EXISTING);
                Image img = Image.getInstance("xxx3.jpg");

                img.scaleAbsolute(200, 100);
                img.setAlignment(Element.ALIGN_MIDDLE);
                document.add(img);
                Files.deleteIfExists(cible);
            }

            Paragraph corp2 = new Paragraph(
                    " \n\n "
                    + verifie(info, "lieu des operations").toString().toUpperCase(),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK));

            corp2.setAlignment(Element.ALIGN_CENTER);
            document.add(corp2);

            document.newPage();
            document.add(Chunk.NEWLINE);

            // page 2
            if (selectedCountry.equalsIgnoreCase("Cameroun") || selectedCountry.equalsIgnoreCase("Cameroon")
                    || selectedCountry.equalsIgnoreCase("Kamerun")) {

                Table table2 = new Table(2, 2);
                cell = new Cell(new Chunk("REPUBLIQUE DU CAMEROUN",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell2 = new Cell(new Chunk("REPUBLIC OF CAMEROON",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));

                cell.setWidth(300);
                cell2.setWidth(300);

                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table2.addCell(cell);
                table2.addCell(cell2);

                cell = new Cell(new Chunk("Paix – Travail – Patrie",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, Color.BLACK)));
                cell2 = new Cell(new Chunk("Peace – Work - Fatherland",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, Color.BLACK)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table2.addCell(cell);
                table2.addCell(cell2);

                cell = new Cell("-------");
                cell2 = new Cell("-------");
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table2.addCell(cell);
                table2.addCell(cell2);

                cell = new Cell(new Chunk(" MINISTERE DU COMMERCE",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell2 = new Cell(new Chunk("MINISTRY OF TRADE",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table2.addCell(cell);
                table2.addCell(cell2);

                cell = new Cell("-------");
                cell2 = new Cell("-------");
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table2.addCell(cell);
                table2.addCell(cell2);

                cell = new Cell(new Chunk("DIRECTION DE LA METROLOGIE, \n"
                        + "DE LA QUALITE ET DES PRIX",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell2 = new Cell(new Chunk(" DEPARTMENT OF METROLOGY, \n"
                        + "QUALITY AND PRICES",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table2.addCell(cell);
                table2.addCell(cell2);

                cell = new Cell("-------");
                cell2 = new Cell("-------");
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table2.addCell(cell);
                table2.addCell(cell2);

                cell = new Cell(new Chunk("SOUS-DIRECTION DE LA METROLOGIE",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD, Color.BLACK)));
                cell2 = new Cell(new Chunk("SUB-DEPARTMENT OF METROLOGY",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD, Color.BLACK)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table2.addCell(cell);
                table2.addCell(cell2);

                cell = new Cell("-------");
                cell2 = new Cell("-------");
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table2.addCell(cell);
                table2.addCell(cell2);

                cell = new Cell(new Chunk("SERVICE DES INSTRUMENTS VOLUMETRIQUES",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell2 = new Cell(new Chunk("SERVICE OF VOLUMETRICS\n"
                        + "INSTRUMENTS",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table2.addCell(cell);
                table2.addCell(cell2);

                cell = new Cell("-------");
                cell2 = new Cell("-------");
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table2.addCell(cell);
                table2.addCell(cell2);

                table2.disableBorderSide(Rectangle.UNDEFINED);
                document.add(table2);

            } else {

                Paragraph ppp = new Paragraph(
                        " \n\n\n\n\n\n",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK));
                ppp.setAlignment(Element.ALIGN_CENTER);
                document.add(ppp);

            }

            Paragraph ppp = new Paragraph(
                    " \n\n\n    N°………………. /" + date.getYear() + "/MINCOMMERCE /SG/DMQP/SDM/SIV",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK));
            ppp.setAlignment(Element.ALIGN_CENTER);
            document.add(ppp);

            Paragraph p3 = new Paragraph(
                    "\n\n\n\n\n Table de jaugeage donnant à ’échelle centimétriques les volumes de "
                    + verifie(info, "produit stocke") + " contenus \n"
                    + "dans la " + verifie(info, "type de la capacite") + " de "
                    + verifie(info,
                            "immatriculation")
                    + " " + verifie(info, "volume nominal") + verifie(info,
                    "unite de volume")
                    + " " + " de " + verifie(info, "detenteur") + " situe a "
                    + verifie(info, "lieu des operations"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK));

            p3.setAlignment(Element.ALIGN_CENTER);
            document.add(p3);

            p3 = new Paragraph("\n\n\n LIMITE DE VALIDITE : " + verifie(info, "fin des travaux"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK));
            p3.setAlignment(Element.ALIGN_LEFT);
            document.add(p3);

            Paragraph p_pied = new Paragraph(
                    "\n\n\n POUR LE MINISTERE DU COMMERCE" + "\n ET PAR ORDRE        ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK));
            p_pied.setAlignment(Element.ALIGN_RIGHT);
            document.add(p_pied);

            document.newPage();
            document.add(Chunk.NEWLINE);

            // page 3
            if (selectedCountry.equalsIgnoreCase("Cameroun") || selectedCountry.equalsIgnoreCase("Cameroon")
                    || selectedCountry.equalsIgnoreCase("Kamerun")) {

                Table table3 = new Table(2, 2);
                cell = new Cell(new Chunk("REPUBLIQUE DU CAMEROUN",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell2 = new Cell(new Chunk("REPUBLIC OF CAMEROON",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));

                cell.setWidth(300);
                cell2.setWidth(300);

                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table3.addCell(cell);
                table3.addCell(cell2);

                cell = new Cell(new Chunk("Paix – Travail – Patrie",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, Color.BLACK)));
                cell2 = new Cell(new Chunk("Peace – Work - Fatherland",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD, Color.BLACK)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table3.addCell(cell);
                table3.addCell(cell2);

                cell = new Cell("-------");
                cell2 = new Cell("-------");
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table3.addCell(cell);
                table3.addCell(cell2);

                cell = new Cell(new Chunk(" MINISTERE DU COMMERCE",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell2 = new Cell(new Chunk("MINISTRY OF TRADE",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table3.addCell(cell);
                table3.addCell(cell2);

                cell = new Cell("-------");
                cell2 = new Cell("-------");
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table3.addCell(cell);
                table3.addCell(cell2);

                cell = new Cell(new Chunk("DIRECTION DE LA METROLOGIE, \n"
                        + "DE LA QUALITE ET DES PRIX",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell2 = new Cell(new Chunk(" DEPARTMENT OF METROLOGY, \n"
                        + "QUALITY AND PRICES",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table3.addCell(cell);
                table3.addCell(cell2);

                cell = new Cell("-------");
                cell2 = new Cell("-------");
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table3.addCell(cell);
                table3.addCell(cell2);

                cell = new Cell(new Chunk("SOUS-DIRECTION DE LA METROLOGIE",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD, Color.BLACK)));
                cell2 = new Cell(new Chunk("SUB-DEPARTMENT OF METROLOGY",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD, Color.BLACK)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table3.addCell(cell);
                table3.addCell(cell2);

                cell = new Cell("-------");
                cell2 = new Cell("-------");
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table3.addCell(cell);
                table3.addCell(cell2);

                cell = new Cell(new Chunk("SERVICE DES INSTRUMENTS VOLUMETRIQUES",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell2 = new Cell(new Chunk("SERVICE OF VOLUMETRICS\n"
                        + "INSTRUMENTS",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table3.addCell(cell);
                table3.addCell(cell2);

                cell = new Cell("-------");
                cell2 = new Cell("-------");
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.disableBorderSide(Rectangle.BOX);
                cell2.disableBorderSide(Rectangle.BOX);
                table3.addCell(cell);
                table3.addCell(cell2);

                table3.disableBorderSide(Rectangle.UNDEFINED);
                document.add(table3);

            } else {

                Paragraph p4 = new Paragraph("\n\n\n\n\n",
                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK));
                p4.setAlignment(Element.ALIGN_CENTER);
                document.add(p4);

            }

            Paragraph p4 = new Paragraph("\n NOTE TECHNIQUE",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK));
            p4.setAlignment(Element.ALIGN_CENTER);
            document.add(p4);

            p3 = new Paragraph(
                    "\n CERTIFICAT DE JAUGEAGE N°………… /" + date.getYear()
                    + "/MINCOMMERCE/SG/DMQP/SDM/SIV",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK));
            p3.setAlignment(Element.ALIGN_CENTER);
            document.add(p3);

            p3 = new Paragraph(verifie(info, "type de la capacite") + " " + verifie(info,
                    "immatriculation") + " DE " + verifie(info, "volume nominal")
                    + verifie(info,
                            "unite de volume"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,
                            Font.BOLD, Color.BLACK));

            p3.setAlignment(Element.ALIGN_CENTER);
            document.add(p3);

            Table table4 = new Table(3, 3);
            table4.setSpacing(0);

            cell = new Cell(new Chunk("FORME GENERALE : ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
            cell.setRowspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.disableBorderSide(Rectangle.BOX);
            table4.addCell(cell);

            cell2 = new Cell(new Chunk(
                    "* Compartiments de " + verifie(info, "type de la capacite") + " : "
                    + verifie(info, "nombre compartiments"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Color.BLACK)));
            Cell cell3 = new Cell(new Chunk("* Fonds : " + verifie(info, "fond capacite"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Color.BLACK)));
            cell2.setColspan(2);
            cell3.setColspan(2);
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table4.addCell(cell2);
            table4.addCell(cell3);

            cell2 = new Cell(new Chunk("*Hauteur totale temoin  " + verifie(info, "hauteur temoin"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Color.BLACK)));
            cell2.setColspan(2);
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            table4.addCell(cell2);

            table4.disableBorderSide(Rectangle.UNDEFINED);
            document.add(table4);
            document.add(new Paragraph(" "));

            Table table5 = new Table(2, 2);
            table5.setSpacing(-1);

            cell2 = new Cell(new Chunk("PRODUIT EN STOCK  ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell3 = new Cell(new Chunk(":  " + verifie(info, "produit stocke"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table5.addCell(cell2);
            table5.addCell(cell3);

            cell2 = new Cell(new Chunk("DETENTEUR  ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell3 = new Cell(new Chunk(":  " + verifie(info, "detenteur"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table5.addCell(cell2);
            table5.addCell(cell3);

            cell2 = new Cell(new Chunk("CONSTRUCTEUR  ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell3 = new Cell(new Chunk(":  " + verifie(info, "fabricant"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table5.addCell(cell2);
            table5.addCell(cell3);

            cell2 = new Cell(new Chunk("MODE OPERATOIRE  ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell3 = new Cell(new Chunk(":  " + verifie(info, "mode operatoire"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table5.addCell(cell2);
            table5.addCell(cell3);

            table5.disableBorderSide(Rectangle.UNDEFINED);
            document.add(table5);
            document.add(new Paragraph(" "));
            // document.add( new Paragraph("\n\n") );

            Table table6 = new Table(5, 5);
            table6.setSpacing(0);

            cell = new Cell(new Chunk("RESULTAT   : ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD, Color.BLACK)));
            cell.setRowspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.disableBorderSide(Rectangle.BOX);
            table6.addCell(cell);
            cell2 = new Cell(new Chunk(
                    "* La table des volumes en fonction des hauteurs à l’échelle centimétrique;",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell3 = new Cell(new Chunk(
                    "* Les hauteurs sont relevées à la verticale de la position de jaugeage;",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell2.setColspan(4);
            cell3.setColspan(4);
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table6.addCell(cell2);
            table6.addCell(cell3);

            cell2 = new Cell(new Chunk("\n -Les volumes sont valables à 30°C.",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell3 = new Cell(new Chunk(
                    "\n -L’erreur relative commise sur les volumes portés par le barème ci-après est"
                    + "inférieure ou égale à + ou - 3/1100 pour une livraison de produit supérieure ou"
                    + "égal à 50 cm",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell2.setColspan(4);
            cell3.setColspan(4);
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table6.addCell(cell2);
            table6.addCell(cell3);

            table6.disableBorderSide(Rectangle.UNDEFINED);
            document.add(table6);
            document.add(new Paragraph(
                    "\n                                                                                                  FAIT A"));
            // PAGE 4

            document.newPage();
            document.add(Chunk.NEWLINE);

            if (logo != null) {
                Path cible = Paths.get("xxx.jpg");
                Files.copy(logo, cible, StandardCopyOption.REPLACE_EXISTING);
                Image img = Image.getInstance("xxx.jpg");

                img.scaleAbsolute(50, 50);
                img.setAlignment(Element.ALIGN_LEFT);
                document.add(img);
                Files.deleteIfExists(cible);
            }

            p3 = new Paragraph(
                    "\n\n CERTIFICAT DE JAUGEAGE N°………… /" + date.getYear()
                    + "/MINCOMMERCE/SG/DMQP/SDM/SIV",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK));
            p3.setAlignment(Element.ALIGN_CENTER);
            document.add(p3);

            Paragraph x = new Paragraph("\n FICHE TECHNIQUE",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK));
            x.setAlignment(Element.ALIGN_CENTER);
            document.add(x);

            Paragraph y = new Paragraph("\n\n",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK));
            y.setAlignment(Element.ALIGN_CENTER);
            document.add(y);

            Table table7 = new Table(2, 2);
            table7.setSpacing(-1);

            cell2 = new Cell(new Chunk("NOM ET ADRESSE DU DEMANDEUR   ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell3 = new Cell(new Chunk(":  " + verifie(info, "agree"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table7.addCell(cell2);
            table7.addCell(cell3);

            cell2 = new Cell(new Chunk("PROFESSION                    ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell3 = new Cell(new Chunk(":  " + "agree".toUpperCase(),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table7.addCell(cell2);
            table7.addCell(cell3);

            cell2 = new Cell(new Chunk(
                    "EXPLOITANT DE LA  " + verifie(info, "type de la capacite") + "   :  "
                    + verifie(info, "detenteur"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell3 = new Cell(new Chunk(" ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table7.addCell(cell2);
            table7.addCell(cell3);

            cell2 = new Cell(new Chunk("DATE DES TRAVAUX              ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell3 = new Cell(new Chunk(":  " + verifie(info, "debut des travaux"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table7.addCell(cell2);
            table7.addCell(cell3);

            cell2 = new Cell(new Chunk("LIEU DES OPERATIONS           ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell3 = new Cell(new Chunk(":  " + verifie(info, "lieu des operations"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table7.addCell(cell2);
            table7.addCell(cell3);

            cell2 = new Cell(new Chunk("CHEF DES OPERATIONS           ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell3 = new Cell(new Chunk(":  " + verifie(info, "chef des operations"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table7.addCell(cell2);
            table7.addCell(cell3);

            table7.disableBorderSide(Rectangle.UNDEFINED);
            document.add(table7);
            document.add(new Paragraph("\n\n 1.) IDENTIFICATION DE LA "
                    + verifie(info, "type de la capacite") + " " + verifie(info, "produit stocke")
                    + "  "
                    + verifie(info, "volume nominal") + verifie(info, "unite de volume") + " \n",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));

            Table table8 = new Table(2, 2);
            table8.setSpacing(-1);

            cell2 = new Cell(new Chunk(
                    "IMMATRICULATION DE LA   " + verifie(info, "type de la capacite"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell3 = new Cell(new Chunk(":  " + verifie(info, "immatriculation"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table8.addCell(cell2);
            table8.addCell(cell3);

            cell2 = new Cell(new Chunk("NOMBRE DE COMPARTIMENTS       ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell3 = new Cell(new Chunk(":  " + verifie(info, "nombre compartiments"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table8.addCell(cell2);
            table8.addCell(cell3);

            cell2 = new Cell(new Chunk("CONSTRUCTEUR                  ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell3 = new Cell(new Chunk(":  " + verifie(info, "fabricant"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table8.addCell(cell2);
            table8.addCell(cell3);

            cell2 = new Cell(new Chunk("NUMERO DE SERIE                 ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell3 = new Cell(new Chunk(":  " + verifie(info, "numero serie"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table8.addCell(cell2);
            table8.addCell(cell3);

            cell2 = new Cell(new Chunk("ANNE DE FABRICATION                 ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell3 = new Cell(new Chunk(":  " + verifie(info, "annee fabrication"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table8.addCell(cell2);
            table8.addCell(cell3);

            cell2 = new Cell(new Chunk("DIAMETRE NOMINAL             ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell3 = new Cell(
                    new Chunk(":  " + verifie(data, "diametre"),
                            FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table8.addCell(cell2);
            table8.addCell(cell3);
            table8.disableBorderSide(Rectangle.UNDEFINED);
            document.add(table8);

            document.add(new Paragraph("\n\n  2.) EXAMEN DE CONFORMITE  ",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));

            Table table9 = new Table(2, 2);
            table9.setSpacing(-1);

            cell2 = new Cell(
                    new Chunk("FORME            ",
                            FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell3 = new Cell(new Chunk(":  "
                    + verifie(info, "forme de la capacite") + " " + verifie(info, "orientation")
                    + " A FOND "
                    + verifie(info, "fond capacite"),
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table9.addCell(cell2);
            table9.addCell(cell3);

            cell2 = new Cell(
                    new Chunk("ETANCHEITE       ",
                            FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
            cell3 = new Cell(
                    new Chunk(": " + verifie(info, "etancheite"),
                            FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD,
                                    Color.BLACK)));
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.disableBorderSide(Rectangle.BOX);
            cell3.disableBorderSide(Rectangle.BOX);
            table9.addCell(cell2);
            table9.addCell(cell3);
            table9.disableBorderSide(Rectangle.UNDEFINED);
            document.add(table9);

            document.add(new Paragraph(
                    "\n\n \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  FAIT A "
                    + verifie(info, "lieu des operations") + ", LE .....",
                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD, Color.BLACK)));

            document.newPage();
            document.add(Chunk.NEWLINE);

            Image hits = null;

            if (typeMethode.equals("geometrie")) {
                if (data.size() >= 1) {
                    JPanel panel = new ZoneGeometrie(info, data);
                    panel.setBackground(Color.white);
                    panel.setSize(new Dimension(500, 500));

                    componentToImage(panel, "./fonction.png");
                    hits = Image.getInstance("./fonction.png");
                    hits.scaleAbsolute(500, 500);
                    hits.setAlignment(MIDDLE);
                    document.add(new Paragraph("\n\n"));
                    document.add(hits);
                    document.add(new Paragraph("\n\n"));

                    Table table10 = new Table(2, 2);
                    table10.setSpacing(-1);

                    document.add(new Paragraph(" VALEUR SAISIR DANS DONNEES GEOMETRIQUES  \n",
                            FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD,
                                    Color.BLACK)));
                    data.keySet().forEach(key -> {

                        try {
                            Cell cell4 = new Cell(new Chunk(key.toString(),
                                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,
                                            Color.BLACK)));

                            Cell cell5 = new Cell(new Chunk(data.get(key).toString(),
                                    FontFactory.getFont(FontFactory.TIMES_ROMAN, 12,
                                            Font.BOLD,
                                            Color.BLACK)));
                            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell4.disableBorderSide(Rectangle.BOX);
                            cell5.disableBorderSide(Rectangle.BOX);
                            table10.addCell(cell4);
                            table10.addCell(cell5);

                        } catch (BadElementException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    });

                    table10.disableBorderSide(Rectangle.UNDEFINED);
                    document.add(table10);
                }

            } else {
                if (abcisse.size() > 2) {
                    JPanel panel = new ZoneVolume(abcisse, ordonne,
                            info.get("unite des hauteurs").toString(),
                            info.get("unite de volume").toString());
                    panel.setBackground(Color.white);
                    panel.setSize(new Dimension(500, 500));

                    componentToImage(panel, "./fonction.png");
                    hits = Image.getInstance("./fonction.png");
                    hits.scaleAbsolute(500, 400);
                    hits.setAlignment(MIDDLE);
                    document.add(new Paragraph("\n\n"));
                    document.add(hits);
                    document.add(new Paragraph("\n\n"));

                    Table table11 = new Table(14, 14);
                    table11.setSpacing(-1);

                    document.add(new Paragraph(
                            " LISTE DES VALEURS DU TABLEAU REMPLIE DANS DONNEES VOLUMETRIQUES \n",
                            FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD,
                                    Color.BLACK)));

                    for (int index = 1; index < abcisse.size(); index++) {

                        cell2 = new Cell(new Chunk(abcisse.get(index) + "",
                                FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Color.BLACK)));
                        cell3 = new Cell(new Chunk(ordonne.get(index) + "",
                                FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD,
                                        Color.BLACK)));
                        cell = new Cell(new Chunk(" ",
                                FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD,
                                        Color.BLACK)));

                        cell2.setColspan(3);
                        cell3.setColspan(3);

                        cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell2.disableBorderSide(Rectangle.BOX);
                        cell3.disableBorderSide(Rectangle.BOX);
                        table11.addCell(cell2);
                        table11.addCell(cell3);
                        table11.addCell(cell);

                    }

                    document.add(table11);
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaremeArtisan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(BaremeArtisan.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.close();
        location.showMessageDialog(null, "Votre FICHER SE TROUVE DANS \n  " + filePath, "LOCALISATION DU FICHIER",
                JOptionPane.INFORMATION_MESSAGE);

    }

    public double convertVolumeUnitCourentTocm(String unite, double value) {
        switch (unite) {
            case "Gallon USA":
            case "US-Gallone":
            case "US Gallon":
                value = (float) (value * 3.78541);
                break;

            case "Gallon Imperial":
            case "Imperial Gallone":
            case "Imperial Gallon":
                value = (float) (value * 4.54609);
                break;

            case "baril":
            case "barrel":
                value = (float) (value * 159);
                break;

            case "m3":
                value = (float) (value * 1000);
                break;

            case "cm3":
                value = (float) (value / 1000);
                break;

        }
        return value;
    }

    public float convertVolumeUnitCourentTocm(String unite, float value) {
        switch (unite) {
            case "Gallon USA":
            case "US-Gallone":
            case "US Gallon":
                value = (float) (value * 3.78541);
                break;

            case "Gallon Imperial":
            case "Imperial Gallone":
            case "Imperial Gallon":
                value = (float) (value * 4.54609);
                break;

            case "baril":
            case "barrel":
                value = (float) (value * 159);
                break;

            case "m3":
                value = (float) (value * 1000);
                break;

            case "cm3":
                value = (float) (value / 1000);
                break;

        }
        return value;
    }

    public void componentToImage(JPanel panel, String fileName) {
        BufferedImage img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D g2d = img.createGraphics();
        panel.paint(g2d);
        try {
            ImageIO.write(img, "png", new File(fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void layoutComponent(Component c) {
        synchronized (c.getTreeLock()) {
            c.doLayout();
            if (c instanceof Container) {
                for (Component child : ((Container) c).getComponents()) {
                    layoutComponent(child);
                }
            }
        }

    }

    public boolean synonymeFormeCapaciteMultiLangueForCylindre(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Cylindrique") || formeCapacite.equalsIgnoreCase("Cylindrical")
                || formeCapacite.equalsIgnoreCase("Zylindrisch")) {
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

        if (formeCapacite.equalsIgnoreCase("Kubisch") || formeCapacite.equalsIgnoreCase("Parallelepiped")
                || formeCapacite.equalsIgnoreCase("Parallélépipède")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean synonymeFormeCapaciteMultiLangueForSphere(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Sphärisch") || formeCapacite.equalsIgnoreCase("Spherical")
                || formeCapacite.equalsIgnoreCase("Spherique")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean synonymeFormeCapaciteMultiLangueForEllpsoide(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Ellipsoide") || formeCapacite.equalsIgnoreCase("Elliptical")
                || formeCapacite.equalsIgnoreCase("Elliptisch") || formeCapacite.equalsIgnoreCase("Elliptique")
                || formeCapacite.equalsIgnoreCase("Forme Non Conventionnelle") || formeCapacite.equalsIgnoreCase("No Conventional Forms")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean synonymeForNatureFlechePlatOfCylinder(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Plat") || formeCapacite.equalsIgnoreCase("Flat")
                || formeCapacite.equalsIgnoreCase("Flach")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean synonymeForNatureFlecheConiqueOfCylinder(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Conique") || formeCapacite.equalsIgnoreCase("Conical")
                || formeCapacite.equalsIgnoreCase("Konisch")) {
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

    private boolean synonymeForOrientationOblique(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Oblique") || formeCapacite.equalsIgnoreCase("Schräg")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean synonymeFormeCapaciteMultiLangueForHemispherique(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("hemispherique") || formeCapacite.equalsIgnoreCase("hemiSpherical")
                || formeCapacite.equalsIgnoreCase("halbkugelformig")) {
            return true;
        } else {
            return false;
        }

    }

    public boolean synonymeFormeCapaciteMultiLangueForToriSpherique(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("torispherique") || formeCapacite.equalsIgnoreCase("torispheric")
                || formeCapacite.equalsIgnoreCase("torispharisch")) {
            return true;
        } else {
            return false;
        }

    }

}
