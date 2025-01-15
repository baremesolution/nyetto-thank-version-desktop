/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class Sphere extends JPanel {
    
           private float rayon;

           public Sphere(float rayon) {
        this.rayon = rayon;

    }
           
           
     public void paintComponent(Graphics area) {
        //Vous verrez cette phrase chaque fois que la méthode sera
        rayon = traceLineOrArcOnHorizontal(rayon);

        Graphics2D g = (Graphics2D) area;
        
                g.drawArc((int) (this.getWidth()/2 - rayon) , (int) (this.getHeight()/2 - rayon), (int) (2*rayon), (int) (2*rayon), 0, 360);

                area.drawLine((int) ( ( this.getWidth() - 2*rayon )/2 ) , (int) (( this.getHeight() ) /2), (int) ( ( this.getWidth() + 2*rayon )/2 ) , (int) (( this.getHeight() ) /2));


        float[] dash21 = {1f, 1f, 1f};
        BasicStroke bs31 = new BasicStroke(2f, BasicStroke.JOIN_ROUND,
                BasicStroke.JOIN_ROUND, 1.0f, dash21, 1f);

        g.setStroke(bs31);

        //g.rotate(Math.PI * 20 / 180);

        // tracer des deux droites horizontales
    
      
        //dessin fleche
        g.drawArc((int) (this.getWidth()/2 - rayon ) , (int) (this.getHeight()/2 - 10), (int) (2*rayon), 20, 0, 360);

                g.setColor(Color.RED);
            g.drawString("rayon = " + rayon, (int) (( this.getWidth() )/2 - rayon) , (int) (( this.getHeight() ) /2) + (int) (rayon) + 10);

        //float[] dash1 = { 2f, 0f, 2f };
        //BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,
        //                BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
        //
        //area.setStroke(bs2);
        
    }

  
           
           
    private float traceLineOrArcOnHorizontal(float value) {

        float result = 0;

            result = 250 * value / 300;
            if (80 >= result) {
                return 80;
            } else if (result >= 250) {
                return 250;
            } else {
                return (float) Math.floor(result);
            }
    }

    
}
