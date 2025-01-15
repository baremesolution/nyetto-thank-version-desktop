package nyettotank2.model;

public class InfoCapacite {

    private String nomCapacite;
    private String numeroSerieCapacite;
    private String produitStockeCapacite;
    private Float volumeNominalCapacite;
    private Float hauteurTemoin;
    private Integer nombreCompartiment;
    private String fondCapacite;
    private String typeCapacite;
    private String etancheite;
    private String fabricant;
    private Integer anneeFabrication;

    public InfoCapacite() {
    }

    public InfoCapacite(String nomCapacite, String numeroSerieCapacite, String produitStockeCapacite, Float volumeNominalCapacite, Float hauteurTemoin, Integer nombreCompartiment, String fondCapacite, String typeCapacite, String etancheite, Integer anneeFabrication, String fabricant) {
        this.nomCapacite = nomCapacite;
        this.numeroSerieCapacite = numeroSerieCapacite;
        this.produitStockeCapacite = produitStockeCapacite;
        this.volumeNominalCapacite = volumeNominalCapacite;
        this.hauteurTemoin = hauteurTemoin;
        this.nombreCompartiment = nombreCompartiment;
        this.fondCapacite = fondCapacite;
        this.typeCapacite = typeCapacite;
        this.etancheite = etancheite;
        this.anneeFabrication = anneeFabrication;
        this.fabricant = fabricant;
    }

    public String getNomCapacite() {
        return nomCapacite;
    }

    public void setNomCapacite(String nomCapacite) {
        this.nomCapacite = nomCapacite;
    }

    public String getNumeroSerieCapacite() {
        return numeroSerieCapacite;
    }

    public void setNumeroSerieCapacite(String numeroSerieCapacite) {
        this.numeroSerieCapacite = numeroSerieCapacite;
    }

    public String getProduitStockeCapacite() {
        return produitStockeCapacite;
    }

    public void setProduitStockeCapacite(String produitStockeCapacite) {
        this.produitStockeCapacite = produitStockeCapacite;
    }

    public Float getVolumeNominalCapacite() {
        return volumeNominalCapacite;
    }

    public void setVolumeNominalCapacite(Float volumeNominalCapacite) {
        this.volumeNominalCapacite = volumeNominalCapacite;
    }

    public Float getHauteurTemoin() {
        return hauteurTemoin;
    }

    public void setHauteurTemoin(Float hauteurTemoin) {
        this.hauteurTemoin = hauteurTemoin;
    }

    public Integer getNombreCompartiment() {
        return nombreCompartiment;
    }

    public void setNombreCompartiment(Integer nombreCompartiment) {
        this.nombreCompartiment = nombreCompartiment;
    }

    public String getFondCapacite() {
        return fondCapacite;
    }

    public void setFondCapacite(String fondCapacite) {
        this.fondCapacite = fondCapacite;
    }

    public String getTypeCapacite() {
        return typeCapacite;
    }

    public void setTypeCapacite(String typeCapacite) {
        this.typeCapacite = typeCapacite;
    }

    public String getEtancheite() {
        return etancheite;
    }

    public void setEtancheite(String etancheite) {
        this.etancheite = etancheite;
    }

    public Integer getAnneeFabrication() {
        return anneeFabrication;
    }

    public void setAnneeFabrication(Integer anneeFabrication) {
        this.anneeFabrication = anneeFabrication;
    }

    public String getFabricant() {
        return fabricant;
    }

    public void setFabricant(String fabricant) {
        this.fabricant = fabricant;
    }
}
