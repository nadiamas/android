package com.example.nadia.game;

import android.graphics.Color;

/**
 * Created by nadia on 09/03/2018.
 */

public class Utils {

    // Méthode pour savoir si le Level est réussi

    public static boolean reussiPuzzle(int[][] view){
        for(int i = 0; i < view.length; i++){
            for(int j = 1; j < view[i].length; j++){
                if(!(view[i][0]==view[i][j]) ){
                    return false;
                }
            }
        }
        return true;
    }



    // Méthode pour transformer tableau de couleur a une matrice

    public static char[][] transform(char[] arr, int N) {
        int M = (arr.length + N - 1) / N;
        char[][] mat = new char[M][];
        int start = 0;
        for (int r = 0; r < M; r++) {
            int L = Math.min(N, arr.length - start);
            mat[r] = java.util.Arrays.copyOfRange(arr, start, start + L);
            start += L;
        }
        return mat;
    }


    // Methode pour remplir Color Picker Selon le Stage
    // les couleurs vont changer en fonction du Stage


    public static int[] getColorsStage(String puzzleName){

        int marronColor = Color.rgb(90, 49, 49);
        int GreenColor = Color.rgb(32,178,170);
        int BeigeColor = Color.rgb(255,222,173);
        int OrangeColor = Color.rgb(218,165,32);
        int RoseColor = Color.rgb(228,27,120);
        int Gris = Color.rgb(11,91,94);

        int[] colors;

        if(puzzleName.contains("SAL")){
            colors = new int[5];
            colors[0] = marronColor;
            colors[1] = Color.RED;
            colors[2] = GreenColor;
        }

        else if(puzzleName.contains("SBL")) {
            colors = new int[4];
            colors[0] = marronColor;
            colors[1] = Color.RED;
            colors[2] = BeigeColor;
            colors[3] = OrangeColor;
        }

        else if(puzzleName.contains("SCL")) {
            colors = new int[4];
            colors[0] = GreenColor;
            colors[1] = RoseColor;
            colors[2] = Gris;
            colors[3] = OrangeColor;
        }
        else {
            colors = new int[5];
            colors[0] = marronColor;
            colors[1] = Color.RED;
            colors[2] = GreenColor;
            colors[3] = BeigeColor;
            colors[3] = Gris;
        }

        return  colors;
    }



 // Methode pour recuperer le nom d'un stage en fonction de puzzle

    public static String getStageName(String puzzleName){
        if(puzzleName.contains("SAL"))
            return "A";
        if(puzzleName.contains("SBL"))
            return "B";
        if(puzzleName.contains("SCL"))
            return "C";
        if(puzzleName.contains("SDL"))
            return "D";
        if(puzzleName.contains("SEL"))
            return "E";
        if(puzzleName.contains("S5C1L"))
            return "5C";
        if(puzzleName.contains("SConvL"))
            return "Conv";
        if(puzzleName.contains("SP1L"))
            return "SP1";

        return  null;
    }

}
