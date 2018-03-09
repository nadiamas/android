package com.example.nadia.game.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ImageButton;

import com.example.nadia.game.MyView;
import com.example.nadia.game.R;
import com.example.nadia.game.Utils;
import com.example.nadia.game.bean.Puzzle;

import uz.shift.colorpicker.LineColorPicker;

public class GameGrid extends AppCompatActivity implements MyView.OnToggledListener {

    MyView[] myViews;
    GridLayout myGridLayout;
    int colorAct = 0 , M =16;
    LineColorPicker colorPicker;
    int[][]   colorArrayInt , colorInit;
    char[][] stringToCharArray;
    Puzzle puzzle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_grid);

        myGridLayout = (GridLayout) findViewById(R.id.mygrid);
        colorPicker = (LineColorPicker) findViewById(R.id.picker);

        int numOfCol = myGridLayout.getColumnCount();
        int numOfRow = myGridLayout.getRowCount();

        ImageButton btnInit = (ImageButton) findViewById(R.id.btnInit);
        ImageButton btnHome = (ImageButton) findViewById(R.id.btnHome);

        colorInit = new int[numOfRow][numOfCol];

        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("inittialisation boutton ");
                myGridLayout.removeAllViews();
                for (int yPos = 0; yPos < 10; yPos++) {
                    for (int xPos = 0; xPos < 16; xPos++) {
                        myViews[yPos * 16 + xPos].setInitialColor(colorInit[yPos][xPos]);
                        myGridLayout.addView( myViews[yPos * 16 + xPos]);
                    }
                }

            }
        });

        // Quand on clique sur ColorPicker colorAct prend la valeur Correspondante
        colorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("color pick " + colorPicker.getColor());
                if(puzzle.getName().contains("SAL")) {
                    if (colorPicker.getColor() == -10866383)
                        colorAct = 1;
                    else if (colorPicker.getColor() == -65536)
                        colorAct = 2;
                    else
                        colorAct = 3;
                }
                else if(puzzle.getName().contains("SBL")){
                    if (colorPicker.getColor() == -10866383)
                        colorAct = 1;
                    else if (colorPicker.getColor() == -65536 )
                        colorAct = 2;
                    else if (colorPicker.getColor() == -2448096 )
                        colorAct = 3;
                    else
                        colorAct = 4;

                }else
                if (colorPicker.getColor() == -14634326)
                    colorAct = 1;
                else if (colorPicker.getColor() == -1827976 )
                    colorAct = 2;
                else if (colorPicker.getColor() == -2448096 )
                    colorAct = 3;
                else if (colorPicker.getColor() == -16032930)
                    colorAct = 4;
                else
                    colorAct = 5;
            }
        });


        // Récupération de du Puzzle  depuis l'activité précédente
        Intent intent = getIntent();
        puzzle = (Puzzle) intent.getSerializableExtra("PUZZLE");


        // Transformation de tableau de couleur vers matrice
        stringToCharArray = Utils.transform(puzzle.getColours().toCharArray(), 16);
        colorArrayInt = new int[numOfRow][numOfCol];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 16; j++) {
                Log.e("string", "" + stringToCharArray[i][j]);
                colorArrayInt[i][j] = Integer.parseInt(String.valueOf(stringToCharArray[i][j]));
                colorInit[i][j] = Integer.parseInt(String.valueOf(stringToCharArray[i][j]));

                Log.e("Int ", "" +colorArrayInt[i][j]);
            }
        }


        myViews = new MyView[numOfCol * numOfRow];

        // Remplissage de GridView avec des view

            for (int yPos = 0; yPos < numOfRow; yPos++) {
                for (int xPos = 0; xPos < numOfCol; xPos++) {
                    MyView tView = new MyView(this, yPos, xPos);
                    tView.setInitialColor(colorArrayInt[yPos][xPos]);
                    tView.setStage(Utils.getStageName(puzzle.getName()));
                    tView.setOnToggledListener(this);
                    myViews[yPos * numOfCol + xPos] = tView;
                    myGridLayout.addView(tView);
                }
            }

            colorPicker.setColors(Utils.getColorsStage(puzzle.getName()));

            // Division du GridLayout
            myGridLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            final int contoure = 1;

                            int pWidth = myGridLayout.getWidth();
                            int pHeight = myGridLayout.getHeight();
                            int numOfCol = myGridLayout.getColumnCount();
                            int numOfRow = myGridLayout.getRowCount();
                            int w = pWidth / numOfCol;
                            int h = pHeight / numOfRow;

                            for (int yPos = 0; yPos < numOfRow; yPos++) {
                                for (int xPos = 0; xPos < numOfCol; xPos++) {
                                    GridLayout.LayoutParams params = (GridLayout.LayoutParams) myViews[yPos * numOfCol + xPos].getLayoutParams();
                                    params.width = w - 2 * contoure;
                                    params.height = h - 2 * contoure;
                                    params.setMargins(contoure, contoure, contoure, contoure);
                                    myViews[yPos * numOfCol + xPos].setLayoutParams(params);
                                }
                            }
                        }
                    });




    }

   // Algorithme pour recuperer les carreaux adjacents

   public int[][] adj(int[][] matrice , int x, int y){
       int c=matrice[x][y];

        if(matrice[x][y] != colorAct  ){
            matrice[x][y]= colorAct;

            if(x-1>=0 && matrice[x-1][y]==c){
                matrice=adj(matrice,x-1,y);
            }
            if(y-1>=0 && matrice[x][y-1]==c){
                matrice=adj(matrice,x,y-1);
            }
            if(x+1 < 10 && matrice[x+1][y]==c){
                matrice=adj(matrice,x+1,y);
            }
            if(y+1 < 16 && matrice[x][y+1]==c){
                matrice=adj(matrice,x,y+1);
            }
        }
        return matrice;
    }


    @Override
    public void OnToggled(MyView v, boolean touchOn) throws InterruptedException {

       // on teste si il y a une couleur séléctionnée
        if(colorAct != 0) {

            int[][] mat = adj(colorArrayInt, v.getIdX(), v.getIdY());
            myGridLayout.removeAllViews();
            for (int yPos = 0; yPos < 10; yPos++) {
                for (int xPos = 0; xPos < 16; xPos++) {
                    myViews[yPos * 16 + xPos].setInitialColor(mat[yPos][xPos]);
                    myGridLayout.addView(myViews[yPos * 16 + xPos]);
                }
            }

         // On teste si le level est réussi ou non

            if (Utils.reussiPuzzle(mat) == true) {
                new AlertDialog.Builder(GameGrid.this)
                        .setTitle("BRAVO ")
                        .setMessage("Felicication vous avez reussi")
                        .show();
            }
        }

    }





}

