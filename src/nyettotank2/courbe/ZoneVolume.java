package nyettotank2.courbe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.List;
import javax.swing.JPanel;
import nyettotank2.metier.Sline;

/**
 *
 * @author Cynthia
 */
public class ZoneVolume extends JPanel {

    /* protected void paintComponent(Graphics g) {
Graphics2D surface = (Graphics2D) g;
Rectangle2D rectangle = new Rectangle2D.Double(10.0, 10.0, 200.0, 100.0);
surface.draw(rectangle);
}*/

 /*private Ellipse2D ellipse = new Ellipse2D.Double();
private Rectangle2D rectangle = new Rectangle2D.Double();
public Zone() {
Rectangle2D r = new Rectangle2D.Double(50, 50, 190, 110);
ellipse.setFrameFromCenter(r.getCenterX(), r.getCenterY(), r.getMaxX(), r.getMaxY());
rectangle.setFrameFromDiagonal(ellipse.getX(), ellipse.getY(), r.getMaxX(), r.getMaxY());
}
protected void paintComponent(Graphics g) {
Graphics2D surface = (Graphics2D) g;
surface.draw(rectangle);
surface.draw(ellipse);
    
}*/
    private List<Float> x;
     private List<Double> y;
    private String uniteVolume = "", uniteHeight = "";

    public ZoneVolume(List<Float> x, List<Double> y, String h, String v) {
        this.x = x;
        this.y = y;
        this.uniteVolume = v;
        this.uniteHeight = h;
    }

    public void paintComponent(Graphics g) {

        g.setColor(Color.BLACK);
        g.drawLine(40, 40, 50, 30);
        g.drawLine(60, 40, 50, 30);
        g.drawLine(470, 450, 460, 440);
        g.drawLine(470, 450, 460, 460);

        g.drawLine(470, 450, 50, 450);
        g.drawLine(50, 450, 50, 30);

        g.drawString("HAUTEUR(" + uniteHeight.toUpperCase() + ")", 475, 450);
        g.drawString("VOLUME(" + uniteVolume.toUpperCase() + ")", 60, 30);

        Graphics2D area = (Graphics2D) g;
        GeneralPath triangle = new GeneralPath();

        Sline fonction = new Sline(x, y);

        float max_x = x.get(x.size() - 1);
        double max_y = y.get(y.size() - 1);

        g.setColor(Color.GRAY);

        double pas = max_x / 10;
        double initial = pas;
        while (initial <= max_x) {
            int x1 = (int) (initial * 400 / max_x);

            g.drawString("" + initial, (int) (50 + initial * 400 / max_x), 460);
            g.drawLine(x1 + 50, 450, 50 + x1, 50);
            initial += pas;

        }

        pas = max_y / 10;
        initial = pas;
        while (initial <= max_y) {
            int y1 = (int) (450 - 400 * initial / max_y);

            g.drawString("" + initial, 20, (int) (450 - 400 * initial / max_y));
            g.drawLine(50, y1, 450, y1);
            initial += pas;
        }

        g.setColor(Color.RED);
        for (int i = 0; i < x.size(); i++) {
            int x1 = (int) (x.get(i) * 400 / max_x);
            int y1 = (int) (450 - 400 * y.get(i) / max_y);

            g.drawLine(x1 + 45, y1, x1 + 55, y1);
            g.drawLine(x1 + 50, y1 - 5, x1 + 50, y1 + 5);

        }

//	float diametreUnite = Float.parseFloat( data.get("diametre").toString() );
//        float diametre = bareme.convertToCentimeter(uniteHeight, diametreUnite);
        GeneralPath courbe = new GeneralPath();
        courbe.moveTo(50, 450);
        g.setColor(Color.BLACK);
        for (int i = 0; i < x.size() - 1; i++) {

            float val = x.get(i) + 0.05f;
            while (val <= x.get(i + 1)) {

                courbe.lineTo(50 + val * 400 / max_x, 450 - 400 * fonction.interpolate(val) / max_y);
                val += 0.05;
            }
            courbe.lineTo(50 + x.get(i + 1) * 400 / max_x, 450 - 400 * y.get(i + 1) / max_y);
        }

        area.draw(courbe);

    }

}
