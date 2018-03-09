package com.example.nadia.game.dataXML;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.nadia.game.bean.Puzzle;
import com.example.nadia.game.bean.PuzzleGroup;
import com.example.nadia.game.bean.Stage;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nadia on 28/02/2018.
 */

public class ParseXML {

    public XmlPullParser parseXML(Context context , String nameFile) throws XmlPullParserException, IOException {

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        InputStream is = context.getAssets().open(nameFile) ;
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(is,null);
        return  parser;
    }

    public PuzzleGroup parsingPuzzleGroupe (Context context , String nameFile) throws XmlPullParserException, IOException {

        XmlPullParser parser = parseXML(context,nameFile);
        int eventType = parser.getEventType();
        PuzzleGroup puzzleGroup = null ;
        List<Stage> stages = new ArrayList<>();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String eltName = null ;
            switch(eventType){
                case XmlPullParser.START_TAG:
                    eltName = parser.getName();
                    if ("PuzzleGroup".equals(eltName)) {
                        Log.i("attribute", parser.getAttributeValue(null,"name"));
                        Log.i("attribute", parser.getAttributeValue(null,"unlockType"));
                        puzzleGroup = new PuzzleGroup(parser.getAttributeValue(null,"name"),parser.getAttributeValue(null,"unlockType"));
                        //classic.setName(parser.nextText());
                        //classic.setName(parser.nextText());
                    }
                        else if (puzzleGroup != null){
                            if("Stage".equals(eltName)){
                                System.out.println(eltName);
                                Log.i("attribute", parser.getAttributeValue(null,"name"));
                                Stage stage = new Stage(parser.getAttributeValue(null,"name"));
                                stages.add(stage);
                            }

                        }
                        puzzleGroup.setStages(stages);
                        break;
                    }
                    eventType = parser.next();
            }
        System.out.println("puzzle groupe = " + puzzleGroup.toString());
        return puzzleGroup;
        }

    public Stage parsingStageData (Context context , String nameFile) throws XmlPullParserException, IOException {

        Log.e("filename","= "+nameFile);
        XmlPullParser parser = parseXML(context,nameFile);
        int eventType = parser.getEventType();
        Stage stage = null ;
        List<Puzzle> puzzles = new ArrayList<>();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String eltName = null ;
            switch(eventType){
                case XmlPullParser.START_TAG:
                    eltName = parser.getName();
                    if ("StageData".equals(eltName)) {
                        Log.i("attribute", parser.getAttributeValue(null,"name"));
                        Log.i("attribute", parser.getAttributeValue(null,"theme"));
                        Log.i("attribute", parser.getAttributeValue(null,"order"));

                        stage = new Stage(parser.getAttributeValue(null,"name"),parser.getAttributeValue(null,"theme"),parser.getAttributeValue(null,"order"));
                    }
                    else if (stage != null){
                        if("Puzzle".equals(eltName)){
                            Log.i("attribute", parser.getAttributeValue(null,"name"));
                            Puzzle puzzle = new Puzzle(parser.getAttributeValue(null,"name"));
                            puzzles.add(puzzle);
                        }

                    }
                    stage.setPuzzles(puzzles);
                    break;
            }
            eventType = parser.next();
        }
        System.out.println("stage = " + stage.toString());
        return stage;
    }

    public Puzzle parsingPuzzleData (Context context , String nameFile) throws XmlPullParserException, IOException {

        Log.e("filename","= "+nameFile);
        XmlPullParser parser = parseXML(context,nameFile);
        int eventType = parser.getEventType();
        Puzzle puzzle = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String eltName = null ;
            switch(eventType){
                case XmlPullParser.START_TAG:
                    eltName = parser.getName();
                    if ("PuzzleData".equals(eltName)) {
                        Log.i("attribute", parser.getAttributeValue(null,"name"));
                        Log.i("attribute", parser.getAttributeValue(null,"numColours"));
                        Log.i("attribute", parser.getAttributeValue(null,"colours"));

                        puzzle = new Puzzle(parser.getAttributeValue(null,"name"),parser.getAttributeValue(null,"colours"));
                    }
                    break;
            }
            eventType = parser.next();
        }
        System.out.println("puzzle = " + puzzle.toString());
        return puzzle;
    }

}
