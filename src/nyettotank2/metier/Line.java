package nyettotank2.metier;

public class Line {

    public Line() {
    }
    
    public Line(double volume, float hauteur) {
        this.volume = volume;
        this.hauteur = hauteur;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public float getHauteur() {
        return hauteur;
    }

    public void setHauteur(float hauteur) {
        this.hauteur = hauteur;
    }

    private float hauteur;
    private double volume;

}
