package williampiron.classique.miage.assogenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by William on 19/01/2017.
 */

public class EcranInscriptionActivity extends AppCompatActivity {
    private BaseDeDonneeLocale bdd;
    EditText pseudo;
    EditText prenom;
    EditText nom;
    EditText mail;
    EditText mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_inscription);

        bdd = BaseDeDonneeLocale.getInstance();

        Button inscrire = (Button) findViewById(R.id.inscription_validateButton);
        pseudo = (EditText) findViewById(R.id.inscription_pseudo);
        prenom = (EditText) findViewById(R.id.inscription_prenom);
        nom = (EditText) findViewById(R.id.inscription_nom);
        mail = (EditText) findViewById(R.id.inscription_mail);
        mdp = (EditText) findViewById(R.id.inscription_motdepasse);

        inscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkTexteVide(pseudo) || checkTexteVide(prenom) || checkTexteVide(nom) || checkTexteVide(mail) || checkTexteVide(mdp)){
                    Toast.makeText(v.getContext(), "Vous n'avez pas rempli tous les champs !", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (bdd.trouverUtilisateur(pseudo.getText().toString())==null){
                        bdd.nouvelInscrit(
                                new Utilisateur(
                                        prenom.getText().toString(),
                                        nom.getText().toString(),
                                        mail.getText().toString(),
                                        pseudo.getText().toString(),
                                        mdp.getText().toString()
                                )
                        );
                        goBackToAccueil(v);
                    }
                    else{
                        Toast.makeText(v.getContext(), "Ce pseudo est déjà pris, veuillez changer et réessayer", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    //Fonction qui vérifie que les EditText ne sont pas vides
    //On prend son texte, on le passe en String, on supprime les espaces inutiles saisis, et on regarde s'il fait 0 caractères de long
    boolean checkTexteVide (EditText text){
        return text.getText().toString().trim().length() == 0;
    }

    //Retourne à l'activité Accueil
    void goBackToAccueil(View view){
        Intent intent = new Intent(this, EcranDAccueilActivity.class);
        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
        startActivity(intent);
    }

}
