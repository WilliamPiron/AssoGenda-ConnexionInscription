package williampiron.classique.miage.assogenda;

/**
 * Created by William on 19/01/2017.
 */

public class Utilisateur {
    private String pseudo;
    private String prenom;
    private String nom;
    private String mail;
    private String motDePasse;

    public Utilisateur(String prenom, String nom, String mail, String pseudo, String motDePasse) {
        this.pseudo = pseudo;
        this.prenom = prenom;
        this.nom = nom;
        this.motDePasse = motDePasse;
        this.mail = mail;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getMail() {
        return mail;
    }
}
