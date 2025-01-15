package nyettotank2.dessin3D.vertical;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class CylinderVerticale extends JPanel {

    private float diam, longueur, flecheg, fleched;

    private String extremiteFlecheGauche = "";
    private String extremiteFlecheDroite = "";

    public CylinderVerticale(float diam, float longueur, float flecheg, float fleched) {
        this.diam = diam;
        this.longueur = longueur;
        this.fleched = fleched;
        this.flecheg = flecheg;

    }

    public CylinderVerticale(float diam, float longueur, float flecheg, float fleched, String extremiteFeleche) {
        this.diam = diam;
        this.longueur = longueur;
        this.fleched = fleched;
        this.flecheg = flecheg;
        this.extremiteFlecheGauche = extremiteFeleche;
        this.extremiteFlecheDroite = extremiteFeleche;

    }

    public CylinderVerticale(float diam, float longueur, float flecheg, float fleched, String extremiteFelecheG, String extremiteFelecheD) {
        this.diam = diam;
        this.longueur = longueur;
        this.fleched = fleched;
        this.flecheg = flecheg;
        this.extremiteFlecheGauche = extremiteFelecheG;
        this.extremiteFlecheDroite = extremiteFelecheD;
    }

    public void paintComponent(Graphics graphics) {
        float lengthDraw = traceLineOrArcOnHorizontal(longueur, "longueur");
        float diametreDraw = traceLineOrArcOnHorizontal(diam, "diametre");
        float flecheDroitDraw = traceLineOrArcOnHorizontal(fleched, "");
        float flecheGaucheDraw = traceLineOrArcOnHorizontal(flecheg, "");

        Graphics2D area = (Graphics2D) graphics.create();

        int x1 = this.getWidth() / 2, y1 = this.getHeight() / 2, cxi = 0, cxj = 0;

        float[] dash21 = {2f, 2f, 2f};
        BasicStroke bs31 = new BasicStroke(3.5f, BasicStroke.JOIN_ROUND,
                BasicStroke.JOIN_ROUND, 1.0f, dash21, 2f);

        area.setColor(Color.BLACK);
        area.setStroke(bs31);

// tracer des deux droites verticales
        area.drawLine((int) ((this.getWidth() - diametreDraw) / 2), (int) ((this.getHeight() - lengthDraw) / 2), (int) ((this.getWidth() - diametreDraw) / 2), (int) ((this.getHeight() + lengthDraw) / 2));
        area.drawLine((int) ((this.getWidth() + diametreDraw) / 2), (int) ((this.getHeight() - lengthDraw) / 2), (int) ((this.getWidth() + diametreDraw) / 2), (int) ((lengthDraw + this.getHeight()) / 2));

//barre horizontale au centre
        area.drawLine((int) ((this.getWidth() - diametreDraw) / 2) + 3, y1, (int) ((this.getWidth() + diametreDraw) / 2) - 3, y1);

// FLECHE POUR LE HAUT
        area.drawLine((int) (x1 + 0.5 * diametreDraw + 20), (int) (y1 - 0.5 * lengthDraw), (int) (x1 + 0.5 * diametreDraw + 20) + 5, (int) (y1 - 0.5 * lengthDraw) + 6);
        area.drawLine((int) (x1 + 0.5 * diametreDraw + 20), (int) (y1 - 0.5 * lengthDraw), (int) (x1 + 0.5 * diametreDraw + 20) - 5, (int) (y1 - 0.5 * lengthDraw) + 6);

// FLECHE POUR LE BAS
        area.drawLine((int) (x1 + 0.5 * diametreDraw + 20), (int) (y1 + lengthDraw * 0.5), (int) (x1 + 0.5 * diametreDraw + 20) + 6, (int) (y1 + lengthDraw * 0.5) - 6);
        area.drawLine((int) (x1 + 0.5 * diametreDraw + 20), (int) (y1 + lengthDraw * 0.5), (int) (x1 + 0.5 * diametreDraw + 20) - 6, (int) (y1 + lengthDraw * 0.5) - 6);

        area.drawLine((int) (x1 + 0.5 * diametreDraw + 20), (int) (y1 + lengthDraw * 0.5), (int) (x1 + 0.5 * diametreDraw + 20), (int) (y1 - lengthDraw * 0.5));

        area.drawString(" " + longueur, (int) (x1 + 0.5 * diametreDraw + 30), y1);
        area.drawString(" " + diam, x1, y1 + 15);

//couronne
        area.drawArc((int) (x1 - diametreDraw * 0.5), (int) (y1 - 0.5 * lengthDraw) - 5, (int) diametreDraw, 10, 0, 360);
        area.drawArc((int) (x1 - diametreDraw * 0.5), (int) (y1 + 0.5 * lengthDraw) - 5, (int) diametreDraw, 10, 0, 360);

//dessin fleche
        if (extremiteFlecheDroite.isEmpty()) {

            if (fleched != 0) {
                //  area.setColor(Color.BLUE);

                area.drawLine(x1, (int) ((this.getHeight() + lengthDraw) / 2), x1, (int) (((this.getHeight() + lengthDraw) / 2) + flecheDroitDraw));

                area.drawString(" " + fleched, x1 + 4, (int) (((this.getHeight() + lengthDraw) / 2) + flecheDroitDraw * 0.5));
                //area.drawArc((int) (x1 - diametreDraw*0.5), (int) ( (y1 + 0.5*lengthDraw) - flecheDroitDraw), (int) diametreDraw, (int) ( 2*flecheDroitDraw), 0, 180);
                area.drawArc((int) (x1 - diametreDraw * 0.5), (int) ((y1 + 0.5 * lengthDraw) - flecheDroitDraw), (int) diametreDraw, (int) (2 * flecheDroitDraw), -180, 180);
            }

        } else {
            if (fleched != 0) {
                area.drawLine(x1, (int) ((this.getHeight() + lengthDraw) / 2), x1, (int) (((this.getHeight() + lengthDraw) / 2) + flecheDroitDraw));
                area.drawString(" " + fleched, x1 + 4, (int) (((this.getHeight() + lengthDraw) / 2) + flecheDroitDraw * 0.5));

                area.drawLine((int) ((this.getWidth() - diametreDraw) / 2), (int) ((this.getHeight() + lengthDraw) / 2), x1, (int) (((this.getHeight() + lengthDraw) / 2) + flecheDroitDraw));
                area.drawLine((int) ((this.getWidth() + diametreDraw) / 2), (int) ((this.getHeight() + lengthDraw) / 2), x1, (int) (((this.getHeight() + lengthDraw) / 2) + flecheDroitDraw));
            }
        }

        if (extremiteFlecheGauche.isEmpty()) {

            if (flecheg != 0) {
                // area.setColor(Color.BLUE);
                area.drawLine(x1, (int) ((this.getHeight() - lengthDraw) / 2), x1, (int) (((this.getHeight() - lengthDraw) / 2) - flecheGaucheDraw));
                area.drawString("" + flecheg, x1 + 4, (int) (((this.getHeight() - lengthDraw) / 2) - 0.5 * flecheGaucheDraw));
                area.drawArc((int) (x1 - diametreDraw * 0.5), (int) ((y1 - 0.5 * lengthDraw) - flecheGaucheDraw), (int) diametreDraw, (int) (2 * flecheGaucheDraw), 0, 180);

            }

        } else {

            if (flecheg != 0) {
                // area.setColor(Color.BLUE);
                area.drawLine(x1, (int) ((this.getHeight() - lengthDraw) / 2), x1, (int) (((this.getHeight() - lengthDraw) / 2) - flecheGaucheDraw));
                area.drawString("" + flecheg, x1 + 4, (int) (((this.getHeight() - lengthDraw) / 2) - 0.5 * flecheGaucheDraw));

                area.drawLine((int) ((this.getWidth() - diametreDraw) / 2), (int) ((this.getHeight() - lengthDraw) / 2), x1, (int) (((this.getHeight() - lengthDraw) / 2) - flecheGaucheDraw));
                area.drawLine((int) ((this.getWidth() + diametreDraw) / 2), (int) ((this.getHeight() - lengthDraw) / 2), x1, (int) (((this.getHeight() - lengthDraw) / 2) - flecheGaucheDraw));
            }
        }

//nouvelle ligne
//float[] dash1 = { 2f, 0f, 2f };
//BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,
//                BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
//
//area.setStroke(bs2);
//
//float[] dash2 = { 4f, 2f, 3f };
//BasicStroke bs3 = new BasicStroke(2, BasicStroke.CAP_BUTT,
//                BasicStroke.JOIN_ROUND, 1.0f, dash2, 2f);
//
//area.setColor(Color.BLACK); 
//area.setStroke(bs3);
        area.drawLine((int) ((this.getWidth() - diametreDraw) / 2), (int) ((this.getHeight() - lengthDraw) / 2), (int) ((this.getWidth() + diametreDraw) / 2), (int) ((this.getHeight() - lengthDraw) / 2));
        area.drawLine((int) ((this.getWidth() - diametreDraw) / 2), (int) ((this.getHeight() + lengthDraw) / 2), (int) ((this.getWidth() + diametreDraw) / 2), (int) ((this.getHeight() + lengthDraw) / 2));

    }

    private float traceLineOrArcOnHorizontal(float value, String nomGradeur) {

        float result = 0;
        if (nomGradeur.equals("longueur")) {

            result = 320 * value / 3000;
            if (80 >= result) {
                return 80;
            } else if (result >= 320) {
                return 320;
            } else {
                return (float) Math.floor(result);
            }
        } else if (nomGradeur.equals("diametre")) {

            result = 200 * value / 400;
            if (50 >= result) {
                return 50;
            } else if (result >= 200) {
                return 200;
            } else {
                return (float) Math.floor(result);
            }

        } else { //pour la fleche
            if (value == 0) {
                return 0;
            }
            result = value * 80 / 200;
            if (25 >= result) {
                return 25;
            } else if (result >= 80) {
                return 80;
            } else {
                return (float) Math.floor(result);
            }
        }
        //return 0;
    }

}
