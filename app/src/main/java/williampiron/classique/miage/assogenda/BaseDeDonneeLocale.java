package williampiron.classique.miage.assogenda;


import java.util.ArrayList;


/**
 * Created by William on 19/01/2017.
 */

public class BaseDeDonneeLocale {
    public ArrayList<Utilisateur> bdd;
    private static BaseDeDonneeLocale instance = null;

    private BaseDeDonneeLocale() {
        this.bdd = new ArrayList<Utilisateur>();
    }

    public static BaseDeDonneeLocale getInstance(){
        if (instance == null){
            return new BaseDeDonneeLocale();
        }
        else{
            return instance;
        }
    }

    //Ajoute un inscrit à la bdd locale
    public void nouvelInscrit(Utilisateur user){
        this.bdd.add(user);
    }

    //Permet de chercher un utilisateur via son pseudo dans la bdd locale
    //Renvoie l'objet Utilisateur avec ce pseudo si trouvé, null sinon
    public Utilisateur trouverUtilisateur(String pseudo){
        for(Utilisateur user : bdd){
            if(user.getPseudo().equals(pseudo)){
                return user;
            }
        }
        return null;
    }
}
