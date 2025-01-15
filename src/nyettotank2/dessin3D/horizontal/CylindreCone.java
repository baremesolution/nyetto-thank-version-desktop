package nyettotank2.dessin3D.horizontal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author AD-IPE
 */
public class CylindreCone extends JPanel {

    private float diam, longueur, flecheg, fleched;

    public CylindreCone(float diam, float longueur, float flecheg, float fleched) {
        this.diam = diam;
        this.longueur = longueur;
        this.fleched = fleched;
        this.flecheg = flecheg;

    }

    public void paintComponent(Graphics graphics) {
//Vous verrez cette phrase chaque fois que la méthode sera
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.red);
// tracer des deux droites horizontales
        g.drawLine(this.getWidth() / 6, this.getHeight() / 10, this.getWidth() - 100, this.getHeight() / 10);
        g.drawLine(this.getWidth() / 6, 300 + this.getHeight() / 10, this.getWidth() - 100, 300 + this.getHeight() / 10);

        g.drawLine(this.getWidth() / 6, this.getHeight() / 10, this.getWidth() / 6 - 40, 150 + this.getHeight() / 10);
        g.drawLine(this.getWidth() / 6, 300 + this.getHeight() / 10, this.getWidth() / 6 - 40, 150 + this.getHeight() / 10);

        g.drawLine(this.getWidth() - 60, this.getHeight() / 10 + 150, this.getWidth() - 100, this.getHeight() / 10);
        g.drawLine(this.getWidth() - 60, 150 + this.getHeight() / 10, this.getWidth() - 100, 300 + this.getHeight() / 10);

//   g.drawArc(this.getWidth()/6-50+17, this.getHeight()/10, 70, 100, 90, 180);
        int x1 = this.getWidth() / 6, y1 = this.getHeight() / 10, x2 = this.getWidth() - 100, y2 = this.getHeight() / 10 + 300;
        int z = (x1 + x2) / 2;
        z -= 15;
        int z2 = (y1 + y2) / 2;

        g.drawLine(z, y1, z, y1 - 10);
        g.drawLine(z + 30, y1, z + 30, y1 - 10);
        g.drawLine(z, y1 - 10, z + 30, y1 - 10);

        g.drawLine((this.getWidth() / 6 + this.getWidth() - 100) / 2, this.getHeight() / 10, (this.getWidth() / 6 + this.getWidth() - 100) / 2, 300 + this.getHeight() / 10);

        g.setColor(Color.GREEN);

        g.drawLine((this.getWidth() / 6 + this.getWidth() - 100) / 2, this.getHeight() / 10, (this.getWidth() / 6 + this.getWidth() - 100) / 2 + 5, this.getHeight() / 10 + 6);
        g.drawLine((this.getWidth() / 6 + this.getWidth() - 100) / 2, this.getHeight() / 10, (this.getWidth() / 6 + this.getWidth() - 100) / 2 - 5, this.getHeight() / 10 + 6);

// FLECHE POUR LE BAS
        g.drawLine((this.getWidth() / 6 + this.getWidth() - 100) / 2, 300 + this.getHeight() / 10, (this.getWidth() / 6 + this.getWidth() - 100) / 2 + 6, 300 + this.getHeight() / 10 - 6);
        g.drawLine((this.getWidth() / 6 + this.getWidth() - 100) / 2, 300 + this.getHeight() / 10, (this.getWidth() / 6 + this.getWidth() - 100) / 2 - 6, 300 + this.getHeight() / 10 - 6);

// bac du recipient
        g.drawLine(this.getWidth() / 6, 300 + this.getHeight() / 10 + 3, this.getWidth() / 6, 300 + this.getHeight() / 10 + 30);
        g.drawLine(this.getWidth() - 100, 300 + this.getHeight() / 10 + 3, this.getWidth() - 100, 300 + this.getHeight() / 10 + 30);
        g.drawLine(this.getWidth() / 6 + 10, 300 + this.getHeight() / 10 + 20, this.getWidth() - 100 - 10, 300 + this.getHeight() / 10 + 20);

        g.setColor(Color.BLUE);

        g.drawString(" " + diam, (this.getWidth() / 6 + this.getWidth() - 100) / 2 + 5, this.getHeight() / 10 + 50);
        g.drawString(" " + longueur, (this.getWidth() / 6 + 10 + this.getWidth() - 100 - 10) / 2 + 5, 300 + this.getHeight() / 10 + 35);

        g.drawLine(this.getWidth() - 100, z2, this.getWidth() - 60, z2);
        g.drawLine(this.getWidth() / 6, z2, this.getWidth() / 6 - 40, z2);
        g.drawString(" " + fleched, this.getWidth() - 80, z2 - 10);
        g.drawString(" " + flecheg, this.getWidth() / 6 - 20, z2 - 10);

        float[] dash1 = {2f, 0f, 2f};
        BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);

        g.setStroke(bs2);
        g.drawLine(this.getWidth() / 6, this.getHeight() / 10, this.getWidth() / 6, this.getHeight() / 10 + 300);
        g.drawLine(this.getWidth() - 100, this.getHeight() / 10, this.getWidth() - 100, this.getHeight() / 10 + 300);
    }

}
