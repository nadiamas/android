package com.example.nadia.game.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by nadia on 20/02/2018.
 */

@DatabaseTable
public class Joueur {

    @DatabaseField(columnName = "idJoueur", generatedId = true)
    private int idJoueur;
    @DatabaseField
    private String Pseudo ;
    @DatabaseField
    private String password ;
    @DatabaseField
    private int score;

    public Joueur() {
    }

    public Joueur(String pseudo, String password, int score) {
        Pseudo = pseudo;
        this.password = password;
        this.score = score;
    }

    public Joueur(String pseudo, String password) {
        Pseudo = pseudo;
        this.password = password;
    }

    public Joueur(int idJoueur, String pseudo, String password, int score) {
        this.idJoueur = idJoueur;
        Pseudo = pseudo;
        this.password = password;
        this.score = score;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getPseudo() {
        return Pseudo;
    }

    public void setPseudo(String pseudo) {
        Pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
