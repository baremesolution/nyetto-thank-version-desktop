package nyettotank2.dessin3D.incline;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class CylindreHorizontaleIncline extends JPanel {

    private float diam, longueur, flecheg, fleched, angle;
    private String extremiteFlecheGauche = "";
    private String extremiteFlecheDroite = "";

    public CylindreHorizontaleIncline(float angle, float diam, float longueur, float flecheg, float fleched) {
        this.diam = diam;
        this.longueur = longueur;
        this.fleched = fleched;
        this.flecheg = flecheg;
        this.angle = angle;

    }

    public CylindreHorizontaleIncline(float angle, float diam, float longueur, float flecheg, float fleched, String extremiteFeleche) {
        this.diam = diam;
        this.longueur = longueur;
        this.fleched = fleched;
        this.flecheg = flecheg;
        this.extremiteFlecheGauche = extremiteFeleche;
        this.extremiteFlecheDroite = extremiteFeleche;
        this.angle = angle;

    }

    public CylindreHorizontaleIncline(float angle, float diam, float longueur, float flecheg, float fleched, String extremiteFelecheG, String extremiteFelecheD) {
        this.diam = diam;
        this.longueur = longueur;
        this.fleched = fleched;
        this.flecheg = flecheg;
        this.extremiteFlecheGauche = extremiteFelecheG;
        this.extremiteFlecheDroite = extremiteFelecheD;
        this.angle = angle;

    }

    public void paintComponent(Graphics area) {
        //Vous verrez cette phrase chaque fois que la méthode sera
        float lengthDraw = traceLineOrArcOnHorizontal(longueur, "longueur");
        float diametreDraw = traceLineOrArcOnHorizontal(diam, "diametre");
        float flecheDroitDraw = traceLineOrArcOnHorizontal(fleched, "");
        float flecheGaucheDraw = traceLineOrArcOnHorizontal(flecheg, "");

        Graphics2D g = (Graphics2D) area;

        float[] dash21 = {2f, 2f, 2f};
        BasicStroke bs31 = new BasicStroke(3.5f, BasicStroke.JOIN_ROUND,
                BasicStroke.JOIN_ROUND, 1.0f, dash21, 2f);

        g.setColor(Color.BLACK);
        g.setStroke(bs31);

        g.rotate(-Math.PI * this.angle / 180);

        // tracer des deux droites horizontales
        g.drawLine((int) ((this.getWidth() - lengthDraw) / 2), (int) ((this.getHeight() - diametreDraw) / 2), (int) ((this.getWidth() + lengthDraw) / 2), (int) ((this.getHeight() - diametreDraw) / 2));
        g.drawLine((int) ((this.getWidth() - lengthDraw) / 2), (int) ((diametreDraw + this.getHeight()) / 2), (int) ((this.getWidth() + lengthDraw) / 2), (int) ((diametreDraw + this.getHeight()) / 2));

        //g.setColor(Color.BLACK); 
        //   g.drawArc(this.getWidth()/6-50+17, this.getHeight()/10, 70, 100, 90, 180);
        int x1 = this.getWidth() / 2, y1 = this.getHeight() / 2, x2 = this.getWidth() - 100, y2 = this.getHeight() / 10 + 300;

        g.drawLine(x1 + 8, (int) ((this.getHeight() - diametreDraw) / 2) - 10, x1 + 8, (int) ((this.getHeight() - diametreDraw) / 2));
        g.drawLine(x1 - 8, (int) ((this.getHeight() - diametreDraw) / 2) - 10, x1 - 8, (int) ((this.getHeight() - diametreDraw) / 2));
        g.drawLine(x1 - 8, (int) ((this.getHeight() - diametreDraw) / 2) - 10, x1 + 8, (int) ((this.getHeight() - diametreDraw) / 2) - 10);

        float angle = 90, cxi = (this.getWidth() + lengthDraw) / 2, cyi = y1, cxj = (this.getWidth() - lengthDraw) / 2;

        g.setColor(Color.BLACK);
        //barre verticale au centre
        g.drawLine(x1, (int) (y1 + diametreDraw * 0.5), x1, (int) (y1 - 0.5 * diametreDraw));
        // FLECHE POUR LE HAUT
        g.setColor(Color.GREEN);

        g.drawLine(x1, (int) (y1 - 0.5 * diametreDraw), x1 + 5, (int) (y1 - 0.5 * diametreDraw) + 6);
        g.drawLine(x1, (int) (y1 - 0.5 * diametreDraw), x1 - 5, (int) (y1 - 0.5 * diametreDraw) + 6);

        // FLECHE POUR LE BAS
        g.drawLine(x1, (int) (y1 + diametreDraw * 0.5), x1 + 6, (int) (y1 + diametreDraw * 0.5) - 6);
        g.drawLine(x1, (int) (y1 + diametreDraw * 0.5), x1 - 6, (int) (y1 + diametreDraw * 0.5) - 6);

        // bac du recipient
        g.setColor(Color.red);
        g.drawLine((int) ((this.getWidth() - lengthDraw) / 2), (int) ((diametreDraw + this.getHeight()) / 2) + 25, (int) ((this.getWidth() + lengthDraw) / 2), (int) ((diametreDraw + this.getHeight()) / 2) + 25);

        g.setColor(Color.BLUE);

        g.drawString(" " + diam, x1 + 10, y1);
        g.drawString(" " + longueur, x1 - 20, (int) ((diametreDraw + this.getHeight()) / 2) + 21);

        //dessin fleche
        if (extremiteFlecheDroite.isEmpty()) {

            if (fleched != 0) {
                g.drawLine((int) ((this.getWidth() + lengthDraw) / 2), y1, (int) (((this.getWidth() + lengthDraw) / 2) + flecheDroitDraw), y1);
                g.drawString(" " + fleched, (int) ((this.getWidth() + lengthDraw) / 2) + 10, y1 + 15);
                g.drawArc((int) (cxi - flecheDroitDraw), (int) (this.getHeight() * 0.5 - diametreDraw * 0.5), (int) (2 * flecheDroitDraw), (int) diametreDraw, -90, 180);
            }

        } else {
            if (fleched != 0) {

                g.drawLine((int) ((this.getWidth() - lengthDraw) / 2), y1, (int) (((this.getWidth() - lengthDraw) / 2) - flecheGaucheDraw), y1);
                g.drawString("" + flecheg, (int) ((this.getWidth() - lengthDraw) / 2) - 35, y1 + 15);

                g.drawLine((int) ((this.getWidth() + lengthDraw) / 2), (int) ((this.getHeight() - diametreDraw) / 2), (int) (((this.getWidth() + lengthDraw) / 2) + flecheGaucheDraw), y1);
                g.drawLine((int) ((this.getWidth() + lengthDraw) / 2), (int) ((this.getHeight() + diametreDraw) / 2), (int) (((this.getWidth() + lengthDraw) / 2) + flecheGaucheDraw), y1);

            }
        }

        g.drawLine((int) ((this.getWidth() - lengthDraw) / 2), (int) ((this.getHeight() - diametreDraw) / 2), (int) ((this.getWidth() + lengthDraw) / 2), (int) ((this.getHeight() - diametreDraw) / 2));
        g.drawLine((int) ((this.getWidth() - lengthDraw) / 2), (int) ((diametreDraw + this.getHeight()) / 2), (int) ((this.getWidth() + lengthDraw) / 2), (int) ((diametreDraw + this.getHeight()) / 2));

        g.drawLine((int) ((this.getWidth() - lengthDraw) / 2), (int) ((this.getHeight() - diametreDraw) / 2), (int) ((this.getWidth() + lengthDraw) / 2), (int) ((this.getHeight() - diametreDraw) / 2));
        g.drawLine((int) ((this.getWidth() - lengthDraw) / 2), (int) ((diametreDraw + this.getHeight()) / 2), (int) ((this.getWidth() + lengthDraw) / 2), (int) ((diametreDraw + this.getHeight()) / 2));

        g.drawArc((int) ((this.getWidth() - lengthDraw) / 2) - 5, (int) (y1 - 0.5 * diametreDraw), 10, (int) diametreDraw, 0, 360);
        g.drawArc((int) ((this.getWidth() + lengthDraw) / 2) - 5, (int) (y1 - 0.5 * diametreDraw), 10, (int) diametreDraw, 0, 360);

        if (extremiteFlecheGauche.isEmpty()) {

            if (flecheg != 0) {

                g.drawLine((int) ((this.getWidth() - lengthDraw) / 2), y1, (int) (((this.getWidth() - lengthDraw) / 2) - flecheGaucheDraw), y1);
                g.drawString("" + flecheg, (int) ((this.getWidth() - lengthDraw) / 2) - 35, y1 + 15);
                g.drawArc((int) (cxj - flecheGaucheDraw), (int) ((this.getHeight() - diametreDraw) / 2), (int) (2 * flecheGaucheDraw), (int) diametreDraw, 90, 180);

            }

        } else {

            if (flecheg != 0) {

                g.drawLine((int) ((this.getWidth() + lengthDraw) / 2), y1, (int) (((this.getWidth() + lengthDraw) / 2) + flecheDroitDraw), y1);
                g.drawString(" " + fleched, (int) ((this.getWidth() + lengthDraw) / 2) + 10, y1 + 15);

                g.drawLine((int) ((this.getWidth() - lengthDraw) / 2), (int) ((this.getHeight() - diametreDraw) / 2), (int) (((this.getWidth() - lengthDraw) / 2) - flecheDroitDraw), y1);
                g.drawLine((int) ((this.getWidth() - lengthDraw) / 2), (int) ((this.getHeight() + diametreDraw) / 2), (int) (((this.getWidth() - lengthDraw) / 2) - flecheDroitDraw), y1);

            }
        }

        //float[] dash1 = { 2f, 0f, 2f };
        //BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,
        //                BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
        //
        //area.setStroke(bs2);
        g.drawLine((int) ((this.getWidth() - lengthDraw) / 2), (int) ((this.getHeight() - diametreDraw) / 2), (int) ((this.getWidth() - lengthDraw) / 2), (int) ((this.getHeight() + diametreDraw) / 2));
        g.drawLine((int) ((this.getWidth() + lengthDraw) / 2), (int) ((this.getHeight() - diametreDraw) / 2), (int) ((this.getWidth() + lengthDraw) / 2), (int) ((this.getHeight() + diametreDraw) / 2));

    }

    private float traceLineOrArcOnHorizontal(float value, String nomGradeur) {

        float result = 0;
        if (nomGradeur.equals("longueur")) {

            result = 400 * value / 3000;
            if (80 >= result) {
                return 80;
            } else if (result >= 400) {
                return 400;
            } else {
                return (float) Math.floor(result);
            }
        } else if (nomGradeur.equals("diametre")) {

            result = 420 * value / 450;
            if (50 >= result) {
                return 50;
            } else if (result >= 420) {
                return 420;
            } else {
                return (float) Math.floor(result);
            }

        } else { //pour la fleche
            if (value == 0) {
                return 0;
            }
            result = value * 90 / 200;
            if (25 >= result) {
                return 25;
            } else if (result >= 90) {
                return 90;
            } else {
                return (float) Math.floor(result);
            }
        }
        //return 0;
    }

}
