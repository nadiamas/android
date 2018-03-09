package com.example.nadia.game.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nadia.game.DataBaseManager;
import com.example.nadia.game.R;
import com.example.nadia.game.bean.Joueur;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText login, password;
    Button btnCnx , btnInscription ;
    private DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentification);

        login =(EditText) findViewById(R.id.edtJoueur);
        password = (EditText) findViewById(R.id.edtPassword);

        btnCnx = findViewById(R.id.btnCnx);
        btnInscription = findViewById(R.id.btnInscription);



        btnCnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseManager = new DataBaseManager(getApplicationContext());
                List<Joueur> joueurs = dataBaseManager.getJoueurs();
                Joueur joueur1 = new Joueur(login.getText().toString(),password.getText().toString());
                for (Joueur j : joueurs) {
                    if (j.getPseudo().equals(joueur1.getPseudo()) && j.getPassword().equals(joueur1.getPassword())) {
                        Log.i("Authentification", "Sucees");
                        Intent intent = new Intent(MainActivity.this, ModeActivity.class);
                        startActivity(intent);
                    }
                }
                dataBaseManager.close();
            }
        });

        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InscriptionActivity.class);
                startActivity(intent);
            }
        });




    }
}
