package com.example.nadia.game.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nadia.game.DataBaseManager;
import com.example.nadia.game.R;
import com.example.nadia.game.bean.Joueur;

/**
 * Created by nadia on 28/02/2018.
 */

public class InscriptionActivity extends AppCompatActivity {

    EditText login , password ;
    private DataBaseManager dataBaseManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);

        Button btnInscrir = (Button) findViewById(R.id.btnInscription);

        login =(EditText) findViewById(R.id.editPseudo);
        password = (EditText) findViewById(R.id.editPassword);

        btnInscrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseManager = new DataBaseManager(getApplicationContext());
                Joueur newJoueur = new Joueur(login.getText().toString(),password.getText().toString(),0);
                dataBaseManager.insertJoueur(newJoueur);
                dataBaseManager.close();
                Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
