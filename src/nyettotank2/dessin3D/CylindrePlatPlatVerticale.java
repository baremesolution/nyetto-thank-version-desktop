package nyettotank2.dessin3D;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author LYZARA SOLUTION
 */
public class CylindrePlatPlatVerticale extends JPanel {
    
       private float diam, longueur;

    public CylindrePlatPlatVerticale(float diam, float longueur) {
        this.diam = diam;
        this.longueur = longueur;

    }
    
  public void paintComponent(Graphics graphics){
//Vous verrez cette phrase chaque fois que la méthode sera 


        Graphics2D area = (Graphics2D) graphics.create();

// tracer des deux droites horizontales
area.drawLine(this.getWidth()/2 -200, this.getHeight()/20, this.getWidth()/2 -200, 350 + this.getHeight()/20);
area.drawLine(this.getWidth()/2 +200, this.getHeight()/20, this.getWidth()/2 +200, 350 + this.getHeight()/20);

area.setColor(Color.BLACK); 
int x1 = this.getWidth()/6 , y1 = this.getHeight()/10 , x2=this.getWidth()-100, y2=this.getHeight()/10 + 300;

//area.fillArc(this.getWidth()/2 - 200, this.getHeight()/20 -10, 400, 20, 0, 360);//modifier
area.fillOval(this.getWidth()/2 - 200, this.getHeight()/20 -10, 400, 20);//modifier


area.fillArc(this.getWidth()/2 - 200, 340 + this.getHeight()/20, 400, 20, 0, 360);//modifier


area.setColor(Color.BLUE); 
//barre verticale au centre
area.drawLine(  this.getWidth()/2, this.getHeight()/20, this.getWidth()/2, 350 + this.getHeight()/20);
// FLECHE POUR LE HAUT
area.setColor(Color.GREEN); 

area.drawLine( this.getWidth()/2, this.getHeight()/20, this.getWidth()/2 + 5, this.getHeight()/20 + 6);
area.drawLine( this.getWidth()/2, this.getHeight()/20, this.getWidth()/2 - 5, this.getHeight()/20 + 6);

// FLECHE POUR LE BAS
area.drawLine( this.getWidth()/2, 350 + this.getHeight()/20, this.getWidth()/2 + 6, 350 + this.getHeight()/20 - 6);
area.drawLine( this.getWidth()/2, 350 + this.getHeight()/20, this.getWidth()/2 - 6, 350 + this.getHeight()/20 - 6);

area.setColor(Color.BLUE); 
area.drawString(" " + diam, this.getWidth()/2 + 10, this.getHeight()/20 + 190);
area.drawString(" " + longueur, ( this.getWidth()/6 + 10 + this.getWidth()-100 - 10 )/2 + 5, 300 + this.getHeight()/10 + 35);


//nouvelle ligne
float[] dash1 = { 2f, 0f, 2f };
BasicStroke bs2 = new BasicStroke(1, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);

area.setStroke(bs2);
area.drawLine(this.getWidth()/2 - 200, this.getHeight()/20, this.getWidth()/2 + 200, this.getHeight()/20);  
area.drawLine(this.getWidth()/2 - 200, 350 + this.getHeight()/20, this.getWidth()/2 + 200, this.getHeight()/20 + 350);  

area.drawLine(this.getWidth()/2 - 195, 175 + this.getHeight()/20 , this.getWidth()/2 + 195, 175 + this.getHeight()/20 );
  

  }
    
}
