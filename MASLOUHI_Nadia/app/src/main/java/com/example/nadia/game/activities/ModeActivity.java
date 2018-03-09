package com.example.nadia.game.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.nadia.game.bean.PuzzleGroup;
import com.example.nadia.game.bean.Stage;
import com.example.nadia.game.dataXML.ParseXML;
import com.example.nadia.game.R;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by nadia on 28/02/2018.
 */

public class ModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_game);

        Button btnClassic = (Button) findViewById(R.id.btnClassic);
        Button btnPremium = (Button) findViewById(R.id.btnPremium);


        btnClassic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("parseXML", "debut parse classic");
                ParseXML p =  new ParseXML();
                try {
                    PuzzleGroup pz = p.parsingPuzzleGroupe(getApplicationContext(),"GroupClassic.xml");
                    Log.i("parseXML", "Fin parse");
                    Intent intent = new Intent(ModeActivity.this, StageActivity.class);
                    intent.putExtra("STAGES", (Serializable) pz.getStages());
                    startActivity(intent);
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        btnPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("parseXML", "debut parse premium");
                ParseXML p =  new ParseXML();
                try {
                    PuzzleGroup pz = p.parsingPuzzleGroupe(getApplicationContext(),"GroupPremium.xml");
                    Log.i("parseXML", "Fin parse");
                    Intent intent = new Intent(ModeActivity.this, StageActivity.class);
                    intent.putExtra("STAGES", (ArrayList<Stage>) pz.getStages() );
                    startActivity(intent);
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
