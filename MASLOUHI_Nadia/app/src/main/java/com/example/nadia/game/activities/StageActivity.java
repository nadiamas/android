package com.example.nadia.game.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.nadia.game.R;
import com.example.nadia.game.bean.Puzzle;
import com.example.nadia.game.bean.Stage;
import com.example.nadia.game.dataXML.ParseXML;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class StageActivity extends AppCompatActivity  implements View.OnClickListener {

    LinearLayout myLayout;
    GridLayout myGrid;
    RelativeLayout rl;
    ParseXML parse ;
    Button btnp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);

        Intent intent = getIntent();
        ArrayList<Stage> stages = (ArrayList<Stage>) intent.getSerializableExtra("STAGES");
        Log.i("STAGES","si"+stages.size());
        rl = (RelativeLayout) findViewById(R.id.myRelative);

        myLayout =  (LinearLayout) findViewById(R.id.myLinearLayout);
        Button btn ;
        for (Stage s : stages){
            Log.e("show stage", s.getName());
            btn = new Button(this);
            btn.setText(s.getName());
            btn.setTag(s.getName());
            myLayout.addView(btn);
            btn.setOnClickListener(this);
        }

        for (int i = 0; i < 9; i++) {
            int id = getResources().getIdentifier("p" + i, "id", getPackageName());
            btnp = (Button) findViewById(id);
            btnp.setTag("puzzle");
            btnp.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        parse = new ParseXML();

        switch (v.getTag().toString()){
            case "puzzle":
                try {
                    Button b = (Button)v;
                    Puzzle puzzle = parse.parsingPuzzleData(getApplicationContext(),((Button) v).getText()+".xml");
                    Intent intent = new Intent(StageActivity.this, GameGrid.class);
                    Log.i("get Puzzle",puzzle.toString());
                    intent.putExtra("PUZZLE", (Serializable) puzzle);
                    startActivity(intent);
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            default:
                try {
                    Log.e("tag", ""+v.getTag());
                    Stage stage = parse.parsingStageData(getApplicationContext(),"Stage"+v.getTag().toString()+".xml");

                    Log.e("get Stage",stage.toString());
                    for (int i = 0; i < 9; i++) {
                        int id = getResources().getIdentifier("p" + i, "id", getPackageName());
                        btnp = (Button) findViewById(id);
                        btnp.setText(stage.getPuzzles().get(i).getName());
                        Log.e("testBtn",btnp.getText().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }



    }
}
