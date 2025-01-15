package nyettotank2.dessin3D.vertical;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author LYZARA SOLUTION
 */
public class CylinderConiqueToPlat extends JPanel {

    private float diam, longueur, flecheg, fleched;

    public CylinderConiqueToPlat(float diam, float longueur, float fleched, float flecheg) {
        this.diam = diam;
        this.longueur = longueur;
        this.fleched = fleched;
        this.flecheg = flecheg;

    }

    public void paintComponent(Graphics graphics) {
//Vous verrez cette phrase chaque fois que la méthode sera 
        Graphics2D area = (Graphics2D) graphics.create();

// tracer des deux droites horizontales
        area.drawLine(this.getWidth() / 2 - 150, 60 + this.getHeight() / 20, this.getWidth() / 2 - 150, 300 + this.getHeight() / 20);
        area.drawLine(this.getWidth() / 2 + 150, 60 + this.getHeight() / 20, this.getWidth() / 2 + 150, 300 + this.getHeight() / 20);

        area.drawLine(this.getWidth() / 2 - 150, 60 + this.getHeight() / 20, this.getWidth() / 2, this.getHeight() / 20);
        area.drawLine(this.getWidth() / 2 + 150, 60 + this.getHeight() / 20, this.getWidth() / 2, this.getHeight() / 20);

        area.setColor(Color.BLACK);

//area.fillArc(this.getWidth()/2 - 200, this.getHeight()/20 -10, 400, 20, 0, 360);//modifier
        area.drawArc(this.getWidth() / 2 - 150, 50 + this.getHeight() / 20, 300, 20, 0, 360);//modifier
        area.drawArc(this.getWidth() / 2 - 150, 290 + this.getHeight() / 20, 300, 20, 0, 360);//modifier

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
        area.drawLine(this.getWidth() / 2 - 150, 60 + this.getHeight() / 20, this.getWidth() / 2 + 150, 60 + this.getHeight() / 20);
        area.drawLine(this.getWidth() / 2 - 150, 300 + this.getHeight() / 20, this.getWidth() / 2 + 150, this.getHeight() / 20 + 300);

        area.setColor(Color.RED);
        area.drawLine(this.getWidth() / 2 - 145, 175 + this.getHeight() / 20, this.getWidth() / 2 + 145, 175 + this.getHeight() / 20);
        area.drawString(" " + diam, this.getWidth() / 2, 195 + this.getHeight() / 20);
        area.drawString(" " + flecheg, this.getWidth() / 2 + 5, 30 + this.getHeight() / 20);
        area.drawString(" " + fleched, this.getWidth() / 2 + 5, 330 + this.getHeight() / 20);

        float[] dash2 = {4f, 2f, 3f};
        BasicStroke bs3 = new BasicStroke(2, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash2, 2f);

        area.setColor(Color.BLACK);
        area.setStroke(bs3);
        area.drawLine(this.getWidth() / 2, this.getHeight() / 20, this.getWidth() / 2, 60 + this.getHeight() / 20);

    }

}
