package com.example.nadia.game.bean;

import java.io.Serializable;

/**
 * Created by nadia on 15/02/2018.
 */

public class Puzzle implements Serializable{

    private String name;
    private double width;
    private double height;
    private int numColours;
    private String colours;
    private int gold;
    private int silver ;
    private int bronze;
    Solution solution;
    private String stage;

    public Puzzle(String name) {
        this.name = name;
    }

    public Puzzle(String name, String colours) {
        this.name = name;
        this.colours = colours;
    }

    public Puzzle() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getNumColours() {
        return numColours;
    }

    public void setNumColours(int numColours) {
        this.numColours = numColours;
    }

    public String getColours() {
        return colours;
    }

    public void setColours(String colours) {
        this.colours = colours;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getBronze() {
        return bronze;
    }

    public void setBronze(int bronze) {
        this.bronze = bronze;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "name='" + name + '\'' +
                ", numColours=" + numColours +
                ", colours='" + colours + '\'' +
                '}';
    }
}
