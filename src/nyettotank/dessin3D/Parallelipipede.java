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
public class Parallelipipede extends JPanel {
    
           private float largeur, longueur, hauteur;

    public Parallelipipede(float largeur, float longueur, float hauteur) {
        this.largeur = largeur;
        this.longueur = longueur;
        this.hauteur = hauteur;

    }
    
     public void paintComponent(Graphics area) {
        //Vous verrez cette phrase chaque fois que la méthode sera
        Graphics2D g = (Graphics2D) area;
            int heightWindow = this.getHeight() , widthWindow = this.getWidth() ;
        int referenceBasY = heightWindow - 20, referenceBasX = 20;
        float longueurTransform = traceLineOrArcOnHorizontal( longueur, "longueur" );
        float largeurTransform = traceLineOrArcOnHorizontal( largeur, "largueur" );
        float hauteurTransform = traceLineOrArcOnHorizontal( hauteur, "haut" );
        
        area.drawRect( (int) referenceBasX, (int) (referenceBasY - hauteurTransform), (int) longueurTransform, (int) hauteurTransform);
        area.drawRect( (int) referenceBasX + 220, (int) (referenceBasY - hauteurTransform - 150), (int) longueurTransform, (int) hauteurTransform);
        
        area.drawLine(referenceBasX, (int) (referenceBasY - hauteurTransform), (int) referenceBasX + 220, (int) (referenceBasY - hauteurTransform - 150));
        area.drawLine((int) (referenceBasX + longueurTransform), (int) (referenceBasY - hauteurTransform), (int) ( referenceBasX + 220 + longueurTransform), (int) (referenceBasY - hauteurTransform - 150));
       
         float[] dash21 = {1f, 1f, 1f};
        BasicStroke bs31 = new BasicStroke(2f, BasicStroke.CAP_SQUARE,
                BasicStroke.CAP_SQUARE, 1.0f, dash21, 1f);

        g.setColor(Color.BLACK);
        g.setStroke(bs31);
        
        area.drawLine(referenceBasX, referenceBasY, (int) referenceBasX + 220, (int) (referenceBasY - 150));
        area.drawLine((int) (referenceBasX + longueurTransform), referenceBasY, (int) ( referenceBasX + 220 + longueurTransform), (int) (referenceBasY - 150));
        
       g.setColor(Color.RED);
       float positionLongueurAbcisse = referenceBasX + longueurTransform/2 ;
       float positionLongueurOrdonne = hauteurTransform + (referenceBasY - hauteurTransform) + 15;
       float positionHauteurAbcisse = referenceBasX + 220 + longueurTransform - 40 ;
       float positionFauteurOrdonne = hauteurTransform + (referenceBasY )/2 + 20;
       float positionLargeurAbcisse = referenceBasX + 130 + longueurTransform;
     //  float positionLargeurOrdonne = (referenceBasY - hauteurTransform - 75) + (referenceBasY - hauteurTransform) + hauteurTransform;
       float positionLargeurOrdonne = referenceBasY - 75 ;

       g.drawString("Longueur = " + this.longueur, positionLongueurAbcisse , positionLongueurOrdonne);
            g.drawString("Largeur = " + this.largeur, positionLargeurAbcisse , positionLargeurOrdonne);
            g.drawString("Hauteur = " + this.hauteur, positionHauteurAbcisse , positionFauteurOrdonne);

        
        //float[] dash1 = { 2f, 0f, 2f };
        //BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,
        //                BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
        //
        //area.setStroke(bs2);
        
    }

  
           
           
    private float traceLineOrArcOnHorizontal(float value, String mesure) { // List<String>

        float result = 0;
        float maxWidth = (float) Math.hypot(220, 150);
        if( mesure.equals("longueur") ){
        
            result = value*350/600;
            if( result <= 100  ){
                return 100;
            } else if ( result > 350 )
                return 350;
            else 
                return result;
        
        } else if( mesure.equals("largeur") ){
        
            result = value*maxWidth/400;
            if( result <= 50  ){
                return 50;
            } else if ( result > maxWidth )
                return maxWidth;
            else 
                return result;
        
        } else {
            
             result = 300 * value / 300;
            if (40 >= result) {
                return 40;
            } else if (result >= 300) {
                return 300;
            } else {
                return result;
            }
            
        }

    }

    
}
