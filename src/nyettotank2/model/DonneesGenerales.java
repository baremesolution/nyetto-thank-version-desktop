package nyettotank2.model;

import nyettotank2.enums.TypeDonnees;

public class DonneesGenerales {

    //info type
    private String chefOperation;
    private String lieuOperation;
    private String fabricant;
    private String detenteur;
    private String adresseClient;
    private String immatriculation;
    private String agree;
    private Integer anneeFabrication;
    private Float hauteurTemoin;
    private String photoLogo;
    private String photoSignature;

    //capacite
    private String nomCapacite;
    private String numeroSerieCapacite;
    private String modeOperatoireCapacite;
    private String produitStockeCapacite;
    private Float volumeNominalCapacite;
    private Integer nombreCompartiment;
    private String fondCapacite;
    private String dateDebutTravaux;
    private String dateFinTravaux;
    private String typeCapacite;
    private String etancheite;

    private TypeDonnees typeDonneGenerale;

    public DonneesGenerales() {
    }

    public DonneesGenerales(String chefOperation, String lieuOperation, String fabricant, String detenteur, String adresseClient, String immatriculation, String agree, Integer anneeFabrication, String photoLogo, String photoSignature, String nomCapacite, String numeroSerieCapacite, String modeOperatoireCapacite, String produitStockeCapacite, Float volumeNominalCapacite, Integer nombreCompartiment, String fondCapacite, String dateDebutTravaux, String dateFinTravaux, String typeCapacite, String etancheite, TypeDonnees typeDonneGenerale) {
        this.chefOperation = chefOperation;
        this.lieuOperation = lieuOperation;
        this.fabricant = fabricant;
        this.detenteur = detenteur;
        this.adresseClient = adresseClient;
        this.immatriculation = immatriculation;
        this.agree = agree;
        this.anneeFabrication = anneeFabrication;
        this.photoLogo = photoLogo;
        this.photoSignature = photoSignature;
        this.nomCapacite = nomCapacite;
        this.numeroSerieCapacite = numeroSerieCapacite;
        this.modeOperatoireCapacite = modeOperatoireCapacite;
        this.produitStockeCapacite = produitStockeCapacite;
        this.volumeNominalCapacite = volumeNominalCapacite;
        this.nombreCompartiment = nombreCompartiment;
        this.fondCapacite = fondCapacite;
        this.dateDebutTravaux = dateDebutTravaux;
        this.dateFinTravaux = dateFinTravaux;
        this.typeCapacite = typeCapacite;
        this.etancheite = etancheite;
        this.typeDonneGenerale = typeDonneGenerale;
    }

    public String getChefOperation() {
        return chefOperation;
    }

    public void setChefOperation(String chefOperation) {
        this.chefOperation = chefOperation;
    }

    public String getLieuOperation() {
        return lieuOperation;
    }

    public void setLieuOperation(String lieuOperation) {
        this.lieuOperation = lieuOperation;
    }

    public String getFabricant() {
        return fabricant;
    }

    public void setFabricant(String fabricant) {
        this.fabricant = fabricant;
    }

    public String getDetenteur() {
        return detenteur;
    }

    public void setDetenteur(String detenteur) {
        this.detenteur = detenteur;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public Integer getAnneeFabrication() {
        return anneeFabrication;
    }

    public void setAnneeFabrication(Integer anneeFabrication) {
        this.anneeFabrication = anneeFabrication;
    }

    public String getPhotoLogo() {
        return photoLogo;
    }

    public void setPhotoLogo(String photoLogo) {
        this.photoLogo = photoLogo;
    }

    public String getPhotoSignature() {
        return photoSignature;
    }

    public void setPhotoSignature(String photoSignature) {
        this.photoSignature = photoSignature;
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

    public String getModeOperatoireCapacite() {
        return modeOperatoireCapacite;
    }

    public void setModeOperatoireCapacite(String modeOperatoireCapacite) {
        this.modeOperatoireCapacite = modeOperatoireCapacite;
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

    public String getDateDebutTravaux() {
        return dateDebutTravaux;
    }

    public void setDateDebutTravaux(String dateDebutTravaux) {
        this.dateDebutTravaux = dateDebutTravaux;
    }

    public String getDateFinTravaux() {
        return dateFinTravaux;
    }

    public void setDateFinTravaux(String dateFinTravaux) {
        this.dateFinTravaux = dateFinTravaux;
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

    public TypeDonnees getTypeDonneGenerale() {
        return typeDonneGenerale;
    }

    public void setTypeDonneGenerale(TypeDonnees typeDonneGenerale) {
        this.typeDonneGenerale = typeDonneGenerale;
    }

    public Float getHauteurTemoin() {
        return hauteurTemoin;
    }

    public void setHauteurTemoin(Float hauteurTemoin) {
        this.hauteurTemoin = hauteurTemoin;
    }

}
