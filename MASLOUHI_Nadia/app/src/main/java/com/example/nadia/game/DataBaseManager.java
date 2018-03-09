package com.example.nadia.game;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.nadia.game.bean.Joueur;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by nadia on 26/02/2018.
 */

public class DataBaseManager extends OrmLiteSqliteOpenHelper{

    private static final String DATABASE_NAME = "GAME.db";
    private static final int DATABASE_VERSION = 1 ;

    public DataBaseManager (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    // création des tables
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource, Joueur.class);
        }catch (Exception e){
            Log.e("DATA","Erreur de creation de la base");
        }
    }

    // Mise a jour de la base

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public void insertJoueur (Joueur joueur){
        try {
            Dao<Joueur, Integer> dao = getDao(Joueur.class);
            dao.create(joueur);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Récupération de la liste des joueurs

    public List<Joueur> getJoueurs(){
        try {
            Dao<Joueur, Integer> dao = getDao(Joueur.class);
            List<Joueur> joueurs = dao.queryForAll();
            return joueurs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
