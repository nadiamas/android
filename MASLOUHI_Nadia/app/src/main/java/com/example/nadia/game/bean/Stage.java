package com.example.nadia.game.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nadia on 15/02/2018.
 */

public class Stage implements Serializable{

    private String name ;
    private String theme;
    private String order;
    private List<Puzzle> puzzles;

    public Stage(String name) {
        this.name = name;
    }

    public Stage(String name, String theme, String order, List<Puzzle> puzzles) {
        this.name = name;
        this.theme = theme;
        this.order = order;
        this.puzzles = puzzles;

    }

    public Stage(String name, String theme, String order) {
        this.name = name;
        this.theme = theme;
        this.order = order;
    }

    public Stage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<Puzzle> getPuzzles() {
        return puzzles;
    }

    public void setPuzzles(List<Puzzle> puzzles) {
        this.puzzles = puzzles;
    }

    @Override
    public String toString() {
        return "Stage{" +
                "name='" + name + '\'' +
                ", theme='" + theme + '\'' +
                ", order='" + order + '\'' +
                ", puzzles=" + puzzles +
                '}';
    }
}
