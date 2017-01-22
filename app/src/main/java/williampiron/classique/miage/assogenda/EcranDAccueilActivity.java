package williampiron.classique.miage.assogenda;

import android.content.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

/**
 * Created by William on 19/01/2017.
 */

public class EcranDAccueilActivity extends AppCompatActivity {
    private BaseDeDonneeLocale bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_daccueil);

        bdd = BaseDeDonneeLocale.getInstance();

        Button inscription = (Button) findViewById(R.id.accueil_signUpButton);
        Button connexion = (Button) findViewById(R.id.accueil_signInButton);

        final EditText pseudoSaisi = (EditText) findViewById(R.id.accueil_pseudo);
        final EditText motdepasseSaisi = (EditText) findViewById(R.id.accueil_password);


        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToInscription(v);
            }
        });

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkTexteVide(pseudoSaisi) || checkTexteVide(motdepasseSaisi)){
                    Toast.makeText(v.getContext(), "Vous n'avez pas rempli tous les champs !", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (checkDonneesSaisiesOK(pseudoSaisi, motdepasseSaisi,bdd)){
                        goToMenu(v);
                    }
                    else{
                        Toast.makeText(v.getContext(), "L'identifiant et/ou le mot de passe est incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        Button test = (Button) findViewById(R.id.accueil_testButton);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), bdd.trouverUtilisateur("test").getMotDePasse(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Démarre l'activité d'inscription
    void goToInscription(View view){
        Intent intent = new Intent(this, EcranInscriptionActivity.class);
        startActivity(intent);
    }

    //Démarre l'activité d'accueil
    void goToMenu(View view){
        //NOTE : TEMPORAIRE, A REMPLACER PAR VRAI MENU PLUS TARD DANS LE PROJET
        Toast.makeText(view.getContext(), "Connexion réussie ! Mais le menu n'existe pas encore, repassez plus tard !", Toast.LENGTH_SHORT).show();
    }

    //Fonction qui vérifie que les EditText ne sont pas vides
    //On prend son texte, on le passe en String, on supprime les espaces inutiles saisis, et on regarde s'il fait 0 caractères de long
    boolean checkTexteVide (EditText text){
        return text.getText().toString().trim().length() == 0;
    }

    //Vérifie que le pseudo existe, et si oui, que le mot de passe est bon
    boolean checkDonneesSaisiesOK(EditText pseudo, EditText mdp, BaseDeDonneeLocale bdd){
        //TODO écrire la fonction
        Utilisateur tmp;
        tmp = bdd.trouverUtilisateur(pseudo.getText().toString());
        //Si l'utilisateur n'est pas dans la base, on renvoie false
        if(tmp == null){
            return false;
        }
        else{
            //S'il y est et que le mot de passe est bon, on renvoie true
            if (tmp.getMotDePasse().equals(mdp.getText().toString())){
                return true;
            }
            //Si le mot de passe est différent, on renvoie false
            else{
                return false;
            }
        }
    }


}
