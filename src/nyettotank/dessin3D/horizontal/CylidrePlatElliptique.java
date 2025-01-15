package nyettotank.dessin3D.horizontal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class CylidrePlatElliptique extends JPanel {

    private float diam, longueur, flecheg, fleched;

    public CylidrePlatElliptique(float diam, float longueur) {
        this.diam = diam;
        this.longueur = longueur;

    }

    public void paintComponent(Graphics graphics) {
//Vous verrez cette phrase chaque fois que la méthode sera 

        Graphics2D area = (Graphics2D) graphics.create();

// tracer des deux droites horizontales
        area.drawLine(this.getWidth() / 6, this.getHeight() / 10, this.getWidth() - 100, this.getHeight() / 10);
        area.drawLine(this.getWidth() / 6, 300 + this.getHeight() / 10, this.getWidth() - 100, 300 + this.getHeight() / 10);

        area.setColor(Color.BLACK);
        int x1 = this.getWidth() / 6, y1 = this.getHeight() / 10, x2 = this.getWidth() - 100, y2 = this.getHeight() / 10 + 300;

        int z = (x1 + x2) / 2;
        z -= 15;
        int z2 = (y1 + y2) / 2;

        area.drawLine(z, y1, z, y1 - 10);
        area.drawLine(z + 30, y1, z + 30, y1 - 10);
        area.drawLine(z, y1 - 10, z + 30, y1 - 10);

        int angle = 0, cxi = x1, cyi = y1 + 150;

        area.drawArc(cxi - 10, y1, 20, 300, 0, 360);//modifier

        area.drawArc(this.getWidth() - 100, 150 + this.getHeight() / 10, 300, 140, 0, 90);//modifier

        area.drawArc(this.getWidth() - 110, this.getHeight() / 10, 20, 300, 0, 360);//modifier

        area.setColor(Color.BLUE);
//barre verticale au centre
        area.drawLine((this.getWidth() / 6 + this.getWidth() - 100) / 2, this.getHeight() / 10, (this.getWidth() / 6 + this.getWidth() - 100) / 2, 300 + this.getHeight() / 10);
// FLECHE POUR LE HAUT
        area.setColor(Color.GREEN);

        area.drawLine((this.getWidth() / 6 + this.getWidth() - 100) / 2, this.getHeight() / 10, (this.getWidth() / 6 + this.getWidth() - 100) / 2 + 5, this.getHeight() / 10 + 6);
        area.drawLine((this.getWidth() / 6 + this.getWidth() - 100) / 2, this.getHeight() / 10, (this.getWidth() / 6 + this.getWidth() - 100) / 2 - 5, this.getHeight() / 10 + 6);

// FLECHE POUR LE BAS
        area.drawLine((this.getWidth() / 6 + this.getWidth() - 100) / 2, 300 + this.getHeight() / 10, (this.getWidth() / 6 + this.getWidth() - 100) / 2 + 6, 300 + this.getHeight() / 10 - 6);
        area.drawLine((this.getWidth() / 6 + this.getWidth() - 100) / 2, 300 + this.getHeight() / 10, (this.getWidth() / 6 + this.getWidth() - 100) / 2 - 6, 300 + this.getHeight() / 10 - 6);

// bac du recipient
        area.drawLine(this.getWidth() / 6, 300 + this.getHeight() / 10 + 3, this.getWidth() / 6, 300 + this.getHeight() / 10 + 30);
        area.drawLine(this.getWidth() - 100, 300 + this.getHeight() / 10 + 3, this.getWidth() - 100, 300 + this.getHeight() / 10 + 30);
        area.drawLine(this.getWidth() / 6 + 10, 300 + this.getHeight() / 10 + 20, this.getWidth() - 100 - 10, 300 + this.getHeight() / 10 + 20);

        area.setColor(Color.BLUE);
        area.drawString(" " + diam, (this.getWidth() / 6 + this.getWidth() - 100) / 2 + 5, this.getHeight() / 10 + 50);
        area.drawString(" " + longueur, (this.getWidth() / 6 + 10 + this.getWidth() - 100 - 10) / 2 + 5, 300 + this.getHeight() / 10 + 35);

//nouvelle ligne
        float[] dash1 = {2f, 0f, 2f};
        BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);

        area.setStroke(bs2);
        area.drawLine(this.getWidth() / 6, this.getHeight() / 10, this.getWidth() / 6, this.getHeight() / 10 + 300);
        area.drawLine(this.getWidth() - 100, this.getHeight() / 10, this.getWidth() - 100, this.getHeight() / 10 + 300);

    }

}
