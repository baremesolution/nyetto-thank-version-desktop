
package nyettotank.dessin3D;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author kenfack-cedric
 */
public class Ellipsoide extends JPanel {
    
           private float grandAxe, petitAxe, hauteur;

           public Ellipsoide(float grandAxe, float petitAxe, float hauteur) {
        this.grandAxe = grandAxe;
        this.petitAxe = petitAxe;
        this.hauteur = hauteur;

    }

                public void paintComponent(Graphics area) {
        //Vous verrez cette phrase chaque fois que la méthode sera
        Graphics2D g = (Graphics2D) area;
        
        float grandAxeTransform = traceLineOrArcOnHorizontal( grandAxe, "grand" );
        float petitAxeTransform = traceLineOrArcOnHorizontal( petitAxe, "petit" );
        float hauteurTransform = traceLineOrArcOnHorizontal( hauteur, "haut" );
        
        
        g.drawArc((int) ( ( this.getWidth() - grandAxeTransform )/2 ) , (int) ( ( this.getHeight()- grandAxeTransform )/2 ), (int) grandAxeTransform, (int) hauteurTransform, 0, 360);

        area.drawLine((int) ( ( this.getWidth() - grandAxeTransform )/2 ) , (int) (( this.getHeight() - petitAxeTransform ) /2), (int) ( ( this.getWidth() + grandAxeTransform )/2 ) , (int) (( this.getHeight() - petitAxeTransform ) /2));
        
        float[] dash21 = {2f, 2f, 2f};
        BasicStroke bs31 = new BasicStroke(3.5f, BasicStroke.JOIN_ROUND,
                BasicStroke.JOIN_ROUND, 1.0f, dash21, 2f);

        g.setColor(Color.BLACK);
        g.setStroke(bs31);

        //g.rotate(Math.PI * 20 / 180);

        // tracer des deux droites horizontales
    
      
        //dessin fleche
        g.drawArc((int) ( ( this.getWidth() - grandAxeTransform )/2 ) , (int) ( this.getHeight()/2 - petitAxeTransform ), (int) grandAxeTransform, (int) petitAxeTransform, 0, 360);
         g.setColor(Color.RED);
            g.drawString("Grand axe = " + this.grandAxe, (int) ( ( this.getWidth() - grandAxeTransform )/2 ) , (int) ( ( this.getHeight()+ grandAxeTransform )/2 ) - 10);
            g.drawString("Petit axe = " + this.petitAxe, (int) ( ( this.getWidth() - grandAxeTransform )/2 ) , (int) ( ( this.getHeight()+ grandAxeTransform )/2 ) + 5);
            g.drawString("Hauteur = " + this.hauteur, (int) ( ( this.getWidth() - grandAxeTransform )/2 ) , (int) ( ( this.getHeight()+ grandAxeTransform )/2 ) + 20);

        
        //float[] dash1 = { 2f, 0f, 2f };
        //BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,
        //                BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
        //
        //area.setStroke(bs2);
        
    }

  
           
           
    private float traceLineOrArcOnHorizontal(float value, String typeMesure) {

        float result = 0;
        if( typeMesure.equals( "grand" ) ){
        
             result = 460 * value / 500;
            if (100 >= result) {
                return 100;
            } else if (result >= 460) {
                return 460;
            } else {
                return result;
            }
        
        } else if( typeMesure.equals( "petit" ) ){
        
             result = 150 * value / 300;
            if (35 >= result) {
                return 35;
            } else if (result >= 150) {
                return 150;
            } else {
                return result;
            }
        
        } else {
        
             result = 450 * value / 600;
            if (100 >= result) {
                return 100;
            } else if (result >= 450) {
                return 450;
            } else {
                return result;
            }
        
        }
           
    }
}
