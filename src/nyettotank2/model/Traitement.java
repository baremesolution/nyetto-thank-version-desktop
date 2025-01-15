package nyettotank2.model;

import nyettotank2.enums.TypeDonnees;
import nyettotank2.enums.TypeValeur;

public class Traitement {

    private TypeDonnees typeDonnees;
    private String nomFichierCertificat;
    private String nomFichierTable;
    private TypeValeur typeValeurVolume;
    private int nombreDivision;
    private Float resultatEnLitres;
    private Float resultatM3;
    private Float resultatEnGallons;

    public Traitement() {
    }

    public Traitement(TypeDonnees typeDonnees, String nomFichierCertificat, String nomFichierTable, TypeValeur typeValeurVolume, int nombreDivision, Float resultatEnLitres, Float resultatM3, Float resultatEnGallons) {
        this.typeDonnees = typeDonnees;
        this.nomFichierCertificat = nomFichierCertificat;
        this.nomFichierTable = nomFichierTable;
        this.typeValeurVolume = typeValeurVolume;
        this.nombreDivision = nombreDivision;
        this.resultatEnLitres = resultatEnLitres;
        this.resultatM3 = resultatM3;
        this.resultatEnGallons = resultatEnGallons;
    }

    public TypeDonnees getTypeDonnees() {
        return typeDonnees;
    }

    public void setTypeDonnees(TypeDonnees typeDonnees) {
        this.typeDonnees = typeDonnees;
    }

    public String getNomFichierCertificat() {
        return nomFichierCertificat;
    }

    public void setNomFichierCertificat(String nomFichierCertificat) {
        this.nomFichierCertificat = nomFichierCertificat;
    }

    public String getNomFichierTable() {
        return nomFichierTable;
    }

    public void setNomFichierTable(String nomFichierTable) {
        this.nomFichierTable = nomFichierTable;
    }

    public TypeValeur getTypeValeurVolume() {
        return typeValeurVolume;
    }

    public void setTypeValeurVolume(TypeValeur typeValeurVolume) {
        this.typeValeurVolume = typeValeurVolume;
    }

    public int getNombreDivision() {
        return nombreDivision;
    }

    public void setNombreDivision(int nombreDivision) {
        this.nombreDivision = nombreDivision;
    }

    public Float getResultatEnLitres() {
        return resultatEnLitres;
    }

    public void setResultatEnLitres(Float resultatEnLitres) {
        this.resultatEnLitres = resultatEnLitres;
    }

    public Float getResultatM3() {
        return resultatM3;
    }

    public void setResultatM3(Float resultatM3) {
        this.resultatM3 = resultatM3;
    }

    public Float getResultatEnGallons() {
        return resultatEnGallons;
    }

    public void setResultatEnGallons(Float resultatEnGallons) {
        this.resultatEnGallons = resultatEnGallons;
    }

}
