package nyettotank2.courbe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;
import nyettotank2.metier.BaremeArtisan;

public class ZoneGeometrie extends JPanel {

    private List<Float> x, y;
    private String uniteVolume = "", uniteHeight = "";
    private BaremeArtisan bareme = new BaremeArtisan();
    private HashMap data = new HashMap();
    private HashMap info = new HashMap();

    public ZoneGeometrie(HashMap info, HashMap data) {
        this.data = data;
        this.info = info;
        this.uniteVolume = info.get("unite de volume").toString();
        this.uniteHeight = info.get("unite des hauteurs").toString();
    }

    public void paintComponent(Graphics g) {

        g.setColor(Color.BLUE);
        g.drawLine(40, 40, 50, 30);
        g.drawLine(60, 40, 50, 30);
        g.drawLine(470, 450, 460, 440);
        g.drawLine(470, 450, 460, 460);

        g.drawLine(470, 450, 50, 450);
        g.drawLine(50, 450, 50, 30);
        g.drawString("HAUTEUR(" + uniteHeight.toUpperCase() + ")", 475, 450);
        g.drawString("VOLUME(" + uniteVolume.toUpperCase() + ")", 60, 30);

        String formeCapacite = info.get("forme de la capacite").toString().toUpperCase();
        float diametreUnite = 0;

        if (synonymeFormeCapaciteMultiLangueForCylindre(info.get("forme de la capacite").toString().toLowerCase())) {
            diametreUnite = Float.parseFloat(data.get("diametre").toString());

        } else if (synonymeFormeCapaciteMultiLangueForSphere(info.get("forme de la capacite").toString().toLowerCase())) {
            float rayonSphere = Float.parseFloat(data.get("rayon sphere").toString());
            diametreUnite = 2 * rayonSphere;

        } else if (synonymeFormeCapaciteMultiLangueForCube(info.get("forme de la capacite").toString().toLowerCase())) {
            diametreUnite = Float.parseFloat(data.get("hauteur").toString());
        } else if (synonymeFormeCapaciteMultiLangueForEllpsoide(info.get("forme de la capacite").toString().toLowerCase())) {
            diametreUnite = Float.parseFloat(data.get("hauteur").toString());

        }

        if (synonymeForOrientationVerticale(info.get("orientation").toString()) && synonymeFormeCapaciteMultiLangueForCylindre(formeCapacite)) {
            diametreUnite = Float.parseFloat(data.get("longueur").toString());
            if (data.containsKey("fleche cote gauche")) {

                diametreUnite += Float.parseFloat(data.get("fleche cote gauche").toString());

            }
            if (data.containsKey("fleche cote droit")) {

                diametreUnite += Float.parseFloat(data.get("fleche cote droit").toString());

            }

        }
        
        
        if( synonymeForOrientationOblique( info.get("orientation").toString() ) && synonymeFormeCapaciteMultiLangueForCylindre(formeCapacite) ){
            
                float angleInclinaison = Float.parseFloat(data.get("angle inclinaison").toString());
                float sinusAngle = (float) Math.sin(Math.PI*angleInclinaison/180);
                float cosinusAngle = (float) Math.cos(Math.PI*angleInclinaison/180);
                diametreUnite = Float.parseFloat(data.get("longueur").toString())*sinusAngle + diametreUnite*cosinusAngle;

            
            }

        diametreUnite = bareme.convertToCentimeter(uniteHeight, diametreUnite);

        double volFull = (float) 0.05, volCalcule = 0;
        volFull = bareme.fonctionCalculVolume(data, info, diametreUnite);
        volFull = bareme.convertToVolumeDesired(uniteVolume, volFull / 1000);
        float increment = (float) 0.00;

        g.setColor(Color.GRAY);
        float pas = diametreUnite / 10;
        float initial = pas;
        while (initial <= diametreUnite) {
            int x1 = (int) (initial * 400 / diametreUnite);

            g.drawString("" + initial, (int) (50 + initial * 400 / diametreUnite), 460);
            g.drawLine(x1 + 50, 450, 50 + x1, 50);
            initial += pas;

        }

        pas = (float) (volFull / 10);
        initial = pas;
        while (initial <= volFull) {
            int y1 = (int) ((int) 450 - 400 * initial / volFull);

            g.drawString("" + initial, 20, (int) (450 - 400 * initial / volFull));
            g.drawLine(50, y1, 450, y1);
            initial += pas;
        }

        g.setColor(Color.BLACK);
        Graphics2D area = (Graphics2D) g;
        GeneralPath courbe = new GeneralPath();
        courbe.moveTo(50, 450);

        while (increment <= diametreUnite) {

            increment += 0.005;
            volCalcule = bareme.fonctionCalculVolume(data, info, increment);

            float t = bareme.convertToUnitDesire(uniteHeight, increment);
            volCalcule = bareme.convertToVolumeDesired(uniteVolume, volCalcule / 1000);

            courbe.lineTo(50 + t * 400 / diametreUnite, 450 - 400 * volCalcule / volFull);

        }

        area.draw(courbe);

    }

    public boolean synonymeFormeCapaciteMultiLangueForCylindre(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Cylindrique") || formeCapacite.equalsIgnoreCase("Cylindrical") || formeCapacite.equalsIgnoreCase("Zylindrisch")) {
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

    public boolean synonymeForNatureFlecheConiqueOfCylinder(String formeCapacite) {

        if (formeCapacite.equalsIgnoreCase("Conique") || formeCapacite.equalsIgnoreCase("Conical") || formeCapacite.equalsIgnoreCase("Konisch")) {
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
