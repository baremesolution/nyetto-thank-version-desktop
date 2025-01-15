package nyettotank2.dessin3D.vertical;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;

/**
 *
 * @author LYZARA SOLUTION
 */
public class CylinderSphericSpheric extends JPanel {

    private float diam, longueur;

    public CylinderSphericSpheric(float diam, float longueur) {
        this.diam = diam;
        this.longueur = longueur;

    }

    public void paintComponent(Graphics graphics) {
//Vous verrez cette phrase chaque fois que la méthode sera 

        Graphics2D area = (Graphics2D) graphics.create();

// tracer des deux droites horizontales
        area.drawLine(this.getWidth() / 2 - 60, 60 + this.getHeight() / 20, this.getWidth() / 2 - 60, 300 + this.getHeight() / 20);
        area.drawLine(this.getWidth() / 2 + 60, 60 + this.getHeight() / 20, this.getWidth() / 2 + 60, 300 + this.getHeight() / 20);

        area.setColor(Color.BLACK);
        int x1 = this.getWidth() / 6, y1 = this.getHeight() / 10, x2 = this.getWidth() - 100, y2 = this.getHeight() / 10 + 300;

//area.fillArc(this.getWidth()/2 - 200, this.getHeight()/20 -10, 400, 20, 0, 360);//modifier
        area.drawArc(this.getWidth() / 2 - 60, this.getHeight() / 20, 120, 120, 0, 180);//modifier
        area.drawArc(this.getWidth() / 2 - 60, 50 + this.getHeight() / 20, 120, 20, 0, 360);//modifier

        float angle = 180, cxi = this.getWidth() / 2, cyi = 300 + this.getHeight() / 20;

        GeneralPath courbe = new GeneralPath();
        courbe.moveTo(cxi - 60, cyi);
        while (angle <= 360) {
            angle += 0.5;
            float x11 = (float) (cxi - 60 * Math.cos(Math.PI * angle / 180));
            float y11 = (float) (cyi - 60 * Math.sin(Math.PI * angle / 180));
            // courbe.lineTo(50 + val, 450 - fonction.interpolate(val) );
            courbe.lineTo(x11, y11);

        }

        area.draw(courbe);
        area.drawArc(this.getWidth() / 2 - 60, 290 + this.getHeight() / 20, 120, 20, 0, 360);//modifier

        area.setColor(Color.BLUE);
//barre verticale au centre
        area.drawLine(this.getWidth() / 2, 60 + this.getHeight() / 20, this.getWidth() / 2, 300 + this.getHeight() / 20);
// FLECHE POUR LE HAUT
        area.setColor(Color.GREEN);

        area.drawLine(this.getWidth() / 2, 60 + this.getHeight() / 20, this.getWidth() / 2 + 5, this.getHeight() / 20 + 66);
        area.drawLine(this.getWidth() / 2, 60 + this.getHeight() / 20, this.getWidth() / 2 - 5, this.getHeight() / 20 + 66);

// FLECHE POUR LE BAS
        area.drawLine(this.getWidth() / 2, 300 + this.getHeight() / 20, this.getWidth() / 2 + 6, 300 + this.getHeight() / 20 - 6);
        area.drawLine(this.getWidth() / 2, 300 + this.getHeight() / 20, this.getWidth() / 2 - 6, 300 + this.getHeight() / 20 - 6);

        area.setColor(Color.BLUE);
        area.drawString(" " + longueur, this.getWidth() / 2 + 10, this.getHeight() / 20 + 170);

//nouvelle ligne
        float[] dash1 = {2f, 0f, 2f};
        BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);

        area.setStroke(bs2);
        area.drawLine(this.getWidth() / 2 - 60, 60 + this.getHeight() / 20, this.getWidth() / 2 + 60, 60 + this.getHeight() / 20);
        area.drawLine(this.getWidth() / 2 - 60, 300 + this.getHeight() / 20, this.getWidth() / 2 + 60, this.getHeight() / 20 + 300);

        area.setColor(Color.RED);
        area.drawLine(this.getWidth() / 2 - 55, 185 + this.getHeight() / 20, this.getWidth() / 2 + 55, 185 + this.getHeight() / 20);
        area.drawString(" " + diam, this.getWidth() / 2, 195 + this.getHeight() / 20);
        area.drawString(" " + diam / 2, this.getWidth() / 2 + 5, 30 + this.getHeight() / 20);
        area.drawString(" " + diam / 2, this.getWidth() / 2 + 5, 330 + this.getHeight() / 20);

        float[] dash2 = {4f, 2f, 3f};
        BasicStroke bs3 = new BasicStroke(2, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash2, 2f);

        area.setColor(Color.BLACK);
        area.setStroke(bs3);
        area.drawLine(this.getWidth() / 2, this.getHeight() / 20, this.getWidth() / 2, 60 + this.getHeight() / 20);
        area.drawLine(this.getWidth() / 2, 360 + this.getHeight() / 20, this.getWidth() / 2, this.getHeight() / 20 + 300);

    }

}
