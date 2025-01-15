package nyettotank2.model;

public class InfoType {

    private String chefOperation;
    private String lieuOperation;
    private String detenteur;
    private String adresseClient;
    private String agree;
    private String photoLogo;
    private String photoSignature;
    private String dateDebutTravaux;
    private String dateFinTravaux;

    public InfoType() {
    }

    public InfoType(String chefOperation, String lieuOperation, String detenteur, String adresseClient, String agree, String photoLogo, String photoSignature, String dateDebutTravaux, String dateFinTravaux) {
        this.chefOperation = chefOperation;
        this.lieuOperation = lieuOperation;

        this.detenteur = detenteur;
        this.adresseClient = adresseClient;
        this.agree = agree;

        this.photoLogo = photoLogo;
        this.photoSignature = photoSignature;
        this.dateDebutTravaux = dateDebutTravaux;
        this.dateFinTravaux = dateFinTravaux;
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

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
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

}
