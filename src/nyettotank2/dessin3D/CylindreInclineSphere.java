package nyettotank2.dessin3D;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;

/**
 *
 * @author AD-IPE
 */
public class CylindreInclineSphere extends JPanel {
    
      private float diam, longueur;

	    public CylindreInclineSphere(float diam, float longueur) {
	        this.diam = diam;
	        this.longueur = longueur;

	    }

	
      public void paintComponent(Graphics graphics){
          Graphics2D g = (Graphics2D) graphics.create();
g.rotate(Math.PI/50); 
//Vous verrez cette phrase chaque fois que la méthode sera

// tracer des deux droites horizontales
g.drawLine(this.getWidth()/6, this.getHeight()/10, this.getWidth()-100, this.getHeight()/10);
g.drawLine(this.getWidth()/6, 200 + this.getHeight()/10, this.getWidth()-100, 200 + this.getHeight()/10);


g.setColor(Color.BLACK); 
//   g.drawArc(this.getWidth()/6-50+17, this.getHeight()/10, 70, 100, 90, 180);
int x1 = this.getWidth()/6 , y1 = this.getHeight()/10 , x2=this.getWidth()-100, y2=this.getHeight()/10 + 200;
int z2 = (y1 + y2)/2;

int z= (x1 + x2)/2 ; z-=15;

g.drawLine(z , y1 , z , y1 - 10);
g.drawLine(z + 30 , y1 , z + 30 , y1 - 10);
g.drawLine(z , y1 -10 , z + 30, y1 - 10);

float angle=90, cxi = x1, cyi = y1 + 100;

          GeneralPath courbe = new GeneralPath();
        courbe.moveTo(x1, y1);
        while(angle<=270) {
                angle +=0.5;
             float   x11 = (float) (cxi + 100*Math.cos( Math.PI*angle/180 ));
             float    y11 = (float) (cyi - 100*Math.sin( Math.PI*angle/180 ));
                // courbe.lineTo(50 + val, 450 - fonction.interpolate(val) );
                courbe.lineTo(x11, y11);

        }
        
        g.draw(courbe);

// a remettre  ------- g.drawArc(this.getWidth()/6-50+17, this.getHeight()/10, 70, 100, 90, 180);
//g.drawArc(this.getWidth()/6-50+17, this.getHeight()/10, 70, 100, -90, 90);
//g.drawArc(this.getWidth()/6-50+17, this.getHeight()/10, 70, 100, 0, 90);

g.setColor(Color.BLACK); 
//g.drawArc(this.getWidth()-135, this.getHeight()/10, 70, 100, 90, 180);
//g.drawArc(this.getWidth()-135, this.getHeight()/10, 70, 100, 0, 270);
g.drawArc(this.getWidth()-200, this.getHeight()/10, 200, 200, -90, 180);

g.setColor(Color.RED); 
//barre verticale au centre
g.drawLine( ( this.getWidth()/6 + this.getWidth()-100 )/2, this.getHeight()/10, ( this.getWidth()/6 + this.getWidth()-100 )/2, 200 + this.getHeight()/10);
// FLECHE POUR LE HAUT
g.drawLine( ( this.getWidth()/6 + this.getWidth()-100 )/2, this.getHeight()/10, ( this.getWidth()/6 + this.getWidth()-100 )/2 + 5, this.getHeight()/10 + 6);
g.drawLine( ( this.getWidth()/6 + this.getWidth()-100 )/2, this.getHeight()/10, ( this.getWidth()/6 + this.getWidth()-100 )/2 - 5, this.getHeight()/10 + 6);

// FLECHE POUR LE BAS
g.drawLine( ( this.getWidth()/6 + this.getWidth()-100 )/2, 200 + this.getHeight()/10, ( this.getWidth()/6 + this.getWidth()-100 )/2 + 6, 200 + this.getHeight()/10 - 6);
g.drawLine( ( this.getWidth()/6 + this.getWidth()-100 )/2, 200 + this.getHeight()/10, ( this.getWidth()/6 + this.getWidth()-100 )/2 - 6, 200 + this.getHeight()/10 - 6);

// bac du recipient
g.drawLine(this.getWidth()/6, 200 + this.getHeight()/10 + 3, this.getWidth()/6, 200 + this.getHeight()/10 + 30);
g.drawLine(this.getWidth()-100, 200 + this.getHeight()/10 + 3, this.getWidth()-100, 200 + this.getHeight()/10 + 30);
g.drawLine(this.getWidth()/6 + 10, 200 + this.getHeight()/10 + 20, this.getWidth()-100 - 10, 200 + this.getHeight()/10 + 20);

g.setColor(Color.BLUE); 

g.drawString(" " + diam, ( this.getWidth()/6 + this.getWidth()-100 )/2 + 5, this.getHeight()/10 + 100);
g.drawString(" " + longueur, ( this.getWidth()/6 + 10 + this.getWidth()-100 - 10 )/2 + 5, 200 + this.getHeight()/10 + 35);

g.drawLine( this.getWidth()-100 , z2, this.getWidth(), z2);
g.drawLine( this.getWidth()/6 , z2, this.getWidth()/6 - 100, z2);


g.drawString(" " + diam/2,  this.getWidth()-50 , z2 - 10);
g.drawString(" " + diam/2, this.getWidth()/6 - 50, z2 -10);


float[] dash1 = { 2f, 0f, 2f };
BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);

g.setStroke(bs2);
g.drawLine(this.getWidth()/6, this.getHeight()/10, this.getWidth()/6, this.getHeight()/10 + 200);  
g.drawLine(this.getWidth()-100, this.getHeight()/10, this.getWidth()-100, this.getHeight()/10 + 200);
    }
    
    
}
