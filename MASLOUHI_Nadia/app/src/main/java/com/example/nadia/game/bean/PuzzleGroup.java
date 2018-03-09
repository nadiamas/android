package com.example.nadia.game.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nadia on 15/02/2018.
 */

public class PuzzleGroup  implements Serializable {

    private String name ;
    private String unlockType;
    private List<Stage> stages;

    public PuzzleGroup() {
    }

    public PuzzleGroup(String name, String unlockType, List<Stage> stages) {
        this.name = name;
        this.unlockType = unlockType;
        this.stages = stages;
    }

    public PuzzleGroup(String name, String unlockType) {
        this.name = name;
        this.unlockType = unlockType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnlockType() {
        return unlockType;
    }

    public void setUnlockType(String unlockType) {
        this.unlockType = unlockType;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    @Override
    public String toString() {
        return "PuzzleGroup{" +
                "name='" + name + '\'' +
                ", unlockType='" + unlockType + '\'' +
                ", stages=" + stages +
                '}';
    }
}
