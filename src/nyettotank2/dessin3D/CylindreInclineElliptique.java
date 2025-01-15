package nyettotank2.dessin3D;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import javax.swing.JPanel;

public class CylindreInclineElliptique extends JPanel {
    
    private float diam, longueur, flecheg, fleched;

    public CylindreInclineElliptique(float diam, float longueur, float flecheg, float fleched) {
        this.diam = diam;
        this.longueur = longueur;
        this.fleched = fleched;
        this.flecheg = flecheg;

    }
    
  
 
  public void paintComponent(Graphics graphics){
//Vous verrez cette phrase chaque fois que la méthode sera 


        Graphics2D area = (Graphics2D) graphics.create();
area.rotate(-Math.PI/100);        
// tracer des deux droites horizontales
area.drawLine(this.getWidth()/6, this.getHeight()/10, this.getWidth()-100, this.getHeight()/10);
area.drawLine(this.getWidth()/6, 300 + this.getHeight()/10, this.getWidth()-100, 300 + this.getHeight()/10);

area.setColor(Color.BLACK); 
//   g.drawArc(this.getWidth()/6-50+17, this.getHeight()/10, 70, 100, 90, 180);
int x1 = this.getWidth()/6 , y1 = this.getHeight()/10 , x2=this.getWidth()-100, y2=this.getHeight()/10 + 300;

int z= (x1 + x2)/2 ; z-=15;
int z2 = (y1 + y2)/2;

area.drawLine(z , y1 , z , y1 - 10);
area.drawLine(z + 30 , y1 , z + 30 , y1 - 10);
area.drawLine(z , y1 -10 , z + 30, y1 - 10);

float angle=90, cxi = x1, cyi = y1 + 150;

          GeneralPath courbe = new GeneralPath();
        courbe.moveTo(x1, y1);
        while(angle<=270) {
                angle +=0.5;
              float  x11 = (float) (cxi + 70*Math.cos( Math.PI*angle/180 ));
              float  y11 = (float) (cyi - 150*Math.sin( Math.PI*angle/180 ));
                // courbe.lineTo(50 + val, 450 - fonction.interpolate(val) );
                courbe.lineTo(x11, y11);

        }
        
        area.draw(courbe);

// a remettre  ------- g.drawArc(this.getWidth()/6-50+17, this.getHeight()/10, 70, 100, 90, 180);
//g.drawArc(this.getWidth()/6-50+17, this.getHeight()/10, 70, 100, -90, 90);
//g.drawArc(this.getWidth()/6-50+17, this.getHeight()/10, 70, 100, 0, 90);

area.setColor(Color.BLACK); 
//g.drawArc(this.getWidth()-135, this.getHeight()/10, 70, 100, 90, 180);
//g.drawArc(this.getWidth()-135, this.getHeight()/10, 70, 100, 0, 270);
area.drawArc(this.getWidth()-170, this.getHeight()/10, 140, 300, -90, 180);

//barre verticale au centre
area.drawLine( ( this.getWidth()/6 + this.getWidth()-100 )/2, this.getHeight()/10, ( this.getWidth()/6 + this.getWidth()-100 )/2, 300 + this.getHeight()/10);
// FLECHE POUR LE HAUT
area.setColor(Color.GREEN); 

area.drawLine( ( this.getWidth()/6 + this.getWidth()-100 )/2, this.getHeight()/10, ( this.getWidth()/6 + this.getWidth()-100 )/2 + 5, this.getHeight()/10 + 6);
area.drawLine( ( this.getWidth()/6 + this.getWidth()-100 )/2, this.getHeight()/10, ( this.getWidth()/6 + this.getWidth()-100 )/2 - 5, this.getHeight()/10 + 6);

// FLECHE POUR LE BAS
area.drawLine( ( this.getWidth()/6 + this.getWidth()-100 )/2, 300 + this.getHeight()/10, ( this.getWidth()/6 + this.getWidth()-100 )/2 + 6, 300 + this.getHeight()/10 - 6);
area.drawLine( ( this.getWidth()/6 + this.getWidth()-100 )/2, 300 + this.getHeight()/10, ( this.getWidth()/6 + this.getWidth()-100 )/2 - 6, 300 + this.getHeight()/10 - 6);

// bac du recipient
area.drawLine(this.getWidth()/6, 300 + this.getHeight()/10 + 3, this.getWidth()/6, 300 + this.getHeight()/10 + 30);
area.drawLine(this.getWidth()-100, 300 + this.getHeight()/10 + 3, this.getWidth()-100, 300 + this.getHeight()/10 + 30);
area.drawLine(this.getWidth()/6 + 10, 300 + this.getHeight()/10 + 20, this.getWidth()-100 - 10, 300 + this.getHeight()/10 + 20);

area.setColor(Color.BLUE); 
area.drawString(" " + diam, ( this.getWidth()/6 + this.getWidth()-100 )/2 + 5, this.getHeight()/10 + 50);
area.drawString(" " + longueur, ( this.getWidth()/6 + 10 + this.getWidth()-100 - 10 )/2 + 5, 300 + this.getHeight()/10 + 35);

area.drawLine( this.getWidth()-100 , z2, this.getWidth()-30, z2);
area.drawLine( this.getWidth()/6 , z2, this.getWidth()/6 - 70, z2);

area.drawString(" " + fleched,  this.getWidth()-50 , z2 - 10);
area.drawString(" " + flecheg, this.getWidth()/6 - 50, z2 -10);

//nouvelle ligne
float[] dash1 = { 2f, 0f, 2f };
float[] dash2 = { 1f, 1f, 1f };
BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);

BasicStroke bs3 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash2, 2f);

area.setStroke(bs2);
area.drawLine(this.getWidth()/6, this.getHeight()/10, this.getWidth()/6, this.getHeight()/10 + 300);  
area.drawLine(this.getWidth()-100, this.getHeight()/10, this.getWidth()-100, this.getHeight()/10 + 300);  
    }
    
}
